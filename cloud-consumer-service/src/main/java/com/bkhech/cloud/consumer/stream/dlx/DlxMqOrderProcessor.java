package com.bkhech.cloud.consumer.stream.dlx;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * stream rabbit dlx 死信队列交换器
 * @author guowm
 * @date 2021/3/29
 */
public interface DlxMqOrderProcessor {

    /**
     * Name of the output channel.
     */
    String ORDER_OUTPUT = "orderOutput";

    /**
     * @return output channel
     */
    @Output(ORDER_OUTPUT)
    MessageChannel orderOutput();

}
