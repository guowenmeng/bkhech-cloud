package com.bkhech.cloud.provider.stream;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

/**
 * sc stream 接收者
 * @author guowm
 * @date 2021/3/25
 */
@Slf4j
@EnableBinding(Sink.class)
public class StreamReceiver {

    /**
     * 监听 binding 为 Sink.INPUT 的消息
     * @param value
     */
    @StreamListener(target = Sink.INPUT) //监听input这个消息队列, 必须定义相应的Input。
    public void handle(Message value, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("[StreamReceiver 消息] 接收到MQ消息: {}", value.getPayload());
        try {
            channel.basicAck(deliveryTag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听 binding 为 Sink.INPUT 的消息
     * @param value
     */
//    @StreamListener(target = Sink.INPUT) //监听input这个消息队列, 必须定义相应的Input。
//    public void handle(String value) {
//        log.info("[StreamReceiver 消息] 接收到MQ消息: {}", value);
//        throw new RuntimeException("Intentional exception");
//    }

    // 若方法签名有返回值，必须指定@SendTo注解
//    @StreamListener(target = Sink.INPUT)
//    @SendTo("responseMessage") //该注解会在消息处理完成后，向responseMessage这个队列发送消息。消息内容就是该方法的返回值。
//    public String handleHavingReturnMessage(String value) {
//        log.info("[StreamReceiver 消息] 接收到MQ消息: {}", value);
//        return "处理消息完成"; //当消息处理完成之后，会将该返回值发送到@SendTo指定
//    }

}
