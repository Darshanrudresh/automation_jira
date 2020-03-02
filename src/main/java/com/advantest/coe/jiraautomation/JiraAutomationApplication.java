package com.advantest.coe.jiraautomation;

import com.advantest.coe.jiraautomation.wrapper.JiraDataRetrieval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author darshan.rudresh
 */
@SpringBootApplication
public class JiraAutomationApplication implements CommandLineRunner {

	@Autowired
	private JiraDataRetrieval jiraDataRetrieval;

	@Value("${jira_host}")
	private String localhost;

	public static void main(String[] args) {
		SpringApplication.run(JiraAutomationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//JIRA EXECUTION
		jiraDataRetrieval.getJiraProjects(localhost);
		System.out.println("Entering the springboot project");
	}
}
