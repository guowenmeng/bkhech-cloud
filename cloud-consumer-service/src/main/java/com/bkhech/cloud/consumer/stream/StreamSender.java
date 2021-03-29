package com.bkhech.cloud.consumer.stream;

import com.bkhech.cloud.consumer.stream.dlx.DlxMqOrderProcessor;
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
@EnableBinding({Source.class, DlxMqOrderProcessor.class})
public class StreamSender {

    /**
     * Source 和 MessageChannel 都可作为发送者示例
     */
    private final Source source;

    private final DlxMqOrderProcessor dlxMqOrderProcessor;

    public StreamSender(Source source, DlxMqOrderProcessor dlxMqOrderProcessor) {
        this.source = source;
        this.dlxMqOrderProcessor = dlxMqOrderProcessor;
    }

    public boolean send(String message, String routingKey) {
        Message<String> payload = MessageBuilder.withPayload(message).setHeader("messageType", routingKey).build();
        boolean send = source.output().send(payload);
        return send;
    }

    public boolean sendDlx(String message) {
        Message<String> payload = MessageBuilder.withPayload(message).build();
        boolean send = dlxMqOrderProcessor.orderOutput().send(payload);
        return send;
    }
}
