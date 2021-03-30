package com.bkhech.cloud.consumer.stream.delaymessageplugins;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * stream rabbit 延时队列交换器
 * @author guowm
 * @date 2021/3/29
 */
public interface DelayMqOrderSource {

    /**
     * Name of the output channel.
     */
    String ORDER_DELAY_OUTPUT = "orderDelayOutput";

    /**
     * @return output channel
     */
    @Output(ORDER_DELAY_OUTPUT)
    MessageChannel orderDelayOutput();

}
