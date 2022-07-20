package com.lguplus.fleta.data.dto;

import lombok.*;

import java.io.Serializable;

/**
 * property에서 hotvod rank 정보 가져오는 VO
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class HotVodLoadDto implements Serializable {

    private String categoryId;
    private String imgUrl;
}

