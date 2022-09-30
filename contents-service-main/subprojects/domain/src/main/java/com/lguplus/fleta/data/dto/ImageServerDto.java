package com.lguplus.fleta.data.dto;

import com.lguplus.fleta.data.type.ImageServerType;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ImageServerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PROTOCOL = "http://";

    private String serverId;
    private String serverIp;
    private String recoveryServerIp;
    private String imagePath;

    public String getImageUrl(final ImageServerType imageServerType) {

        return getImageUrl(imageServerType, serverIp);
    }

    public String getRecoveryImageUrl(final ImageServerType imageServerType) {

        return getImageUrl(imageServerType, recoveryServerIp);
    }

    private String getImageUrl(final ImageServerType imageServerType, final String serverIp) {

        if (ImageServerType.SMARTUX_RESIZE == imageServerType) {
            return PROTOCOL + serverIp + "/smartux/";
        } else if (ImageServerType.ORIGINAL == imageServerType) {
            return serverIp;
        } else if (ImageServerType.POSTER_CLOUD == imageServerType) {
            return PROTOCOL + serverIp + "/";
        } else {
            return PROTOCOL + serverIp + imagePath;
        }
    }
}
