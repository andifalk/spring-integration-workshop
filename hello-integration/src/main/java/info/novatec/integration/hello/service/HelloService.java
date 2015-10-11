package info.novatec.integration.hello.service;

public interface HelloService {
	/**
	 * Grettings from spring integration to user with given name.
	 * @param name name of user
	 * @return greetings
	 */
	String sayHelloToIntegration(String name);

}
