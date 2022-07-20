package com.lguplus.fleta.data.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class SubscriberDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * saId
     */
    private String saId;
    /**
     * macAddr
     */
    private String macAddr;

}