package org.ahala.examples.theserver.util;

import java.util.Random;

public class RandomUtil {

    public static String generateString(int length) {
        return generateString(new Random(), length);
    }

    public static String generateString(Random random, int length) {
        return random.ints(0, 255)
                .filter(i -> ('0' <= i && i <= '9') || ('a' <= i && i <= 'z') || ('A' <= i && i <= 'Z'))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
