package info.novatec.integration.hello.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import info.novatec.integration.hello.config.MessageFlowConfiguration;

/**
 * Integration test to verify the hello world message flow for spring integration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HelloServiceIntegrationTestConfig.class, MessageFlowConfiguration.class })
public class HelloServiceIntegrationTest extends AbstractHelloServiceIntegrationTest {
				
}
