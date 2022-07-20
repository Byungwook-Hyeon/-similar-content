package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AdvertisementMetaJpaEmRepository extends AbstractJpaEmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AdvertisementMetaDto> getAdvertisementLiveMeta(final AdvertisementMetaRequestDto request) {

        if ("N".equals(request.getScreenType())) {
            return getMobileAdvertisementLiveMeta(request);
        } else {
            return getIptvAdvertisementLiveMeta(request);
        }
    }

    private List<AdvertisementMetaDto> getIptvAdvertisementLiveMeta(final AdvertisementMetaRequestDto request) {

        final StringBuilder buffer = new StringBuilder(
                "/* Contents.AdvertisementMetaJpaEmRepository.getIptvAdvertisementLiveMeta.01 */ " +
                "SELECT A.ADS_NO AS adsNo, A.ADS_ID as adsId, A.ADS_TYPE AS adsType, A.TITLE, A.LINK_URL AS linkUrl, " +
                "       A.SAVE_FILE_NAME AS saveFileName, A.SAVE_FILE_NAME2 AS saveFileName2 " +
                "  FROM UXUSER.PT_HDTV_ADS_INFO_IPTV A " +
                "  JOIN UXUSER.PT_HDTV_ADS_MASTER_IPTV B " +
                "    ON A.ADS_ID = B.ADS_ID " +
                " WHERE A.LIVE_YN = 'Y' " +
                "   AND B.SERVICE_TYPE = 'I' ");
        if (request.getAdsId() == null) {
            buffer.append("   AND A.ADS_NO IN (:adsNo) ");
        } else {
            buffer.append("   AND A.ADS_ID IN (:adsId) " +
                    " ORDER BY ADS_ORDER ");
        }

        final Query query = entityManager.createNativeQuery(buffer.toString());
        if (request.getAdsId() == null) {
            query.setParameter("adsNo", request.getAdsNo());
        } else {
            query.setParameter("adsId", request.getAdsId());
        }
        return convertList(query, AdvertisementMetaDto.class);
    }

    private List<AdvertisementMetaDto> getMobileAdvertisementLiveMeta(final AdvertisementMetaRequestDto request) {

        final StringBuilder buffer = new StringBuilder(
                "/* Contents.AdvertisementMetaJpaEmRepository.getMobileAdvertisementLiveMeta.01 */ " +
                "SELECT A.ADS_NO AS adsNo, A.ADS_ID as adsId, A.ADS_TYPE AS adsType, A.TITLE, A.LINK_URL AS linkUrl, " +
                "       A.SAVE_FILE_NAME AS saveFileName, A.SAVE_FILE_NAME2 AS saveFileName2, A.ETC, A.ETC2, A.ETC3, " +
                "       A.ETC4, A.MENU_UI_TYPE AS menuUiType " +
                "  FROM SMARTUX.PT_HDTV_ADS_INFO A " +
                "  JOIN SMARTUX.PT_HDTV_ADS_MASTER B " +
                "    ON A.ADS_ID = B.ADS_ID " +
                " WHERE A.LIVE_YN = 'Y' " +
                "   AND (B.SERVICE_TYPE IS NULL OR B.SERVICE_TYPE <> 'I') ");
        if (request.getAdsId() == null) {
            buffer.append("   AND A.ADS_NO IN (:adsNo) ");
        } else {
            buffer.append("   AND A.ADS_ID IN (:adsId) " +
                    " ORDER BY ADS_ORDER ");
        }

        final Query query = entityManager.createNativeQuery(buffer.toString());
        if (request.getAdsId() == null) {
            query.setParameter("adsNo", request.getAdsNo());
        } else {
            query.setParameter("adsId", request.getAdsId());
        }
        return convertList(query, AdvertisementMetaDto.class);
    }
}
