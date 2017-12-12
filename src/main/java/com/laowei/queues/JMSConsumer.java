package com.laowei.queues;

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

        ConnectionFactory factory = new ActiveMQConnectionFactory(username, password, url);
        try {
            Connection connection = factory.createConnection();

            // 持久订阅模式时 设置ClientID (相当于身份标识)
            connection.setClientID("aaa");

            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 点对点模式
//            Destination destination = session.createQueue("test1");

            //订阅模式
//            Destination destination = session.createTopic("test1");
//            MessageConsumer consumer = session.createConsumer(destination);

            // 持久订阅模式
            Topic topic = session.createTopic("test1");
            // 创建有身份标识的消费者
            MessageConsumer consumer = session.createDurableSubscriber(topic, "aaa");
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
