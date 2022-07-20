package com.lguplus.fleta.data.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Minwoo Lee
 * @since 1.0
 */
@JacksonXmlRootElement(localName = "error")
public interface CommonErrorResponseDto extends CommonResponseDto {

    @ApiModelProperty(position=1, dataType="string", value="순번: 1<br>자리수: 4<br>설명: 결과 코드", example="0000")
	@JsonGetter("code")
	String getFlag();

}
