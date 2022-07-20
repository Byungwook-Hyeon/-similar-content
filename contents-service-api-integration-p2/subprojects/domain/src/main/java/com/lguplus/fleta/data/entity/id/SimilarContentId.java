package com.lguplus.fleta.data.entity.id;

import java.io.Serializable;
import javax.persistence.Access;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimilarContentId implements Serializable {
    private String albumId;
    private String similarCont;
}
