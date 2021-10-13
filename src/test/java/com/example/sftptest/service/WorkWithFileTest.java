package com.example.sftptest.service;

import com.example.sftptest.sftp.SftpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class WorkWithFileTest {

    @Autowired
    WorkWithFile workWithFile;

    @Autowired
    SftpService sftp;


    String filename = "kekekeke1_1234.pdf";

    @Test
    public void getFileWeight() throws IOException {
//        new WorkWithFile().workWithFile();
        new WorkWithFile().learnFileWeight();
    }


    @Test
    public void getFileExtension() {
        System.out.println(new WorkWithFile().learnFileExtension(filename));

    }

    @Test
    public void learnFullname() {
        System.out.println(workWithFile.learnFullname(filename));
    }

    @Test
    public void learnShortName() {
        System.out.println(workWithFile.learnShortname(filename));
    }


    @Test
    public void learnListnumber() {
        System.out.println(workWithFile.learnListnumber(filename));
    }

    @Test
    public void learnFileWeight() {
        sftp.learnFileWeight();
    }

}
