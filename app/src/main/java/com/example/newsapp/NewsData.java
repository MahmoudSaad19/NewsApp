package com.example.newsapp;

public class NewsData {

    private String sectionName , webTitle , webPublicationDate , webUrl;

    String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public NewsData(String sectionName, String webTitle, String webPublicationDate, String url) {
        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = url;
    }

    String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    NewsData(String sectionName, String webTitle, String webPublicationDate) {
        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
