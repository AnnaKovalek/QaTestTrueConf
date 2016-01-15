package com.trueconf.videochat.test.testJReport;


import com.robotium.solo.Solo;

import junit.framework.AssertionFailedError;

import java.util.Random;

import static junit.framework.Assert.assertTrue;

public class JReport extends IOReport {
    protected Solo solo;
    private Test test;
    private boolean success = true;
    private static int countTest = 1;
    private int countTestCase = 1;
    private int countImage = 1;
    private int generateId;
    private int generateCountImage;



    protected JReport(Solo solo) {
        this.solo = solo;
        this.test = new Test();
    }

    protected void initReport(String name, String title) {
        this.solo.sleep(3000);
        this.test.setId(countTest++);
        this.test.setName(name);
        this.test.setTitle(title);
        this.generateId = new Random().nextInt(1000);
        this.generateCountImage = new Random().nextInt(100000);
        solo.takeScreenshot("init");
    }

    /**
     * assertTextCase(String tittleTestCase)
     *
     * Данный метод получает на вход имя TestCase
     * */
    protected void assertTextCase(String tittleTestCase) {
        solo.sleep(1000);
        TestCase testCase = new TestCase();
        testCase.setId(countTestCase++);
        testCase.setTitle(tittleTestCase);
        testCase.setImage(screenshot(generateCountImage++));
        testCase.setTime(20);
        testCase.setSuccessfully(getSuccess());
        test.addTest(testCase);
        test.addStagesOfTest(tittleTestCase);
        solo.sleep(1200);
    }

    /**
     * assertTextCaseActivity(String title, String activity)
     *
     * Данный метод получает на вход имя TestCase и имя проверяемого Activity
     * */
    protected void assertTextCaseActivity(String title, String activity) {
        solo.sleep(1000);
        TestCase testCase = new TestCase();
        testCase.setId(countTestCase++);
        testCase.setTitle(title);
        testCase.setImage(screenshot(generateCountImage++));
        testCase.setTime(20);
        testCase.setSuccessfully(getSuccess());
        try {
            assertTrue("Login is not found!", solo.waitForActivity(activity));
        } catch (AssertionFailedError e) {
            testCase.setSuccessfully(false);
            testCase.setErrorMessage(e.getMessage());
            throw new IllegalArgumentException(e);
        } finally {
            test.addTest(testCase);
            test.addStagesOfTest(title);
        }
        solo.sleep(1200);
    }

    protected void destroyReport(String errorMesage) {
        destroy(test, errorMesage);
    }

    public boolean getSuccess() {
        return success;
    }

    private String screenshot(Integer image) {
        StringBuilder builder = new StringBuilder();
        builder.append(countImage++ + " " + image + " " + generateId);
        String result = builder.toString();
        solo.takeScreenshot(result);
        return result;
    }


}