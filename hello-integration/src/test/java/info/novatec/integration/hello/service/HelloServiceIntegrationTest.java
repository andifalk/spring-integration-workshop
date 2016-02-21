package info.novatec.integration.hello.service;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import info.novatec.integration.hello.config.MessageFlowConfiguration;

import static org.junit.Assert.assertThat;

/**
 * Integration test to verify the hello world message flow for spring integration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HelloServiceIntegrationTestConfig.class, MessageFlowConfiguration.class })
public class HelloServiceIntegrationTest {

    @Value ("${greeting.message}")
    private String greetingForAllOthers;

    @Autowired
    private HelloServiceGateway cut;

    /**
     * Verify that given name is dispatched to greetings channel with expected greeting.
     * This is executed synchronously!
     */
    @Test
    public void verifySimpleHello()  {

        String greeting = cut.sayHelloToIntegration("Peter");

        assertThat("Should have received expected greeting", greeting,
                Matchers.is (String.format(greetingForAllOthers, "Peter")));
    }
}
