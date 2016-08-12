package ru.camel.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.camel.example.route.ApplicationCamelRouter;

/**
 *
 */
@Configuration
public class CamelConfiguration {
    @Value("${camel.servlet-path}")
    private String CAMEL_URL_MAPPING;
    @Value("${camel.servlet-name:CamelServlet}")
    private String CAMEL_SERVLET_NAME;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }

    @Bean
    public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
        SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
        // enable Jackson json type converter
        camelContext.getProperties().put("CamelJacksonEnableTypeConverter", "true");
        // allow Jackson json to convert to pojo types also (by default jackson only converts to String and other simple types)
        camelContext.getProperties().put("CamelJacksonTypeConverterToPojo", "true");

        camelConverterConfiguration(camelContext);
        //activeMqFactoryConfiguration(camelContext);
        camelContext.addRoutes(routeBuilder());

        return camelContext;
    }

    private void camelConverterConfiguration(SpringCamelContext camelContext) {
        //camelContext.getTypeConverterRegistry().addTypeConverter(TEST1.class, TEST2.class, new TEST1_TEST2());
    }


    @Bean
    public RouteBuilder routeBuilder() {
        return new ApplicationCamelRouter();
    }


}
