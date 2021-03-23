package com.bkhech.cloud.zuul.server;

import org.junit.jupiter.api.Test;

public class CommontTest {

    @Test
    public void test() {
        String loginUri = "/user/user/login";
        String[] split = loginUri.split("/", 3);
        System.out.println(split);
    }

}
