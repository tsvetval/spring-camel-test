package ru.jetty;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(value = {org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration.class})
public class JettyConfiguration{

    @Value("${jetty.thread.min:10}")
    private Integer threadMin;
    @Value("${jetty.thread.max:200}")
    private Integer threadMax;
    @Value("${jetty.thread.idleTimeout:60000}")
    private Integer threadIdleTimeout;


    @Bean
    public QueuedThreadPool queuedThreadPool() {
        return new QueuedThreadPool(threadMax, threadMin,threadIdleTimeout);
    }

    @Bean
    public JettyMetrics jettyMetrics(){
        return new JettyMetrics();
    }

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
        return new CustomJettyEmbeddedServletContainerFactory();
    }

}
