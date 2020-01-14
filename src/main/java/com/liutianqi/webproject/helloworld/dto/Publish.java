package com.liutianqi.webproject.helloworld.dto;

public class Publish {

    private String title;
    private String question;
    private String tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Publish{" +
                "title='" + title + '\'' +
                ", question='" + question + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
