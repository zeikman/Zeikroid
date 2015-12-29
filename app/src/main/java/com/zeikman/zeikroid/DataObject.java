package com.zeikman.zeikroid;

/**
 * Created by zeikman on 12/29/15.
 */
public class DataObject {
    private String title, content;

    DataObject(String text1, String text2) {
        title = text1;
        content = text2;
    }

    public String getTitle() {
        return title;
    }

    public  String getContent() {
        return content;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setContent(String newContent) {
        this.content = newContent;
    }
}
