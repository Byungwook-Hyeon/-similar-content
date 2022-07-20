package com.lguplus.fleta.data.entity;


import com.lguplus.fleta.data.entity.id.SimilarContentId;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Entity
@Table(name = "SMARTUX.TB_P_SIMILAR_CONT_A")
@IdClass(SimilarContentId.class)
public class SimilarContentEntity implements Serializable {

    @Id
    @Column(name = "album_id")
    private String albumId;

    @Id
    @Column(name = "val")
    private String similarCont;

}
