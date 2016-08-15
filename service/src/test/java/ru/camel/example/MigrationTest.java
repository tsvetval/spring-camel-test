package ru.camel.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by tsval on 15.08.2016.
 */

@SpringApplicationConfiguration(CamelProxyApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
@TestExecutionListeners(/*listeners = [ActiveMqListener.class, DependencyInjectionTestExecutionListener.class]*/)
@EnableAutoConfiguration(/*exclude = [MongoAutoConfiguration.class, MongoConfiguration.class]*/)
@ActiveProfiles("testProfile")
public class MigrationTest {


}
