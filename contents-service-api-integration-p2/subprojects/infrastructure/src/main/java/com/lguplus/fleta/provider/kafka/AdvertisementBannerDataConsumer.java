package com.lguplus.fleta.provider.kafka;

import com.lguplus.fleta.service.DataManipulationListener;
import com.lguplus.fleta.service.ManipulationDataDispatcher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Profile("!local")
@Slf4j
@Component
@AllArgsConstructor
public class AdvertisementBannerDataConsumer {

    private final ManipulationDataDispatcher manipulationDataDispatcher;

    @Qualifier("advertisementBannerManipulationListener")
    private final DataManipulationListener advertisementBannerManipulationListener;

    @KafkaListener(topics = "REV_msa_mylgdb.uxuser.pt_hdtv_ads_master_iptv", groupId = "contents.AdvertisementBannerDataConsumer")
    public void onAdvertisementMasterIptvManipulated(final String message) {
        log.debug("Manipulation data for REV_msa_mylgdb.uxuser.pt_hdtv_ads_master_iptv: {}", message);
        manipulationDataDispatcher.dispatch(advertisementBannerManipulationListener, message);
    }

    @KafkaListener(topics = "REV_msa_mylgdb.uxuser.pt_hdtv_ads_info_iptv", groupId = "contents.AdvertisementBannerDataConsumer")
    public void onAdvertisementInfoIptvManipulated(final String message) {
        log.debug("Manipulation data for REV_msa_mylgdb.uxuser.pt_hdtv_ads_info_iptv: {}", message);
        manipulationDataDispatcher.dispatch(advertisementBannerManipulationListener, message);
    }
}
