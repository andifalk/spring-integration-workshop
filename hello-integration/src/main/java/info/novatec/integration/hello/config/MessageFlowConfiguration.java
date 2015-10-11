package info.novatec.integration.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
@EnableIntegration
public class MessageFlowConfiguration {

	@Bean(name="inputChannel")
	public DirectChannel inputChannel() {
		return new DirectChannel();
	}
	
	@Bean(name="replyChannel")
	public DirectChannel replyChannel() {
		return new DirectChannel();
	}
		
	@Bean
	public IntegrationFlow integrationFlow() {
		
		return IntegrationFlows
			.from(inputChannel())
			.handle("helloService", "sayHelloToIntegration")
			.channel(replyChannel())
			.get();
		
	}
	
}
