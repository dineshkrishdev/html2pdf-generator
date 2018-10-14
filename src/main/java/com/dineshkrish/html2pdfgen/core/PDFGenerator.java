package com.dineshkrish.html2pdfgen.core;

import com.dineshkrish.html2pdfgen.util.HelperUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class PDFGenerator {

    public static void generate(final String url, final String type, final OutputStream os) {
        String command = HelperUtils.getCommand(type) + url + " -";
        try {
            final Process process = Runtime.getRuntime().exec(command);
            Thread pdfWriteThread = new Thread(() -> {
                try {
                    IOUtils.copy(process.getInputStream(), os);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            pdfWriteThread.start();
            process.waitFor();
        } catch (Exception e) {

        }
    }
}
