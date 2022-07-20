package com.lguplus.fleta.service.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class WaitableSynchronizedInvoker {

    private static final long HOLD_TIME_MILLIS = 2000;

    private final Set<String> waitings;
    private final Map<String, Lock> runnings;

    public WaitableSynchronizedInvoker() {

        waitings = Collections.synchronizedSet(new HashSet<>());
        runnings = new ConcurrentHashMap<>();
    }

    public void invoke(final String name, final Runnable job) {

        if (!waitings.add(name)) {
            log.debug("New job for [" + name + "] rejected because another job already waiting.");
            return;
        }

        final Lock waitLock = runnings.get(name);
        if (waitLock != null) {
            waitLock.lock();
        }

        final Lock runLock = new ReentrantLock();
        runLock.lock();
        runnings.put(name, runLock);
        if (waitLock != null) {
            waitLock.unlock();
        }
        waitings.remove(name);

        try {
            // 동일한 메시지가 연속적으로 인입되어 캐싱 작업이 반복 실행되는 것을 막기 위해 2초간 작업을 유예한다.
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(HOLD_TIME_MILLIS));
            job.run();
        } finally {
            runLock.unlock();
            runnings.remove(name);
        }
    }
}
