package com.lguplus.fleta.service;

import java.util.Map;

public interface DataManipulationListener {

    void onInsert(Map<String, String> data);
    void onUpdate(Map<String, String> data, Map<String, String> before);
    void onDelete(Map<String, String> before);

    default String extractValue(final Map<String, String> data, final String key) {

        if (data.containsKey(key)) {
            return data.get(key);
        } else if (data.containsKey(key.toLowerCase())) {
            return data.get(key.toLowerCase());
        } else {
            return data.get(key.toUpperCase());
        }
    }
}
