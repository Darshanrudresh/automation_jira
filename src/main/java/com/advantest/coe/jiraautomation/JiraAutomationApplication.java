package com.advantest.coe.jiraautomation;

import com.advantest.coe.jiraautomation.wrapper.JiraDataRetrieval;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * @author darshan.rudresh
 */
@SpringBootApplication
public class JiraAutomationApplication implements CommandLineRunner {

    @Autowired
    private JiraDataRetrieval jiraDataRetrieval;

    @Value("${jira_host}")
    private String jiraHost;


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(JiraAutomationApplication.class, args);
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {
        String crNum = null;
        String foldName = null;
        final Option number = Option.builder("CRNumber")
                .required()
                .hasArg()
                .longOpt("CRNumber")
                .desc("CRNumber must be added.")
                .build();
        final Option outputDir = Option.builder("OutputDir")
                .required()
                .longOpt("OutputDir")
                .hasArg()
                .desc("Output Directory must be added.")
                .build();


        final Options options = new Options();
        options.addOption(number);
        options.addOption(outputDir);


        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);


            if (cmd.hasOption("CRNumber") && cmd.hasOption("OutputDir")) {


                crNum = cmd.getOptionValue("CRNumber");
                foldName = cmd.getOptionValue("OutputDir");
            }
            boolean val=foldName.endsWith("/");
            if(!val){
                foldName=foldName+"/";
            }

            //JIRA EXECUTION
            jiraDataRetrieval.getJiraProjects(jiraHost, crNum, foldName);
            System.out.println("All the tasks are completed ");
            System.out.println("=================================================");

        } catch (ParseException parseException) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Valid Argument List are below", options);
            System.out.println(
                    "ERROR: Unable to parse command-line arguments "
                            + parseException.getMessage());
        }


    }
}
