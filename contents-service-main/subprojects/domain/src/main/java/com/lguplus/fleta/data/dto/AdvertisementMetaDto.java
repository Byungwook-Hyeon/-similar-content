package com.lguplus.fleta.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "광고 번호", type = "int")
    private int adsNo;

    @Schema(description = "제목", type = "string")
    private String title;

    @Schema(description = "광고 타입", type = "string")
    private String type;

    @Schema(description = "내용", type = "string")
    private String content;

    @Schema(description = "카테고리 정보", type = "string")
    private String category;

    @Schema(description = "시리즈 정보", type = "string")
    private String series;

    @Schema(description = "이미지 서버 경로", type = "string")
    private String imageServerUrl;

    @Schema(description = "첫번째 이미지 파일명", type = "string")
    private String saveFileName;

    @Schema(description = "두번째 이미지 파일명", type = "string")
    private String saveFileName2;

    @Schema(description = "첫번째 예비 필드 값", type = "string")
    private String etc;

    @Schema(description = "두번째 예비 필드 값", type = "string")
    private String etc2;

    @Schema(description = "세번째 예비 필드 값", type = "string")
    private String etc3;

    @Schema(description = "네번째 예비 필드 값", type = "string")
    private String etc4;

    @Schema(description = "메뉴 UI 타입", type = "string")
    private String menuUiType;
}
