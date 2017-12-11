package com.laowei.queues2;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送者
 */
public class JMSProducter {

    private static final String UserName = ActiveMQConnection.DEFAULT_USER;
    private static final String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {

        // 创建连接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(UserName, password, url);

        try {
            // 创建连接
            Connection connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建 Session  true:开启事务
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 消息的目的地

            // 使用该方法创建队列 , 多个消息接收者一共获得一份数据
//            Destination destination = session.createQueue("test1");

            // 使用该方法创建, 每个消息接收者都会获得一份完整的数据
            Destination destination = session.createTopic("test1");

            // 消费生产者
            MessageProducer producer = session.createProducer(destination);

            producer.setTimeToLive(10 * 1000);

            MapMessage message1 = session.createMapMessage();
            message1.setString("name", "laowei");
            message1.setIntProperty("age", 19);

            MapMessage message2 = session.createMapMessage();
            message2.setString("name", "xiaowang");
            message2.setIntProperty("age", 10);

            producer.send(destination, message1, DeliveryMode.NON_PERSISTENT, 4,1000*60*10);
            producer.send(destination, message2, DeliveryMode.NON_PERSISTENT, 4,1000*60*10);

            session.commit();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
