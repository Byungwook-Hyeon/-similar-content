package com.lguplus.fleta.provider.sftp;

import com.jcraft.jsch.*;
import com.lguplus.fleta.config.SftpProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SftpJschClientTest {

    @Mock
    private Session session;

    @Mock
    private ChannelSftp channelSftp;

    @InjectMocks
    private SftpJschClient sftpJschClient;

    private File localFile;
    private SftpProperties properties;

    @BeforeEach
    void setUp() throws JSchException, SftpException {

        doReturn(channelSftp).when(session).openChannel(anyString());
        doReturn("/home").when(channelSftp).getHome();

        localFile = new File(System.getProperty("java.io.tmpdir"), "some.tar.gz");
        properties = new SftpProperties();
    }

    @Test
    void test_transferFile() {

        try (final MockedConstruction<JSch> construction = mockConstruction(JSch.class, (mock, context) ->
                doReturn(session).when(mock).getSession(any(), any(), anyInt())
        )) {
            assertDoesNotThrow(() -> sftpJschClient.transferFile(localFile, properties, "/dir/subdir"));
        }
    }

    @Test
    void test_transferFile_With_RelativePath() {

        try (final MockedConstruction<JSch> construction = mockConstruction(JSch.class, (mock, context) ->
                doReturn(session).when(mock).getSession(any(), any(), anyInt())
        )) {
            assertDoesNotThrow(() -> sftpJschClient.transferFile(localFile, properties, "dir/subdir"));
        }
    }

    @Test
    void test_transferFile_cd_Fail() throws SftpException {

        try (final MockedConstruction<JSch> construction = mockConstruction(JSch.class, (mock, context) ->
                doReturn(session).when(mock).getSession(any(), any(), anyInt())
        )) {
            doThrow(new SftpException(0, null)).when(channelSftp).cd(any());

            assertThrows(IOException.class, () ->
                    sftpJschClient.transferFile(localFile, properties, "/dir/subdir"));
        }
    }

    @Test
    void test_transferFile_cd_mkdir_Fail() throws SftpException {

        try (final MockedConstruction<JSch> construction = mockConstruction(JSch.class, (mock, context) ->
                doReturn(session).when(mock).getSession(any(), any(), anyInt())
        )) {
            doThrow(new SftpException(0, null)).when(channelSftp).cd(any());
            doThrow(new SftpException(0, null)).when(channelSftp).mkdir(any());

            assertThrows(IOException.class, () ->
                    sftpJschClient.transferFile(localFile, properties, "/dir/subdir"));
        }
    }

    @Test
    void test_transferFile_RelativeHomePath_And_cd_Fail() throws SftpException {

        try (final MockedConstruction<JSch> construction = mockConstruction(JSch.class, (mock, context) ->
                doReturn(session).when(mock).getSession(any(), any(), anyInt())
        )) {
            doReturn("home").when(channelSftp).getHome();

            doThrow(new SftpException(0, null)).when(channelSftp).cd(any());

            assertDoesNotThrow(() -> sftpJschClient.transferFile(localFile, properties, "dir/subdir"));
        }
    }

    @Test
    void test_transferFile_getSession_Fail() {

        try (final MockedConstruction<JSch> construction = mockConstruction(JSch.class, (mock, context) ->
                doThrow(new JSchException()).when(mock).getSession(any(), any(), anyInt())
        )) {
            assertThrows(IOException.class, () ->
                    sftpJschClient.transferFile(localFile, properties, "/dir/subdir"));
        }
    }
}
