package com.trueconf.videochat.test.testActivity;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/***
 * automatic black-box UI tests for Android applications TrueConf
 */

public class TestLoginActivity  {
    private Solo solo;

    public TestLoginActivity(Solo solo) {
        this.solo = solo;
    }

    //Test 1
    public void testLoginActivitySingUp() {
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 2000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on "Sign up for TrueConf"
        solo.clickOnView(solo.getView("tv_registrate"));
        //check that we have the right activity
        assertTrue("Register is not found!", solo.waitForActivity("Register"));
        solo.sleep(3000);
        solo.goBack();
        solo.sleep(2000);
        // check that we have the right activity
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
    }

    //Test 2
    public void testLoginActivityLoginIn() {
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 2000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on "Have an account Log in0"
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(5000);
        assertTrue("Login Activity is not found", solo.waitForActivity("dgggsdgsd"));
        solo.goBack();
    }

    //Test 3
    public void testIsHaveAccount() {
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 2000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on Have an account Log in
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(5000);
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

}
