package com.laowei.queues;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息接收者
 */
public class JMSConsumer2 {

    private static final String username = ActiveMQConnection.DEFAULT_USER;
    private static final String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {

        ConnectionFactory factory = new ActiveMQConnectionFactory(username, password, url);
        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

//            Destination destination = session.createQueue("test1");
            Destination destination = session.createTopic("test1");
            MessageConsumer consumer = session.createConsumer(destination);
            while (true){
                TextMessage message = (TextMessage) consumer.receive();
                if (message!=null){
                    System.out.println("收到的信息为: "+ message.getText());
                }else {
                    break;
                }
            }

            session.close();
            connection.close();



        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
