package com.lguplus.fleta.data.type;

public enum ImageServerType {

    /**
     * AS-IS Type: P
     */
    RESIZE(ServerName.RESIZE),

    /**
     * AS-IS Type: E
     */
    LIVE_CAPTURE("live_cap_server"),

    /**
     * AS-IS Type: I
     */
    SMARTUX_RESIZE(ServerName.RESIZE),

    /**
     * AS-IS Type: S
     */
    SNAPSHOT("snap_server"),

    /**
     * AS-IS Type: V
     */
    DEFAULT("img_server"),

    /**
     * AS-IS Type: O
     */
    ORIGINAL(ServerName.RESIZE),

    /**
     * AS-IS Type: M
     */
    PPM_PRODUCT("img_ppmprod_server"),

    /**
     * AS-IS Type: T
     */
    STILLCUT("img_still_server"),

    /**
     * AS-IS Type: PC
     */
    POSTER_CLOUD("img_poster_cloud");

    private static class ServerName {
        private static final String RESIZE = "img_resize_server";
    }

    private final String imageServerType;

    ImageServerType(final String imageServerType) {

        this.imageServerType = imageServerType;
    }

    @Override
    public String toString() {

        return imageServerType;
    }
}
