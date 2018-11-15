package rocketmq.le.demo;

import java.io.UnsupportedEncodingException;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author Isen
 * @date 2018/11/11 10:23
 * @since 1.0
 */
public class FilterProducer {

    public static void main(String[] args)
            throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        Message msg = new Message("TopicTest",
                "TOPA",
                ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        // Set some properties.
        msg.putUserProperty("a", String.valueOf(10));

        SendResult sendResult = producer.send(msg);

        producer.shutdown();
    }
}
