package info.novatec.integration.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.channel.MessageChannels;

@Configuration
@EnableIntegration
public class MessageFlowConfiguration {

    @Bean
    public DirectChannel outputChannel() {
        return MessageChannels.direct ().get ();
    }

	@Bean
	public IntegrationFlow integrationFlow() {
		return f -> f
			.channel(MessageChannels.direct("inputChannel"))
            .route ( "headers['action']",
                    m -> m
                            .subFlowMapping ( "SUM", sumFlow () )
                            .subFlowMapping ( "SUB", subtractFlow () )
                            .subFlowMapping ( "MULT", multiplyFlow () )
                            .subFlowMapping ( "DIV", divideFlow () ) );
	}

    @Bean
    public IntegrationFlow sumFlow() {
        return f -> f
                .transform ( "payload[0].add(payload[1])" )
                .channel ( outputChannel () );
    }

    @Bean
    public IntegrationFlow subtractFlow() {
        return f -> f
                .transform ( "payload[0].subtract(payload[1])" )
                .channel ( outputChannel () );
    }

    @Bean
    public IntegrationFlow multiplyFlow() {
        return f -> f
                .transform ( "payload[0].multiply(payload[1])" )
                .channel ( outputChannel () );
    }

    @Bean
    public IntegrationFlow divideFlow() {
        return f -> f
                .transform ( "payload[0].divide(payload[1])" )
                .channel ( outputChannel () );
    }

}
