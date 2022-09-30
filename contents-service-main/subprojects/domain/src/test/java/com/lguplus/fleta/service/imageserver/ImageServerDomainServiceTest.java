package com.lguplus.fleta.service.imageserver;

import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.ImageServerDto;
import com.lguplus.fleta.data.type.ImageServerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ImageServerDomainServiceTest {

    @Mock
    VodLookupDomainClient vodLookupDomainClient;
    @Mock
    ImageServerDomainService self;
    @Mock
    BeanFactory beanFactory;
//    @Mock
//    SecureRandom random;

    @InjectMocks
    private ImageServerDomainService imageServerDomainService;

    @BeforeEach
    void setUp() throws Exception {
        List<ImageServerDto> list = new ArrayList<>();
        ImageServerDto imageServerDto = ImageServerDto.builder()
                .serverId("img_server")
                .serverIp("0.0.0.0")
                .recoveryServerIp("1.1.1.1")
                .imagePath("/none")
                .build();
        list.add(imageServerDto);
        ImageServerDto imageServerDto2 = ImageServerDto.builder()
                .serverId("img_server")
                .serverIp("0.0.0.0")
                .recoveryServerIp("1.1.1.1")
                .imagePath("/none")
                .build();
        list.add(imageServerDto2);
        when(vodLookupDomainClient.getImageServers()).thenReturn(list);
    }

    @Test
    void testGetImageServers() {
        Map<String, List<ImageServerDto>> imageServers = imageServerDomainService.getImageServers();

        List<ImageServerDto> list = imageServers.get("img_server");
        ImageServerDto imageServerDto = list.get(0);
        String serverIp = imageServerDto.getServerIp();
        assertThat(serverIp).isEqualTo("0.0.0.0");
    }

    @DisplayName("ImageServerCachingServiceTest 정상 케이스")
    @Test
    void testLoadImageServers() {
        Map<String, List<ImageServerDto>> imageServers = imageServerDomainService.loadImageServers();

        List<ImageServerDto> list = imageServers.get("img_server");
        ImageServerDto imageServerDto = list.get(0);
        String serverIp = imageServerDto.getServerIp();

        assertThat(serverIp).isEqualTo("0.0.0.0");
    }

    @Test
    void testGetImageServerUrl_case1() {
        Map<String, List<ImageServerDto>> map = new HashMap<>();
        List<ImageServerDto> list = new ArrayList<>();
        ImageServerDto imageServerDto = ImageServerDto.builder()
                .serverId("img_server")
                .serverIp("0.0.0.0")
                .recoveryServerIp("1.1.1.1")
                .imagePath("/none")
                .build();
        list.add(imageServerDto);
        map.put("img_server", list);

        when(beanFactory.getBean(ImageServerDomainService.class)).thenReturn(self);
        when(self.getImageServers()).thenReturn(map);
        imageServerDomainService.setBeanFactory(beanFactory);

        //when(random.nextBoolean()).thenReturn(true);

        String imageServerUrl = null;
        for(int i=0; i<2; i++) {
            imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.DEFAULT);
        }

        assertTrue(imageServerUrl.equals("http://0.0.0.0/none") || imageServerUrl.equals("http://1.1.1.1/none"));
    }

    @Test
    void testGetImageServerUrl_case2() {
        Map<String, List<ImageServerDto>> map = new HashMap<>();

        when(beanFactory.getBean(ImageServerDomainService.class)).thenReturn(self);
        when(self.getImageServers()).thenReturn(map);
        imageServerDomainService.setBeanFactory(beanFactory);

        String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.DEFAULT);

        assertThat(imageServerUrl).isNull();
    }

}
