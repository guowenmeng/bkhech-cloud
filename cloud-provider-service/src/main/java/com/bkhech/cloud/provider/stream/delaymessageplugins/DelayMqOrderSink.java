package com.bkhech.cloud.provider.stream.delaymessageplugins;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * stream rabbit 延时队列交换器
 * @author guowm
 * @date 2021/3/29
 */
public interface DelayMqOrderSink {

    /**
     * Input channel name.
     */
    String ORDER_DELAY_INPUT = "orderDelayInput";

    /**
     * @return input channel.
     */
    @Input(ORDER_DELAY_INPUT)
    SubscribableChannel orderDelayInput();

}
