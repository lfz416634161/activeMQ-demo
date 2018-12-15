package com.gyf;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ProjectName: activeMQ-demo
 * @Package: com.gyf
 * @ClassName: MyMessageListener
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2018/12/15 13:57
 * @Version: 1.0
 */
//自己写一个监听 ,这种监听获取过来不会被消费,要告诉active自己收到了才会被消耗掉
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage=(TextMessage)message;
            System.out.println("从MQ获取的消息:"+textMessage.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
