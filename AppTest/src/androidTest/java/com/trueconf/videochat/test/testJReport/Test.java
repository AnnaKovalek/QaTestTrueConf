package com.trueconf.videochat.test.testJReport;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Test implements Serializable {
    private int id;
    private String name;
    private String title;
    private List<String> stagesOfTest;
    private List<TestCase> testCases;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStagesOfTest(List<String> stagesOfTest) {
        this.stagesOfTest = stagesOfTest;
    }


    public Test() {
        this.testCases = new LinkedList<TestCase>();
        this.stagesOfTest = new LinkedList<String>();
    }

    public void addTest(TestCase test) {
        this.testCases.add(test);
    }

    public List<String> getStagesOfTest() {
        return stagesOfTest;
    }

    public void addStagesOfTest(String stages) {
        this.stagesOfTest.add(stages);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", stagesOfTest=" + stagesOfTest +
                ", testCases=" + testCases +
                '}';
    }
}
