package com.manager.common.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.manager.common.jms.model.Demo;

@Component
public class Producer {
	private static Logger log = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${spring.snv.sync.topic:services.snv.sync.common}")
	private String destination;

	public void sendMessage(String queueName, Demo demo) {
		log.info("sending with convertAndSend() to topic <" + demo + ">");
		jmsTemplate.convertAndSend(queueName, demo);
	}
}