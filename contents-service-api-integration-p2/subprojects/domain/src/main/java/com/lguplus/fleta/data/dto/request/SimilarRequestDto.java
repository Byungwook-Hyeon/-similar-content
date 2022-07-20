package com.lguplus.fleta.data.dto.request;

import com.lguplus.fleta.data.dto.PlainTextibleDto;
import com.lguplus.fleta.data.dto.response.CommonResponseDto;
import com.lguplus.fleta.data.dto.response.CommonResponseDto.Separator;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimilarRequestDto implements Serializable {

    private String albumId;

    private String similarCont;
}
