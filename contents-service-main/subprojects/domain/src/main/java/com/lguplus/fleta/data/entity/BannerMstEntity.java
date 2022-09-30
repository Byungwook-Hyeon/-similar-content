package com.lguplus.fleta.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Table(name = "PT_UF_MAIN_BANNER_MST", schema = "SMARTUX")
public class BannerMstEntity implements Serializable {

    @Id
    @Column(name = "group_id")
    private String group_id;

    @Column(name = "group_nm")
    private String group_nm;

    @Column(name = "group_gbn")
    private String group_gbn;
}
