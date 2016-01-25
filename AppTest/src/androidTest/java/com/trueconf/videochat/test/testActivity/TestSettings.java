package com.trueconf.videochat.test.testActivity;


import android.preference.CheckBoxPreference;
import android.preference.PreferenceCategory;
import android.view.View;
import android.widget.ListView;


import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки компонентов PreferenceActivity - Settings: checkBoxPreference, ListPreference, EditTextPreference
 * Настройки по умолчанию:
 * - Start automatically - isChecked
 * - Buzz - isChecked
 * - Synchronization - isChecked
 **/


public class TestSettings {
    private Solo solo;

    public TestSettings(Solo solo) {
        this.solo = solo;
    }

    /**
     * Метод проверки
     */
    public void testSettings() {

        // Подготовка приложения к запуску
        solo.sleep(4000);
        solo.waitForActivity("Login", 3000);
        Timeout.setSmallTimeout(12000);
        // 1.1 Click on Have an account Log in
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(2000);
        // 1.2 Click on Empty Text View
        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");
        // 1.3 Enter the text: my password 'pop2233'
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
            //если активное, нажимаем Back
            solo.goBack();
        }


        /** 3 Поиск контакта */
        //1. Определили главнй лист
        android.widget.ListView contactListView = solo.getView(ListView.class, 0);
        solo.scrollListToLine(contactListView, contactListView.getFirstVisiblePosition());
        solo.sleep(2000);

        //2. Нажатимаем на HomeButton
        solo.clickOnActionBarHomeButton();
        solo.sleep(300);
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.sleep(1000);

        //3 Определяем setting
        String itemSetting = (String) homeListView.getItemAtPosition(8);

        solo.sleep(300);

        //4 прокручиваем список
        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        solo.sleep(2000);
        // 4.1. нажимает на выбранную кнопку
        solo.clickOnText(itemSetting);
        solo.sleep(3000);
        // 4.2. проверка перехода на заданное активити
        assertTrue(" Ожидался вызов активити: SettingsHoneycomb при нажатии: " + itemSetting + " ,а вызвалось: " + solo.getCurrentActivity().getLocalClassName(),
                solo.waitForActivity("SettingsHoneycomb"));
        solo.sleep(1000);


        /** ````````````````````````````   SettingsHoneycomb ``````````````````````````````````````    */

        //Step 1 :
        //Step 1 : ArrayList<View> currentViews = solo.getCurrentViews();
        //Step 1 : assertEquals(currentViews, 1);

        //5.  Определяе ListView компонентов
        //Step 2
        android.widget.ListView settingListView = solo.getView(ListView.class, 0);
        solo.sleep(500);

        //5.1
        //CheckBoxPreference StartAutomatically
        CheckBoxPreference itemStartAutomatically = (CheckBoxPreference) settingListView.getItemAtPosition(0);
        testChecked(itemStartAutomatically, 0);

        //TODO settingListView.getItemAtPosition(1) не являеться CheckBoxPreference!!!!!

        //5.2
        //CheckBoxPreference ReceiveFilter
        CheckBoxPreference itemReceiveFilter = (CheckBoxPreference) settingListView.getItemAtPosition(2);
        testChecked(itemReceiveFilter, 1);


        //5.3
        //CheckBoxPreference AutoReplay
        CheckBoxPreference itemAutoReplay = (CheckBoxPreference) settingListView.getItemAtPosition(3);
        testChecked(itemAutoReplay, 2);

        //TODO settingListView.getItemAtPosition(4) не являеться CheckBoxPreference!!!!!

        /** ``````````````````````````````````````````````````````````````````    */
        //5.4
        //CheckBoxPreference ReceiveFilter
        CheckBoxPreference itemReceiveFilterMessages = (CheckBoxPreference) settingListView.getItemAtPosition(5);
        testChecked(itemReceiveFilterMessages, 3);


        //5.5
        //CheckBoxPreference Buzz
        // 6 позиция по первому общему ListView (общий список settings)
        CheckBoxPreference itemBuzz = (CheckBoxPreference) settingListView.getItemAtPosition(6);
        // 4 позиция по списку CheckBoxPreference
        testChecked(itemBuzz, 4);
        solo.sleep(2000);

        //5.6
        // Background mode
        PreferenceCategory itemBackgroundMode = (PreferenceCategory) settingListView.getItemAtPosition(7);
        testBackgroundMode(itemBackgroundMode,0);
        solo.sleep(200);


        solo.sleep(1000);

        //TODO settingListView.getItemAtPosition(8) не являеться CheckBoxPreference!!!!!
        solo.scrollListToLine(settingListView, settingListView.getLastVisiblePosition());
        solo.sleep(500);



        //5.7
        // CheckBoxPreference TrueConf status
        /**
        CheckBoxPreference itemTrueConfStatus = (CheckBoxPreference) settingListView.getItemAtPosition(9);
        testChecked(itemTrueConfStatus, 6);
        solo.sleep(1000);
         */





        solo.goBack();

        /** 2. Выход с приложения  */

        //2.0 Нажатимаем на HomeButton
        solo.sleep(1000);
        solo.clickOnActionBarHomeButton();
        solo.sleep(300);

        //2.1 Определяем Logout
        String itemLogout = (String) homeListView.getItemAtPosition(11);
        solo.sleep(300);

        //2.2 Прокручиваем список на последнюю позицию
        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        solo.sleep(300);

        //2.3 Нажимаем на Logout
        solo.clickOnText(java.util.regex.Pattern.quote(itemLogout));
        solo.sleep(300);

        //2.4 проверка на переход Activity Login
        assertTrue("Activity Login is not found!", solo.waitForActivity("Login"));
        solo.sleep(300);
        solo.goBack();


    }


    private boolean testChecked(CheckBoxPreference itemAtPosition, int item) {

        if (itemAtPosition.isChecked()) {
            solo.clickOnCheckBox(item);
            solo.sleep(1000);
            solo.clickOnCheckBox(item);
            solo.sleep(1000);
            return true;
        } else {
            solo.clickOnCheckBox(item);
            solo.sleep(1000);
            solo.clickOnCheckBox(item);
            solo.sleep(1000);
            return false;
        }
    }
        private void testBackgroundMode (PreferenceCategory itemAtPosition, int item) {
        solo.clickOnButton(item);
            solo.sleep(1000);

    }






}
