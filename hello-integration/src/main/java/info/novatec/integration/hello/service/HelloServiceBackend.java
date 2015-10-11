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
	
	@Value("${greeting.message}")
	private String greetingMessage;

	@Override
	public String sayHelloToIntegration(String name) {
		LOGGER.info("Called sayHelloToAllOthers({})", name);
		
		return String.format(greetingMessage, name);
	}

}
