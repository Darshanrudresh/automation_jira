package com.advantest.coe.jiraautomation.wrapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import com.advantest.coe.jiraautomation.Processor.Collector;
import com.advantest.coe.jiraautomation.wrapper.modules.AttachmentEntity;
import com.advantest.coe.jiraautomation.wrapper.modules.IssueEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
/**
 * @author darshan.rudresh
 */

@Service
public class JiraDataRetrieval {


    @Value("${jira_host}")
    private String host;
    @Value("${jira_tmp}")
    private String tmp;

    private static ArrayList<IssueEntity> issueObjects = new ArrayList<>();
    private static ArrayList<AttachmentEntity> attachmentEntities = new ArrayList<>();

    private static ArrayList<String> issueIds = new ArrayList<>();

    private ObjectMapper mapper = new ObjectMapper();

@Autowired
private  RestTemplate restTemplate;

    @Resource
    private Environment environment;

    public JiraDataRetrieval() {}


    public void getJiraProjects(String url) throws IOException {
        /**
         * define request properties
         */
        String fooResourceUrl
                = url + "/rest/api/2/issue/AD-1";
        /**
         * execute get request
         */
      ResponseEntity<String> commentJson = restTemplate.getForEntity(fooResourceUrl,String.class);
      restTemplate.getForObject("http://localhost:8080/secure/attachment/10102/DARSHAN_RUDRESH_2020-02-12_1558.pdf",String.class);
       // ResponseEntity<String> commentJson = restTemplate.getForEntity("https://wetrack.advantest.com/rest/api/2/issue/CR-119044",String.class);
        JsonNode commentTree = new ObjectMapper().readTree(Objects.requireNonNull(commentJson.getBody()));
        System.out.println("commenttree"+commentTree);
        getIssues(commentTree);
    }

    public ArrayList<String> getAttachments(ArrayNode attachments, IssueEntity issue) throws IOException {
        ArrayList<String> attachmentIds = new ArrayList<>();
        for (JsonNode attachment : attachments) {
            AttachmentEntity attachmentEntity = new AttachmentEntity();
            attachmentEntity.setId(attachment.path("id").asInt());
            attachmentEntity.setAuthor(attachment.path("author").path("name").asText());
            attachmentEntity.setContent(attachment.path("content").asText());
            attachmentEntity.setCreated(attachment.path("created").asText());
            attachmentEntity.setFilename(attachment.path("filename").asText());
            attachmentEntity.setSelf(attachment.path("self").asText());
            attachmentEntity.setSize(attachment.path("size").asInt());
            attachmentEntity.setThumbnail(attachment.path("thumbnail").asText());
            attachmentEntities.add(attachmentEntity);
            attachmentIds.add(attachment.path("id").asText());
            Collector collector = new Collector();
           // collector.setBacklink(host + "/browse/" + issue.getKey());
            //collector.setDatasourceIdentifier("wetrack_Attachment");
            collector.setTextBody(attachmentEntity.getContent());
            mapper.writeValue(new File(tmp + "/Jira_attachment_" + attachmentEntity.getId() + ".json"), collector);
            System.out.println("Creating JSON file => Attachment " + attachmentEntity.getId() + ".json");
        }
        return attachmentIds;
    }

    public void getIssues(JsonNode issue) throws IOException {
        IssueEntity issueEntity = new IssueEntity();
        String bodyNoHtml = Jsoup.parse(issue.path("fields").path("description").asText()).text();
        issueEntity.setId(issue.path("id").asInt());
        issueEntity.setExpand(issue.path("expand").asText());
        issueEntity.setSelf(issue.path("self").asText());
        issueEntity.setKey(issue.path("key").asText());
        issueEntity.setIssuetype(issue.path("fields").path("issuetype").path("name").asText());
        issueEntity.setProject(issue.path("fields").path("project").path("name").asText());
        issueEntity.setCreated(issue.path("fields").path("created").asText());
        issueEntity.setStatus(issue.path("fields").path("status").path("name").asText());
        issueEntity.setUpdated(issue.path("fields").path("updated").path("name").asText());
        issueEntity.setDescription(issue.path("fields").path("description").asText());
        //issueEntity.setDescription(bodyNoHtml);
        //System.out.println("1111111111"+issue.path("fields").path("description").asText());
        //System.out.println("2222222222"+issueEntity.getDescription());
        issueEntity.setSummary(issue.path("fields").path("summary").asText());
        issueEntity.setReporter(issue.path("fields").path("reporter").path("name").asText());
        issueEntity.setCreator(issue.path("fields").path("creator").path("name").asText());
        issueEntity.setAssigned(issue.path("fields").path("assignee").path("name").asText());
        issueEntity.setWatches(issue.path("fields").path("watches").path("self").asText());
        ArrayNode attachments = (ArrayNode) issue.path("fields").path("attachment");
        issueEntity.setAttachments(getAttachments(attachments, issueEntity));
        Collector collector = new Collector();
        //collector.setBacklink(host + "/projects/" + issue.path("fields").path("project").path("key").asText() + "/issues/" + issueEntity.getKey());
        //collector.setDatasourceIdentifier("wetrack_description");
        collector.setTextBody(issueEntity.getDescription());
        issueObjects.add(issueEntity);
        issueIds.add(String.valueOf(issueEntity.getId()));
        mapper.writeValue(new File(tmp + "/Jira_issue_" + issueEntity.getId() + ".json"), collector);
        System.out.println("Creating JSON file => Issue " + issueEntity.getId() + ".json");
    }


    @Bean(name = "JiraBean")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication(
                environment.getRequiredProperty("jira_user_name"),
                environment.getRequiredProperty("jira_user_password"))
                .build();
    }

}
