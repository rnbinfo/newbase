package com.rnb.newbase.toolkit.util;

import org.junit.Test;

import javax.sound.midi.SysexMessage;

public class RandomUtilTest {
    @Test
    public void testRandomUtil() throws Exception {
        System.out.println("Generate Digital String, length 16 : " + RandomUtil.generateDigitalString(16));
        System.out.println("Generate Alphabet String, length 15 : " + RandomUtil.generateAlphabetString(15));
        System.out.println("Generate No Symbol String, length 18 : " + RandomUtil.generateNoSymbleString(18));
        System.out.println("Generate All String, length 20 : " + RandomUtil.generateAllString(20));
        System.out.println("Generate Fix Length String, num 25, length 10 : " + RandomUtil.toFixdLengthString(25, 10));
        System.out.println("Generate Fix Length Number, fix 2, num 15, length 10 : " + RandomUtil.toFixdLengthInteger("2", 15, 10));
    }
}
