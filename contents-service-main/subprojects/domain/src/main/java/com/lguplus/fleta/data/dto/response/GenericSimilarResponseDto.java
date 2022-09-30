package com.lguplus.fleta.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@SuperBuilder(builderMethodName = "genericSimilarResponseBuilder")
@JsonPropertyOrder({"flag", "message", "record"})
public class GenericSimilarResponseDto<SimilarContentDto> extends SuccessResponseDto {

    @JsonProperty("list")
    @JacksonXmlElementWrapper(localName = "list")
    @JacksonXmlProperty(localName = "record")
    private List<SimilarContentDto> recordset;
}
