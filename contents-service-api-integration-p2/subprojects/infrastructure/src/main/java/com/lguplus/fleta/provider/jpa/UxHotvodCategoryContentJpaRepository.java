package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.entity.UxHotvodCategoryContent;
import com.lguplus.fleta.data.entity.id.UxHotvodCategoryContentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UxHotvodCategoryContentJpaRepository
        extends JpaRepository<UxHotvodCategoryContent, UxHotvodCategoryContentId> {

}
