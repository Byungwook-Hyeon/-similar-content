package com.lguplus.fleta.data.vo.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lguplus.fleta.data.dto.PlainTextibleDto;
import com.lguplus.fleta.data.dto.response.ErrorResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder(builderMethodName = "genericRecordsetErrorResponseBuilder")
@JsonPropertyOrder({"flag", "message", "total_cnt", "recordset"})
public class GenericRecordsetErrorResponseVo<S> extends ErrorResponseDto {

    /**
     * 검색 총 건수
     */
    @ApiModelProperty(position = 3, dataType = "string", value = "순번: 3<br>자리수: 3<br>설명: 검색 갯수<br>검색 총 레코드 건수", example = "1")
    @JsonProperty("total_cnt")
    private final String totalCount;

    /**
     * 결과 레코드 세트
     */
    @ApiModelProperty(position = 4, dataType = "array", value = "순번: 4 <br>설명: 검색 레코드", example = "")
    @JsonProperty("recordset")
    @JacksonXmlElementWrapper(localName = "recordset")
    @JacksonXmlProperty(localName = "record")
    private final List<S> recordset;

    public String getTotalCount() {
        return String.valueOf(0);
    }


    @Override
    public String toPlainText() {

        final StringBuilder buffer = new StringBuilder()
                .append(getTotalCount());
        final List<S> localRecordset = getRecordset();
        if (localRecordset != null && !localRecordset.isEmpty()) {
            buffer.append(Separator.RECORD)
                    .append(localRecordset.stream()
                            .filter(PlainTextibleDto.class::isInstance)
                            .map(value -> ((PlainTextibleDto) value).toPlainText())
                            .collect(Collectors.joining(Separator.ROW)));
        }

        return String.join(Separator.COLUMN,
                super.toPlainText(), buffer);
    }

}
