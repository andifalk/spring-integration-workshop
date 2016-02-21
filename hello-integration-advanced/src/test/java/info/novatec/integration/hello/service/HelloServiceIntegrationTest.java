package info.novatec.integration.hello.service;

import info.novatec.integration.hello.config.MessageFlowConfiguration;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration test to verify the hello world message flow for spring integration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HelloServiceIntegrationTestConfig.class, MessageFlowConfiguration.class })
public class HelloServiceIntegrationTest {
	
	@Autowired
	private HelloServiceGateway cut;

    @Autowired
    @Qualifier ("outputChannel")
    private DirectChannel outputChannel;

	/**
	 * Verify that all given names are dispatched to greeting channel with expected greetings.
	 */
	@Test
	public void verifyNonFilteredHello() throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch ( 1 );
        final String[] result = new String[1];

        outputChannel.subscribe ( message -> {
            result[0] = (String) message.getPayload ();
            countDownLatch.countDown ();
        } );

        cut.sayHelloToIntegration ( "Andreas" );

        countDownLatch.await ();

		assertThat("Should have received expected greeting", result[0],
				is("A very special greeting to ANDREAS"));

	}
	
}
