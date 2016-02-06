package com.trueconf.videochat.test.testActivity;


import android.view.View;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TestCreateConference {
    private Solo solo;

    public TestCreateConference(Solo solo) {
        this.solo = solo;
    }

    public void testCreateConference() {

        solo.sleep(4000);
        solo.waitForActivity("Login", 3000);
        Timeout.setSmallTimeout(12000);
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(2000);
        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");
        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");
        // 1.4 Click on Login button
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));

        // Стартовое уведомление (появляется при условии первого входа в приложения)
        View menuDialogHeader = null;
        try {
            menuDialogHeader = solo.getView("menuDialogHeader");
        } catch (AssertionFailedError e) {
            //nothing
        }
        if (menuDialogHeader != null) {
            solo.goBack();
        }
        solo.sleep(1000);
        //solo.clickOnText("Conference");
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_conference"));
        solo.sleep(500);
        assertTrue("Activity ConferenceSettings is not found", solo.waitForActivity("ConferenceSettings"));
        solo.sleep(500);


        // android.widget.ListView contactListView = solo.getView(ListView.class, 0);


        solo.clickOnView(solo.getView("et_conference_topic"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_conference_topic"));
        solo.enterText((android.widget.EditText) solo.getView("et_conference_topic"), "MyConference");
        solo.sleep(200);
        //выбор конференции
        solo.clickOnImage(1);
        solo.sleep(500);
/**
        ArrayList<View> currentViews = solo.getCurrentViews();
        assertEquals(currentViews, 1);
 */

        solo.clickOnView(solo.getView("cb_automatic_entry"));
        solo.sleep(200);
        ToggleButton toggleButton = (ToggleButton) solo.getView("cb_automatic_entry");
        assertTrue("ToggleButton is not found", solo.isToggleButtonChecked(0));
        solo.sleep(500);
        solo.clickOnView(toggleButton);
        assertFalse("ToggleButton in position ON", solo.isToggleButtonChecked(0));
        solo.sleep(100);
        solo.pressSoftKeyboardNextButton();

        //TODO: получить список возможных участников конференции (варианты кто онлайн, проверка необходимо участника для добавления в конфу)

        /**ArrayList<View> currentViews = solo.getCurrentViews();
         assertEquals(currentViews, 1);
         */

        //  solo.clickOnView(solo.getView("cb_automatic_entry"));
//        solo.clickOnToggleButton("cb_automatic_entry");
        //  solo.clickOnToggleButton("NO");
        //ToggleButton toggleButton1 = (ToggleButton) solo.getView("cb_automatic_entry");

        //assertTrue("ToggleButton is not found", solo.isToggleButtonChecked(0));
        solo.sleep(500);
        solo.clickOnView(solo.getView("btn_add_members"));
        solo.sleep(200);

        ListView listView = (ListView) solo.getView("lv_chat_contacts");
        listView.getItemAtPosition(0);



        try {
            solo.clickOnText("Anna Kovalek");
        } catch (AssertionFailedError e) {
            //nothing
        }

        //  solo.clickOnView(solo.getView("btn_add_members"));
        // solo.clickOnText("Igor Shtykh");
        solo.sleep(200);

        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_start_conference"));
        solo.sleep(1000);
        assertTrue("Activity Call is not found", solo.waitForActivity("Call"));
        solo.sleep(1000);
/**
 ArrayList<View> currentViews = solo.getCurrentViews();
 assertEquals(currentViews, 1);
 */

        // solo.pressSoftKeyboardNextButton();
        // solo.clickOnView(solo.getView("btn_share"));
        solo.sleep(2000);
        solo.goBack();
        assertTrue("Activity ContactTabs is not found", solo.waitForActivity("ContactTabs"));
        solo.sleep(500);


        /** 2. Выход с приложения  */
        solo.clickOnActionBarHomeButton();
        solo.sleep(300);
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.sleep(300);

        //2.1 Определяем Logout
        String itemLogout = (String) homeListView.getItemAtPosition(11);
        solo.sleep(300);

        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        solo.sleep(300);

        //2.3 Нажимаем на Logout
        solo.clickOnText(java.util.regex.Pattern.quote(itemLogout));
        solo.sleep(300);

        //2.4 проверка на переход Activity Login
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
        solo.sleep(300);
        solo.goBack();


    }


}
