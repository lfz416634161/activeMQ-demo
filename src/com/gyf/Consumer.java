package com.gyf;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ProjectName: activeMQ-demo
 * @Package: com.gyf
 * @ClassName: Producer
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2018/12/14 11:33
 * @Version: 1.0
 */
public class Consumer {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
    public static void main(String[] args) {
        //1.连接MQ
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接
        Session session=null;//会话
        Destination destination=null;//消息目的地
        MessageConsumer messageConsumer=null;//消息消费者
        try{
            System.out.println("USERNAME:"+USERNAME);
            System.out.println(PASSWORD);
            System.out.println(BROKEURL);
            connectionFactory =new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
            connection=connectionFactory.createConnection();//创建连接
            connection.start();
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);//创建连接会话;
            //短信发送的名称要和生产者对应
            destination=session.createQueue("短信发送");
            messageConsumer=session.createConsumer(destination);
            //2.发送消息
            for(int i=0;i<5;i++){
               TextMessage textMessage=(TextMessage) messageConsumer.receive();
                System.out.println(textMessage.getText());
            }
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //3.断开
            try{
                messageConsumer.close();
                session.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        }
    }

