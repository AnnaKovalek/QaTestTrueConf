package com.trueconf.videochat.test.testJReport;

import java.io.Serializable;

public class TestCase implements Serializable {
    private int id;
    private String title;
    private String image;
    private String errorMessage = "No error";
    private boolean successfully;
    private int time;

    public TestCase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getSuccessfully() {
        return successfully;
    }

    public void setSuccessfully(boolean successfully) {
        this.successfully = successfully;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", successfully=" + successfully +
                ", time=" + time +
                '}';
    }
}
