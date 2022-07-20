package com.lguplus.fleta.service;

import com.lguplus.fleta.config.CtcConfigProperties;
import com.lguplus.fleta.config.CtcConfigProperties.RemoteProperties;
import com.lguplus.fleta.data.annotation.SynchronousScheduled;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.service.log.CtcLogDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CtcLogService {

    private final CtcConfigProperties properties;
    private final CtcLogDomainService ctcLogDomainService;

    @SynchronousScheduled(cron = "0 0 2/1 * * *", semaphore = CacheName.CTC_LOG, autoReleaseSeconds = 3000)
    public void transfer() throws IOException {

        if (isNeededToTransferUxHotvodCategory(properties)) {
            transferUxHotvodCategory(properties);
        }

        if (isNeededToTransferUxHotvodCategoryContent(properties)) {
            transferUxHotvodCategoryContent(properties);
        }

        if (isNeededToTransferUxHotvodContent(properties)) {
            transferUxHotvodContent(properties);
        }

        if (isNeededToTransferUxHotvodContentExtension(properties)) {
            transferUxHotvodContentExtension(properties);
        }

        if (isNeededToTransferUxHotvodHitcountLog(properties)) {
            transferUxHotvodHitcountLog(properties);
        }

        if (isNeededToTransferUxHotvodSite(properties)) {
            transferUxHotvodSite(properties);
        }

        if (isNeededToTransferHdtvAdvertisementInfo(properties)) {
            transferHdtvAdvertisementInfo(properties);
        }

        if (isNeededToTransferHdtvAdvertisementInfoLog(properties)) {
            transferHdtvAdvertisementInfoLog(properties);
        }

        if (isNeededToTransferHdtvAdvertisementMaster(properties)) {
            transferHdtvAdvertisementMaster(properties);
        }

        if (isNeededToTransferHdtvAdvertisementMasterLog(properties)) {
            transferHdtvAdvertisementMasterLog(properties);
        }
    }

    private boolean isNeededToTransferUxHotvodCategory(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getUxHotvodCategoryPath() != null &&
                    isNeededToTransfer(ctcLogDomainService.getLastUxHotvodCategoryTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferUxHotvodCategory(final CtcConfigProperties properties) throws IOException {

        final File
                file = ctcLogDomainService.getUxHotvodCategoryFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getUxHotvodCategoryPath() != null &&
                        isNeededToTransfer(ctcLogDomainService.getLastUxHotvodCategoryTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferUxHotvodCategory(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer uxHotvodCategory.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferUxHotvodCategoryContent(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getUxHotvodCategoryContentPath() != null &&
                    isNeededToTransfer(ctcLogDomainService
                            .getLastUxHotvodCategoryContentTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferUxHotvodCategoryContent(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getUxHotvodCategoryContentFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getUxHotvodCategoryContentPath() != null &&
                        isNeededToTransfer(ctcLogDomainService
                                .getLastUxHotvodCategoryContentTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferUxHotvodCategoryContent(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer uxHotvodCategoryContent.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferUxHotvodContent(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getUxHotvodContentPath() != null &&
                    isNeededToTransfer(ctcLogDomainService.getLastUxHotvodContentTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferUxHotvodContent(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getUxHotvodContentFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getUxHotvodContentPath() != null &&
                        isNeededToTransfer(ctcLogDomainService.getLastUxHotvodContentTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferUxHotvodContent(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer uxHotvodContent.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferUxHotvodContentExtension(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getUxHotvodContentExtensionPath() != null &&
                    isNeededToTransfer(ctcLogDomainService
                            .getLastUxHotvodContentExtensionTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferUxHotvodContentExtension(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getUxHotvodContentExtensionFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getUxHotvodContentExtensionPath() != null &&
                        isNeededToTransfer(ctcLogDomainService
                                .getLastUxHotvodContentExtensionTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferUxHotvodContentExtension(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer uxHotvodContentExtension.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferUxHotvodHitcountLog(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getUxHotvodHitcountLogPath() != null &&
                    isNeededToTransfer(ctcLogDomainService.getLastUxHotvodHitcountLogTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferUxHotvodHitcountLog(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getUxHotvodHitcountLogFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getUxHotvodHitcountLogPath() != null &&
                        isNeededToTransfer(ctcLogDomainService
                                .getLastUxHotvodHitcountLogTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferUxHotvodHitcountLog(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer uxHotvodHitcountLog.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferUxHotvodSite(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getUxHotvodSitePath() != null &&
                    isNeededToTransfer(ctcLogDomainService.getLastUxHotvodSiteTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferUxHotvodSite(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getUxHotvodSiteFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getUxHotvodSitePath() != null &&
                        isNeededToTransfer(ctcLogDomainService.getLastUxHotvodSiteTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferUxHotvodSite(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer uxHotvodSite.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferHdtvAdvertisementInfo(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getHdtvAdvertisementInfoPath() != null &&
                    isNeededToTransfer(ctcLogDomainService
                            .getLastHdtvAdvertisementInfoTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferHdtvAdvertisementInfo(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getHdtvAdvertisementInfoFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getHdtvAdvertisementInfoPath() != null &&
                        isNeededToTransfer(ctcLogDomainService
                                .getLastHdtvAdvertisementInfoTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferHdtvAdvertisementInfo(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer hdtvAdvertisementInfo.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferHdtvAdvertisementInfoLog(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getHdtvAdvertisementInfoLogPath() != null &&
                    isNeededToTransfer(ctcLogDomainService
                            .getLastHdtvAdvertisementInfoLogTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferHdtvAdvertisementInfoLog(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getHdtvAdvertisementInfoLogFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getHdtvAdvertisementInfoLogPath() != null &&
                        isNeededToTransfer(ctcLogDomainService
                                .getLastHdtvAdvertisementInfoLogTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferHdtvAdvertisementInfoLog(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer hdtvAdvertisementInfoLog.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferHdtvAdvertisementMaster(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getHdtvAdvertisementMasterPath() != null &&
                    isNeededToTransfer(ctcLogDomainService
                            .getLastHdtvAdvertisementMasterTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferHdtvAdvertisementMaster(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getHdtvAdvertisementMasterFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getHdtvAdvertisementMasterPath() != null &&
                        isNeededToTransfer(ctcLogDomainService
                                .getLastHdtvAdvertisementMasterTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferHdtvAdvertisementMaster(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer hdtvAdvertisementMaster.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransferHdtvAdvertisementMasterLog(final CtcConfigProperties properties) {

        final List<RemoteProperties> remotePropertiesList = properties.getRemote();
        if (remotePropertiesList == null) {
            return false;
        }

        for (final RemoteProperties remoteProperties : remotePropertiesList) {
            if (!remoteProperties.isDisable() && remoteProperties.getHdtvAdvertisementMasterLogPath() != null &&
                    isNeededToTransfer(ctcLogDomainService
                            .getLastHdtvAdvertisementMasterLogTransferTime(remoteProperties))) {
                return true;
            }
        }
        return false;
    }

    private void transferHdtvAdvertisementMasterLog(final CtcConfigProperties properties) throws IOException {

        final File file = ctcLogDomainService.getHdtvAdvertisementMasterLogFile();
        properties.getRemote().stream()
                .filter(remoteProperties -> !remoteProperties.isDisable() &&
                        remoteProperties.getHdtvAdvertisementMasterLogPath() != null &&
                        isNeededToTransfer(ctcLogDomainService
                                .getLastHdtvAdvertisementMasterLogTransferTime(remoteProperties)))
                .forEach(remoteProperties -> {
                    try {
                        ctcLogDomainService.transferHdtvAdvertisementMasterLog(file, remoteProperties);
                    } catch (final Exception e) {
                        log.error("Fail to transfer hdtvAdvertisementMasterLog.", e);
                    }
                });
        deleteDataFile(file);
    }

    private boolean isNeededToTransfer(final LocalDateTime lastTransferTime) {

        final LocalDateTime thisTime = LocalDateTime.now();
        return lastTransferTime.getYear() != thisTime.getYear() &&
                lastTransferTime.getDayOfYear() != thisTime.getDayOfYear();
    }

    private void deleteDataFile(final File file) {

        if (file != null) {
            try {
                Files.delete(Path.of(file.getAbsolutePath()));
            } catch (final IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }
}
