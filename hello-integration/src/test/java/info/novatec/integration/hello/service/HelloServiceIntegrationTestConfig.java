package info.novatec.integration.hello.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@ComponentScan (basePackages = "info.novatec.integration.hello")
@IntegrationComponentScan (basePackages = "info.novatec.integration.hello")
public class HelloServiceIntegrationTestConfig {

}
