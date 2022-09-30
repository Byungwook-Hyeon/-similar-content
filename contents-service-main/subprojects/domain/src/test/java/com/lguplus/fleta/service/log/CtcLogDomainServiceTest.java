package com.lguplus.fleta.service.log;

import com.lguplus.fleta.client.SftpClient;
import com.lguplus.fleta.config.CtcConfigProperties.RemotePathProperties;
import com.lguplus.fleta.config.CtcConfigProperties.RemoteProperties;
import com.lguplus.fleta.data.dto.HdtvAdvertisementInfoLogDto;
import com.lguplus.fleta.data.dto.HdtvAdvertisementMasterLogDto;
import com.lguplus.fleta.data.dto.UxHotvodHitcountLogDto;
import com.lguplus.fleta.data.entity.*;
import com.lguplus.fleta.repository.log.CtcLogRepository;
import com.lguplus.fleta.util.JunitTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class CtcLogDomainServiceTest {

    @Mock
    private CtcLogRepository ctcLogRepository;

    @Mock
    private SftpClient ctcClient;

    @InjectMocks
    private CtcLogDomainService ctcLogDomainService;

    @BeforeEach
    void setUp() {

        JunitTestUtils.setValue(ctcLogDomainService, "localPath", System.getProperty("java.io.tmpdir"));

        doReturn(List.of(UxHotvodCategory.builder()
                        .test(true)
                        .unifySearch(true)
                        .delete(true)
                        .build(),
                UxHotvodCategory.builder().build())).when(ctcLogRepository).findAllUxHotvodCategory();
        doReturn(List.of(UxHotvodCategoryContent.builder()
                        .delete(true)
                        .build(),
                UxHotvodCategoryContent.builder().build())).when(ctcLogRepository).findAllUxHotvodCategoryContent();
        doReturn(List.of(UxHotvodContent.builder().build())).when(ctcLogRepository).findAllUxHotvodContent();
        doReturn(List.of(UxHotvodContentExtension.builder().build())).when(ctcLogRepository).findAllUxHotvodContentExtension();
        doReturn(List.of(UxHotvodHitcountLogDto.builder().build()).stream()).when(ctcLogRepository).streamAllUxHotvodHitcountLog();
        doReturn(List.of(UxHotvodSite.builder()
                        .delete(true)
                        .build(),
                UxHotvodSite.builder().build())).when(ctcLogRepository).findAllUxHotvodSite();
        doReturn(List.of(HdtvAdvertisementInfo.builder()
                        .live(true)
                        .build(),
                HdtvAdvertisementInfo.builder().build())).when(ctcLogRepository).findAllHdtvAdvertisementInfo();
        doReturn(List.of(HdtvAdvertisementInfoLogDto.builder()
                        .live(true)
                        .build(),
                HdtvAdvertisementInfoLogDto.builder().build()).stream()).when(ctcLogRepository).streamAllHdtvAdvertisementInfoLog();
        doReturn(List.of(HdtvAdvertisementMaster.builder().build())).when(ctcLogRepository).findAllHdtvAdvertisementMaster();
        doReturn(List.of(HdtvAdvertisementMasterLogDto.builder().build()).stream()).when(ctcLogRepository).streamAllHdtvAdvertisementMasterLog();
    }

    @Test
    void test_getFile() throws IOException {

        final File uxHotvodCategoryFile = ctcLogDomainService.getUxHotvodCategoryFile();
        assertThat(uxHotvodCategoryFile).exists();
        uxHotvodCategoryFile.delete();

        final File uxHotvodCategoryContentFile = ctcLogDomainService.getUxHotvodCategoryContentFile();
        assertThat(uxHotvodCategoryContentFile).exists();
        uxHotvodCategoryContentFile.delete();

        final File uxHotvodContentFile = ctcLogDomainService.getUxHotvodContentFile();
        assertThat(uxHotvodContentFile).exists();
        uxHotvodContentFile.delete();

        final File uxHotvodContentExtensionFile = ctcLogDomainService.getUxHotvodContentExtensionFile();
        assertThat(uxHotvodContentExtensionFile).exists();
        uxHotvodContentExtensionFile.delete();

        final File uxHotvodHitcountLogFile = ctcLogDomainService.getUxHotvodHitcountLogFile();
        assertThat(uxHotvodHitcountLogFile).exists();
        uxHotvodHitcountLogFile.delete();

        final File uxHotvodSiteFile = ctcLogDomainService.getUxHotvodSiteFile();
        assertThat(uxHotvodSiteFile).exists();
        uxHotvodSiteFile.delete();

        final File hdtvAdvertisementInfoFile = ctcLogDomainService.getHdtvAdvertisementInfoFile();
        assertThat(hdtvAdvertisementInfoFile).exists();
        hdtvAdvertisementInfoFile.delete();

        final File hdtvAdvertisementInfoLogFile = ctcLogDomainService.getHdtvAdvertisementInfoLogFile();
        assertThat(hdtvAdvertisementInfoLogFile).exists();
        hdtvAdvertisementInfoLogFile.delete();

        final File hdtvAdvertisementMasterFile = ctcLogDomainService.getHdtvAdvertisementMasterFile();
        assertThat(hdtvAdvertisementMasterFile).exists();
        hdtvAdvertisementMasterFile.delete();

        final File hdtvAdvertisementMasterLogFile = ctcLogDomainService.getHdtvAdvertisementMasterLogFile();
        assertThat(hdtvAdvertisementMasterLogFile).exists();
        hdtvAdvertisementMasterLogFile.delete();
    }

    @Test
    void test_getLastTransferTime() {

        final LocalDateTime thisTime = LocalDateTime.now();
        final LocalDateTime lastUxHotvodCategoryTransferTime = ctcLogDomainService.getLastUxHotvodCategoryTransferTime(null);
        assertThat(lastUxHotvodCategoryTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastUxHotvodCategoryContentTransferTime = ctcLogDomainService.getLastUxHotvodCategoryContentTransferTime(null);
        assertThat(lastUxHotvodCategoryContentTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastUxHotvodContentTransferTime = ctcLogDomainService.getLastUxHotvodContentTransferTime(null);
        assertThat(lastUxHotvodContentTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastUxHotvodContentExtensionTransferTime = ctcLogDomainService.getLastUxHotvodContentExtensionTransferTime(null);
        assertThat(lastUxHotvodContentExtensionTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastUxHotvodHitcountLogTransferTime = ctcLogDomainService.getLastUxHotvodHitcountLogTransferTime(null);
        assertThat(lastUxHotvodHitcountLogTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastUxHotvodSiteTransferTime = ctcLogDomainService.getLastUxHotvodSiteTransferTime(null);
        assertThat(lastUxHotvodSiteTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastHdtvAdvertisementInfoTransferTime = ctcLogDomainService.getLastHdtvAdvertisementInfoTransferTime(null);
        assertThat(lastHdtvAdvertisementInfoTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastHdtvAdvertisementInfoLogTransferTime = ctcLogDomainService.getLastHdtvAdvertisementInfoLogTransferTime(null);
        assertThat(lastHdtvAdvertisementInfoLogTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastHdtvAdvertisementMasterTransferTime = ctcLogDomainService.getLastHdtvAdvertisementMasterTransferTime(null);
        assertThat(lastHdtvAdvertisementMasterTransferTime.getYear()).isLessThan(thisTime.getYear());
        final LocalDateTime lastHdtvAdvertisementMasterLogTransferTime = ctcLogDomainService.getLastHdtvAdvertisementMasterLogTransferTime(null);
        assertThat(lastHdtvAdvertisementMasterLogTransferTime.getYear()).isLessThan(thisTime.getYear());
    }

    @Test
    void test_transfer() {

        final RemotePathProperties remotePathProperties = new RemotePathProperties();
        remotePathProperties.setUxHotvodCategory("ctc/PT_UX_HV_CATEGORY");
        remotePathProperties.setUxHotvodCategoryContent("ctc/PT_UX_HV_CATE_CONTENT");
        remotePathProperties.setUxHotvodContent("ctc/PT_UX_HV_CONT");
        remotePathProperties.setUxHotvodContentExtension("ctc/PT_UX_HV_CONT_EXT");
        remotePathProperties.setUxHotvodHitcountLog("ctc/PT_UX_HV_HITCNT_LOG");
        remotePathProperties.setUxHotvodSite("ctc/PT_UX_HV_SITE");
        remotePathProperties.setHdtvAdvertisementInfo("ctc/PT_HDTV_ADS_INFO");
        remotePathProperties.setHdtvAdvertisementInfoLog("ctc/PT_HDTV_ADS_INFO_LOG");
        remotePathProperties.setHdtvAdvertisementMaster("ctc/PT_HDTV_ADS_MASTER");
        remotePathProperties.setHdtvAdvertisementMasterLog("ctc/PT_HDTV_ADS_MASTER_LOG");
        final RemoteProperties remoteProperties = new RemoteProperties();
        remoteProperties.setPath(remotePathProperties);

        assertDoesNotThrow(() -> ctcLogDomainService.transferUxHotvodCategory(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferUxHotvodCategoryContent(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferUxHotvodContent(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferUxHotvodContentExtension(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferUxHotvodHitcountLog(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferUxHotvodSite(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferHdtvAdvertisementInfo(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferHdtvAdvertisementInfoLog(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferHdtvAdvertisementMaster(null, remoteProperties));
        assertDoesNotThrow(() -> ctcLogDomainService.transferHdtvAdvertisementMasterLog(null, remoteProperties));
    }

    @Test
    void test_formatDateTime() throws IOException {

        final UxHotvodCategory uxHotvodCategory = UxHotvodCategory.builder().build();
        JunitTestUtils.setValue(uxHotvodCategory, "registerDate", LocalDateTime.now());
        doReturn(List.of(uxHotvodCategory)).when(ctcLogRepository).findAllUxHotvodCategory();

        final File uxHotvodCategoryFile = ctcLogDomainService.getUxHotvodCategoryFile();
        assertThat(uxHotvodCategoryFile).exists();
        uxHotvodCategoryFile.delete();
    }
}
