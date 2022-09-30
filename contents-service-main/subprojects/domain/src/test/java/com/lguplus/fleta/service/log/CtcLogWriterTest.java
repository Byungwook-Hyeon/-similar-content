package com.lguplus.fleta.service.log;

import com.lguplus.fleta.util.JunitTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
class CtcLogWriterTest {

    @Test
    void test_close_Fail() throws IOException {

        final File textFile = new File(System.getProperty("java.io.tmpdir"), "some.txt");
        final CtcLogWriter writer = new CtcLogWriter("PX_UX_HV_CATEGORY", System.getProperty("java.io.tmpdir"));
        writer.close();
        JunitTestUtils.setValue(writer, "textFile", textFile);

        assertDoesNotThrow(writer::close);
    }
}
