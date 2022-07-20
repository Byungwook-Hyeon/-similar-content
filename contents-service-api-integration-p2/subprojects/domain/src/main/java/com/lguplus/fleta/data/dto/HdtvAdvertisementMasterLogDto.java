package com.lguplus.fleta.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HdtvAdvertisementMasterLogDto {

    private String advertisementId;
    private String advertisementName;
    private int liveCount;
    private LocalDateTime registerDate;
    private LocalDateTime actionDate;
    private String actor;
    private String actorIp;
    private String actionGubun;
    private LocalDateTime modifyDate;
    private String registerer;
    private Character serviceType;
}
