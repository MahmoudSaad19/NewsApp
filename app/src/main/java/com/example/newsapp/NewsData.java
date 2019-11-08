package com.example.newsapp;

class NewsData {

    private String sectionName, webTitle, webPublicationDate, webUrl ,contributor;

    NewsData(String sectionName, String webTitle, String webPublicationDate, String url,String contributor) {
        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = url;
        this.contributor=contributor;
    }

    String getSectionName() {
        return sectionName;
    }

    String getWebTitle() {
        return webTitle;
    }

    String getWebPublicationDate() {
        return webPublicationDate;
    }

    String getWebUrl() {
        return webUrl;
    }

    String getContributor() {
        return contributor;
    }
}
