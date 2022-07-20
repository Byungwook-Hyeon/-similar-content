package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.entity.PtUxHvCategoryEntityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotVodCategoryJpaRepository extends JpaRepository<PtUxHvCategoryEntityDto, String> {

    @Query("SELECT n FROM PtUxHvCategoryEntityDto n WHERE n.hvCategoryId in (:categoryId) AND n.delYn = 'N'")
    List<PtUxHvCategoryEntityDto> findHvCategoryByCategoryIds(@Param("categoryId") List<String> categoryIds);

}