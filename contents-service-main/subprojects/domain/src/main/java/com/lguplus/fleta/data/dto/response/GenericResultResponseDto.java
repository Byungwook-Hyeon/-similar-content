package com.lguplus.fleta.data.dto.response;

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

@Schema
@Getter
@SuperBuilder(builderMethodName = "genericResultResponseDtoBuilder")
@JsonPropertyOrder({"flag", "message", "total_count", "recordset"})
public class GenericResultResponseDto<S> extends SuccessResponseDto {

    /**
     *
     */
    @Schema(type = "string", description = "순번: 3<br>자리수: 3<br>설명: 검색 갯수<br>검색된 앨범들의 총 갯수", example = "1")
    @JsonProperty("total_count")
    private int totalCount;

    /**
     *
     */
    @Schema(type = "array")
    @JsonProperty("recordset")
    @JacksonXmlElementWrapper(localName = "recordset")
    @JacksonXmlProperty(localName = "result")
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
