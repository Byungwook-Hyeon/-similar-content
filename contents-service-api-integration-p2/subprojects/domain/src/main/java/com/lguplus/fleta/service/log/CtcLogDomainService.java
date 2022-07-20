package com.lguplus.fleta.service.log;

import com.lguplus.fleta.client.SftpClient;
import com.lguplus.fleta.config.CtcConfigProperties.RemoteProperties;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.repository.log.CtcLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CtcLogDomainService {

    private static final String SERVICE_TYPE_FIELD = "SERVICE_TYPE";
    private static final String REGISTERER_FIELD = "REG_ID";
    private static final String REGISTER_DATE_FIELD = "REG_DT";
    private static final String MODIFIER_FIELD = "MOD_ID";
    private static final String MODIFY_DATE_FIELD = "MOD_DT";
    private static final String DELETE_FIELD = "DEL_YN";

    @Value("${ctc.local.path}")
    private String localPath;

    private final CtcLogRepository ctcLogRepository;
    private final SftpClient ctcClient;

    public File getUxHotvodCategoryFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_UX_HV_CATEGORY", localPath)) {
            ctcLogRepository.findAllUxHotvodCategory().forEach(e ->
                    writer.write("HV_CATEGORY_ID=" + e.getCategoryId() +
                            "|PARENT_ID=" + e.getParentCategoryId() +
                            "|CATEGORY_INFO=" + e.getCategoryInfo() +
                            "|CATEGORY_NAME=" + e.getCategoryName() +
                            "|CATEGORY_ORDER=" + e.getCategoryOrder() +
                            "|TEST_YN=" + (e.isTest() ? "Y" : "N") +
                            "|UNIFY_SEARCH_YN=" + (e.isUnifySearch() ? "Y" : "N") +
                            "|" + DELETE_FIELD + "=" + (e.isDelete() ? "Y" : "N") +
                            "|" + REGISTER_DATE_FIELD + "=" + formatDateTime(e.getRegisterDate()) +
                            "|" + REGISTERER_FIELD + "=" + e.getRegisterer() +
                            "|" + MODIFY_DATE_FIELD + "=" + formatDateTime(e.getModifyDate()) +
                            "|" + MODIFIER_FIELD + "=" + e.getModifier() +
                            "|CATEGORY_IMG=" + e.getCategoryImage() +
                            "|CATEGORY_IMG_TV=" + e.getCategoryTvImage() +
                            "|BADGE_DATA=" + e.getBadgeData() +
                            "|" + SERVICE_TYPE_FIELD + "=" + e.getServiceType() +
                            "|MULTI_SERVICE_TYPE=" + e.getMultiServiceType()));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CATEGORY'")
    public LocalDateTime getLastUxHotvodCategoryTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CATEGORY'")
    public LocalDateTime transferUxHotvodCategory(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getUxHotvodCategoryPath());
    }

    public File getUxHotvodCategoryContentFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_UX_HV_CATE_CONTENT", localPath)) {
            ctcLogRepository.findAllUxHotvodCategoryContent().forEach(e ->
                    writer.write("HV_CATEGORY_ID=" + e.getCategoryId() +
                            "|CONTENT_ID=" + e.getContentId() +
                            "|CONTENT_ORDER=" + e.getContentOrder() +
                            "|" + DELETE_FIELD + "=" + (e.isDelete() ? "Y" : "N")));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CATE_CONTENT'")
    public LocalDateTime getLastUxHotvodCategoryContentTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CATE_CONTENT'")
    public LocalDateTime transferUxHotvodCategoryContent(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getUxHotvodCategoryContentPath());
    }

    public File getUxHotvodContentFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_UX_HV_CONT", localPath)) {
            ctcLogRepository.findAllUxHotvodContent().forEach(e ->
                    writer.write("CONTENT_ID=" + e.getContentId() +
                            "|CONTENT_NAME=" + e.getContentName() +
                            "|CONTENT_TYPE=" + e.getContentType() +
                            "|CONTENT_INFO=" + e.getContentInfo() +
                            "|CONTENT_IMG=" + e.getContentImage() +
                            "|CONTENT_URL=" + e.getContentUrl() +
                            "|CONTENT_IMG_TV=" + e.getContentTvImage() +
                            "|DURATION=" + e.getDuration() +
                            "|HIT_CNT=" + e.getHitCount() +
                            "|" + REGISTER_DATE_FIELD + "=" + formatDateTime(e.getRegisterDate()) +
                            "|" + REGISTERER_FIELD + "=" + e.getRegisterer() +
                            "|" + MODIFY_DATE_FIELD + "=" + formatDateTime(e.getModifyDate()) +
                            "|" + MODIFIER_FIELD + "=" + e.getModifier() +
                            "|START_DT=" + formatDateTime(e.getStartDate()) +
                            "|END_DT=" + formatDateTime(e.getEndDate()) +
                            "|HV_UI_TYPE=" + e.getUiType()));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CONT'")
    public LocalDateTime getLastUxHotvodContentTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CONT'")
    public LocalDateTime transferUxHotvodContent(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getUxHotvodContentPath());
    }

    public File getUxHotvodContentExtensionFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_UX_HV_CONT_EXT", localPath)) {
            ctcLogRepository.findAllUxHotvodContentExtension().forEach(e ->
                    writer.write("CONTENT_ID=" + e.getContentId() +
                            "|ALBUM_ID=" + e.getAlbumId() +
                            "|CATEGORY_ID=" + e.getCategoryId() +
                            "|START_TIME=" + formatDateTime(e.getStartTime()) +
                            "|END_TIME=" + formatDateTime(e.getEndTime()) +
                            "|SITE_ID=" + e.getSiteId() +
                            "|BADGE_DATA=" + e.getBadgeData()));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CONT_EXT'")
    public LocalDateTime getLastUxHotvodContentExtensionTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_CONT_EXT'")
    public LocalDateTime transferUxHotvodContentExtension(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getUxHotvodContentExtensionPath());
    }

    public File getUxHotvodHitcountLogFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_UX_HV_HITCNT_LOG", localPath)) {
            ctcLogRepository.streamAllUxHotvodHitcountLog().forEach(e ->
                    writer.write("HITCNT_YEAR=" + e.getYear() +
                            "|HITCNT_MONTH=" + e.getMonth() +
                            "|HITCNT_DAY=" + e.getDay() +
                            "|CONTENT_ID=" + e.getContentId() +
                            "|HIT_CNT=" + e.getHitCount() +
                            "|" + REGISTER_DATE_FIELD + "=" + formatDateTime(e.getRegisterDate()) +
                            "|" + MODIFY_DATE_FIELD + "=" + formatDateTime(e.getModifyDate())));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_HITCNT_LOG'")
    public LocalDateTime getLastUxHotvodHitcountLogTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_HITCNT_LOG'")
    public LocalDateTime transferUxHotvodHitcountLog(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getUxHotvodHitcountLogPath());
    }

    public File getUxHotvodSiteFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_UX_HV_SITE", localPath)) {
            ctcLogRepository.findAllUxHotvodSite().forEach(e ->
                    writer.write("SITE_ID=" + e.getSiteId() +
                            "|SITE_NAME=" + e.getSiteName() +
                            "|SITE_URL=" + e.getSiteUrl() +
                            "|SITE_IMG=" + e.getSiteImage() +
                            "|SITE_IMG_TV=" + e.getSiteTvImage() +
                            "|" + REGISTER_DATE_FIELD + "=" + formatDateTime(e.getRegisterDate()) +
                            "|" + REGISTERER_FIELD + "=" + e.getRegisterer() +
                            "|" + MODIFY_DATE_FIELD + "=" + formatDateTime(e.getModifyDate()) +
                            "|" + MODIFIER_FIELD + "=" + e.getModifier() +
                            "|" + DELETE_FIELD + "=" + (e.isDelete() ? "Y" : "N")));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_SITE'")
    public LocalDateTime getLastUxHotvodSiteTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.UX_HV_SITE'")
    public LocalDateTime transferUxHotvodSite(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getUxHotvodSitePath());
    }

    public File getHdtvAdvertisementInfoFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_HDTV_ADS_INFO", localPath)) {
            ctcLogRepository.findAllHdtvAdvertisementInfo().forEach(e ->
                    writer.write("ADS_NO=" + e.getAdvertisementNumber() +
                            "|TITLE=" + e.getTitle() +
                            "|ROL_TIME=" + e.getRollingTime() +
                            "|ADS_TYPE=" + e.getAdvertisementType() +
                            "|FILE_NAME=" + e.getFilename() +
                            "|SAVE_FILE_NAME=" + e.getSaveFilename() +
                            "|LINK_URL=" + e.getLinkUrl() +
                            "|S_TIME=" + formatDateTime(e.getStartTime()) +
                            "|E_TIME=" + formatDateTime(e.getEndTime()) +
                            "|R_TIME=" + formatDateTime(e.getRegisterTime()) +
                            "|R_ID=" + e.getRegisterer() +
                            "|LIVE_YN=" + (e.isLive() ? "Y" : "N") +
                            "|GRADE=" + e.getGrade() +
                            "|EV_STAT_ID=" + e.getEventStatisticsId() +
                            "|ADS_ID=" + e.getAdvertisementId() +
                            "|BG_COLOR=" + e.getBackgroundColor() +
                            "|BG_FILE_NAME_V=" + e.getPortraitBackgroundFilename() +
                            "|BG_SAVE_FILE_NAME_V=" + e.getPortraitBackgroundSaveFilename() +
                            "|BG_FILE_NAME_H=" + e.getLandscapeBackgroundFilename() +
                            "|BG_SAVE_FILE_NAME_H=" + e.getLandscapeBackgroundSaveFilename() +
                            "|ADS_ORDER=" + e.getAdvertisementOrder() +
                            "|ADS_DATE_TYPE=" + e.getAdvertisementDateType() +
                            "|FILE_NAME2=" + e.getFilename2() +
                            "|SAVE_FILE_NAME2=" + e.getSaveFilename2() +
                            "|PRODUCT_CODE=" + e.getProductCode() +
                            "|CHARGE_TYPE=" + e.getChargeType() +
                            "|DISPLAY_SERVICE=" + e.getDisplayService() +
                            "|ETC=" + e.getEtc() +
                            "|ETC2=" + e.getEtc2() +
                            "|OS_GB=" + e.getOsGubun() +
                            "|LINK_SERVICE=" + e.getLinkService()));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_INFO'")
    public LocalDateTime getLastHdtvAdvertisementInfoTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_INFO'")
    public LocalDateTime transferHdtvAdvertisementInfo(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getHdtvAdvertisementInfoPath());
    }

    public File getHdtvAdvertisementInfoLogFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_HDTV_ADS_INFO_LOG", localPath)) {
            ctcLogRepository.streamAllHdtvAdvertisementInfoLog().forEach(e ->
                    writer.write("ADS_NO=" + e.getAdvertisementNumber() +
                            "|TITLE=" + e.getTitle() +
                            "|ROL_TIME=" + e.getRollingTime() +
                            "|ADS_TYPE=" + e.getAdvertisementType() +
                            "|FILE_NAME=" + e.getFilename() +
                            "|SAVE_FILE_NAME=" + e.getSaveFilename() +
                            "|LINK_URL=" + e.getLinkUrl() +
                            "|S_TIME=" + formatDateTime(e.getStartTime()) +
                            "|E_TIME=" + formatDateTime(e.getEndTime()) +
                            "|R_ID=" + e.getRegisterer() +
                            "|R_TIME=" + formatDateTime(e.getRegisterTime()) +
                            "|LIVE_YN=" + (e.isLive() ? "Y" : "N") +
                            "|GRADE=" + e.getGrade() +
                            "|EV_STAT_ID=" + e.getEventStatisticsId() +
                            "|ACT_DT=" + formatDateTime(e.getActionDate()) +
                            "|ACT_ID=" + e.getActor() +
                            "|ACT_IP=" + e.getActorIp() +
                            "|ACT_GBN=" + e.getActionGubun() +
                            "|ADS_ID=" + e.getAdvertisementId() +
                            "|BG_COLOR=" + e.getBackgroundColor() +
                            "|BG_FILE_NAME_V=" + e.getPortraitBackgroundFilename() +
                            "|BG_SAVE_FILE_NAME_V=" + e.getPortraitBackgroundSaveFilename() +
                            "|BG_FILE_NAME_H=" + e.getLandscapeBackgroundFilename() +
                            "|BG_SAVE_FILE_NAME_H=" + e.getLandscapeBackgroundSaveFilename() +
                            "|ADS_ORDER=" + e.getAdvertisementOrder() +
                            "|ADS_DATE_TYPE=" + e.getAdvertisementDateType() +
                            "|FILE_NAME2=" + e.getFilename2() +
                            "|SAVE_FILE_NAME2=" + e.getSaveFilename2() +
                            "|PRODUCT_CODE=" + e.getProductCode() +
                            "|CHARGE_TYPE=" + e.getChargeType() +
                            "|DISPLAY_SERVICE=" + e.getDisplayService() +
                            "|ETC=" + e.getEtc() +
                            "|ETC2=" + e.getEtc2() +
                            "|OS_GB=" + e.getOsGubun() +
                            "|LINK_SERVICE=" + e.getLinkService()));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_INFO_LOG'")
    public LocalDateTime getLastHdtvAdvertisementInfoLogTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_INFO_LOG'")
    public LocalDateTime transferHdtvAdvertisementInfoLog(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getHdtvAdvertisementInfoLogPath());
    }

    public File getHdtvAdvertisementMasterFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_HDTV_ADS_MASTER", localPath)) {
            ctcLogRepository.findAllHdtvAdvertisementMaster().forEach(e ->
                    writer.write("ADS_ID=" + e.getAdvertisementId() +
                            "|ADS_NM=" + e.getAdvertisementName() +
                            "|LIVE_CNT=" + e.getLiveCount() +
                            "|" + REGISTER_DATE_FIELD + "=" + formatDateTime(e.getRegisterDate()) +
                            "|" + MODIFY_DATE_FIELD + "=" + formatDateTime(e.getModifyDate()) +
                            "|" + REGISTERER_FIELD + "=" + e.getRegisterer() +
                            "|" + SERVICE_TYPE_FIELD + "=" + e.getServiceType() +
                            "|IMG_RATIO=" + e.getImageRatio()));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_MASTER'")
    public LocalDateTime getLastHdtvAdvertisementMasterTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_MASTER'")
    public LocalDateTime transferHdtvAdvertisementMaster(final File localFile, final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getHdtvAdvertisementMasterPath());
    }

    public File getHdtvAdvertisementMasterLogFile() throws IOException {

        try (final CtcLogWriter writer = new CtcLogWriter("PT_HDTV_ADS_MASTER_LOG", localPath)) {
            ctcLogRepository.streamAllHdtvAdvertisementMasterLog().forEach(e ->
                    writer.write("ADS_ID=" + e.getAdvertisementId() +
                            "|ADS_NM=" + e.getAdvertisementName() +
                            "|LIVE_CNT=" + e.getLiveCount() +
                            "|" + REGISTER_DATE_FIELD + "=" + formatDateTime(e.getRegisterDate()) +
                            "|ACT_DT=" + formatDateTime(e.getActionDate()) +
                            "|ACT_ID=" + e.getActor() +
                            "|ACT_IP=" + e.getActorIp() +
                            "|ACT_GB=" + e.getActionGubun() +
                            "|" + MODIFY_DATE_FIELD + "=" + formatDateTime(e.getModifyDate()) +
                            "|" + REGISTERER_FIELD + "=" + e.getRegisterer() +
                            "|" + SERVICE_TYPE_FIELD + "=" + e.getServiceType()));
            return writer.createFile();
        }
    }

    @Cacheable(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_MASTER_LOG'")
    public LocalDateTime getLastHdtvAdvertisementMasterLogTransferTime(final RemoteProperties remoteProperties) {

        return getOldestTime();
    }

    @CachePut(cacheNames = CacheName.CTC_LOG, key="#remoteProperties.name + '.HDTV_ADS_MASTER_LOG'")
    public LocalDateTime transferHdtvAdvertisementMasterLog(final File localFile,
                                                            final RemoteProperties remoteProperties)
            throws IOException {

        return transferData(localFile, remoteProperties, remoteProperties.getHdtvAdvertisementMasterLogPath());
    }

    private LocalDateTime getOldestTime() {

        return LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0));
    }

    private LocalDateTime transferData(final File localFile, final RemoteProperties remoteProperties,
                                       final String remotePath) throws IOException {

        ctcClient.transferFile(localFile, remoteProperties,
                remotePath.replaceAll("/+", "/").replaceFirst("/$", ""));
        return LocalDateTime.now();
    }

    private String formatDateTime(final LocalDateTime dateTime) {

        if (dateTime == null) {
            return null;
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ".0";
        }
    }
}
