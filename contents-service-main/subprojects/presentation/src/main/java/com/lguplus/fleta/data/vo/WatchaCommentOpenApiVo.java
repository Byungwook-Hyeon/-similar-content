package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.annotation.ParamAlias;
import com.lguplus.fleta.data.dto.request.WatchaCommentRequestDto;
import com.lguplus.fleta.exception.ParameterTypeMismatchException;
import com.lguplus.fleta.exception.UndefinedException;
import com.lguplus.fleta.validation.Groups;
import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@GroupSequence({Groups.C1.class, Groups.C2.class, Groups.C3.class, Groups.C4.class, Groups.C5.class,
        Groups.C6.class, Groups.C7.class, Groups.C8.class, Groups.C9.class, Groups.C10.class, Groups.C11.class, Groups.C12.class,
        WatchaCommentOpenApiVo.class})
public class WatchaCommentOpenApiVo extends CommonVo {

    @NotBlank(message = "필수 요청 정보 누락(album_id 가 Null 혹은 빈값 입니다.)", groups = Groups.C1.class)
    @ParamAlias("album_id")
    private String albumId;

    @NotBlank(message = "필수 요청 정보 누락(start_num 가 Null 혹은 빈값 입니다.)", groups = Groups.C2.class)
    @Pattern(regexp = "^[-]?[0-9]*$", message = "잘못된 포맷 형식 전달 및 응답 결과 지원하지 않는 포맷(start_num 는 숫자만 가능합니다)", payload = ParameterTypeMismatchException.class, groups = Groups.C3.class)
    @ParamAlias("start_num")
    private String startNumber;

    @ParamAlias("req_count")
    private String requestCount;

    @ParamAlias("access_key")
    public String accessKey;

    @ParamAlias("cp_id")
    public String cpId;

    @Override
    public WatchaCommentRequestDto convert() {

        try {
            Integer.parseInt(startNumber);
        } catch (Exception ex) {
            throw new UndefinedException();
        }

        return WatchaCommentRequestDto.builder()
                .saId(this.getSaId())
                .stbMac(this.getStbMac())
                .albumId(this.getAlbumId())
                .startNumber(NumberUtils.toInt(startNumber, 1))
                .requestCount(NumberUtils.toInt(requestCount, 1))
                .build();
    }
}