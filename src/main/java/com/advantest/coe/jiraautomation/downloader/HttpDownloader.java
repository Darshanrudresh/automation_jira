package com.advantest.coe.jiraautomation.downloader;

import com.advantest.coe.jiraautomation.unzip.UnZipper;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class which calls DownloadUtility.class to download a file and calls unzipper.class to extact the class.
 *
 * @author darshan.rudresh
 */


public class HttpDownloader {


    public void downloader(String content, String outDir, String userName, String passWord) throws MalformedURLException {

        HttpDownloadUtility httpDownloadUtility = new HttpDownloadUtility();
        UnZipper unzipper = new UnZipper();
        URL url = new URL(content);
        String filename = FilenameUtils.getName(url.getPath());
        String destination = outDir + filename;


        try {
            httpDownloadUtility.downloadFile(url, destination, userName, passWord);
        } catch (IOException ex) {
            System.out.println("Failed to download the attachment!!!!!!!" + ex.getMessage());
        }
        unzipper.extractor(destination, outDir);
    }
}



