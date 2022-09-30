package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.entity.BannerMstEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerGroupJpaRepository extends JpaRepository<BannerMstEntity, String> {

    List<BannerMstEntity> findAll();
}
