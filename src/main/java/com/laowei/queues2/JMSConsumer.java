package com.laowei.queues2;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息接收者
 */


public class JMSConsumer {

    private static final String username = ActiveMQConnection.DEFAULT_USER;
    private static final String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {

        String condition = "age>=20";

        ConnectionFactory factory = new ActiveMQConnectionFactory(username, password, url);
        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Destination destination = session.createTopic("test1");
            MessageConsumer consumer = session.createConsumer(destination, condition);

                // 监听消息
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    if (message instanceof MapMessage) {
                        try {
                            System.out.println(((MapMessage) message).getString("name"));
                            message.acknowledge();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            } catch(JMSException e){
                e.printStackTrace();
            }


    }
}
