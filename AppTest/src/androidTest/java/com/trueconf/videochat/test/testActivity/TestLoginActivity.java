package com.trueconf.videochat.test.testActivity;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/***
 * automatic black-box UI tests for Android applications TrueConf
 */

public class TestLoginActivity {
    private Solo solo;

    public TestLoginActivity(Solo solo) {
        this.solo = solo;
    }

    //Test 1
    public void testLoginActivitySingUp() {
        solo.sleep(2000);
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 10000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on "Sign up for TrueConf"
        solo.clickOnView(solo.getView("tv_registrate"));
        //check that we have the right activity
        assertTrue("Register is not found!", solo.waitForActivity("Register"));
        solo.sleep(4000);
        solo.goBack();
        // check that we have the right activity
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
    }

    //Test 2
    public void testLoginActivityLoginIn() {
        solo.sleep(2000);
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 3000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on "Have an account Log in0"
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(3000);
        assertTrue("Login Activity is not found", solo.waitForActivity("Login"));
        solo.goBack();
    }

    //Test 3
    public void testIsHaveAccount() {
        solo.sleep(2000);
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 3000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on Have an account Log in
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(3000);
        //Click on Empty Text View
        solo.clickOnView(solo.getView("et_videochat_id"));
        //Enter the text: 'kovalek'
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");
        solo.pressSoftKeyboardNextButton();

        //Click on Login
        solo.clickOnView(solo.getView("btn_login_ll"));

        assertFalse("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
        solo.goBack();
    }

    //Test4
    public void testHardvard() {


    }

    //Test5
    public void testFalseLogin() {
        solo.sleep(2000);
        solo.waitForActivity("Login", 10000);
        //spoon.screenshot (activity, "Login");
        Timeout.setSmallTimeout(12000);
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(2000);
        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));

        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");

        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertFalse("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
        solo.goBack();
    }

    //Test6
    public void testCorrectLogin() {
        solo.sleep(3000);
        solo.waitForActivity("Login", 10000);
        Timeout.setSmallTimeout(12000);
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(2000);

        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "fear724");

        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");

        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("Login"));
        solo.goBack();
    }

}
