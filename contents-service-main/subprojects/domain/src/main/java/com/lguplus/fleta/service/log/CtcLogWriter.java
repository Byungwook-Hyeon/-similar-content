package com.lguplus.fleta.service.log;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CtcLogWriter implements Closeable {

    private File textFile;
    private PrintWriter textWriter;

    public CtcLogWriter(final String name, final String path) throws IOException {

        textFile = new File(path, getTextFileName(name));
        textWriter = new PrintWriter(new BufferedWriter(new FileWriter(textFile, Charset.forName("euc-kr"))));
    }

    private String getTextFileName(final String tableName) {

        return tableName + "_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "000000.txt";
    }

    public void write(final String data) {

        textWriter.println(data);
    }

    public File createFile() throws IOException {

        finish();

        final String textFileName = textFile.getAbsolutePath();
        final String compressFileName = textFileName.substring(0, textFileName.lastIndexOf('.')) + ".tar.gz";

        try (final TarArchiveOutputStream out = new TarArchiveOutputStream(new GzipCompressorOutputStream(
                new BufferedOutputStream(new FileOutputStream(compressFileName))))) {
            final TarArchiveEntry archiveEntry = new TarArchiveEntry(textFile, textFile.getName());
            out.putArchiveEntry(archiveEntry);
            Files.copy(Path.of(textFileName), out);
            out.closeArchiveEntry();
            return new File(compressFileName);
        }
    }

    private void finish() {

        if (textWriter != null) {
            textWriter.close();
            textWriter = null;
        }
    }

    @Override
    public void close() {

        finish();
        try {
            Files.delete(Path.of(textFile.getAbsolutePath()));
        } catch (final IOException e) {
            // Do nothing.
        }
    }
}
