package com.advantest.coe.jiraautomation.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A utility that downloads a file from a URL.
 *
 * @author darshan.rudresh
 */

public class HttpDownloadUtility {
    private static final int BUFFER_SIZE = 4096;


    /**
     *      * Downloads a file from a URL
     *      * @param url HTTP URL of the file to be downloaded
     *      * @param saveDir path of the directory to save the file
     *      * @throws IOException
     *      
     */
    public void downloadFile(URL url, String saveDir, String userName, String passWord)
            throws IOException {
        System.out.println("=================================================");
        System.out.println("Download initiated");
        System.out.println("The received values of URL:  " + url + " Destination is: " + saveDir);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        String encoded = Base64.getEncoder()
                .encodeToString((userName + ":" + passWord).getBytes(StandardCharsets.UTF_8));

        httpConn.setRequestProperty("Authorization", "Basic " + encoded);
        int responseCode = httpConn.getResponseCode();
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpConn.getInputStream();
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveDir);
            //Repeatedly read array of bytes from the input stream and write them to the output stream, until the input stream is empty
            int bytesRead;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
            System.out.println("Attachment is downloaded successfully!!!!!!");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}

