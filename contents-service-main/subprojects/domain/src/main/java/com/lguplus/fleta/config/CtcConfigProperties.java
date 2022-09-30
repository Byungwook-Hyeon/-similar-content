package com.lguplus.fleta.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("ctc")
@Getter
@Setter
public class CtcConfigProperties {

    private List<RemoteProperties> remote;

    @Getter
    @Setter
    public static class RemoteProperties extends SftpProperties {

        private RemotePathProperties path;

        public String getUxHotvodCategoryPath() {

            return path == null ? null : path.getUxHotvodCategory();
        }

        public String getUxHotvodCategoryContentPath() {

            return path == null ? null : path.getUxHotvodCategoryContent();
        }

        public String getUxHotvodContentPath() {

            return path == null ? null : path.getUxHotvodContent();
        }

        public String getUxHotvodContentExtensionPath() {

            return path == null ? null : path.getUxHotvodContentExtension();
        }

        public String getUxHotvodHitcountLogPath() {

            return path == null ? null : path.getUxHotvodHitcountLog();
        }

        public String getUxHotvodSitePath() {

            return path == null ? null : path.getUxHotvodSite();
        }

        public String getHdtvAdvertisementInfoPath() {

            return path == null ? null : path.getHdtvAdvertisementInfo();
        }

        public String getHdtvAdvertisementInfoLogPath() {

            return path == null ? null : path.getHdtvAdvertisementInfoLog();
        }

        public String getHdtvAdvertisementMasterPath() {

            return path == null ? null : path.getHdtvAdvertisementMaster();
        }

        public String getHdtvAdvertisementMasterLogPath() {

            return path == null ? null : path.getHdtvAdvertisementMasterLog();
        }
    }

    @Getter
    @Setter
    public static class RemotePathProperties {

        private String uxHotvodCategory;
        private String uxHotvodCategoryContent;
        private String uxHotvodContent;
        private String uxHotvodContentExtension;
        private String uxHotvodHitcountLog;
        private String uxHotvodSite;
        private String hdtvAdvertisementInfo;
        private String hdtvAdvertisementInfoLog;
        private String hdtvAdvertisementMaster;
        private String hdtvAdvertisementMasterLog;
    }
}
