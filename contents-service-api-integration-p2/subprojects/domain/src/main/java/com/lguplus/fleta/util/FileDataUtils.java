package com.lguplus.fleta.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public final class FileDataUtils {

    private FileDataUtils() {
        // Do nothing.
    }


    public static List<File> findFile(String findName, String type, String qualityDir) {

        File qatFolder = new File(qualityDir);
        List<File> rtnFile = new ArrayList<>();

        File[] file = qatFolder.listFiles(new CustomNameFilter(type, findName));
        if (file != null) {
            List<File> fileList = Arrays.asList(file);
            rtnFile.addAll(fileList);
            log.debug("dirName {}, {}, {}", qualityDir + fileList, type, findName);
        }

        return rtnFile;
    }


    /**
     * 해당 파일의 데이터 추출
     * try-with-resources : try 안에 안에서 선언된 객체의 close() 메소드들을 호출합니다.
     * 그래서 finally에서 close()를 명시적으로 호출해줄 필요가 없습니다
     */
    public static List<String> getFileLineData(File file) {
        List<String> brList = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader(file.toString()))
        ) {
            brList = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            log.error("FileReadMiss:[{}][{}]", e.getClass().getName(), e.getMessage(), e);
        }
        return brList;
    }

}
