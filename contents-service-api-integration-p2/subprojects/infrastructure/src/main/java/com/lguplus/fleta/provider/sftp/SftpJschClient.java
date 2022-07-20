package com.lguplus.fleta.provider.sftp;

import com.jcraft.jsch.*;
import com.lguplus.fleta.client.SftpClient;
import com.lguplus.fleta.config.SftpProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class SftpJschClient implements SftpClient {

    private static final String PATH_SEPARATOR = "/";

    private Session createSession(final SftpProperties properties) throws JSchException {

        final JSch jSch = new JSch();
        final Session session = jSch.getSession(properties.getUsername(), properties.getHost(), properties.getPort());
        session.setPassword(properties.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        return session;
    }

    private ChannelSftp openChannel(final Session session) throws JSchException {

        final ChannelSftp channel = (ChannelSftp)session.openChannel("sftp");
        channel.connect();
        return channel;
    }

    private void transferFile(final ChannelSftp channel, final File localFile, final String remotePath,
                              final String remoteFileName) throws JSchException, SftpException {

        if (!createRemotePath(channel, remotePath)) {
            throw new JSchException("Fail to create remote path: " + remotePath);
        }

        channel.put(localFile.getAbsolutePath(), remotePath + "/" + remoteFileName);
    }

    @Override
    public void transferFile(final File localFile, final SftpProperties properties, final String remotePath)
            throws IOException {

        Session session = null;
        ChannelSftp channel = null;
        try {
            session = createSession(properties);
            channel = openChannel(session);

            final String absoluteRemotePath = remotePath.startsWith("/") ? remotePath :
                    channel.getHome().replaceFirst("/$", "") + PATH_SEPARATOR + remotePath;
            transferFile(channel, localFile, absoluteRemotePath, localFile.getName());
        } catch (final JSchException | SftpException e) {
            throw new IOException(e.getMessage(), e);
        } finally {
            if (channel != null) {
                channel.quit();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    private boolean createRemotePath(final ChannelSftp channel, final String remotePath) throws SftpException {

        if (remotePath.isEmpty()) {
            return false;
        }

        try {
            channel.cd(remotePath);
            return true;
        } catch (final SftpException e) {
            final int position = remotePath.lastIndexOf('/');
            if (position < 0 || createRemotePath(channel, remotePath.substring(0, position))) {
                channel.mkdir(remotePath);
                return true;
            }
        }
        return false;
    }
}
