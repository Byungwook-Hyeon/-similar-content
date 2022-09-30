package com.lguplus.fleta.data.vo.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lguplus.fleta.data.dto.response.ErrorResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(builderMethodName = "genericErrorResultResponseBuilder")
@JsonPropertyOrder({"flag", "message", "total_cnt", "recordset"})
public class GenericErrorResultResponseVo extends ErrorResponseDto {

}

