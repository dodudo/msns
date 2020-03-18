package com.dxg.msns.common.util;

import java.util.Random;

public class NumberUtils {
    public static String generateCode(int len){
        len = Math.min(len,6);
        int min = Double.valueOf(Math.pow(10,len-1)).intValue();
        int num = new Random().nextInt(Double.valueOf(Math.pow(10,len+1)).intValue()-1)+min;
        return String.valueOf(num).substring(0,len);
    }
}
