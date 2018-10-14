package com.dineshkrish.html2pdfgen.controller;

import com.dineshkrish.html2pdfgen.core.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/v1")
public class PDFGeneratorController {

    @Autowired
    PDFGenerator pdfGenerator;

    @GetMapping
    public String getInfo() {
        return "Application is Running...";
    }

    @ResponseBody
    @GetMapping(value = "/generate")
    public HttpEntity<byte[]> generate(@RequestParam("apikey") String apikey, @RequestParam("url") String url, @RequestParam("type") String type) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        pdfGenerator.generate(url, type, bos);
        if("Pdf".equalsIgnoreCase(type)) {
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(bos.toByteArray().length);
            return new HttpEntity<byte[]>(bos.toByteArray(), headers);
        } else if("Image".equalsIgnoreCase(type)) {
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(bos.toByteArray().length);
            return new HttpEntity<byte[]>(bos.toByteArray(), headers);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
