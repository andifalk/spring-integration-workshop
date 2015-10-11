package info.novatec.integration.hello.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import info.novatec.integration.hello.config.MessageFlowConfiguration;
import info.novatec.integration.hello.service.HelloServiceGateway;

/**
 * Integration test to verify the hello world message flow for spring integration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HelloServiceIntegrationTestConfig.class, MessageFlowConfiguration.class })
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class HelloServiceIntegrationTest {
	
	@Value("${greeting.andreas}")
	private String greetingForAndreas;

	@Value("${greeting.hans}")
	private String greetingForHans;

	@Value("${greeting.others}")
	private String greetingForAllOthers;
	
	@Autowired
	private HelloServiceGateway cut; 
	
	@Qualifier("helloReply")
	@Autowired
	private DirectChannel replyChannel;

	@Qualifier("helloDiscard")
	@Autowired
	private DirectChannel discardChannel;
	
	/**
	 * Verify that given name is dispatched to greetings channel with expected greeting.
	 * This is executed synchronously!
	 * 
	 * @throws InterruptedException not expected
	 */
	@Test
	public void verifySimpleHello() throws InterruptedException {

		String greeting = cut.sayHelloToIntegration("Peter");
		
		assertThat("Should have received expected greeting", greeting, 
				Matchers.is (String.format(greetingForAllOthers, "Peter")));		
	}

	/**
	 * Verify that all given names are dispatched to greeting channel with expected greetings.
	 * This is executed asynchronously!
	 * 
	 * @throws InterruptedException not expected
	 */
	@Test
	public void verifyMultipleHello() throws InterruptedException {

		final List<String> input = Arrays.asList("Andreas", "Hans", "Oliver");
		final CountDownLatch latch = new CountDownLatch(input.size());
		
		TestMessageHandler messageHandler = new TestMessageHandler(latch);
		replyChannel.subscribe(messageHandler);
		
		cut.sayMultipleHelloToIntegration(input);

		latch.await(2, TimeUnit.SECONDS);
		
		assertThat("Should have received expected greetings", messageHandler.getReceivedGreetings(), 
				Matchers.hasItems(
						String.format(greetingForAndreas, input.get(0)),
						String.format(greetingForHans, input.get(1)),
						String.format(greetingForAllOthers, input.get(2))));
		
	}

	/**
	 * Verify that names that should be filtered or are specified duplicate are moved to discard channel
	 * and only expected names are dispatched to greeting channel.
	 * This is executed asynchronously!
	 * 
	 * @throws InterruptedException not expected
	 */
	@Test
	public void verifyMultipleHelloWithFilteredNames() throws InterruptedException {
		final List<String> input = Arrays.asList("Andreas", "Andreas", "Otto", "Hans", "Gustav");
		
		final CountDownLatch messageLatch = new CountDownLatch(2);
		final CountDownLatch discardLatch = new CountDownLatch(3);
		
		TestMessageHandler messageHandler = new TestMessageHandler(messageLatch);
		replyChannel.subscribe(messageHandler);

		TestMessageHandler discardMessageHandler = new TestMessageHandler(discardLatch);
		discardChannel.subscribe(discardMessageHandler);

		cut.sayMultipleHelloToIntegration(input);
		
		messageLatch.await(2, TimeUnit.SECONDS);
		discardLatch.await(2, TimeUnit.SECONDS);
		
		assertThat("Should have received expected greetings", messageHandler.getReceivedGreetings(), 
				Matchers.hasItems(String.format(greetingForAndreas, input.get(0)),
				String.format(greetingForHans, input.get(3))));
		assertThat("Should have received expected discarded names", discardMessageHandler.getReceivedGreetings(), 
				Matchers.hasItems("Andreas", "Otto", "Gustav"));
	}
	
	/**
	 * Message handler to asynchronously get the messages from publish-subscribe channel
	 */
	static class TestMessageHandler implements MessageHandler {
		
		private List<String> receivedGreetings;
		private CountDownLatch countDownLatch;
		
		public TestMessageHandler(CountDownLatch latch) {
			super();
			this.countDownLatch = latch;
			this.receivedGreetings = new ArrayList<>();
		}

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			assertThat("Should have retrieved the reply message with payload of expected type", 
					message.getPayload(), is(Matchers.instanceOf(String.class)));
			receivedGreetings.add((String)message.getPayload());
			countDownLatch.countDown();
		}
		
		public List<String> getReceivedGreetings() {
			return receivedGreetings;
		}
		
	}
	
}
