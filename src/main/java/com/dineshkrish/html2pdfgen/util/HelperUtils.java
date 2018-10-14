package com.dineshkrish.html2pdfgen.util;

public class HelperUtils {

    private static String getOSName() {
        String osName = System.getProperties().getProperty("os.name");
        if(osName != null && !osName.isEmpty()) {
            if(osName.contains("Windows"))
                return "Windows";
            else
                return "Linux";
        }
        return osName;
    }

    public static String getCommand(final String type) {
        String osName = getOSName();
        if("Windows".equals(osName)) {
            String path = Thread.currentThread().getContextClassLoader().getResource("windows").getPath();
            return prepareCommand(path, type);
        } else if("Linux".equals(osName)) {
            String path = Thread.currentThread().getContextClassLoader().getResource("linux").getPath();
            return prepareCommand(path, type);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String prepareCommand(final String path, final String type) {
        if("Pdf".equalsIgnoreCase(type)) {
            return path+"\\wkhtmltox\\bin\\wkhtmltopdf ";
        } else if("Image".equalsIgnoreCase(type)) {
            return path+"\\wkhtmltox\\bin\\wkhtmltoimage ";
        } else {
            throw new IllegalArgumentException();
        }
    }

}
