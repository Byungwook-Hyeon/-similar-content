package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.NewHotVodInfoDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class HotVodJpaRepository extends AbstractJpaEmRepository {

    @PersistenceContext
    private EntityManager em;


    /**
     * 화제동영상 목록전체
     */
    public List<NewHotVodInfoDto> getNewHotVods(HotVodListRequestDto param) {
        StringBuilder sql = new StringBuilder();
        sql.append("/* SVC.Contents.HotVodJpaRepository.getNewHotVods.01 */   \n");
        sql.append("select '01'                      as rating,                     \n");
        sql.append("       cont.content_type         as type,                       \n");
        sql.append("       cont.reg_dt               as reg_date,                   \n");
        sql.append("       ''                        as category_id,                \n");
        sql.append("       ''                        as category_name,              \n");
        sql.append("       ''                        as category_img,               \n");
        sql.append("       ''                        as category_img_tv,            \n");
        sql.append("       cont.content_id           as content_id,                 \n");
        sql.append("       cont.content_url          as content_url,                \n");
        sql.append("       cont.content_name         as title,                      \n");
        sql.append("       cont.content_info         as content_desc,               \n");
        sql.append("       site.site_img             as site_logo_url,              \n");
        sql.append("       site.site_img_tv          as site_logo_url_tv,           \n");
        sql.append("       site.site_name            as site_name,                  \n");
        sql.append("       cont.content_img          as img_url,                    \n");
        sql.append("       cont.content_img_tv       as img_url_tv,                 \n");
        sql.append("       cont.hit_cnt              as hit_cnt,                    \n");
        sql.append("       cont.duration             as duration,                   \n");
        sql.append("       cc.hv_category_id         as parent_cate,                \n");
        sql.append("       ( select pcat.category_name                              \n");
        sql.append("           from smartux.pt_ux_hv_category pcat                  \n");
        sql.append("          where pcat.hv_category_id = cc.hv_category_id         \n");
        sql.append("       )                         as parent_cate_name,           \n");
        sql.append("       ext.site_id               as site_id,                    \n");
        sql.append("       ext.album_id              as vod_album_id,               \n");
        sql.append("       ext.category_id           as vod_category_id,            \n");
        sql.append("       ext.start_time            as start_time,                 \n");
        sql.append("       ext.end_time              as end_time,                   \n");
        sql.append("       ''                        as contents_name,              \n");
        sql.append("       ''                        as series_no,                  \n");
        sql.append("       ''                        as series_yn,                  \n");
        sql.append("       ''                        as series_desc,                \n");
        sql.append("       coalesce(nullif(ext.badge_data, ''), '0')  as badge_data,\n");
        sql.append("       ( select cate.test_yn                                    \n");
        sql.append("           from smartux.pt_ux_hv_category cate                  \n");
        sql.append("          where cate.hv_category_id = cc.hv_category_id         \n");
        sql.append("       )                         as test_yn,                    \n");
        sql.append("       cc.content_order          as content_order,              \n");
        sql.append("       cont.start_dt             as start_dt,                   \n");
        sql.append("       cont.end_dt               as end_dt,                     \n");
        sql.append("       cont.hv_ui_type           as ui_type                     \n");
        sql.append("  from smartux.pt_ux_hv_cont cont                               \n");
        sql.append(" inner join smartux.pt_ux_hv_cate_content cc                    \n");
        sql.append("    on cont.content_id = cc.content_id                          \n");
        sql.append("   and cont.content_type in ('V','N','L','M')                   \n");
        sql.append("   and cc.del_yn = 'N'                                          \n");
        if(StringUtils.isNotEmpty(param.getCategoryId())) {
            sql.append("          and cc.hv_category_id = :categoryId                   \n");
        }
        sql.append("  left outer join smartux.pt_ux_hv_cont_ext ext                 \n");
        sql.append("    on cont.content_id = ext.content_id                         \n");
        sql.append("  left outer join smartux.pt_ux_hv_site site                    \n");
        sql.append("    on ext.site_id = site.site_id                               \n");
        sql.append(" order by parent_cate, content_order, reg_date desc             \n");

        Query query = em.createNativeQuery(sql.toString());
        if(StringUtils.isNotEmpty(param.getCategoryId())) {
            query.setParameter("categoryId", param.getCategoryId());
        }
        return super.convertList(query, NewHotVodInfoDto.class);
    }

}
