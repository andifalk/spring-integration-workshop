package info.novatec.integration.hello.service;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name="helloServiceGateway")
public interface HelloServiceGateway {
	
	/**
	 * Greetings from spring integration to a list of user names.
	 * @param names list containing user names
	 * @return greetings
	 */
	@Gateway(requestChannel="helloChainInput")
	Future<String> sayMultipleHelloToIntegration(List<String> names);
	
	/**
	 * Greetings from spring integration to a user name.
	 * @param name user name
	 * @return greetings
	 */
	@Gateway(requestChannel="channelForAllOtherNames", replyChannel="helloReply")
	String sayHelloToIntegration(String name);

}
