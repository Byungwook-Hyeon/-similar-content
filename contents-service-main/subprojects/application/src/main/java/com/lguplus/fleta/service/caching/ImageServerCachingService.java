package com.lguplus.fleta.service.caching;

import com.lguplus.fleta.data.annotation.SynchronousScheduled;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ImageServerCachingService {

    private final ImageServerDomainService imageServerDomainService;

    @SynchronousScheduled(cron = "0 0 0/2 * * *", semaphore = CacheName.IMAGE_SERVER)
    public void cacheImageServers() {

        log.debug("Caching ImageServers");

        imageServerDomainService.loadImageServers();
    }
}
