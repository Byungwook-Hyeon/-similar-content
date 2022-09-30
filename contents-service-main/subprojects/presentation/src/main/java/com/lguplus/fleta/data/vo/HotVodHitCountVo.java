package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.annotation.ParamAlias;
import com.lguplus.fleta.exception.ParameterContainsNonAlphanumericException;
import com.lguplus.fleta.exception.ParameterContainsWhitespaceException;
import com.lguplus.fleta.exception.ParameterOverBoundsException;
import com.lguplus.fleta.exception.ParameterUnderBoundsException;
import com.lguplus.fleta.validation.Groups;
import lombok.Getter;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@GroupSequence({Groups.C1.class, Groups.C2.class, Groups.C3.class, 
    Groups.C4.class, Groups.C5.class, Groups.C6.class, HotVodHitCountVo.class})
public class HotVodHitCountVo extends CommonVo {
    
    @NotBlank(message="필수 요청 정보 누락(content_id)", groups=Groups.C3.class)
    @Pattern(regexp="^[^\\s]+$", message="파라미터 content_id는 값에 공백이 없어야 함", payload=ParameterContainsWhitespaceException.class, groups=Groups.C4.class)
    @Pattern(regexp="^[a-zA-Z0-9_]*$", message="파라미터 content_id는 값에 영문,숫자만 포함되어야 함", payload=ParameterContainsNonAlphanumericException.class, groups=Groups.C5.class)
    @Size(min=9, message="파라미터 content_id는 값이 9 이상이어야 함", payload=ParameterUnderBoundsException.class, groups=Groups.C6.class)
    @Size(max=10, message="파라미터 content_id는 값이 10 이하이어야 함", payload=ParameterOverBoundsException.class, groups=Groups.C6.class)
    @ParamAlias("content_id")
    private String contentId;
    
    @ParamAlias("cat_id")
    private String categoryIdForStatistics;
    
    @ParamAlias("cat_name")
    private String categoryNameForStatistics;
    
    @ParamAlias("cont_id")
    private String contentIdForStatistics;
    
    @ParamAlias("cont_name")
    private String contentNameForStatistics;
    
    @ParamAlias("cont_type")
    private String contentTypeForStatistics;
    
    @NotBlank(message="필수 요청 정보 누락(sa_id)", groups=Groups.C1.class)
    @Override
    public String getSaId() {
        return super.getSaId();
    }
    
    @NotBlank(message="필수 요청 정보 누락(stb_mac)", groups=Groups.C2.class)
    @Override
    public String getStbMac() {
        return super.getStbMac();
    }
}
