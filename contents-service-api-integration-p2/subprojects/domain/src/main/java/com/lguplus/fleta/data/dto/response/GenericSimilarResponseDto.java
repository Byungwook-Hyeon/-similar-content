package com.lguplus.fleta.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lguplus.fleta.data.dto.PlainTextibleDto;
import com.lguplus.fleta.data.dto.SimilarContentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(builderMethodName = "genericSimilarResponseBuilder")
@JsonPropertyOrder({"flag", "message", "recordset"})
public class GenericSimilarResponseDto<SimilarContentDto> extends SuccessResponseDto{

    @ApiModelProperty(position = 3, dataType = "array")
    @JsonProperty("recordset")
    @JacksonXmlElementWrapper(localName = "recordset")
    @JacksonXmlProperty(localName = "record")
    private List<SimilarContentDto> recordset2; // 얘가 Json으로 자동 변환이....

//    @Override
//    public String toPlainText() {
//        final StringBuilder buffer = new StringBuilder();
//        final List<S> localRecordset2 = getRecordset();
//        if (localRecordset2 != null && !localRecordset2.isEmpty()) {
//            buffer.append(Separator.RECORD)
//                    .append(localRecordset2.stream()
//                            .filter(PlainTextibleDto.class::isInstance)
//                            .map(value -> ((PlainTextibleDto)value).toPlainText())
//                            .collect(Collectors.joining(Separator.ROW)));
//        }
//
//        return String.join(Separator.COLUMN,
//                super.toPlainText(), buffer);
//    }
}
