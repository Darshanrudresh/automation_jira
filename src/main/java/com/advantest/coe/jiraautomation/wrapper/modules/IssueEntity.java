package com.advantest.coe.jiraautomation.wrapper.modules;

import java.util.List;

/**
 * @author darshan.rudresh
 */
public class IssueEntity {
    private int id;
    private String expand;
    private String self;
    private String key;
    private String issuetype;
    private String project;
    private String created;
    private String updated;
    private String status;
    private String description;
    private String summary;
    private List<String> comments;
    private List<String> attachments;
    private String watches;
    private String assigned;
    private String creator;
    private String reporter;

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getWatches() {
        return watches;
    }

    public void setWatches(String watches) {
        this.watches = watches;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "IssueEntity{" +
                "id=" + id +
                ", expand='" + expand + '\'' +
                ", self='" + self + '\'' +
                ", key='" + key + '\'' +
                ", issuetype='" + issuetype + '\'' +
                ", project='" + project + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", comments=" + comments +
                ", attachments=" + attachments +
                ", watches='" + watches + '\'' +
                ", assigned='" + assigned + '\'' +
                ", creator='" + creator + '\'' +
                ", reporter='" + reporter + '\'' +
                '}';
    }
}
