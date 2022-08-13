package com.janitha.videoenhancer.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerApplication {

	@Bean
	@ServiceActivator(inputChannel = "pubsubOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
		return (MessageHandler) new PubSubMessageHandler(pubsubTemplate, "projects/smooth-elysium-359302/topics/heartBeatCloudlets");
	}

	@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
	public interface PubsubOutboundGateway {
		void sendToPubsub(String text);
	}
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
