package com.lguplus.fleta.service.caching;

import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ImageServerCachingServiceTest {

    @Mock
    ImageServerDomainService imageServerDomainService;
    
    @InjectMocks
    private ImageServerCachingService imageServerCachingService;

    @BeforeEach
    void setUp() throws Exception {
        when(imageServerDomainService.loadImageServers()).thenReturn(null);
    }
    
    @Test
    void testCacheImageServers() {
        assertDoesNotThrow(() -> imageServerCachingService.cacheImageServers());
    }
}
