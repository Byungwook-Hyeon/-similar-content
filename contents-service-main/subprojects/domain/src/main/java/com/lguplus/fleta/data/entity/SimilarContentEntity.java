package com.lguplus.fleta.data.entity;

import com.lguplus.fleta.data.entity.id.SimilarContentId;
import com.lguplus.fleta.data.entity.id.SimilarContentMetaId;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Table(name = "TB_P_SIMILAR_CONT_B", schema = "SMARTUX")
public class SimilarContentEntity implements Serializable {

    @Id
    @Column(name = "album_id")
    private String albumId;

    @Column(name = "val")
    private String similarContent;
}
