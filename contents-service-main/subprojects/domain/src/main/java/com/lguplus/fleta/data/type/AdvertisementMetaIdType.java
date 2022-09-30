package com.lguplus.fleta.data.type;

import java.util.HashSet;
import java.util.Set;

public enum AdvertisementMetaIdType {

    IPTV_BANNER("SEAM1", "SEAM2", "SEAM3", "SEAM4", "dolbysound01"),
    IPTV_CATEGORY("Seamless02"),
    IPTV_APP_LINK("IA_0000000023"),
    MOBILE_CATEGORY("BA_0000000058");

    private final Set<String> advertisementIdSet;

    AdvertisementMetaIdType(final String... advertisementIds) {

        advertisementIdSet = new HashSet<>(Set.of(advertisementIds));
    }

    public boolean contains(final String advertisementId) {

        return advertisementIdSet.contains(advertisementId);
    }
}
