package com.longwei.mall.common.util.business;

import java.util.UUID;

public class UUIDutil {
    public final static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }
}
