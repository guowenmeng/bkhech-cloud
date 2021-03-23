package com.bkhech.cloud.user.api;

import com.bkhech.cloud.commons.APIResponse;
import com.bkhech.cloud.commons.SystemException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 实现 feign 的异常处理类
 * @author guowm
 * @date 2021/3/17
 */
@Slf4j
public class UserFeignExceptionConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
    /**
     * 重新实现feign的异常处理，捕捉restful接口返回的json格式的异常信息
     *
     */
    public class FeignErrorDecoder implements ErrorDecoder  {

        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = null;
            ObjectMapper mapper = new ObjectMapper();
            //空属性处理
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //禁止使用int代表enum的order来反序列化enum
            mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
            try {
                String json = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                exception = new SystemException(json);
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                APIResponse result = mapper.readValue(json, APIResponse.class);
                // 业务异常包装成自定义异常类SystemException
                if (result.getCode() != HttpStatus.OK.value()) {
                    exception = new SystemException(result.getCode(), result.getMessage());
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
            return exception;
        }

    }
}
