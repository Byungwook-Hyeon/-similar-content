package com.lguplus.fleta.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lguplus.fleta.data.dto.PlainTextibleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @author mwlee
 * @since 1.0
 */
@Getter
@SuperBuilder(builderMethodName = "genericRecordsetResponseBuilder")
@JsonPropertyOrder({"flag", "message", "total_cnt", "recordset"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRecordsetResponseDto<S> extends SuccessResponseDto {

    /**
     * 검색 총 건수
     */
    @Schema(type = "string", description = "순번: 3<br>자리수: 3<br>설명: 검색 갯수<br>검색 총 레코드 건수", example = "1")
    @JsonProperty("total_cnt")
    private String totalCount;

    /**
     * 결과 레코드 세트
     */
    @Schema(type = "array", description = "순번: 4 <br>설명: 검색 레코드", example = "")
    @JsonProperty("recordset")
    @JacksonXmlElementWrapper(localName = "recordset")
    @JacksonXmlProperty(localName = "record")
    private List<S> recordset;

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
