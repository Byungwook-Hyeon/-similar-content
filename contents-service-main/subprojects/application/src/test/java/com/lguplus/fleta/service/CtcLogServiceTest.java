package com.lguplus.fleta.service;

import com.lguplus.fleta.config.CtcConfigProperties;
import com.lguplus.fleta.config.CtcConfigProperties.RemotePathProperties;
import com.lguplus.fleta.config.CtcConfigProperties.RemoteProperties;
import com.lguplus.fleta.service.log.CtcLogDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
class CtcLogServiceTest {

    @Mock
    private CtcConfigProperties ctcConfigProperties;

    @Mock
    private CtcLogDomainService ctcLogDomainService;

    @InjectMocks
    private CtcLogService ctcLogService;

    @BeforeEach
    void setUp() {

        final RemotePathProperties remotePathProperties = new RemotePathProperties();
        remotePathProperties.setUxHotvodCategory("ctc/UX_HV_CATEGORY");
        remotePathProperties.setUxHotvodCategoryContent("ctc/UX_HV_CATE_CONTENT");
        remotePathProperties.setUxHotvodContent("ctc/UX_HV_CONT");
        remotePathProperties.setUxHotvodContentExtension("ctc/UX_HV_CONT_EXT");
        remotePathProperties.setUxHotvodHitcountLog("ctc/UX_HV_HITCNT_LOG");
        remotePathProperties.setUxHotvodSite("ctc/UX_HV_SITE");
        remotePathProperties.setHdtvAdvertisementInfo("ctc/HDTV_ADS_INFO");
        remotePathProperties.setHdtvAdvertisementInfoLog("ctc/HDTV_ADS_INFO_LOG");
        remotePathProperties.setHdtvAdvertisementMaster("ctc/HDTV_ADS_MASTER");
        remotePathProperties.setHdtvAdvertisementMasterLog("ctc/HDTV_ADS_MASTER_LOG");
        final RemoteProperties remoteProperties = new RemoteProperties();
        remoteProperties.setPath(remotePathProperties);
        doReturn(List.of(remoteProperties)).when(ctcConfigProperties).getRemote();

        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodCategoryTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodCategoryContentTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodContentTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodContentExtensionTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodHitcountLogTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodSiteTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementInfoTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementInfoLogTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementMasterTransferTime(any());
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementMasterLogTransferTime(any());
    }

    @Test
    void test_transfer() {

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_NullRemoteProperties() throws IOException {

        doReturn(null).when(ctcConfigProperties).getRemote();

        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategory(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategoryContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContentExtension(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodHitcountLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodSite(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfo(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfoLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMaster(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMasterLog(any(), any());

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_NoRemotePath() throws IOException {

        final RemotePathProperties remotePathProperties = new RemotePathProperties();
        final RemoteProperties remoteProperties = new RemoteProperties();
        remoteProperties.setPath(remotePathProperties);
        doReturn(List.of(remoteProperties)).when(ctcConfigProperties).getRemote();

        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategory(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategoryContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContentExtension(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodHitcountLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodSite(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfo(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfoLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMaster(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMasterLog(any(), any());

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_PartialNoRemotePath() throws IOException {

        final RemotePathProperties remotePathProperties1 = new RemotePathProperties();
        remotePathProperties1.setUxHotvodCategory("ctc/UX_HV_CATEGORY");
        remotePathProperties1.setUxHotvodCategoryContent("ctc/UX_HV_CATE_CONTENT");
        remotePathProperties1.setUxHotvodContent("ctc/UX_HV_CONT");
        remotePathProperties1.setUxHotvodContentExtension("ctc/UX_HV_CONT_EXT");
        remotePathProperties1.setUxHotvodHitcountLog("ctc/UX_HV_HITCNT_LOG");
        remotePathProperties1.setUxHotvodSite("ctc/UX_HV_SITE");
        remotePathProperties1.setHdtvAdvertisementInfo("ctc/HDTV_ADS_INFO");
        remotePathProperties1.setHdtvAdvertisementInfoLog("ctc/HDTV_ADS_INFO_LOG");
        remotePathProperties1.setHdtvAdvertisementMaster("ctc/HDTV_ADS_MASTER");
        remotePathProperties1.setHdtvAdvertisementMasterLog("ctc/HDTV_ADS_MASTER_LOG");
        final RemoteProperties remoteProperties1 = new RemoteProperties();
        remoteProperties1.setPath(remotePathProperties1);
        final RemotePathProperties remotePathProperties2 = new RemotePathProperties();
        final RemoteProperties remoteProperties2 = new RemoteProperties();
        remoteProperties2.setPath(remotePathProperties2);
        doReturn(List.of(remoteProperties1, remoteProperties2)).when(ctcConfigProperties).getRemote();

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_DisableRemoteProperties() throws IOException {

        final RemotePathProperties remotePathProperties = new RemotePathProperties();
        final RemoteProperties remoteProperties = new RemoteProperties();
        remoteProperties.setDisable(true);
        remoteProperties.setPath(remotePathProperties);
        doReturn(List.of(remoteProperties)).when(ctcConfigProperties).getRemote();

        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategory(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategoryContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContentExtension(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodHitcountLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodSite(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfo(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfoLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMaster(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMasterLog(any(), any());

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_PartialDisableRemoteProperties() throws IOException {

        final RemotePathProperties remotePathProperties1 = new RemotePathProperties();
        remotePathProperties1.setUxHotvodCategory("ctc/UX_HV_CATEGORY");
        remotePathProperties1.setUxHotvodCategoryContent("ctc/UX_HV_CATE_CONTENT");
        remotePathProperties1.setUxHotvodContent("ctc/UX_HV_CONT");
        remotePathProperties1.setUxHotvodContentExtension("ctc/UX_HV_CONT_EXT");
        remotePathProperties1.setUxHotvodHitcountLog("ctc/UX_HV_HITCNT_LOG");
        remotePathProperties1.setUxHotvodSite("ctc/UX_HV_SITE");
        remotePathProperties1.setHdtvAdvertisementInfo("ctc/HDTV_ADS_INFO");
        remotePathProperties1.setHdtvAdvertisementInfoLog("ctc/HDTV_ADS_INFO_LOG");
        remotePathProperties1.setHdtvAdvertisementMaster("ctc/HDTV_ADS_MASTER");
        remotePathProperties1.setHdtvAdvertisementMasterLog("ctc/HDTV_ADS_MASTER_LOG");
        final RemoteProperties remoteProperties1 = new RemoteProperties();
        remoteProperties1.setPath(remotePathProperties1);
        final RemotePathProperties remotePathProperties2 = new RemotePathProperties();
        final RemoteProperties remoteProperties2 = new RemoteProperties();
        remoteProperties2.setDisable(true);
        remoteProperties2.setPath(remotePathProperties2);
        doReturn(List.of(remoteProperties1, remoteProperties2)).when(ctcConfigProperties).getRemote();

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_AlreadyTransferred() throws IOException {

        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodCategoryTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodCategoryContentTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodContentTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodContentExtensionTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodHitcountLogTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodSiteTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementInfoTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementInfoLogTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementMasterTransferTime(any());
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementMasterLogTransferTime(any());

        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategory(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategoryContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContentExtension(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodHitcountLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodSite(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfo(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfoLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMaster(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMasterLog(any(), any());

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_AlreadyTransferredLastYear() throws IOException {

        final int dayOfYear = LocalDateTime.now().getDayOfYear();
        final LocalDateTime lastTransferTime = LocalDateTime.now()
                .minusYears(1)
                .withDayOfYear(dayOfYear);
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastUxHotvodCategoryTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastUxHotvodCategoryContentTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastUxHotvodContentTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastUxHotvodContentExtensionTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastUxHotvodHitcountLogTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastUxHotvodSiteTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastHdtvAdvertisementInfoTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastHdtvAdvertisementInfoLogTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastHdtvAdvertisementMasterTransferTime(any());
        doReturn(lastTransferTime).when(ctcLogDomainService).getLastHdtvAdvertisementMasterLogTransferTime(any());

        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategory(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategoryContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContentExtension(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodHitcountLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodSite(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfo(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfoLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMaster(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMasterLog(any(), any());

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_PartialAlreadyTransferred() throws IOException {

        final RemotePathProperties remotePathProperties = new RemotePathProperties();
        remotePathProperties.setUxHotvodCategory("ctc/UX_HV_CATEGORY");
        remotePathProperties.setUxHotvodCategoryContent("ctc/UX_HV_CATE_CONTENT");
        remotePathProperties.setUxHotvodContent("ctc/UX_HV_CONT");
        remotePathProperties.setUxHotvodContentExtension("ctc/UX_HV_CONT_EXT");
        remotePathProperties.setUxHotvodHitcountLog("ctc/UX_HV_HITCNT_LOG");
        remotePathProperties.setUxHotvodSite("ctc/UX_HV_SITE");
        remotePathProperties.setHdtvAdvertisementInfo("ctc/HDTV_ADS_INFO");
        remotePathProperties.setHdtvAdvertisementInfoLog("ctc/HDTV_ADS_INFO_LOG");
        remotePathProperties.setHdtvAdvertisementMaster("ctc/HDTV_ADS_MASTER");
        remotePathProperties.setHdtvAdvertisementMasterLog("ctc/HDTV_ADS_MASTER_LOG");
        final RemoteProperties remoteProperties1 = new RemoteProperties();
        remoteProperties1.setPath(remotePathProperties);
        final RemoteProperties remoteProperties2 = new RemoteProperties();
        remoteProperties2.setPath(remotePathProperties);
        doReturn(List.of(remoteProperties1, remoteProperties2)).when(ctcConfigProperties).getRemote();

        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodCategoryTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodCategoryTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodCategoryContentTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodCategoryContentTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodContentTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodContentTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodContentExtensionTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodContentExtensionTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodHitcountLogTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodHitcountLogTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastUxHotvodSiteTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastUxHotvodSiteTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementInfoTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementInfoTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementInfoLogTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementInfoLogTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementMasterTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementMasterTransferTime(remoteProperties1);
        doReturn(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.ofHours(0)))
                .when(ctcLogDomainService).getLastHdtvAdvertisementMasterLogTransferTime(remoteProperties2);
        doReturn(LocalDateTime.now()).when(ctcLogDomainService).getLastHdtvAdvertisementMasterLogTransferTime(remoteProperties1);

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_transfer_TransferFailed() throws IOException {

        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategory(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodCategoryContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContent(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodContentExtension(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodHitcountLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferUxHotvodSite(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfo(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementInfoLog(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMaster(any(), any());
        doThrow(new IOException()).when(ctcLogDomainService).transferHdtvAdvertisementMasterLog(any(), any());

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_deleteDataFile() throws IOException {

        final String temporaryPath = System.getProperty("java.io.tmpdir");
        final File uxHotvodCategoryFile = new File(temporaryPath, "PT_UX_HV_CATEGORY.tar.gz");
        uxHotvodCategoryFile.createNewFile();
        uxHotvodCategoryFile.deleteOnExit();
        doReturn(uxHotvodCategoryFile).when(ctcLogDomainService).getUxHotvodCategoryFile();

        final File uxHotvodCategoryContentFile = new File(temporaryPath, "PT_UX_HV_CATE_CONTENT.tar.gz");
        uxHotvodCategoryContentFile.createNewFile();
        uxHotvodCategoryContentFile.deleteOnExit();
        doReturn(uxHotvodCategoryContentFile).when(ctcLogDomainService).getUxHotvodCategoryContentFile();

        final File uxHotvodContentFile = new File(temporaryPath, "PT_UX_HV_CONT.tar.gz");
        uxHotvodContentFile.createNewFile();
        uxHotvodContentFile.deleteOnExit();
        doReturn(uxHotvodContentFile).when(ctcLogDomainService).getUxHotvodContentFile();

        final File uxHotvodContentExtensionFile = new File(temporaryPath, "PT_UX_HV_CONT_EXT.tar.gz");
        uxHotvodContentExtensionFile.createNewFile();
        uxHotvodContentExtensionFile.deleteOnExit();
        doReturn(uxHotvodContentExtensionFile).when(ctcLogDomainService).getUxHotvodContentExtensionFile();

        final File uxHotvodHitcountLogFile = new File(temporaryPath, "PT_UX_HV_HITCNT_LOG.tar.gz");
        uxHotvodHitcountLogFile.createNewFile();
        uxHotvodHitcountLogFile.deleteOnExit();
        doReturn(uxHotvodHitcountLogFile).when(ctcLogDomainService).getUxHotvodHitcountLogFile();

        final File uxHotvodSiteFile = new File(temporaryPath, "PT_UX_HV_SITE.tar.gz");
        uxHotvodSiteFile.createNewFile();
        uxHotvodSiteFile.deleteOnExit();
        doReturn(uxHotvodSiteFile).when(ctcLogDomainService).getUxHotvodSiteFile();

        final File hdtvAdvertisementInfoFile = new File(temporaryPath, "PT_HDTV_ADS_INFO.tar.gz");
        hdtvAdvertisementInfoFile.createNewFile();
        hdtvAdvertisementInfoFile.deleteOnExit();
        doReturn(hdtvAdvertisementInfoFile).when(ctcLogDomainService).getHdtvAdvertisementInfoFile();

        final File hdtvAdvertisementInfoLogFile = new File(temporaryPath, "PT_HDTV_ADS_INFO_LOG.tar.gz");
        hdtvAdvertisementInfoLogFile.createNewFile();
        hdtvAdvertisementInfoLogFile.deleteOnExit();
        doReturn(hdtvAdvertisementInfoLogFile).when(ctcLogDomainService).getHdtvAdvertisementInfoLogFile();

        final File hdtvAdvertisementMasterFile = new File(temporaryPath, "PT_HDTV_ADS_MASTER.tar.gz");
        hdtvAdvertisementMasterFile.createNewFile();
        hdtvAdvertisementMasterFile.deleteOnExit();
        doReturn(hdtvAdvertisementMasterFile).when(ctcLogDomainService).getHdtvAdvertisementMasterFile();

        final File hdtvAdvertisementMasterLogFile = new File(temporaryPath, "PT_HDTV_ADS_MASTER_LOG.tar.gz");
        hdtvAdvertisementMasterLogFile.createNewFile();
        hdtvAdvertisementMasterLogFile.deleteOnExit();
        doReturn(hdtvAdvertisementMasterLogFile).when(ctcLogDomainService).getHdtvAdvertisementMasterLogFile();

        assertDoesNotThrow(ctcLogService::transfer);
    }

    @Test
    void test_deleteDataFile_Fail() throws IOException {

        final File someFile = new File("some.tar.gz");
        doReturn(someFile).when(ctcLogDomainService).getUxHotvodCategoryFile();
        doReturn(someFile).when(ctcLogDomainService).getUxHotvodCategoryContentFile();
        doReturn(someFile).when(ctcLogDomainService).getUxHotvodContentFile();
        doReturn(someFile).when(ctcLogDomainService).getUxHotvodContentExtensionFile();
        doReturn(someFile).when(ctcLogDomainService).getUxHotvodHitcountLogFile();
        doReturn(someFile).when(ctcLogDomainService).getUxHotvodSiteFile();
        doReturn(someFile).when(ctcLogDomainService).getHdtvAdvertisementInfoFile();
        doReturn(someFile).when(ctcLogDomainService).getHdtvAdvertisementInfoLogFile();
        doReturn(someFile).when(ctcLogDomainService).getHdtvAdvertisementMasterFile();
        doReturn(someFile).when(ctcLogDomainService).getHdtvAdvertisementMasterLogFile();

        assertDoesNotThrow(ctcLogService::transfer);
    }
}
