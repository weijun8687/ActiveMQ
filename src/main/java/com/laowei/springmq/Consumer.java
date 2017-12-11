package com.laowei.springmq;

import org.apache.activemq.Message;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer implements SessionAwareMessageListener<Message> {


    public void onMessage(Message message, Session session) throws JMSException {
        if (message instanceof TextMessage){
            System.out.println("*******" +((TextMessage) message).getText());
        }
    }
}
