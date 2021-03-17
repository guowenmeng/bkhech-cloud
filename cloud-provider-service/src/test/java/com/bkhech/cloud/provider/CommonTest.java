package com.bkhech.cloud.provider;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author guowm
 * @date 2021/3/17
 */
public class CommonTest {

    @Test
    public void random() {
        for (int i = 0; i < 10; i++) {
            ThreadLocalRandom current = ThreadLocalRandom.current();
            int rand = current.nextInt(3, 7);
            System.out.println(rand);
        }

    }

}
