package com.lguplus.fleta.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UxHotvodHitcountLogDto {

    private String year;
    private String month;
    private String day;
    private String contentId;
    private int hitCount;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
}
