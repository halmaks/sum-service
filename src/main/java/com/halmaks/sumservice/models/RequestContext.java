package com.halmaks.sumservice.models;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    private static final ThreadLocal<Map<String, String>> REQUEST_CONTEXT = ThreadLocal.withInitial(() -> {
            return new HashMap<>();
    });

    public static void set(final String key, final String value) {
        REQUEST_CONTEXT.get().put(key, value);
    }

    public static String get(final String key) {
        return REQUEST_CONTEXT.get().get(key);
    }

    public static void clean() {
        REQUEST_CONTEXT.remove();
    }
}
