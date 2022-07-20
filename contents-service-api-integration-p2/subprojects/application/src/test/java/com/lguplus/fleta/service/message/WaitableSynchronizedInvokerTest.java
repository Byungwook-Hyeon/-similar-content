package com.lguplus.fleta.service.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WaitableSynchronizedInvokerTest {

    @Test
    void test() throws Exception {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);
        CountDownLatch noCountDown = new CountDownLatch(1);

        Set<String> names = Collections.synchronizedSet(new HashSet<>());
        Runnable job = () -> {
            await(noCountDown, 1000);
            names.add(Thread.currentThread().getName());
        };

        WaitableSynchronizedInvoker executor = new WaitableSynchronizedInvoker();

        // execute
        Thread t1 = new Thread(() -> {
            await(latch1, 1000);
            executor.invoke("", job);
        });
        t1.setName("t1");
        t1.start();

        // wait & execute
        Thread t2 = new Thread(() -> {
            await(latch2, 1000);
            executor.invoke("", job);
        });
        t2.setName("t2");
        t2.start();

        // reject
        Thread t3 = new Thread(() -> {
            await(latch3, 1000);
            executor.invoke("", job);
        });
        t3.setName("t3");
        t3.start();

        latch1.countDown();
        await(noCountDown, 100);
        latch2.countDown();
        await(noCountDown, 100);
        latch3.countDown();

        t1.join();
        t2.join();
        t3.join();

        assertThat(names.size()).isEqualTo(2);
        assertThat(names).contains("t1", "t2")
                .doesNotContain("t3");
    }

    private void await(CountDownLatch latch, int millis) {
        try {
            latch.await(millis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
