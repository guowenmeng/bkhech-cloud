package com.bkhech.cloud.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootTest
class I18nTests {

    @Autowired
    private MessageSource messageSource;

    @Test
    void contextLoads() {
        String message = messageSource.getMessage("operation.success", null, Locale.CHINA);
        String enMessage = messageSource.getMessage("operation.success", null, Locale.US);
        System.out.println(message);
        System.out.println(enMessage);
    }

}
