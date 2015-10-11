package info.novatec.integration.hello.service;

public interface HelloService {
	/**
	 * Grettings from spring integration to user with given name.
	 * @param name name of user
	 * @return greetings
	 */
	String sayHelloToAllOthers(String name);

	/**
	 * Grettings from spring integration to user with given name.
	 * @param name name of user
	 * @return greetings
	 */
	String sayHelloToIntegrationForAndreas(String name);

	/**
	 * Grettings from spring integration to user with given name.
	 * @param name name of user
	 * @return greetings
	 */
	String sayHelloToIntegrationForHans(String name);

}
