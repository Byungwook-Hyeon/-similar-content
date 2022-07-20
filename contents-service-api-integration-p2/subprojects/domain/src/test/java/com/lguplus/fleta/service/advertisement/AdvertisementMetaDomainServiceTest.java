package com.lguplus.fleta.service.advertisement;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import com.lguplus.fleta.data.type.AdvertisementMetaType;
import com.lguplus.fleta.repository.advertisement.AdvertisementMetaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AdvertisementMetaDomainServiceTest {

    @Mock
    AdvertisementMetaRepository advertisementMetaRepository;

    @InjectMocks
    AdvertisementMetaDomainService advertisementMetaDomainService;

    @Test
    void testGetAdvertisementLiveMetaIptvBannerVod() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("SEAM1");
        advertisementMeta.setAdsType("2");
        advertisementMeta.setLinkUrl("A7006||M01218Q018PPV00||I30||N||");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().banner(true).build());
        assertThat(result).hasSize(1);
        result.forEach(e -> {
            assertThat(e.getType()).isEqualTo(AdvertisementMetaType.VOD.toString());
            assertThat(e.getContent()).isEqualTo("M01218Q018PPV00");
            assertThat(e.getCategory()).isEqualTo("A7006");
            assertThat(e.getSeries()).isEqualTo("N");
        });
    }

    @Test
    void testGetAdvertisementLiveMetaIptvBannerCategory() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("SEAM1");
        advertisementMeta.setAdsType("3");
        advertisementMeta.setLinkUrl("AK100||I30");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().banner(true).build());
        assertThat(result).hasSize(1);
        result.forEach(e -> {
            assertThat(e.getType()).isEqualTo(AdvertisementMetaType.CATEGORY.toString());
            assertThat(e.getContent()).isEqualTo("AK100");
            assertThat(e.getCategory()).isEqualTo("AK100");
        });
    }

    @Test
    void testGetAdvertisementLiveMetaIptvBannerAppLink() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("SEAM1");
        advertisementMeta.setAdsType("5");
        advertisementMeta.setLinkUrl("KIDS||com.lguplus.iptv3.base.launcher||com.lguplus.iptv3.base.launcher.kids.KidsHomeMainActivity");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().banner(true).build());
        assertThat(result).hasSize(1);
        result.forEach(e -> {
            assertThat(e.getType()).isEqualTo(AdvertisementMetaType.APP_LINK.toString());
            assertThat(e.getContent()).isEqualTo("KIDS");
            assertThat(e.getCategory()).isEqualTo("KIDS");
        });
    }

    @Test
    void testGetAdvertisementLiveMetaIptvBannerInvalidAdsId() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("SEAM10");
        advertisementMeta.setAdsType("5");
        advertisementMeta.setLinkUrl("KIDS||com.lguplus.iptv3.base.launcher||com.lguplus.iptv3.base.launcher.kids.KidsHomeMainActivity");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().banner(true).build());
        assertThat(result).isEmpty();
    }

    @Test
    void testGetAdvertisementLiveMetaIptvBannerInvalidAdsType() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("SEAM1");
        advertisementMeta.setAdsType("6");
        advertisementMeta.setLinkUrl("KIDS||com.lguplus.iptv3.base.launcher||com.lguplus.iptv3.base.launcher.kids.KidsHomeMainActivity");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().banner(true).build());
        assertThat(result).isEmpty();
    }

    @Test
    void testGetAdvertisementLiveMetaIptvCategory() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("Seamless02");
        advertisementMeta.setLinkUrl("A7003||I30");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().build());
        assertThat(result).hasSize(1);
        result.forEach(e -> {
            assertThat(e.getType()).isEqualTo(AdvertisementMetaType.CATEGORY.toString());
            assertThat(e.getContent()).isEqualTo("A7003");
        });
    }

    @Test
    void testGetAdvertisementLiveMetaIptvAppLink() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("seam05");
        advertisementMeta.setLinkUrl("AM00||I30");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().build());
        assertThat(result).hasSize(1);
        result.forEach(e -> {
            assertThat(e.getType()).isEqualTo(AdvertisementMetaType.APP_LINK.toString());
            assertThat(e.getContent()).isEqualTo("AM00");
        });
    }

    @Test
    void testGetAdvertisementLiveMetaMobileCategory() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("BA_0000000058");
        advertisementMeta.setLinkUrl("M046643");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().build());
        assertThat(result).hasSize(1);
        result.forEach(e -> {
            assertThat(e.getType()).isEqualTo(AdvertisementMetaType.CATEGORY.toString());
            assertThat(e.getContent()).isEqualTo("M046643");
        });
    }

    @Test
    void testGetAdvertisementLiveMetaInvalidAdsId() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("seam06");
        advertisementMeta.setLinkUrl("AM00||I30");

        doReturn(List.of(advertisementMeta)).when(advertisementMetaRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaDomainService.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().build());
        assertThat(result).isEmpty();
    }

    @Test
    void testUpdateInformationInvalidAdsId() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("seam06");
        advertisementMeta.setLinkUrl("AM00||I30");

        final AdvertisementMetaDto result = ReflectionTestUtils.invokeMethod(
                advertisementMetaDomainService, "updateInformation", advertisementMeta);
        assertThat(result.getAdsId()).isEqualTo("seam06");
        assertThat(result.getLinkUrl()).isEqualTo("AM00||I30");
    }

    @Test
    void testUpdateIptvBannerInformationInvalidAdsId() {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsType("A");
        advertisementMeta.setLinkUrl("M046643");

        final AdvertisementMetaDto result = ReflectionTestUtils.invokeMethod(
                advertisementMetaDomainService, "updateIptvBannerInformation", advertisementMeta);
        assertThat(result.getAdsType()).isEqualTo("A");
        assertThat(result.getLinkUrl()).isEqualTo("M046643");
    }
}
