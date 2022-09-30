package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.annotation.ParamAlias;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.exception.*;
import com.lguplus.fleta.validation.Groups;
import com.lguplus.fleta.validation.NumberPattern;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@GroupSequence({Groups.C1.class, Groups.C2.class, Groups.C3.class,
        Groups.C4.class, Groups.C5.class, Groups.C6.class, Groups.C7.class, Groups.C8.class,
        Groups.C9.class, Groups.C10.class, Groups.C11.class, Groups.C12.class,HotVodListVo.class})
public class HotVodListVo extends CommonVo {

    @NotBlank(message="필수 요청 정보 누락(version)", groups=Groups.C3.class)
    @Pattern(regexp="^[^\\s]+$", message="파라미터 version는 값에 공백이 없어야 함", payload=ParameterContainsWhitespaceException.class, groups=Groups.C4.class)
    @Pattern(regexp="^[a-zA-Z0-9_]*$", message="파라미터 version는 값에 영문,숫자만 포함되어야 함", payload=ParameterContainsNonAlphanumericException.class, groups=Groups.C5.class)
    @Size(min=14, message="파라미터 version는 값이 14 이상이어야 함", payload=ParameterUnderBoundsException.class, groups=Groups.C6.class)
    @Size(max=15, message="파라미터 version는 값이 15 이하이어야 함", payload=ParameterOverBoundsException.class, groups=Groups.C6.class)
    @ParamAlias("version")
    private String version;

    @ParamAlias("parent_cate")
    private String parentCate;

    @ParamAlias("type")
    private String type;

    @ParamAlias("order")
    private String order;

    @ParamAlias("start_num")
    private String startNum;

    @ParamAlias("req_count")
    private String reqCount;

    @ParamAlias("site_id")
    private String siteId;

    @ParamAlias("filtering_id")
    private String filteringId;

    @ParamAlias("master_content_include")
    private String masterContentInclude;

    @ParamAlias("cat_id")
    private String categoryIdForStatistics;

    @ParamAlias("cat_name")
    private String categoryNameForStatistics;

    @NotBlank(message="필수 요청 정보 누락(sa_id)", groups=Groups.C1.class)
    @Pattern(regexp="^[a-zA-Z0-9_]*$", message="(sa_id - 특수문자 입력 불가)", payload=ParameterContainsNonAlphanumericException.class, groups=Groups.C9.class)
    @Pattern(regexp="^[^\\s]+$", message="(sa_id - 공백 입력 불가)", payload=ParameterContainsWhitespaceException.class, groups=Groups.C7.class)
    @Size(max=12, message="(sa_id - 12 자리 이하)", payload=ParameterUnderBoundsException.class, groups=Groups.C8.class)
    @Override
    public String getSaId() {
        return super.getSaId();
    }

    @NotBlank(message="필수 요청 정보 누락(stb_mac)", groups=Groups.C2.class)
    @Pattern(regexp="^[A-Za-z0-9]*$", message="(stb_mac - 특수문자 입력 불가)", payload=ParameterContainsNonAlphanumericException.class, groups=Groups.C12.class)
    @Pattern(regexp="^[^\\s]+$", message="(stb_mac - 공백 입력 불가)", payload=ParameterContainsWhitespaceException.class, groups=Groups.C10.class)
    @Size(max=38, message="(stb_mac - 38 자리 이하)", payload=ParameterUnderBoundsException.class, groups=Groups.C11.class)
    @Override
    public String getStbMac() {
        return super.getStbMac();
    }

    public String getStartNum() {
        if(StringUtils.equals("Y", this.getMasterContentInclude()) ) {
            startNum = "-1";
        }
        return StringUtils.defaultIfEmpty( startNum, "-1");
    }


    @Override
    public HotVodListRequestDto convert() {

        NumberPattern.Validator numberValidator = new NumberPattern.Validator();
        boolean numberResult = numberValidator.isValid(this.getStartNum(), null);
        if (!numberResult) {
            throw new NumberFormatException();
        }

        return HotVodListRequestDto.builder()
                .version(this.version)
                .parentCate(StringUtils.defaultIfEmpty( this.parentCate, ""))
                .type(StringUtils.defaultIfEmpty( this.type, "C|V|M|N|L"))
                .order(StringUtils.defaultIfEmpty( this.order, "O"))
                .startNumber(Integer.parseInt(this.getStartNum()))
                .requestCount(Integer.parseInt(StringUtils.defaultIfEmpty( this.reqCount, "10")))
                .siteId(StringUtils.defaultIfEmpty( this.siteId, ""))
                .filteringId(StringUtils.defaultIfEmpty( this.filteringId, "iptv").toLowerCase())
                .stbMac(super.getStbMac())
                .saId(super.getSaId())
                .callByScheduler(StringUtils.defaultString("Y"))
                .masterContentInclude(this.masterContentInclude)
                .build();
    }
}
