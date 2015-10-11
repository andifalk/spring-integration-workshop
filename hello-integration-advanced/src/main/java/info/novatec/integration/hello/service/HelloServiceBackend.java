package info.novatec.integration.hello.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Back-end implementation for {@link HelloService}.
 */
@Service("helloService")
public class HelloServiceBackend implements HelloService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceBackend.class);
	
	@Value("${greeting.andreas}")
	private String greetingForAndreas;

	@Value("${greeting.hans}")
	private String greetingForHans;

	@Value("${greeting.others}")
	private String greetingForAllOthers;

	@Override
	public String sayHelloToAllOthers(String name) {
		LOGGER.info("Called sayHelloToAllOthers({})", name);
		
		return String.format(greetingForAllOthers, name);
	}

	@Override
	public String sayHelloToIntegrationForAndreas(String name) {
		LOGGER.info("Called sayHelloToIntegrationForAndreas({})", name);
		
		return String.format(greetingForAndreas, name);
	}

	@Override
	public String sayHelloToIntegrationForHans(String name) {
		LOGGER.info("Called sayHelloToIntegrationForHans({})", name);
		
		return String.format(greetingForHans, name);
	}

}
