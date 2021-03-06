package com.bkhech.cloud.consumer.stream;

import com.bkhech.cloud.commons.APIResponse;
import com.bkhech.cloud.commons.APIResponseUtil;
import com.bkhech.cloud.provider.api.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * sc stream 发送消息测试
 * @author guowm
 * @date 2021/3/25
 */
@RestController
public class StreamTestController {

    private final StreamSender streamSender;

    public StreamTestController(StreamSender streamSender) {
        this.streamSender = streamSender;
    }

    @GetMapping("stream/send")
    public APIResponse<Boolean> send(@RequestParam String message, @RequestParam String messageType) {
        Boolean result = streamSender.send(message, messageType);
        return APIResponseUtil.success(result);
    }

    @GetMapping("stream/send/dlx")
    public APIResponse<Boolean> sendDlx(@RequestParam String message) {
        Boolean result = streamSender.sendDlx(message);
        return APIResponseUtil.success(result);
    }

    @GetMapping("stream/send/delay")
    public APIResponse<Boolean> sendDelay(@RequestParam String message,
                                          @RequestParam String messageType,
                                          @RequestParam Integer delayMills) {
        Boolean result = streamSender.sendDelay(message, messageType, delayMills);
        return APIResponseUtil.success(result);
    }
}
