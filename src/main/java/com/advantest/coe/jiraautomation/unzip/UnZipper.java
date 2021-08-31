package com.advantest.coe.jiraautomation.unzip;

import org.codehaus.plexus.archiver.tar.TarGZipUnArchiver;
import org.codehaus.plexus.logging.console.ConsoleLoggerManager;


import java.io.File;

/**
 * Unzips the .tar.gz file
 * @author darshan.rudresh
 */

public class UnZipper {

    public void extractor(String destination, String tmp) {
        File saveDir = new File(destination);
        File out = new File(tmp);
        if(out.isDirectory()){
            System.out.println("Directory is there Hence Unzip is initiated");
        }else {
            System.out.println("Directory not present Hence Cannot unzip the file");
            System.exit(1);
        }
        TarGZipUnArchiver archive = new TarGZipUnArchiver();
        ConsoleLoggerManager manager = new ConsoleLoggerManager();
        manager.initialize();
        archive.enableLogging(manager.getLoggerForComponent("Advantest"));
        try {
            archive.setSourceFile(saveDir);
            //out.mkdirs();
            archive.setDestDirectory(out);
            archive.extract();
            System.out.println("Extraction is successfully!!!!!!");
            System.out.println("=================================================");

        } catch (Exception e) {
            System.out.println("Failed in extraction !!!!!!!" + e.getMessage());
        }

    }
}
