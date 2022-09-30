package com.lguplus.fleta.data.type;

public enum AdvertisementMetaType {

    VOD("V"),
    CATEGORY("C"),
    APP_LINK("L");

    private final String type;

    AdvertisementMetaType(final String type) {

        this.type = type;
    }

    @Override
    public String toString() {

        return type;
    }
}
