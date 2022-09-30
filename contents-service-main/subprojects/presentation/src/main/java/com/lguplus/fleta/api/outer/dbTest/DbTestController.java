package com.lguplus.fleta.api.outer.dbTest;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.service.DBTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DbTestController {

    private final DBTestService dbTestService;

    @GetMapping("/db/test")
    public SimilarContentDto getDBTest(String albumId) {
        return dbTestService.getDBTest(albumId);
    }
}
