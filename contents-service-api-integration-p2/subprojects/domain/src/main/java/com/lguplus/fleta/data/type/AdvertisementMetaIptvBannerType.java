package com.lguplus.fleta.data.type;

import java.util.HashSet;
import java.util.Set;

public enum AdvertisementMetaIptvBannerType {

    ALL("2", "3", "5"),
    VOD("2"),
    CATEGORY("3"),
    APP_LINK("5");

    private final Set<String> advertisementTypeSet;

    AdvertisementMetaIptvBannerType(final String... advertisementTypes) {

        this.advertisementTypeSet = new HashSet<>(Set.of(advertisementTypes));
    }

    public boolean contains(final String advertisementType) {

        return advertisementTypeSet.contains(advertisementType);
    }
}
