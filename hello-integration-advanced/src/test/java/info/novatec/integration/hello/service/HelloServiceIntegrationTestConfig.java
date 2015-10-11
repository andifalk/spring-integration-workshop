package info.novatec.integration.hello.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@IntegrationComponentScan
//@ImportResource("classpath:spring-integration.xml")
public class HelloServiceIntegrationTestConfig {

}
