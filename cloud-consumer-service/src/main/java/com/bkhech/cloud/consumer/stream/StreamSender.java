package com.bkhech.cloud.consumer.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * sc stream sender
 * @author guowm
 * @date 2021/3/25
 */
@Slf4j
@EnableBinding(Source.class)
public class StreamSender {

    /**
     * Source 和 MessageChannel 都可作为发送者示例
     */
    private final Source source;

//    private MessageChannel messageChannel;

    public StreamSender(Source source) {
        this.source = source;
    }

    public boolean send(String message, String routingKey) {
        Message<String> payload = MessageBuilder.withPayload(message).setHeader("messageType", routingKey).build();
        boolean send = source.output().send(payload);
//        boolean send = messageChannel.send(payload);
        return send;
    }
}
