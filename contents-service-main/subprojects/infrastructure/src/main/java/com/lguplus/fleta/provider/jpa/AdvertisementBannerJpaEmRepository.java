package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AdvertisementBannerJpaEmRepository extends AbstractJpaEmRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<AdsLiveInfoDto> getIptvAdvertisementBannerList(String advertisementId, String imgResizeServer) {
        StringBuilder sql = new StringBuilder();
        sql.append("/* SVC.Contents.AdvertisementBannerJpaRepository.getIptvAdvertisementBannerList.01 */ \n");
        sql.append("select B.ADS_NO AS advertisementNo                                                    \n");
        sql.append("     , B.TITLE                                                                        \n");
        sql.append("     , case when B.SAVE_FILE_NAME IS NOT NULL                                         \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.SAVE_FILE_NAME)                   \n");
        sql.append("            else NULL                                                                 \n");
        sql.append("       end AS saveFileName                                                            \n");
        sql.append("     , B.ADS_TYPE AS advertisementType                                                \n");
        sql.append("     , CAST(B.ROL_TIME AS INTEGER) AS duration                                        \n");
        sql.append("     , B.LINK_URL AS advertisementUrl                                                 \n");
        sql.append("     , B.GRADE                                                                        \n");
        sql.append("     , B.EV_STAT_ID AS eventId                                                        \n");
        sql.append("     , TO_CHAR(B.S_TIME,'YYYYMMDDHH24MISS') AS startTime                              \n");
        sql.append("     , TO_CHAR(B.E_TIME,'YYYYMMDDHH24MISS') AS expiredTime                            \n");
        sql.append("     , B.ADS_ID AS advertisementId                                                    \n");
        sql.append("     , B.ADS_ORDER AS order                                                           \n");
        sql.append("     , B.BG_COLOR AS backgroundColor                                                  \n");
        sql.append("     , case when B.BG_SAVE_FILE_NAME_V IS NOT NULL                                    \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.BG_SAVE_FILE_NAME_V)              \n");
        sql.append("            else NULL                                                                 \n");
        sql.append("       end AS backgroundSaveFileNameVertical                                          \n");
        sql.append("     , case when B.BG_SAVE_FILE_NAME_H IS NOT NULL                                    \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.BG_SAVE_FILE_NAME_H)              \n");
        sql.append("            else NULL                                                                \n");
        sql.append("        end AS backgroundSaveFileNameHorizontal                                       \n");
        sql.append("     , B.ADS_DATE_TYPE AS dateType                                                    \n");
        sql.append("     , case when B.SAVE_FILE_NAME2 IS NOT NULL                                        \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.SAVE_FILE_NAME2)                  \n");
        sql.append("            else NULL                                                                \n");
        sql.append("       end AS saveFileName2                                                           \n");
        sql.append("     , B.ETC                                                                          \n");
        sql.append("     , B.ETC2                                                                         \n");
        sql.append("     , case when B.ADS_TYPE = '2'then SPLIT_PART(B.LINK_URL, '||', 2)                 \n");
        sql.append("            else null                                                                 \n");
        sql.append("        end as albumId                                                                \n");
        sql.append("from UXUSER.PT_HDTV_ADS_MASTER_IPTV A                                                 \n");
        sql.append("   , UXUSER.PT_HDTV_ADS_INFO_IPTV B                                                   \n");
        sql.append("where 1=1                                                                             \n");
        if(StringUtils.isNotEmpty(advertisementId)) {
            sql.append("  and A.ADS_ID = :advertisementId                                                 \n");
        }
        sql.append("  and A.ADS_ID = B.ADS_ID                                                             \n");
        sql.append("  and A.SERVICE_TYPE = 'I'                                                            \n");
        sql.append("  and B.LIVE_YN = 'Y'                                                                 \n");
        sql.append("  and B.E_TIME > NOW()                                                                \n");
        sql.append("order by B.ADS_ORDER, B.R_TIME desc                                                   \n");

        Query query = em.createNativeQuery(sql.toString());
        if(StringUtils.isNotEmpty(advertisementId)) {
            query.setParameter("advertisementId", advertisementId);
        }
        query.setParameter("imgResizeServer", imgResizeServer);

        return convertList(query, AdsLiveInfoDto.class);
    }

    @SuppressWarnings("unchecked")
    public List<AdsLiveInfoDto> getMobileAdvertisementBannerList(String advertisementId, String imgResizeServer) {
        StringBuilder sql = new StringBuilder();
        sql.append("/* SVC.Contents.AdvertisementBannerJpaRepository.getMobileAdvertisementBannerList.01 */ \n");
        sql.append("select B.ADS_NO AS advertisementNo                                                      \n");
        sql.append("     , B.TITLE                                                                          \n");
        sql.append("     , case when B.SAVE_FILE_NAME IS NOT NULL                                           \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.SAVE_FILE_NAME)                     \n");
        sql.append("            else NULL                                                                   \n");
        sql.append("       end AS saveFileName                                                              \n");
        sql.append("     , B.ADS_TYPE AS advertisementType                                                  \n");
        sql.append("     , CAST(B.ROL_TIME AS INTEGER) AS duration                                          \n");
        sql.append("     , B.LINK_URL AS advertisementUrl                                                   \n");
        sql.append("     , B.GRADE                                                                          \n");
        sql.append("     , B.EV_STAT_ID AS eventId                                                          \n");
        sql.append("     , TO_CHAR(B.S_TIME,'YYYYMMDDHH24MISS') AS startTime                                \n");
        sql.append("     , TO_CHAR(B.E_TIME,'YYYYMMDDHH24MISS') AS expiredTime                              \n");
        sql.append("     , B.ADS_ID AS advertisementId                                                      \n");
        sql.append("     , B.ADS_ORDER AS order                                                             \n");
        sql.append("     , B.BG_COLOR AS backgroundColor                                                    \n");
        sql.append("     , case when B.BG_SAVE_FILE_NAME_V IS NOT NULL                                      \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.BG_SAVE_FILE_NAME_V)                \n");
        sql.append("            else NULL                                                                   \n");
        sql.append("       end AS backgroundSaveFileNameVertical                                            \n");
        sql.append("     , case when B.BG_SAVE_FILE_NAME_H IS NOT NULL                                      \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.BG_SAVE_FILE_NAME_H)                \n");
        sql.append("            else NULL                                                                  \n");
        sql.append("        end AS backgroundSaveFileNameHorizontal                                         \n");
        sql.append("     , B.ADS_DATE_TYPE AS dateType                                                      \n");
        sql.append("     , case when B.SAVE_FILE_NAME2 IS NOT NULL                                          \n");
        sql.append("            then concat(:imgResizeServer, 'ads/', B.SAVE_FILE_NAME2)                    \n");
        sql.append("            else NULL                                                                  \n");
        sql.append("       end AS saveFileName2                                                             \n");
        sql.append("     , B.ETC                                                                            \n");
        sql.append("     , B.ETC2                                                                           \n");
        sql.append("     , case when B.ADS_TYPE = '2'then SPLIT_PART(B.LINK_URL, '||', 2)                   \n");
        sql.append("            else null                                                                   \n");
        sql.append("        end as albumId                                                                  \n");
        sql.append("from SMARTUX.PT_HDTV_ADS_MASTER A                                                       \n");
        sql.append("   , SMARTUX.PT_HDTV_ADS_INFO B                                                         \n");
        sql.append("where 1=1                                                                               \n");
        if(StringUtils.isNotEmpty(advertisementId)) {
            sql.append("  and A.ADS_ID = :advertisementId                                                   \n");
        }
        sql.append("  and A.ADS_ID = B.ADS_ID                                                               \n");
        sql.append("  and (A.SERVICE_TYPE IS NULL OR A.SERVICE_TYPE <> 'I')                                 \n");
        sql.append("  and B.LIVE_YN = 'Y'                                                                   \n");
        sql.append("  and B.E_TIME > NOW()                                                                  \n");
        sql.append("order by B.ADS_ORDER, B.R_TIME desc                                                     \n");

        Query query = em.createNativeQuery(sql.toString());
        if(StringUtils.isNotEmpty(advertisementId)) {
            query.setParameter("advertisementId", advertisementId);
        }
        query.setParameter("imgResizeServer", imgResizeServer);

        return convertList(query, AdsLiveInfoDto.class);
    }

}
