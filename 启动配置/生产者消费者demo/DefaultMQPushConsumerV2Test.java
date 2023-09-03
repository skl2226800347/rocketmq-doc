package org.apache.rocketmq.client.consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class DefaultMQPushConsumerV2Test {
    public static void main(String[]args) throws Exception{
        DefaultMQPushConsumer defaultMQPushConsumer = init();

    }
    public static DefaultMQPushConsumer init()throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("defaultMQPushConsumerV2TestConsumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        MessageListenerConcurrentlyBusiness business = new MessageListenerConcurrentlyBusiness();
        consumer.subscribe("test","*");
        consumer.registerMessageListener(business);
        consumer.start();
        System.out.println("消费者启动成功");
        return consumer;
    }
    static class MessageListenerConcurrentlyBusiness implements MessageListenerConcurrently{
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            int i=1/0;
            MessageExt messageExt = msgs.get(0);
            System.out.println("messageExt"+messageExt);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }
}
