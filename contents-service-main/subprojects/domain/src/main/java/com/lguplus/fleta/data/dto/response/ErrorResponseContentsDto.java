package com.lguplus.fleta.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author Minwoo Lee
 * @since 1.0
 */
@Getter
@SuperBuilder
public class ErrorResponseContentsDto extends ErrorResponseDto {

	@JsonProperty("total_cnt")
	private final Integer totalCount;
}
