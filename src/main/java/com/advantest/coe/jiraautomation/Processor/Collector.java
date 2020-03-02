package com.advantest.coe.jiraautomation.Processor;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author darshan.rudresh
 */
public class Collector {

    @JsonProperty("backlink")
    private String backlink;

    @JsonProperty("datasourceIdentifier")
    private String datasourceIdentifier;

    @JsonProperty("textBody")
    private String textBody;

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public String getDatasourceIdentifier() {
        return datasourceIdentifier;
    }

    public void setDatasourceIdentifier(String datasourceIdentifier) {
        this.datasourceIdentifier = datasourceIdentifier;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    @Override
    public String toString() {
        return "Collector{" +
                ", backlink='" + backlink + '\'' +
                ", datasourceIdentifier='" + datasourceIdentifier + '\'' +
                ", textBody='" + textBody + '\'' +
                '}';
    }
}
