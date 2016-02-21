package info.novatec.integration.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.channel.MessageChannels;

@Configuration
@EnableIntegration
public class MessageFlowConfiguration {

	@Bean
	public IntegrationFlow integrationFlow() {
		return f -> f
			.channel(MessageChannels.direct("inputChannel"))
            .transform ("payload.toUpperCase()")
            .transform ("A very special greeting to " ::concat)
            .channel ( MessageChannels.direct ( "outputChannel" ) );
	}

}
