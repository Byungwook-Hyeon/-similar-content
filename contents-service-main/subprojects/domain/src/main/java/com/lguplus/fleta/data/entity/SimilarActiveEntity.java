package com.lguplus.fleta.data.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "TB_U_ACTIVE_TABLE", schema = "SMARTUX")
public class SimilarActiveEntity implements Serializable {

    @Id
    @Column(name = "table_nm")
    private String tableNm;

    @Column(name = "is_active")
    private String isActive;
}
