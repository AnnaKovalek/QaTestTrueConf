package com.trueconf.videochat.test.testSendMessage;

import com.robotium.solo.*;

import static junit.framework.Assert.assertTrue;

public class TestMessage {
    private Solo solo;

    public TestMessage(Solo solo) {
        this.solo = solo;
    }

    public void testRun() {
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 2000);
        //Click on Have an account? Log In
        //Set default small timeout to 11881 milliseconds
        Timeout.setSmallTimeout(11881);
        solo.clickOnView(solo.getView("tv_is_have_account"));
        //Click on Empty Text View
        solo.clickOnView(solo.getView("et_videochat_id"));
        //Enter the text: 'kovalek'
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");
        //Enter the text: 'pop2233'
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");
        //Press next button which will start a new Activity
        solo.pressSoftKeyboardNextButton();
        //Click on Login
        solo.clickOnView(solo.getView("btn_login_ll"));
        //Wait for activity: 'com.trueconf.gui.activities.ContactTabs'
        assertTrue("ContactTabs is not found!", solo.waitForActivity("ContactTabs"));
        //Click on rtsp, LinearLayout LinearLayout LinearLayout
        solo.clickLongInList(1, 0,1);
        //Click on ImageView
        solo.clickOnView(solo.getView(android.widget.ImageView.class, 13));
        //Wait for activity: 'com.trueconf.gui.activities.UserProfile'
        //check that we have the right activity
        assertTrue("UserProfile is not found!", solo.waitForActivity("UserProfile"));
        //Press menu back key
        solo.goBack();
        //Click on HomeView
        solo.clickOnView(solo.getView(android.widget.FrameLayout.class, 13));
        //Click on Search LinearLayout
        solo.clickInList(8, 2);
        // Set small timeout to 13130 millisecond
        Timeout.setSmallTimeout(13130);
        //Enter the text for search by name: 'stetsi'
        solo.clearEditText((android.widget.EditText) solo.getView("search_src_text"));
        solo.enterText((android.widget.EditText) solo.getView("search_src_text"), "виро");
        //Wait for 2 second
        solo.sleep(2000);
        // English - 2 in list, Russian -1
        solo.clickInList(1, 0);
        //Click on ImageView
        solo.clickOnView(solo.getView(android.widget.ImageView.class, 12));
        //Wait for activity: 'com.vc.gui.activities.Chat' (check that we have the right activity)
        assertTrue("Chat is not found!", solo.waitForActivity("Chat"));
        //Click on Empty Text View
        solo.clickOnView(solo.getView("et_chat_message"));
        // Send messages n=9
        int i = 0;
        while (i < 5) {
            //Enter the text: 'Test message test'
            solo.clearEditText((android.widget.EditText) solo.getView("et_chat_message"));
            solo.enterText((android.widget.EditText) solo.getView("et_chat_message"), "Test message " + i);
            //Click on ImageView
            solo.clickOnView(solo.getView("btn_chat_message_send"));
            i++;
        }

        //Press menu back key
        solo.goBack();
        //Click on HomeView
        solo.clickOnView(solo.getView(android.widget.FrameLayout.class, 13));
        //Scroll to Logout fear724@trueconf.com
        android.widget.ListView listView0 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 2);
        solo.scrollListToLine(listView0, 3);
        //Click on Logout kovalek@trueconf.com
        solo.clickOnText(java.util.regex.Pattern.quote("Logout"));
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
        //Press menu back key
        solo.goBack();
    }

}
