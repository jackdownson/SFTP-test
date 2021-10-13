package com.example.sftptest.sftp;

import com.example.sftptest.service.WorkWithFile;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SftpService {


    private final SftpSessionFactory sftpSessionFactory;
    private final WorkWithFile workWithFile;


    private final String host = "127.0.0.1";
    private final String username = "teplo";
    private final String pass = "teplo";
    private final List validExtensions = Arrays.asList("tiff", "jpeg", "pdf");


    public void upload() {
        SftpSession session = sftpSessionFactory.createFactory().getSession();

        InputStream testupload = SftpService.class.getClassLoader().getResourceAsStream("Java.pdf");

        try {
            session.write(testupload, "upload/Java" + LocalDateTime.now() + ".pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        session.close();
    }

    public String downloadTiffPdfJpeg(String localFiledir, String remoteFileDir) {
        SftpSession session = sftpSessionFactory.createFactory().getSession();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ChannelSftp channel = session.getClientInstance();
            channel.cd(remoteFileDir);
            List<String> validListNamesForDownload = validateListNames(session.listNames(remoteFileDir));
            for (String filename : validListNamesForDownload) {
                channel.get(filename, localFiledir + filename);

            }
            return byteArrayOutputStream.toString();

        } catch (SftpException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] listNames() throws IOException {
        SftpSession session = sftpSessionFactory.createFactory().getSession();
        return session.listNames("/upload");
    }

    //что делать если Vector не параметризирован. ковертировать в ArrayList?
    public List<?> lscommand(String remoteFileDir) throws SftpException, IOException {
        SftpSession session = sftpSessionFactory.createFactory().getSession();
        ChannelSftp channel = session.getClientInstance();
        return channel.ls(remoteFileDir);
    }

    public String[] lsOnlyNames(String remoteFileDir) {
        try {
            SftpSession session = sftpSessionFactory.createFactory().getSession();
            return session.listNames(remoteFileDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //todo change privacy
    public List<String> validateListNames(String[] fileNames) {
        List<String> listFileNames = new ArrayList<>(Arrays.asList(fileNames));
        List<String> validated = listFileNames.stream()
                .filter(name -> validExtensions.contains(workWithFile.learnFileExtension(name)))
                .collect(Collectors.toList());
        return validated;
    }


    public List<?> learnFileWeight() {

        try {
            List<?> lscommand = lscommand("/upload");
            String s = (String) lscommand.get(3);
            for (char i : s.toCharArray()){
                System.out.println(i);
            }

            return lscommand;
        } catch (IOException | SftpException e ) {
            throw new RuntimeException(e);
        }

    }
}
