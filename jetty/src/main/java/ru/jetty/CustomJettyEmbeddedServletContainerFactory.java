package ru.jetty;

import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CustomJettyEmbeddedServletContainerFactory extends JettyEmbeddedServletContainerFactory {
    Logger log = LoggerFactory.getLogger(JettyConfiguration.class);

    @Autowired
    QueuedThreadPool threadPool;

    private ResourceLoader resourceLoader;

    @Override
    public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... initializers) {
        WebAppContext context = new WebAppContext(null,null, new ServletHandler(),null);
        int port = (getPort() >= 0 ? getPort() : 0);
        Server server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});
        configureContext(context, initializers);
        server.setHandler(context);
        log.info("Server initialized with port: " + port);
        addMBeanContainer();
        for (JettyServerCustomizer customizer : getServerCustomizers()) {
            customizer.customize(server);
        }
        return getJettyEmbeddedServletContainer(server);
    }

    private void configureContext(WebAppContext context,
                                                ServletContextInitializer... initializers) {
        context.setTempDirectory(getTempDirectory());
        if (this.resourceLoader != null) {
            context.setClassLoader(this.resourceLoader.getClassLoader());
        }
        String contextPath = getContextPath();
        context.setContextPath(StringUtils.hasLength(contextPath) ? contextPath : "/");
        context.setDisplayName(getDisplayName());
        ServletContextInitializer[] initializersToUse = mergeInitializers(initializers);
        Configuration[] configurations = getWebAppContextConfigurations(context,
                initializersToUse);
        context.setConfigurations(configurations);
    }

    private File getTempDirectory() {
        String temp = System.getProperty("java.io.tmpdir");
        return (temp == null ? null : new File(temp));
    }

    private void addMBeanContainer() {
        addServerCustomizers(server -> {
            MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
            server.addEventListener(mbContainer);
            server.addBean(mbContainer);
        });
    }

    @Override
    protected org.eclipse.jetty.webapp.Configuration[] getWebAppContextConfigurations(WebAppContext webAppContext, ServletContextInitializer... initializers) {
        List<Configuration> configurations = new ArrayList<>();
        configurations.add(
                getServletContextInitializerConfiguration(webAppContext, initializers));
        configurations.addAll(getConfigurations());
        configurations.add(getMimeTypeConfiguration());
        return configurations.toArray(new org.eclipse.jetty.webapp.Configuration[configurations.size()]);
    }

    private org.eclipse.jetty.webapp.Configuration getMimeTypeConfiguration() {
        return new AbstractConfiguration() {
            @Override
            public void configure(WebAppContext context) throws Exception {
                MimeTypes mimeTypes = context.getMimeTypes();
                for (MimeMappings.Mapping mapping : getMimeMappings()) {
                    mimeTypes.addMimeMapping(mapping.getExtension(),
                            mapping.getMimeType());
                }
            }
        };
    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
