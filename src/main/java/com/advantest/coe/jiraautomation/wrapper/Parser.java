package com.advantest.coe.jiraautomation.wrapper;

import org.jsoup.Jsoup;

public class Parser {
    /**
     * Remove the HTML tags from a given string.
     * @param toParse the String object to parse.
     * @return a String object without HTML tags.
     */
    public static String getTextFromHtml(String toParse) {
        return Jsoup.parse(toParse).text();
    }

}
