package com.lguplus.fleta.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class AdvertisementMetaDto {

    @JsonIgnore
    private String adsId;

    @JsonIgnore
    private String adsType;

    @JsonIgnore
    private String linkUrl;

    @JsonIgnore
    public String[] getLinkUrlValues() {

        return StringUtils.splitByWholeSeparatorPreserveAllTokens(StringUtils.defaultString(linkUrl), "||");
    }

    @ApiModelProperty(value = "광고 번호", dataType = "int")
    private int adsNo;

    @ApiModelProperty(value = "제목", dataType = "string")
    private String title;

    @ApiModelProperty(value = "광고 타입", dataType = "string")
    private String type;

    @ApiModelProperty(value = "내용", dataType = "string")
    private String content;

    @ApiModelProperty(value="카테고리 정보", dataType="string")
    private String category;

    @ApiModelProperty(value="시리즈 정보", dataType="string")
    private String series;

    @ApiModelProperty(value = "이미지 서버 경로", dataType = "string")
    private String imageServerUrl;

    @ApiModelProperty(value = "첫번째 이미지 파일명", dataType = "string")
    private String saveFileName;

    @ApiModelProperty(value = "두번째 이미지 파일명", dataType = "string")
    private String saveFileName2;

    @ApiModelProperty(value = "첫번째 예비 필드 값", dataType = "string")
    private String etc;

    @ApiModelProperty(value = "두번째 예비 필드 값", dataType = "string")
    private String etc2;

    @ApiModelProperty(value = "세번째 예비 필드 값", dataType = "string")
    private String etc3;

    @ApiModelProperty(value = "네번째 예비 필드 값", dataType = "string")
    private String etc4;

    @ApiModelProperty(value = "메뉴 UI 타입", dataType = "string")
    private String menuUiType;
}
