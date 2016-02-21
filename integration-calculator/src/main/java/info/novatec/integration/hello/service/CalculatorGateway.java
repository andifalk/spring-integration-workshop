package info.novatec.integration.hello.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;

import java.math.BigInteger;

@MessagingGateway(name="helloServiceGateway")
public interface CalculatorGateway {

	/**
	 * Calculate sum of specified values.
	 * @param values
	 * @return calculated sum
	 */
	@Gateway(requestChannel="inputChannel", replyChannel = "outputChannel", headers = { @GatewayHeader (name = "action", value = "SUM" ) })
    BigInteger sum(BigInteger[] values);

	/**
	 * Calculate sum of specified values.
	 * @param values
	 * @return calculated sum
	 */
	@Gateway(requestChannel="inputChannel", replyChannel = "outputChannel", headers = { @GatewayHeader (name = "action", value = "SUB" ) })
    BigInteger subtract(BigInteger[] values);

	/**
	 * Calculate multiple of specified values.
	 * @param values
	 * @return calculated multiple
	 */
	@Gateway(requestChannel="inputChannel", replyChannel = "outputChannel", headers = { @GatewayHeader (name = "action", value = "MULT" ) })
    BigInteger multiply(BigInteger[] values);

    /**
     * Calculate division of specified values.
     * @param values
     * @return calculated division
     */
    @Gateway(requestChannel="inputChannel", replyChannel = "outputChannel", headers = { @GatewayHeader (name = "action", value = "DIV" ) })
    BigInteger divide(BigInteger[] values);

}
