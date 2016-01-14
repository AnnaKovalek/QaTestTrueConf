package com.trueconf.videochat.test.testActivity;

import android.view.View;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки звонков юзеру
 */
public class TestCallUser {
    private Solo solo;

    public TestCallUser(Solo solo) {
        this.solo = solo;
    }

    public void testCallUser() {

        //Запуск приложения
        solo.sleep(2000);
        // 1.1 Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 3000);
        // 1.2 Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        solo.sleep(2000);
        // 1.3 Click on Have an account Log in
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(3000);

        // Edit login and password
        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");

        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");

        solo.pressSoftKeyboardNextButton();
        // 1.6 Click on Login
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
        solo.sleep(1000);

        // 1.7 Проверка стартового уведомления по id
        View menuDialogHeader = null;
        try {
            menuDialogHeader = solo.getView("menuDialogHeader");
        } catch (AssertionFailedError e) {
            //nothing
        }
        if (menuDialogHeader != null) {
            //если активное, нажимаем Back
            solo.goBack();
        }

        //Click on HomeView
        solo.clickOnView(solo.getView(android.widget.FrameLayout.class, 13));
        //Click on Search LinearLayout
        solo.clickInList(8, 2);
        // Set small timeout to 13130 millisecond
        Timeout.setSmallTimeout(13130);
        //Enter the text for search by name: 'kovale'
        solo.clearEditText((android.widget.EditText) solo.getView("search_src_text"));
        solo.enterText((android.widget.EditText) solo.getView("search_src_text"), "kovale");
        //Wait for 2 second
        solo.sleep(2000);
        // English - 2 in list, Russian -1
        solo.clickInList(2, 0);
        solo.sleep(2000);
       // solo.clickOnImage((android.widget.ImageButton) solo.getImageButton(1));
        solo.clickOnView(solo.getImage(1));
       // solo.clickOnView(solo.getView("CALL"));
        solo.sleep(2000);

/**
 // Click on ActionBar - поиск по full_name and ID
 solo.clickOnActionBarItem(1);
 solo.sleep(2000);
 Timeout.setSmallTimeout(12000);
 // Enter the text for search by ID: "anna_m"

 solo.clickOnView(solo.getView("search_src_text"));
 solo.clearEditText((android.widget.EditText) solo.getView("search_src_text"));
 solo.enterText((android.widget.EditText) solo.getView("search_src_text"), "anna_m");

 solo.sleep(2000);
 //Click
 solo.clickInList(0, 0);
 solo.clickLongOnView(solo.getView(android.widget.ImageView.class, 12));
 solo.sleep(2000);
 */


    }


}
