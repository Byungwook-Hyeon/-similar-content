package com.lguplus.fleta.service;

import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ImageServerTestService {

    private final ImageServerDomainService imageServerDomainService;

//    public String getNewImageServerTest() {
//        String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.DEFAULT);
////        String result = imageServerUrl.concat("poster/");
//        return imageServerUrl;
//    }

    public String[] getNewImageServerTest() {
        String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.DEFAULT);
        String posterServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.RESIZE);
        String stillImgeServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.STILLCUT);

//        String result = imageServerUrl.concat("poster/");
        String[] result = {"null", "null", "null"};
        result[0] = imageServerUrl;
        result[1] = posterServerUrl;
        result[2] = stillImgeServerUrl;

//        return imageServerUrl;
        return result;
    }
}
