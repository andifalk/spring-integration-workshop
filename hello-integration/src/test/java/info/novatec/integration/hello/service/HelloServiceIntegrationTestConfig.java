package info.novatec.integration.hello.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@IntegrationComponentScan
public class HelloServiceIntegrationTestConfig {

}
