package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.annotation.ParamAlias;
import com.lguplus.fleta.exception.ParameterContainsNonAlphanumericException;
import com.lguplus.fleta.exception.ParameterContainsWhitespaceException;
import com.lguplus.fleta.exception.ParameterOverBoundsException;
import com.lguplus.fleta.validation.Groups;
import lombok.Getter;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 1. 문자열인지 체크 (app_type, id)
 *  - flag.paramnotfound 5000
 *  - message.paramnotfound {0} 파라미터값이 전달이 안됨, ketKey
 * 2. id 패턴 체크 (공백, \\S)
 *  - flag.blank 5013
 *  - message.blank  파라미터 id는 값에 공백이 없어야 함
 * 3. id 패턴 체크 (^[a-zA-Z0-9_]*$)
 *  - flag.alphabetAndNumber 5014
 *  - message.alphabetAndNumber  파라미터 {0}는 값에 영문,숫자만 포함되어야 함
 * 4. id 길이 체크 (20이하)
 *  - flag.range3 5004
 *  - message.range3 파라미터 {0}는 값이 {1} 이하이어야 함, id, 20
 * 5. start_num 있는 경우
 *      1. start_num 검색 시작 인덱스가 숫자형인지 확인
 *          - flag.numberformat 5001
 *          - message.numberformat {0} 파라미터는 숫자형 데이터이어야 함, start_num
 *      2. start_num 이 O이 아닌 경우
 *          1. req_count가 없으면
 *              - flag.paramnotfound 5000
 *              - message.paramnotfound {0} 파라미터값이 전달이 안됨, req_count
 *          2. req_count가 숫자형인지 확인
 *              - flag.numberformat 5001
 *              - message.numberformat {0} 파라미터는 숫자형 데이터이어야 함, req_count
 *          3. req_count 파라미터 값이 0이하이면
 *              - flag.range2 5003
 *              - message.range2 파라미터 {0}는 값이 {1} 이상이어야 함, "req_count", "1"
 */
@Getter
@GroupSequence({Groups.C1.class, Groups.C2.class, Groups.C3.class, Groups.C4.class, Groups.C5.class,
        Groups.R4.class, Groups.R6.class, AdvertisementBannerVo.class})
public class AdvertisementBannerVo extends CommonPagingVo {

    @NotBlank(message="id 파라미터값이 전달이 안됨", groups=Groups.C2.class)
    //@Pattern(regexp="^.*[\\s]+.*$", message="파라미터 id는 값에 공백이 없어야 함", payload=ParameterContainsWhitespaceException.class, groups=Groups.C2.class)
    @Pattern(regexp="^[^\\s]+$", message="파라미터 id는 값에 공백이 없어야 함", payload=ParameterContainsWhitespaceException.class, groups=Groups.C3.class)
    @Pattern(regexp="^[a-zA-Z0-9_]*$", message="파라미터 id는 값에 영문,숫자만 포함되어야 함", payload=ParameterContainsNonAlphanumericException.class, groups=Groups.C4.class)
    @Size(max=20, message="파라미터 id는 값이 20 이하이어야 함", payload=ParameterOverBoundsException.class, groups=Groups.C5.class)
    @ParamAlias("id")
    private String id;
    
    @ParamAlias("cat_id")
    private String idForStatistics;
    
    @NotBlank(message="app_type 파라미터값이 전달이 안됨", groups=Groups.C1.class)
    @Override
    public String getAppType() {
        return super.getAppType();
    }
}
