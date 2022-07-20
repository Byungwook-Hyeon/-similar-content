package com.lguplus.fleta.data.type;

import java.lang.reflect.Field;

public final class CacheName {

    private CacheName() {}

    public static final String HOTVOD_HIT_COUNT = "HOTVOD_HIT_COUNT";
    public static final String HOTVOD_NEW_GET = "HOTVOD_NEW_GET";
    public static final String HOTVOD_NEW_GET_LIST = "HOTVOD_NEW_GET_LIST";
    public static final String HOTVOD_META_TITLE_ASSET = "HOTVOD_META_TITLE_ASSET";
    public static final String FILTERING_SITE = "FILTERING_SITE";
    public static final String ADVERTISEMENT2 = "ADVERTISEMENT2";
    public static final String IMAGE_SERVER = "IMAGE_SERVER";
    public static final String TEST_SUBSCRIBER = "TEST_SUBSCRIBER";
    public static final String TEST_SUBSCRIBER_LIST = "TEST_SUBSCRIBER_LIST";
    public static final String CTC_LOG = "CTC_LOG";

    static {
        try {
            for (final Field field : CacheName.class.getDeclaredFields()) {
                final String fieldName = field.getName();
                if (field.getType() != String.class) {
                    throw new IllegalStateException(fieldName + ": Define only cache name here.");
                }

                final String cacheName = (String) field.get(null);
                if (!field.getName().equals(cacheName)) {
                    throw new IllegalStateException(fieldName + ": Cache name should be equal to it's constant name.");
                }
            }
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
