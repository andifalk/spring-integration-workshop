package info.novatec.integration.hello.service;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Integration test to verify the hello world message flow for spring integration.
 */
public class AbstractHelloServiceIntegrationTest {
	
	@Value("${greeting.message}")
	private String greetingForAllOthers;
	
	@Autowired
	private HelloServiceGateway cut; 
		
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
	
}
