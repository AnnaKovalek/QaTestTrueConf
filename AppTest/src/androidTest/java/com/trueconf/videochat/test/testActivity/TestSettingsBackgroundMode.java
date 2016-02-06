package com.trueconf.videochat.test.testActivity;

import android.preference.ListPreference;
import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.trueconf.videochat.test.testJReport.JReport;

import junit.framework.AssertionFailedError;

import java.util.ArrayList;

/**
 * Created by anna on 02.02.16.
 * Класс проверки компонентов всплывающего меню BackgroundMode (по умолчанию первый RadioButton is checked) и нажатие на другие RadioButton
 */

public class TestSettingsBackgroundMode extends JReport {

    private String errorMessage;
    private CharSequence summary;
    private android.widget.ListView homeListView;
    private android.widget.ListView settingListView;
    private ListView listSelectDialog;

    public TestSettingsBackgroundMode(Solo solo) {
        super(solo);
    }

    public void testSettings() {
        try {
            initReport("TestSettingsBackgroundMode : Lang: eng : vertical orientation", "Класс проверки компонентов всплывающего меню BackgroundMode");

            /** 1. Подготовка к запуску приложения */
            goToBeginTest();

            /** 2. Переход на HomeButton и нажатие на кнопку Setting*/
            goToSettingTest();

            /** 3. Переход на SettingsHoneycomb */
            goToSettingsHoneycomb();

            /** 4. Тестирование компонентов BackgroundMode */
            // 4.1 нажатие на BackgroundMode
            clickBackgroundMode();
            assertTextCase("Нажатие на компонент: BackgroundMode");

            // 4.2  определение елементов BackgroundMode
            String idElement = "select_dialog_listview";
            int countElement = getCountElementAlertController(idElement); //TODO если нужно колличество

            for (int i = 0; i < countElement; i++) {
                clickOnCustomView(idElement, i);
                clickBackgroundMode();
            }
            clickOnCustomView(idElement, 0);


            //TODO нужно найти кнопку Cancel

            // 4.3 Back to activity contact
            solo.goBack();

            /** 2. Выход с приложения  */
            goToEndTest();

        } catch (Exception e) {
            errorMessage = e.getMessage();
            throw new RuntimeException(e);
        } catch (AssertionFailedError e) {
            errorMessage = e.getMessage();
            throw new RuntimeException(e);
        } finally {
            destroyReport(errorMessage);
        }
    }

    private int getCountElementAlertController(String id) {
        solo.sleep(200);
        listSelectDialog = (ListView) solo.getView(id);
        solo.sleep(200);
        return listSelectDialog.getChildCount();
    }

    private void clickOnCustomView(String id, int item) {
        solo.sleep(1000);
        ListView listSelectDialog = (ListView) solo.getView(id);
        ArrayList<View> viewArrayList = listSelectDialog.getTouchables();
        assertTextCase("Нажатие на компонент: " + listSelectDialog.getItemAtPosition(item));
        solo.clickOnView(viewArrayList.get(item));
        solo.sleep(1000);
    }

    private void clickBackgroundMode() {
        solo.sleep(300);
        ListPreference itemBackgroundMode = (ListPreference) settingListView.getItemAtPosition(8);
        summary = itemBackgroundMode.getSummary();
        solo.clickOnText(summary.toString());
        solo.sleep(300);
        //   solo.clickOnText("Cancel");
    }

    private void goToBeginTest() {
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

        // 1.7 Проверка стартового уведомления по id
        View menuDialogHeader = null;
        try {
            menuDialogHeader = solo.getView("menuDialogHeader");
        } catch (AssertionFailedError e) {
        }
        if (menuDialogHeader != null) {
            assertTextCaseActivity("Проверка стартового уведомления по id на Activity: ContactTabs", "ContactTabs"); // :6 case
            solo.goBack();
        }
    }

    private void goToEndTest() {
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

        assertTextCaseActivity("Проверка перехода на Activity: Login", "Login"); // :8 case
        solo.goBack();

    }

    private void goToSettingTest() {
        /** 2 Переход на HomeButton и нажатие на кнопку Setting*/
        //2.1. Определили главнй лист
        android.widget.ListView contactListView = solo.getView(ListView.class, 0);
        solo.scrollListToLine(contactListView, contactListView.getFirstVisiblePosition());
        solo.sleep(2000);

        //2.2. Нажатимаем на HomeButton
        solo.clickOnActionBarHomeButton();
        solo.sleep(300);
        homeListView = solo.getView(ListView.class, 2);
        solo.sleep(1000);
        assertTextCase("Проверка перехода на Navigation при нажатии HomeButton");

        //2.3 Определяем setting
        String itemSetting = (String) homeListView.getItemAtPosition(8);

        solo.sleep(300);

        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        solo.sleep(1500);
        //2.5. нажимает на выбранную кнопку
        solo.clickOnText(itemSetting);
        solo.sleep(2000);
    }

    private void goToSettingsHoneycomb() {
        // 3.1. проверка перехода на заданное активити
        assertTextCaseActivity("Проверка перехода на активити SettingsHoneycomb при нажати на setting", "SettingsHoneycomb");
        //3.2  Определяем ListView компонентов
        settingListView = solo.getView(ListView.class, 0);
    }

}
