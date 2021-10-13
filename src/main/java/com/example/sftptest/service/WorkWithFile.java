package com.example.sftptest.service;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WorkWithFile {

    private final String absfilepath = "O:\\Java\\DigitalLeague\\SFTP-test\\src\\main\\";
    private final String constantFilename = "resourcesJava.pdf";

    private File getFile() {
        return new File(absfilepath + constantFilename);
    }

    void workWithFile1() throws IOException {

        PdfReader reader = new PdfReader(absfilepath + constantFilename);
        String content = PdfTextExtractor.getTextFromPage(reader, 1);
        System.out.println(content);

    }

    void learnFileWeight() {

        System.out.println((double) getFile().length() / (1024 * 1024));
    }

    public String learnFileExtension(String filename) {
        if (filename.lastIndexOf(".") != -1 &&
                filename.lastIndexOf(".") != 0) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else return "";
    }


    public String learnFullname(String filename) {
        if (filename.lastIndexOf(".") != -1 &&
                filename.lastIndexOf(".") != 0) {

            return filename.substring(0, filename.lastIndexOf("."));
        } else {
            return "";
        }
    }


    public String learnShortname(String filename) {
        if (filename.lastIndexOf(".") != -1 &&
                filename.lastIndexOf(".") != 0 &&
                filename.contains("_")) {

            filename.substring(0, filename.lastIndexOf("_"));

            return filename.substring(0, filename.lastIndexOf("_"));
        } else {
            return "";
        }
    }

    public String learnListnumber(String filename) {

        if (filename.lastIndexOf(".") != -1 &&
                filename.lastIndexOf(".") != 0 &&
                filename.contains("_")) {

            filename.substring(0, filename.lastIndexOf("_"));

            return filename.substring(filename.lastIndexOf("_") + 1, filename.lastIndexOf("."));
        } else {
            return "";
        }
    }
}


