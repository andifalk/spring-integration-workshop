package info.novatec.integration.hello.service;

import info.novatec.integration.hello.config.MessageFlowConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration test to verify the hello world message flow for spring integration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { HelloServiceIntegrationTestConfig.class, MessageFlowConfiguration.class })
public class HelloServiceIntegrationTest {
	
	@Autowired
	private CalculatorGateway cut;

	/**
	 * Verify that all given names are dispatched to greeting channel with expected greetings.
	 */
	@Test
	public void verifySumCalculation() throws InterruptedException {

        BigInteger result = cut.sum ( new BigInteger[] { BigInteger.valueOf (1L), BigInteger.valueOf (2L) } );

		assertThat("Should have received expected value", result,
				is(BigInteger.valueOf(3L)));
	}

	/**
	 * Verify that all given names are dispatched to greeting channel with expected greetings.
	 */
	@Test
	public void verifySubtractCalculation() throws InterruptedException {

		BigInteger result = cut.subtract ( new BigInteger[] { BigInteger.valueOf (1L), BigInteger.valueOf (2L) } );

		assertThat("Should have received expected value", result,
				is(BigInteger.valueOf(-1L)));

	}

	/**
	 * Verify that all given names are dispatched to greeting channel with expected greetings.
	 */
	@Test
	public void verifyMultiplyCalculation() throws InterruptedException {

		BigInteger result = cut.multiply ( new BigInteger[] { BigInteger.valueOf (2L), BigInteger.valueOf (2L) } );

		assertThat("Should have received expected value", result,
				is(BigInteger.valueOf(4L)));

	}

    /**
     * Verify that all given names are dispatched to greeting channel with expected greetings.
     */
    @Test
    public void verifyDivideCalculation() throws InterruptedException {

        BigInteger result = cut.divide ( new BigInteger[] { BigInteger.valueOf (4L), BigInteger.valueOf (2L) } );

        assertThat("Should have received expected value", result,
                is(BigInteger.valueOf(2L)));
    }

    /**
     * Verify that all given names are dispatched to greeting channel with expected greetings.
     */
    @Test(expected = ArithmeticException.class)
    public void verifyDivideCalculationError() throws InterruptedException {

       cut.divide ( new BigInteger[] { BigInteger.valueOf (4L), BigInteger.valueOf (0L) } );

    }
	
}
