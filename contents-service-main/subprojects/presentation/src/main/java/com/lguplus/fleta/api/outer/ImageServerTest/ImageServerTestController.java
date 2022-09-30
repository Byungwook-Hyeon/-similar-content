package com.lguplus.fleta.api.outer.ImageServerTest;


import com.lguplus.fleta.service.ImageServerTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageServerTestController {

    private final ImageServerTestService imageServerTestService;


    @GetMapping("/image/test")
    public String getImageServer() {
//        String IMG_URL = imageServerTestService.getNewImageServerTest();
//        String POSTER_URL = imageServerTestService.;
//        String STILL_IMG_RUL = null;

        String[] get = imageServerTestService.getNewImageServerTest();

        System.out.println("#############################################");
        System.out.println("IMG_URL : " + get[0]);
        System.out.println("POSTER_URL : " + get[1]);
        System.out.println("STILL_IMG_URL : " + get[2]);
        System.out.println("#############################################");

        return null;
    }
}
