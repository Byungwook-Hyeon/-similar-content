package com.lguplus.fleta.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class TrailerInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("albumId")
    private String albumId;
    
    @JsonProperty("trailerSh")
    private String trailerSh;
    
    @JsonProperty("trailerHd")
    private String trailerHd;
    
    @JsonProperty("badge")
    private String badge;
}