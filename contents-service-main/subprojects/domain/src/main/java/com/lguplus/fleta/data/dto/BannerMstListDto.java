package com.lguplus.fleta.data.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BannerMstListDto implements Serializable {

    private String group_id;

    private String group_nm;

    private String group_gbn;

}
