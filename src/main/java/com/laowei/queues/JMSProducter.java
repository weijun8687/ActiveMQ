package com.laowei.queues;

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

            // 是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 消息的目的地

            // 点对点  多个消息接收者一共获得一份数据
//            Destination destination = session.createQueue("test1");

            // 订阅模式, 每个消息接收者都会获得一份完整的数据
            Destination destination = session.createTopic("test1");

            // 消费生产者
            MessageProducer producer = session.createProducer(destination);

            for (int i = 0; i < 5; i++) {
                // 创建文本消息
                TextMessage message = session.createTextMessage("message" + i);
                producer.send(message);
            }

//            session.commit();
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
