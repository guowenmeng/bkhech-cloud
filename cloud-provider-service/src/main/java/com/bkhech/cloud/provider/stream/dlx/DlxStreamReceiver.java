package com.bkhech.cloud.provider.stream.dlx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * sc stream 接收者
 * @author guowm
 * @date 2021/3/25
 */
@Slf4j
@EnableBinding(DlxMqOrderProcessor.class)
public class DlxStreamReceiver {

    /**
     * 监听 binding 为 DlxMqOrderProcessor.DLX_ORDER_INPUT 的消息
     * @param value
     */
    @StreamListener(target = DlxMqOrderProcessor.DLX_ORDER_INPUT)
    public void handle(String value) {
        log.info("[DlxMqOrderProcessor 消息] 接收到MQ消息: {}", value);
    }


}
