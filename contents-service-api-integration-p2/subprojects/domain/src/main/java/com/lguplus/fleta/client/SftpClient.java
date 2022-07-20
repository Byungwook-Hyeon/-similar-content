package com.lguplus.fleta.client;

import com.lguplus.fleta.config.SftpProperties;

import java.io.File;
import java.io.IOException;

public interface SftpClient {

    void transferFile(File localFile, SftpProperties properties, String remotePath) throws IOException;
}
