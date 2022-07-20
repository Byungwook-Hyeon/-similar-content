package com.lguplus.fleta.data.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lguplus.fleta.data.dto.PlainTextibleDto;
import com.lguplus.fleta.data.dto.response.CommonResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
@JsonPropertyOrder({"comment_id", "comment_writer", "comment_rating", "comment_text", "comment_date"})
public class WatchaCommentDto implements Serializable, PlainTextibleDto {

    @ApiModelProperty(position = 1, value = "순번: 1<br><br>설명: INDEX 순번", example = "1")
    @JsonProperty("comment_id")
    private String commentId;

    @ApiModelProperty(position = 2, value = "순번: 2<br><br>설명: 댓글 등록자", example = "테스터")
    @JsonProperty("comment_writer")
    private String commentWriter;

    @ApiModelProperty(position = 3, value = "순번: 3<br><br>설명: 등록자 평점", example = "1")
    @JsonProperty("comment_rating")
    private String commentRating;

    @ApiModelProperty(position = 4, value = "순번: 4<br><br>설명: 댓글 내용", example = "댓글 내용 입니다.")
    @JsonProperty("comment_text")
    private String commentText;

    @ApiModelProperty(position = 5, value = "순번: 5<br><br>설명: 등록일시", example = "2021-12-24 00:00:00")
    @JsonProperty("comment_date")
    private String commentDate;

    @Override
    public String toPlainText() {
        return String.join(CommonResponseDto.Separator.COLUMN,
                getCommentId(),
                getCommentWriter(),
                getCommentRating(),
                getCommentText(),
                getCommentDate()
        );
    }
}