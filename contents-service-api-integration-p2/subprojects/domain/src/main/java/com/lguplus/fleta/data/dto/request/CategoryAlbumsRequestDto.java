package com.lguplus.fleta.data.dto.request;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class CategoryAlbumsRequestDto implements Serializable {

    private List<String> categoryId;

    private List<String> contentsId;

}
