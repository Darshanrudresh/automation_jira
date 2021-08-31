package com.advantest.coe.jiraautomation.propertiesparser;

import java.io.*;
import java.util.Properties;


/**
 * Spilt the description and stores in each line base on new line.
 * Also spilt the line and store as key value pair based on colon as delimiter
 *
 * @author darshan.rudresh
 */

public class JiraProperties {
    public void reader(String description, String outDIr) throws IOException {
        String pathPropertyFile = outDIr + "config_jira.properties";

        File dirCheck = new File(outDIr);
        if (dirCheck.isDirectory()) {
            File f = new File(pathPropertyFile);
            FileWriter propertyFileWriter = new FileWriter(pathPropertyFile, false);

            System.out.println("Directory is present initiated to create .properties file");

            try {
                String[] descriptionByLine = description.split("\r\n");
                Properties prop = new Properties();
                for (String line : descriptionByLine) {
                    if (line != null) {
                        String[] properties = line.split(":");
                        if (properties.length > 1) {
                            prop.setProperty(properties[0].trim(), properties[1].trim());
                        }
                    }
                }
                System.out.println("Config.properties file is created!!!!!");
                System.out.println("=================================================");
                OutputStream out = new FileOutputStream(f);
                prop.store(out, "Data to feed to jenkins");
                propertyFileWriter.close();

            } catch (IOException e) {
                System.out.println("Failed to create config properties!!!!!!!!" + e.getMessage());
            }
        } else {
            System.out.println("directory not present");
        }
    }
}