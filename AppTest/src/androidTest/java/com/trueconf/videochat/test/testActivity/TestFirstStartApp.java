package com.trueconf.videochat.test.testActivity;

import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertTrue;

public class TestFirstStartApp {
    private Solo solo;

    public TestFirstStartApp(Solo solo) {
        this.solo = solo;
    }

    //Test1
    public void testCorrectLogin() {
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
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "fear724");

        //Enter the text: '2687484'
        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "2687484");

        solo.pressSoftKeyboardNextButton();

        //Click on Login
        solo.clickOnView(solo.getView("btn_login_ll"));

        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));


        //Проверка стартового уведомления по id
        View menuDialogHeader = null;
        try{
            menuDialogHeader = solo.getView("menuDialogHeader");
        }catch (AssertionFailedError e){
            //nothing
        }
        if (menuDialogHeader!=null) {
            //если активное, нажимаем Back
            solo.goBack();
        }


        //Реализация выхода с App
        solo.clickOnActionBarHomeButton();
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.scrollListToLine(homeListView, 3);
        //Click on Logout kovalek@trueconf.com
        solo.clickOnText(java.util.regex.Pattern.quote("Logout"));
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
    }


}
