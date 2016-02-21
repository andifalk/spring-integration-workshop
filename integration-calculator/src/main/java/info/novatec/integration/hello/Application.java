package info.novatec.integration.hello;

import info.novatec.integration.hello.service.CalculatorGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import java.math.BigInteger;

@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger ( Application.class );

    @Autowired
    private CalculatorGateway calculatorGateway;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run ( String... strings ) throws Exception {
        BigInteger result = calculatorGateway.sum ( new BigInteger[] {BigInteger.valueOf (1L), BigInteger.valueOf (2L)} );

        LOGGER.info ( "Result: {}", result );
    }
}
