package com.lguplus.fleta.service.imageserver;

import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.ImageServerDto;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.data.type.ImageServerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class ImageServerDomainService implements BeanFactoryAware {

    private ImageServerDomainService self;
    private final VodLookupDomainClient vodLookupDomainClient;
    private final AtomicInteger serverAtom;
    private final AtomicInteger urlAtom;

    public ImageServerDomainService(final VodLookupDomainClient vodLookupDomainClient) {

        this.vodLookupDomainClient = vodLookupDomainClient;
        this.serverAtom = new AtomicInteger();
        this.urlAtom = new AtomicInteger();
    }

    @Cacheable(cacheNames=CacheName.IMAGE_SERVER, key="'DEFAULT'")
    public Map<String, List<ImageServerDto>> getImageServers() {

        return loadImageServers();
    }

    @CachePut(cacheNames=CacheName.IMAGE_SERVER, key="'DEFAULT'")
    public Map<String, List<ImageServerDto>> loadImageServers() {

        final Map<String, List<ImageServerDto>> imageServers = new HashMap<>();
        vodLookupDomainClient.getImageServers()
                .forEach(dto -> {
                    final String serverId = dto.getServerId();
                    if (!imageServers.containsKey(serverId)) {
                        imageServers.put(serverId, new ArrayList<>());
                    }

                    imageServers.get(serverId).add(dto);
                });
        return imageServers;
    }

    public String getImageServerUrl(final ImageServerType imageServerType) {

        final String imageServerName = imageServerType.toString();
        final List<ImageServerDto> imageServers = self.getImageServers().get(imageServerName);
        if (imageServers == null) {
            return null;
        }

        final ImageServerDto imageServer = imageServers.get(
                Math.abs(serverAtom.getAndIncrement()) % imageServers.size());
        if (urlAtom.getAndIncrement() % 2 == 0) {
            return imageServer.getImageUrl(imageServerType);
        } else {
            return imageServer.getRecoveryImageUrl(imageServerType);
        }
    }

    @Override
    public void setBeanFactory(final BeanFactory beanFactory) {

        self = beanFactory.getBean(ImageServerDomainService.class);
    }
}
