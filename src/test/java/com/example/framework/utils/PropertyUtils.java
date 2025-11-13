package com.example.framework.utils;

import java.io.InputStream;
import java.util.*;

public final class PropertyUtils {
    private static final Properties LAYERED = new Properties();
    private static boolean initialized = false;

    private PropertyUtils(){}

    public static synchronized void init(String env) {
        if (initialized) return;
        load("config/application-common.properties", false);
        load("config/application-" + env + ".properties", true);
        load("config/application-local.properties", false);
        load("config/application-secrets.properties", false);
        System.getProperties().forEach((k,v)-> LAYERED.setProperty(String.valueOf(k), String.valueOf(v)));
        System.getenv().forEach((k,v)-> {
            if (k.startsWith("TEST__")) {
                String key = k.substring("TEST__".length()).replace("__",".").toLowerCase(Locale.ROOT);
                LAYERED.setProperty(key, v);
            }
        });
        initialized = true;
    }

    private static void load(String path, boolean required) {
        try (InputStream is = ClassLoader.getSystemResourceAsStream(path)) {
            if (is != null) {
                Properties p = new Properties(); p.load(is); LAYERED.putAll(p);
            } else if (required) {
                throw new IllegalStateException("Required properties missing: " + path);
            }
        } catch (Exception e) {
            if (required) throw new RuntimeException("Failed to load: " + path, e);
        }
    }

    public static String get(String key) {
        String v = LAYERED.getProperty(key);
        if (v == null || v.isBlank()) throw new IllegalArgumentException("Missing property: " + key);
        return v.trim();
    }
    public static int getInt(String key){ return Integer.parseInt(get(key)); }
    public static long getLong(String key){ return Long.parseLong(get(key)); }
    public static boolean getBool(String key){ return Boolean.parseBoolean(get(key)); }

    public static void require(String... keys){
        List<String> miss = new ArrayList<>();
        for (String k: keys) { if (!LAYERED.containsKey(k) || get(k).isBlank()) miss.add(k); }
        if (!miss.isEmpty()) throw new IllegalStateException("Missing required: " + String.join(", ", miss));
    }
}
