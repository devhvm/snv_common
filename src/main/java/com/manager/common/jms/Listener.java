package com.manager.common.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.manager.common.jms.model.Demo;

@Component
public class Listener {
	private static Logger log = LoggerFactory.getLogger(Listener.class);

	@JmsListener(destination = "${spring.snv.sync.topic:services.snv.sync.common}")
	// @SendTo("services.name.${spring.application.name}")
	public String receiveMessage(@Payload Demo demo
			, @Headers MessageHeaders headers
			, Message jsonMessage,
			Session session) throws JMSException {
		//String messageData = null;
		log.info("received <" + demo + ">");
		return demo.toString();
	}
}