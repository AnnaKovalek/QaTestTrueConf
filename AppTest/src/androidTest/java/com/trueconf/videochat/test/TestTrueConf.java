package com.trueconf.videochat.test;

import com.robotium.solo.*;
import com.trueconf.videochat.test.testActivity.TestLoginActivity;
import com.trueconf.videochat.test.testSendMessage.TestMessage;

import android.test.ActivityInstrumentationTestCase2;


public class TestTrueConf extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    private TestLoginActivity testLoginActivity;
    private TestMessage testMessage;
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

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        testLoginActivity = new TestLoginActivity(this.solo);
        testMessage = new TestMessage(this.solo);
        getActivity();
    }

    public void testLoginActivity() {
        testLoginActivity.testIsHaveAccount();
        testLoginActivity.testLoginActivityLoginIn();
        testLoginActivity.testLoginActivitySingUp();
    }

    public void testMessage() {
        testMessage.testRun();
    }



    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
