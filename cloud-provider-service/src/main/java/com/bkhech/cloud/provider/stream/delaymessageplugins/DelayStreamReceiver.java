package com.bkhech.cloud.provider.stream.delaymessageplugins;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * sc stream 接收者
 * @author guowm
 * @date 2021/3/25
 */
@Slf4j
@EnableBinding(DelayMqOrderSink.class)
public class DelayStreamReceiver {

    /**
     * 监听 binding 为 DelayMqOrderSink.ORDER_DELAY_INPUT 的消息
     * @param value
     */
    @StreamListener(target = DelayMqOrderSink.ORDER_DELAY_INPUT)
    public void handle(String value) {
        log.info("[DelayStreamReceiver 消息] 接收到MQ消息: {}", value);
    }


}
