package com.bkhech.cloud.provider.stream.dlx;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * stream rabbit dlx 死信队列交换器
 * @author guowm
 * @date 2021/3/29
 */
public interface DlxMqOrderSink {

    /**
     * Input channel name.
     */
    String DLX_ORDER_INPUT = "dlxOrderInput";

    /**
     * @return input channel.
     */
    @Input(DLX_ORDER_INPUT)
    SubscribableChannel dlxOrderInput();

}
