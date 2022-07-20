package com.lguplus.fleta.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DataManipulationListenerTest {

    @Test
    void test() {

        final DataManipulationListener listener = new DataManipulationListener() {
            @Override
            public void onInsert(Map<String, String> data) {}

            @Override
            public void onUpdate(Map<String, String> data, Map<String, String> before) {}

            @Override
            public void onDelete(Map<String, String> before) {}
        };

        final Map<String, String> data = Map.of("Key1", "1", "key2", "2", "KEY3", "3");

        final String value1 = listener.extractValue(data, "Key1");
        assertThat(value1).isEqualTo("1");

        final String value2 = listener.extractValue(data, "Key2");
        assertThat(value2).isEqualTo("2");

        final String value3 = listener.extractValue(data, "Key3");
        assertThat(value3).isEqualTo("3");
    }
}
