package info.novatec.integration.hello.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.util.concurrent.ListenableFuture;

@MessagingGateway(name="helloServiceGateway")
public interface HelloServiceGateway {

	/**
	 * Greetings from spring integration to a user name.
	 * @param name user name
	 * @return greeting
	 */
	@Gateway(requestChannel="inputChannel", replyTimeout = 1000)
	String sayHelloToIntegration( String name);

}
