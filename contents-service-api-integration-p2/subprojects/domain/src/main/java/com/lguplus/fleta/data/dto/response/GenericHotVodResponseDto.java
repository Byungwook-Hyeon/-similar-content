package com.lguplus.fleta.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lguplus.fleta.data.dto.HotvodRecordTypeDto;
import com.lguplus.fleta.data.dto.PlainTextibleDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author mwlee
 * @since 1.0
 */
@Getter
@SuperBuilder(builderMethodName = "genericHotVodResponseBuilder")
@JsonPropertyOrder({"flag", "message", "version", "total_cnt"
		//, "filtering_site", "recordset"
})
public class GenericHotVodResponseDto<S> extends SuccessResponseDto {

	@ApiModelProperty(position=3, dataType="string", value="순번: 3<br>자리수: 14<br>설명: 버전정보", example="20140324004")
	@JsonProperty("version")
	private String version;

	@ApiModelProperty(position=4, dataType="string", value="순번: 4<br>자리수: 10<br>설명: 전체게시물수", example="11")
	@JsonProperty("total_cnt")
	private String totalCnt;

	@ApiModelProperty(position=5, dataType="array", value="순번: 5<br>자리수: <br>설명:  필터링 사이트", example="")
	@JsonProperty("filtering_site")
	@JacksonXmlElementWrapper(localName="filtering_site")
	@JacksonXmlProperty(localName="site")
	private List<String> filteringSite;

	@ApiModelProperty(position=100, dataType="array")
	@JsonProperty("recordset")
	@JacksonXmlElementWrapper(localName="recordset")
	@JacksonXmlProperty(localName="record")
	private List<S> recordset;


	@Override
	public String toPlainText() {

		return null;
	}
}
