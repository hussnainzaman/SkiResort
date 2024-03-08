package org.example;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

public class JettyServer {

    public static void main(String[] args) throws Exception {
        // Define thread pool settings
        int maxThreads = 100;
        int minThreads = 10;
        int idleTimeout = 120;

        // Define server port
        int port = 8080;

        // Create a QueuedThreadPool with defined settings
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        // Create a Jetty server instance with the thread pool
        Server server = new Server(threadPool);

        // Create a server connector and set its port
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        // Define context path for the web application
        String contextPath = "/dss";

        // Find the base resource for the web application
        URI webResourceBase = findWebResourceBase(server.getClass().getClassLoader());
        System.err.println("Using BaseResource: " + webResourceBase);

        // Create a WebAppContext and configure it
        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newResource(webResourceBase));
        context.setContextPath(contextPath);
        context.setParentLoaderPriority(true);

        // Set the context as the handler for the server
        server.setHandler(context);

        // Start the server and wait for it to join
        server.start();
        server.join();
    }

    // Method to find the base resource for the web application
    private static URI findWebResourceBase(ClassLoader classLoader) {
        String webResourceRef = "WEB-INF/web.xml";

        try {
            // Look for resource in classpath
            URL webXml = classLoader.getResource('/' + webResourceRef);
            if (webXml != null) {
                URI uri = webXml.toURI().resolve("..").normalize();
                System.err.printf("WebResourceBase (Using ClassLoader reference) %s%n", uri);
                return uri;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Bad ClassPath reference for: " + webResourceRef, e);
        }

        // Look for resource in common file system paths
        try {
            Path pwd = new File(System.getProperty("user.dir")).toPath().toRealPath();
            FileSystem fs = pwd.getFileSystem();

            // Try the generated maven path first
            PathMatcher matcher = fs.getPathMatcher("glob:**/*");
            try (DirectoryStream<Path> dir = Files.newDirectoryStream(pwd.resolve("target"))) {
                for (Path path : dir) {
                    if (Files.isDirectory(path) && matcher.matches(path)) {
                        Path possible = path.resolve(webResourceRef);
                        if (Files.exists(possible)) {
                            URI uri = path.toUri();
                            System.err.printf("WebResourceBase (Using discovered /target/ Path) %s%n", uri);
                            return uri;
                        }
                    }
                }
            }

            // Try the source path next
            Path srcWebapp = pwd.resolve("src/main/webapp/" + webResourceRef);
            if (Files.exists(srcWebapp)) {
                URI uri = srcWebapp.getParent().toUri();
                System.err.printf("WebResourceBase (Using /src/main/webapp/ Path) %s%n", uri);
                return uri;
            }
        } catch (Throwable t) {
            throw new RuntimeException("Unable to find web resource in file system: " + webResourceRef, t);
        }

        throw new RuntimeException("Unable to find web resource ref: " + webResourceRef);
    }
}
