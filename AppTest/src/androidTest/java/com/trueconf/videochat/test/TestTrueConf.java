package com.trueconf.videochat.test;

import com.robotium.solo.*;
import com.trueconf.videochat.test.testActivity.TestFirstStartApp;
import com.trueconf.videochat.test.testActivity.TestLoginActivity;

import android.test.ActivityInstrumentationTestCase2;


public class TestTrueConf extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    private TestLoginActivity testLoginActivity;
    private TestFirstStartApp testFirstStartApp;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.trueconf.gui.activities.Login";

    private static Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TestTrueConf() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

    //Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        testLoginActivity = new TestLoginActivity(solo);
        testFirstStartApp = new TestFirstStartApp(solo);
        getActivity();
    }


    //Test 1
    public void testLoginActivitySingUp() {
        testLoginActivity.testLoginActivitySingUp();
    }

    //Test 2
    public void testLoginActivityLoginIn() {
        testLoginActivity.testLoginActivityLoginIn();
    }

    //Test 3
    public void testLoginActivityIsHaveAccount() {
        testLoginActivity.testIsHaveAccount();
    }

    //Test4
    public void testHardvard() {
        testLoginActivity.testHardvard();
    }

    //Test5
    public void testFalseLogin() {
        testLoginActivity.testFalseLogin();
    }

    //Test6
    public void testCorrectLogin() {
        testLoginActivity.testCorrectLogin();
    }

    //Test7
    public void testFirstLoginToListActivityAndBack() {
        testFirstStartApp.testCorrectLogin();
    }

    //After
    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
