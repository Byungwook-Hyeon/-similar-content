package com.lguplus.fleta.data.dto.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.lguplus.fleta.data.dto.PlainTextibleDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Minwoo Lee
 * @since 1.0
 */
@JacksonXmlRootElement(localName = "result")
public interface CommonResponseDto extends PlainTextibleDto {

    @Schema(type = "string", description = "순번: 1<br>자리수: 4<br>설명: 성공여부 코드값", example = "0000")
    @JsonGetter("flag")
    String getFlag();

    @Schema(type = "string", description = "순번: 2<br>자리수: 50<br>설명: 결과 메시지", example = "성공")
    @JsonGetter("message")
    String getMessage();

    class Separator {

        private Separator() {
        }

        public static final String ROW = "\f";
        public static final String COLUMN = "!^";
        public static final String RECORD = "!@";
        public static final String ARRAY = "\b";
    }

    @Override
    default String toPlainText() {
        return String.join(Separator.COLUMN,
            getFlag(), getMessage());
    }
}
