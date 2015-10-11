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

	@Bean(name="channelForAllOtherNames")
	public DirectChannel inputChannel() {
		return new DirectChannel();
	}
	
	@Bean(name="helloReply")
	public DirectChannel replyChannel() {
		return new DirectChannel();
	}
	
	@Bean(name="helloDiscard")
	public DirectChannel discardChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public IntegrationFlow integrationFlow() {
		return IntegrationFlows
			.from(inputChannel())
			.handle("helloService", "sayHelloToAllOthers").channel(replyChannel()).get();
	}
	
}
