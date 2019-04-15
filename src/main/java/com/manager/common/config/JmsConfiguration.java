package com.manager.common.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@EnableJms
@Configuration
@ImportAutoConfiguration(classes = { JmsAutoConfiguration.class, ActiveMQAutoConfiguration.class })
public class JmsConfiguration {
	@Value("${destinationName.publish.events:VirtualTopic.Events}")
	private String eventTopic;

	@Bean
	public ConnectionFactory connectionFactory(@Value("${spring.activemq.user}") String username,
			@Value("${spring.activemq.password}") String password,
			@Value("${spring.activemq.broker-url}") String brokerUrl) {

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
		// connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
		connectionFactory.setWatchTopicAdvisories(false);
		return connectionFactory;
	}

	// @Bean
	// public MarshallingMessageConverter createMarshallingMessageConverter(final
	// Jaxb2Marshaller jaxb2Marshaller) {
	// return new MarshallingMessageConverter(jaxb2Marshaller);
	// }
	//
	// @Bean
	// public Jaxb2Marshaller createJaxb2Marshaller(@Value("${jaxb.package}") final
	// String packageToScane) {
	// Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
	// jaxb2Marshaller.setPackagesToScan(packageToScane);
	// Map<String, Object> properties = new HashMap<>();
	// properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	// jaxb2Marshaller.setMarshallerProperties(properties);
	// return jaxb2Marshaller;
	// }
	//
	// /*
	// * By default jmsTemplate transmit to queues by having pubSubDomain set to
	// * false, as per requirement I need to transmit messages to topic. so I make
	// * pubSubDomain set to true, link https://spring.io/guides/gs/messaging-jms/
	// *
	// */
	// @Bean
	// public JmsTemplate jmsTemplate(final MarshallingMessageConverter
	// marshallingMessageConverter,
	// ConnectionFactory connectionFactory) {
	// JmsTemplate template = new JmsTemplate();
	// template.setConnectionFactory(connectionFactory);
	// template.setMessageConverter(marshallingMessageConverter);
	// factory.setConcurrency("3-10");
	// template.setPubSubDomain(false);
	// return template;
	// }
	//
	// @Bean
	// public JmsListenerContainerFactory<DefaultMessageListenerContainer>
	// jmsListenerContainerFactory(
	// final MarshallingMessageConverter marshallingMessageConverter,
	// ConnectionFactory connectionFactory) {
	//
	// DefaultJmsListenerContainerFactory factory = new
	// DefaultJmsListenerContainerFactory();
	// factory.setConnectionFactory(connectionFactory);
	// // factory.setErrorHandler(new ListenerErrorHandler());
	// factory.setMessageConverter(marshallingMessageConverter);
	// return factory;
	//
	// }
	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		converter.setObjectMapper(objectMapper());
		return converter;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}

	@Bean
	public JmsListenerContainerFactory topicContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrency("3-20");
		factory.setPubSubDomain(true);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory);
		return template;
	}

	@Bean
	public DefaultJmsListenerContainerFactory queueContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrency("3-20");
		// true: using jms topic, false: using jms queue
		factory.setPubSubDomain(true);

		return factory;
	}

	// @Bean
	// public RedeliveryPolicy redeliveryPolicy() {
	// RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
	// redeliveryPolicy.setMaximumRedeliveries(0);
	//
	// // Note: We must set the queue name here. This name will be part of the error
	// // message
	// // shown in ActiveMQ for a failed message. If we don't set the queue name, we
	// // cannot
	// // see which listener has failed.
	// redeliveryPolicy.setQueue("Consumer.checking_system.VirtualTopic.Events");
	// return redeliveryPolicy;
	// }
}
