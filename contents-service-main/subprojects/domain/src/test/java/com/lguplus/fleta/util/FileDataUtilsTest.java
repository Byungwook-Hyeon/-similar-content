package com.lguplus.fleta.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
class FileDataUtilsTest {

    @TempDir
    Path directory;
    @Mock
    File file1;

    @Autowired
    ResourceLoader resourceLoader;

    private Resource resource;

    @BeforeEach
    void init() {

        resource = resourceLoader.getResource("classpath:");

    }
    @AfterEach
    void tearDown() {

        log.info("@AfterEach - executed after each test method.");
    }


    @Test
    void testFindFile() {
        String findName = "test"+"^!";
        String type = "startWith";
        String folderDir = null;
        try {
            folderDir = resource.getURI().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> rtnFile = FileDataUtils.findFile(findName, type, folderDir);
        log.info("past result >> {}", rtnFile);
        Assertions.assertNotNull(rtnFile);
    }

    @Test
    void findFile_NameFilter() {
        String findName = "test"+"^!";
        String folderDir = "D:/workspace/contents-service/subprojects/presentation/src/main/resources/iptv/";
        List<File> rtnFile = FileDataUtils.findFile(findName, "endsWith", folderDir);
        log.info("past result >> {}", rtnFile);
        Assertions.assertNotNull(rtnFile);

        rtnFile = FileDataUtils.findFile(findName, "equals", folderDir);
        log.info("past result >> {}", rtnFile);
        Assertions.assertNotNull(rtnFile);

        rtnFile = FileDataUtils.findFile(findName, "contains", folderDir);
        log.info("past result >> {}", rtnFile);
        Assertions.assertNotNull(rtnFile);

        rtnFile = FileDataUtils.findFile(findName, "", folderDir);
        log.info("past result >> {}", rtnFile);
        Assertions.assertNotNull(rtnFile);
    }

    @Test
    void testGetFileLineData() {

        Path fileOne = directory.resolve("file1.fts");
        String str = "1|https://kids.youtube.com|2022-03-21 16:38";
        byte[] bytes = str.getBytes();
        try {
            Files.write(fileOne, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        file1 = fileOne.toFile();
        List<String> brList = FileDataUtils.getFileLineData(file1);
        Assertions.assertNotNull(brList);
    }

    @Test
    void testGetFileLineData_ex() {

        Path fileOne = directory.resolve("file1.fts");
        file1 = fileOne.toFile();
        List<String> brList = FileDataUtils.getFileLineData(file1);
        Assertions.assertNotNull(brList);
    }
}