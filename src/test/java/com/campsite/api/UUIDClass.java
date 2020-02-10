package com.campsite.api;

import java.util.UUID;

public class UUIDClass {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
