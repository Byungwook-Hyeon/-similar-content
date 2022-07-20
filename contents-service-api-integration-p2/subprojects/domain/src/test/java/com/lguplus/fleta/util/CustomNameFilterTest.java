package com.lguplus.fleta.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomNameFilterTest {

    @Test
    void accept_endsWith() {
        CustomNameFilter customNameFilter = new CustomNameFilter("endsWith", "test");
        boolean result = ReflectionTestUtils.invokeMethod(customNameFilter, "checkRtn", "endsWithtest");
        assertThat(result).isTrue();
    }

    @Test
    void accept_equals() {
        CustomNameFilter customNameFilter = new CustomNameFilter("equals", "equalstest");
        boolean result = ReflectionTestUtils.invokeMethod(customNameFilter, "checkRtn", "equalstest");
        assertThat(result).isTrue();
    }

    @Test
    void accept_contains() {
        CustomNameFilter customNameFilter = new CustomNameFilter("contains", "contains");
        boolean result = ReflectionTestUtils.invokeMethod(customNameFilter, "checkRtn", "contains test");
        assertThat(result).isTrue();
    }

    @Test
    void accept_others() {
        CustomNameFilter customNameFilter = new CustomNameFilter("others", "test");
        boolean result = ReflectionTestUtils.invokeMethod(customNameFilter, "checkRtn", "endsWithtest");
        assertThat(result).isTrue();
    }
}