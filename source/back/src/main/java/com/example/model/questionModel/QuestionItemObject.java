package com.example.model.questionModel;

public class QuestionItemObject {
    private String prefix;

    private String content;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QuestionItemObject{" +
                "prefix='" + prefix + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
