package info.novatec.integration.hello.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name="helloServiceGateway")
public interface HelloServiceGateway {
		
	/**
	 * Greetings from spring integration to a user name.
	 * @param name user name
	 * @return greetings
	 */
	@Gateway(requestChannel="inputChannel", replyChannel="replyChannel")
	String sayHelloToIntegration(String name);

}
