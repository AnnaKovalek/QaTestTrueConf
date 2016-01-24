package com.trueconf.videochat.test;

import com.robotium.solo.*;
import com.trueconf.videochat.test.testActivity.TestCallUser;
import com.trueconf.videochat.test.testActivity.TestFirstStartApp;
import com.trueconf.videochat.test.testActivity.TestListNavigationDrawer;
import com.trueconf.videochat.test.testActivity.TestLoginActivity;
import com.trueconf.videochat.test.testActivity.TestLoginActivity_Button_ChangeServer;
import com.trueconf.videochat.test.testActivity.TestSendMessage;
import com.trueconf.videochat.test.testActivity.TestSendMessage_2;
import com.trueconf.videochat.test.testActivity.TestSettings;
import com.trueconf.videochat.test.testActivity.TestUserProfile;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Основной тестовый класс
 */

public class TestTrueConf extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    private TestLoginActivity testLoginActivity;
    private TestFirstStartApp testFirstStartApp;
    private TestLoginActivity_Button_ChangeServer testLoginActivity_button_changeServer;
    private TestListNavigationDrawer testListNavigationDrawer;
    private TestSendMessage testSendMessage;
    private TestCallUser testCallUser;
    private TestSendMessage_2 testSendMessage_2;
    private TestUserProfile testUserProfile;
    private TestSettings testSettings;


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
        testLoginActivity_button_changeServer = new TestLoginActivity_Button_ChangeServer(solo);
        testListNavigationDrawer = new TestListNavigationDrawer(solo);
        testSendMessage = new TestSendMessage(solo);
        testCallUser = new TestCallUser(solo);
        testSendMessage_2 = new TestSendMessage_2(solo);
        testUserProfile = new TestUserProfile(solo);
        testSettings = new TestSettings(solo);
        getActivity();
    }

    // ****************************  TestLoginActivity    ******************************
    // TestLoginActivity: Test 1
    public void testLoginActivitySingUp() {
        testLoginActivity.testLoginActivitySingUp();
    }

    // TestLoginActivity: Test 2
    public void testLoginActivityLoginIn() {
        testLoginActivity.testLoginActivityLoginIn();
    }

    // TestLoginActivity: Test 3
    public void testLoginActivityIsHaveAccount() {
        testLoginActivity.testIsHaveAccount();
    }

    // TestLoginActivity: Test4
    public void testHardWardButton() {
        testLoginActivity.testHardWardButton();
    }

    // TestLoginActivity: Test5
    public void testFalseLogin() {
        testLoginActivity.testFalseLogin();
    }

    // TestLoginActivity: Test6
    public void testCorrectLogin() {
        testLoginActivity.testCorrectLogin();
    }

    // TestLoginActivity:  Test7
    public void testButtonChangeServer() {
        testLoginActivity.testButtonChangeServer();
    }

    // ****************************  TestFirstStartApp    ******************************
    // TestFirstStartApp: Test 1
    public void testFirstLoginToListActivityAndBack() {
        testFirstStartApp.testCorrectLogin();
    }

    // ****************************  TestLoginActivity_Button_ChangeServer    ******************************
    public void testButtonChangeServer_01() {
        testLoginActivity_button_changeServer.testButtonChangeServer();
    }

    public void testButtonChangeServer_02() {
        testLoginActivity_button_changeServer.testButtonChangeServer_2();
    }

    // ******************************** TestListNavigationDrawer **********************************************
    public void testListNavigationDrawer() {
        testListNavigationDrawer.testListNavigationDrawer();
    }

    // ********************************* TestSendMessage ******************************************************
    public void testSendMessage() {
        testSendMessage.testSendMessage();
    }

    public void testCallUser() {
        testCallUser.testCallUser();
    }

    public void testSendMessage_2() {
        testSendMessage_2.testSendMessage_2();
    }
    // ******************************* TestUserProfile ******************************************************
    public void testUserProfile() {
        testUserProfile.testUserProfile();
    }

    public void testSettings(){
        testSettings.testSettings();
    }

    //After
    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
