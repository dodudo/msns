package com.dxg.msns.common.util;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
       return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
