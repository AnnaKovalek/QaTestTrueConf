package com.trueconf.videochat.test.testActivity;


import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.view.View;
import android.widget.ListView;


import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.trueconf.videochat.test.testJReport.JReport;

import junit.framework.AssertionFailedError;

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


public class TestSettings extends JReport {
    private String errorMessage;
    private String component;

    public TestSettings(Solo solo) {
        super(solo);
    }

    /**
     * Метод проверки
     */
    public void testSettings() {

        try {
            // test 1 testSettings : Lang: eng : vertical orientation
            // test 2 testSettings : Lang: eng : horizontal orientation
            // test 3 testSettings : Lang: ru : vertical orientation
            // test 4 testSettings : Lang: ru : horizontal orientation
            initReport("testSettings : Lang: ru : horizontal orientation", "Класс проверки компонентов PreferenceActivity - Settings: checkBoxPreference, ListPreference, EditTextPreference"); // :1 Begin Report

            /** 1. Подготовка к запуску приложения */
            //1.1 Проверка перехода на Activity: Login
            solo.waitForActivity("Login", 3000);
            assertTextCaseActivity("Запуск приложения:", "Login");
            Timeout.setSmallTimeout(12000);

            // 1.2 Click on Have an account Log in
            solo.clickOnView(solo.getView("tv_is_have_account"));
            assertTextCaseActivity("Проверка перехода на Activity: Login", "Login");

            // 1.4 Enter the login
            solo.clickOnView(solo.getView("et_videochat_id"));
            solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
            solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");
            assertTextCaseActivity("Enter the login", "Login"); // :3 case

            // 1.5 Enter the password
            solo.clickOnView(solo.getView("et_password"));
            solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
            solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");
            assertTextCaseActivity("Enter the password", "Login"); // :4 case

            solo.pressSoftKeyboardNextButton();

            // 1.6 Click on Login
            solo.clickOnView(solo.getView("btn_login_ll"));
            assertTextCaseActivity("Проверка перехода на Activity: ContactTabs", "ContactTabs");
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
                assertTextCaseActivity("Проверка стартового уведомления по id на Activity: ContactTabs", "ContactTabs"); // :6 case
                solo.goBack();
            }


            /** 2 Переход на HomeButton */
            //1. Определили главнй лист
            android.widget.ListView contactListView = solo.getView(ListView.class, 0);
            solo.scrollListToLine(contactListView, contactListView.getFirstVisiblePosition());
            solo.sleep(2000);

            //2. Нажатимаем на HomeButton
            solo.clickOnActionBarHomeButton();
            solo.sleep(300);
            android.widget.ListView homeListView = solo.getView(ListView.class, 2);
            solo.sleep(1000);
            assertTextCase("Проверка перехода на Navigation при нажатии HomeButton");

            //3 Определяем setting
            String itemSetting = (String) homeListView.getItemAtPosition(8);

            solo.sleep(300);

            //4 прокручиваем список
            solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
            solo.sleep(1500);
            // 4.1. нажимает на выбранную кнопку
            solo.clickOnText(itemSetting);
            solo.sleep(2000);
            // 4.2. проверка перехода на заданное активити
            assertTextCaseActivity("Проверка перехода на активити SettingsHoneycomb при нажати на setting", "SettingsHoneycomb");

            //5.  Определяе ListView компонентов
            //Step 2
            android.widget.ListView settingListView = solo.getView(ListView.class, 0);

            //5.1
            // StartAutomatically
            CheckBoxPreference itemStartAutomatically = (CheckBoxPreference) settingListView.getItemAtPosition(0);
            component = "StartAutomatically";
            String text_StartAutomatically = itemStartAutomatically.getSummary().toString();
            testChecked(text_StartAutomatically, component);


            //5.2
            // ReceiveFilter
            CheckBoxPreference itemReceiveFilter = (CheckBoxPreference) settingListView.getItemAtPosition(2);
            component = "ReceiveFilter";
            String text_ReceiveFilter = itemReceiveFilter.getSummary().toString();
            testChecked(text_ReceiveFilter, component);

            //5.3
            // AutoReplay
            CheckBoxPreference itemAutoReplay = (CheckBoxPreference) settingListView.getItemAtPosition(3);
            component = "AutoReplay";
            String text_AutoReplay = itemAutoReplay.getSummary().toString();
            testChecked(text_AutoReplay, component);

            //5.4
            // ReceiveFilter
            CheckBoxPreference itemReceiveFilterMessages = (CheckBoxPreference) settingListView.getItemAtPosition(5);
            component = "ReceiveFilter";
            String text_ReceiveFilMess = itemReceiveFilterMessages.getSummary().toString();
            testChecked(text_ReceiveFilMess, component);

            //5.5
            // Buzz
            CheckBoxPreference itemBuzz = (CheckBoxPreference) settingListView.getItemAtPosition(6);
            component = "Buzz";
            String text_Buzz = itemBuzz.getSummary().toString();
            testChecked(text_Buzz, component);

            // 5.6
            //  BackgroundMode
            ListPreference itemBackgroundMode = (ListPreference) settingListView.getItemAtPosition(8);
            component = "BackgroundMode";
            CharSequence summary = itemBackgroundMode.getSummary();
            solo.clickOnText(summary.toString());
            assertTextCase("Нажатие на компонент: " + component);
            solo.goBack();

            //5.7
            //  TrueConf status
            CheckBoxPreference itemTrueConfStatus = (CheckBoxPreference) settingListView.getItemAtPosition(10);
            component = "";
            String text_TrueConf = itemTrueConfStatus.getSummary().toString();
            testChecked(text_TrueConf, component);

            //5.8
            // Synchronization
            CheckBoxPreference itemSynchronization = (CheckBoxPreference) settingListView.getItemAtPosition(12);
            component = "Synchronization";
            String text_Synchronization = itemSynchronization.getSummary().toString();
            testChecked(text_Synchronization, component);

            //5.9
            // Back to activity contact
            solo.goBack();

            /** 2. Выход с приложения  */
            //2.0 Нажатимаем на HomeButton
            solo.clickOnActionBarHomeButton();
            assertTextCase("Выход с приложения: Navigation drawer -> Logout "); // :7 case
            solo.sleep(300);
            //  android.widget.ListView homeListView = solo.getView(ListView.class, 2);
            solo.sleep(300);

            //2.1 Определяем Logout
            String itemLogout = (String) homeListView.getItemAtPosition(11);
            solo.sleep(300);

            //2.2 Прокручиваем список на последнюю позицию
            solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
            solo.sleep(300);

            //2.3 Нажимаем на Logout
            solo.clickOnText(java.util.regex.Pattern.quote(itemLogout));

            //2.4 проверка на переход Activity Login
            assertTextCaseActivity("Проверка перехода на Activity: Login", "Login"); // :8 case
            solo.goBack();

            //Выход с репорта в конце
        } catch (Exception e) {
            errorMessage = e.getMessage();
            throw new RuntimeException(e);
        } catch (AssertionFailedError e) {
            errorMessage = e.getMessage();
            throw new RuntimeException(e);
        } finally {
            // :11 END Report
            destroyReport(errorMessage);
        }
    }

    private void testChecked(String id_text, String component) {
        assertTextCase("Исходное состояние компонента: " + component + " до нажатия");
        solo.clickOnText(id_text);
        assertTextCase("Нажатие на компонент: " + component);
        solo.clickOnText(id_text);
        assertTextCase("Возврат в исходное состояние компонента: " + component);
    }

}
