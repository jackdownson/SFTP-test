package com.example.sftptest.sftp;

import com.example.sftptest.service.WorkWithFile;
import com.jcraft.jsch.SftpException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SftpServiceTest {
    @Autowired
    private SftpService sftpService;

    @Autowired
    private WorkWithFile workWithFile;


    @Test
    public void upload(){
        sftpService.upload();


    }

    @Test
    public void downloadTiffPdfJpeg(){
        String localFiledir = "src/main/resources/test-download/";
        String remoteFileDir ="upload/";

        String download = sftpService.downloadTiffPdfJpeg(localFiledir, remoteFileDir);
        System.out.println("donwloaded:\n" + download);

    }

    @Test
    void listNames() throws IOException {
        String[] listNames = sftpService.listNames();
        System.out.println(Arrays.asList(listNames));
    }

    @Test
    void lsCommand() throws SftpException, IOException {
        String remoteFileDir = "upload/";
        List<?> ls = sftpService.lscommand(remoteFileDir);
        for (Object l : ls){
        System.out.println(l);
        }
    }

    @Test
    public void validateListNames(){
        String[] filenames = {
                "БТ-012-23-12_02.pdf",
                "БТ-012-23-12_02.tiff",
                "БТ-012-23-12_02.jpeg",
                "БТ-012-23-12_02.123",
                "БТ-012-23-12_02.pfd"
        };
        List<String> validatedList = sftpService.validateListNames(filenames);
        System.out.println(validatedList);
    }



}
