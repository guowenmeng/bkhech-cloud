package com.bkhech.cloud.consumer.stream;

import com.bkhech.cloud.consumer.stream.delaymessageplugins.DelayMqOrderSource;
import com.bkhech.cloud.consumer.stream.dlx.DlxMqOrderSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
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
@EnableBinding({Source.class, DlxMqOrderSource.class, DelayMqOrderSource.class})
public class StreamSender {

    /**
     * Source 和 MessageChannel 都可作为发送者示例
     */
    private final Source source;

    private final DlxMqOrderSource dlxMqOrderSource;

    private final DelayMqOrderSource delayMqOrderSource;

    public StreamSender(Source source, DlxMqOrderSource dlxMqOrderSource, DelayMqOrderSource delayMqOrderSource) {
        this.source = source;
        this.dlxMqOrderSource = dlxMqOrderSource;
        this.delayMqOrderSource = delayMqOrderSource;
    }

    public boolean send(String message, String routingKey) {
        Message<String> payload = MessageBuilder.withPayload(message).setHeader("messageType", routingKey).build();
        boolean send = source.output().send(payload);
        return send;
    }

    public boolean sendDlx(String message) {
        Message<String> payload = MessageBuilder.withPayload(message).build();
        boolean send = dlxMqOrderSource.orderOutput().send(payload);
        return send;
    }

    public boolean sendDelay(String message, String routingKey, Integer delayMills) {
        Message<String> payload = MessageBuilder.withPayload(message)
                .setHeader("messageType", routingKey)
                .setHeader(MessageProperties.X_DELAY, delayMills)
                .build();
        boolean send = delayMqOrderSource.orderDelayOutput().send(payload);
        return send;
    }
}
