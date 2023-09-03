package org.apache.rocketmq.client.producer;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.common.message.Message;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultMQProducerV2Test {

    public static void main(String[]args) throws Exception{
        DefaultMQProducer producer = init();
        Message message = new Message();
        message.setTopic("test");
        message.setTopic("skl_cluser_topic");
        message.setBody("hello world".getBytes());
        int count=2;
        for (int i=1;i<count;i++){
            SendResult sendResult = producer.send(message);
            System.out.println(JSONObject.toJSONString(sendResult));
        }
    }
    public static DefaultMQProducer init()throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("defaultMQProducerV2TestProduct");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setSendMsgTimeout(1000000000);
        producer.start();
        return producer;
    }
}
