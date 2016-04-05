package com.trueconf.videochat.test.testActivity;

import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.trueconf.videochat.test.testJReport.jreport.JReport;

import junit.framework.AssertionFailedError;

/**
 * Класс проверки уведомления (menuDialogHeader) при первом запуске приложения
 */

public class TestFirstStartApp extends JReport {
    private String errorMessage;

    public TestFirstStartApp(Solo solo) {
        super(solo);
    }

    public void testCorrectLogin() {

        try {
            initReport("testCorrectLogin", "Метод проверки первой авторизации при введении корректных значений TrueConfId и password, проверки уведомления menuDialogHeader"); // :1 Begin Report

            /** 1. Подготовка к запуску приложения */
            //1.1 Проверка перехода на Activity: Login
            solo.waitForActivity("Login", 3000);
            assertTextCaseActivity("Запуск приложения:", "Login"); // :1 case
            Timeout.setSmallTimeout(12000);

            // 1.2 Click on Have an account Log in
            solo.clickOnView(solo.getView("tv_is_have_account"));
            assertTextCaseActivity("Проверка перехода на Activity: Login", "Login"); // :2 case

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
            assertTextCaseActivity("Проверка перехода на Activity: ContactTabs", "ContactTabs"); // :5 case

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

            /** 2. Выход с приложения  */
            //2.0 Нажатимаем на HomeButton
            solo.clickOnActionBarHomeButton();
            assertTextCase("Выход с приложения: Navigation drawer -> Logout "); // :7 case
            solo.sleep(300);
            android.widget.ListView homeListView = solo.getView(ListView.class, 2);
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
        } catch (Exception | AssertionFailedError e) {
            errorMessage = e.getMessage();
            throw new RuntimeException(e);
        } finally {
            // :11 END Report
            destroyReport(errorMessage);
        }
    }
}
