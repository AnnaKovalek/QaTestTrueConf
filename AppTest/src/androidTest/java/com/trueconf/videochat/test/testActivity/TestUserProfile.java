package com.trueconf.videochat.test.testActivity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки компонентов активити UserProfile
 */
public class TestUserProfile {
    private Solo solo;

    public TestUserProfile(Solo solo) {
        this.solo = solo;
    }

    /**
     * Метод проверки компонентов активити UserProfile
     */

    public void testUserProfile() {
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

        //1 Определяем компоненты Navigation Drawer
        android.widget.ListView contactListView = solo.getView(ListView.class, 0);
        solo.scrollListToLine(contactListView, contactListView.getFirstVisiblePosition());
        solo.sleep(2000);

        //2 Нажатие на HomeButton
        solo.clickOnActionBarHomeButton();
        solo.sleep(500);
        android.widget.ListView homeListView_1 = solo.getView(ListView.class, 2);
        solo.sleep(500);

        //3 Определяем UserProfile
        String itemUserProfile = (String) homeListView_1.getItemAtPosition(0);
        solo.sleep(300);

        //4 Нажатие на UserProfile
        solo.clickOnText(java.util.regex.Pattern.quote(itemUserProfile));
        solo.sleep(2000);
        assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));

        //5 Нажатие на кнопку Edit
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_edit"));
        solo.sleep(500);
        solo.clickOnView(solo.getView("info_et_name"));
        solo.sleep(1000);
        // First name
        solo.clickOnView(solo.getView("info_et_name"));
        solo.clearEditText((android.widget.EditText) solo.getView("info_et_name"));
        solo.enterText((android.widget.EditText) solo.getView("info_et_name"), "Anna");
        solo.sleep(100);
        // Last name
        solo.clickOnView(solo.getView("info_et_last_name"));
        solo.clearEditText((android.widget.EditText) solo.getView("info_et_last_name"));
        solo.enterText((android.widget.EditText) solo.getView("info_et_last_name"), "Kovalek");
        solo.sleep(1000);
        // Organization
        solo.clickOnView(solo.getView("info_et_organization"));
        solo.clearEditText((android.widget.EditText) solo.getView("info_et_organization"));
        solo.enterText((android.widget.EditText) solo.getView("info_et_organization"), "TrueConf");
        solo.sleep(300);
        solo.clickOnText("Save");
        solo.sleep(200);
        assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));


        //Возвращаем к предыдущему состоянию
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_edit"));
        solo.sleep(500);
        solo.clickOnView(solo.getView("info_et_name"));
        solo.sleep(1000);
        // First name
        solo.clickOnView(solo.getView("info_et_name"));
        solo.clearEditText((android.widget.EditText) solo.getView("info_et_name"));
        solo.enterText((android.widget.EditText) solo.getView("info_et_name"), "Анна");
        solo.sleep(100);
        // Last name
        solo.clickOnView(solo.getView("info_et_last_name"));
        solo.clearEditText((android.widget.EditText) solo.getView("info_et_last_name"));
        solo.enterText((android.widget.EditText) solo.getView("info_et_last_name"), "Ковалек");
        solo.sleep(1000);

        solo.clickOnText("Save");
        solo.sleep(200);
        assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));
        int i = 0;
        while (i < 2) {
            solo.pressSoftKeyboardNextButton();
            solo.clickOnView(solo.getView("btn_edit"));
            //solo.clickOnText("Save");
            //click on button "Save"
            solo.clickOnButton(1);
            solo.pressSoftKeyboardNextButton();
            solo.clickOnView(solo.getView("btn_edit"));
            solo.clickOnText("Cancel");
            i++;
        }
        // проверка отсутствия при открытых полях для заполнения, отсутствует в меню хардварда "Change your password"
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_edit"));
        solo.sleep(200);
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        assertFalse("Menu Change your password is not found", solo.waitForText("Change your password"));

        //TODO: нет ID верхнего поля ввода fullname

        solo.goBack();
        //click on button "Save"
        solo.clickOnButton(1);

        //меню хардварной кнопки на Alcatel 4027D
        solo.sleep(300);
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        //TODO: не проверяет текст полностью на правильность
        assertTrue("Change your password is not found", solo.waitForText("Change your password"));
        solo.clickOnMenuItem("Change your password");
        assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));

        // The old password
        solo.clickOnView(solo.getView("et_info_old_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_info_old_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_info_old_password"), "pop2233");
        solo.sleep(100);

        //The new password
        solo.clickOnView(solo.getView("et_info_new_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_info_new_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password"), "pop2233");
        solo.sleep(100);

        //Repeat the new password
        solo.clickOnView(solo.getView("et_info_new_password_confirmation"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"));
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"), "p");
        solo.sleep(300);
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"), "o");
        solo.sleep(300);
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"), "p");
        solo.sleep(300);
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"), "2");
        solo.sleep(300);
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"), "2");
        solo.sleep(300);
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"), "3");
        solo.sleep(300);
        solo.enterText((android.widget.EditText) solo.getView("et_info_new_password_confirmation"), "3");
        solo.sleep(300);
        solo.clickOnText("Save");
        assertTrue(solo.waitForText("Error: The new password cannot be the same as the old one"));
        solo.sleep(2000);

        /**
         long downTime = SystemClock.uptimeMillis();
         long eventTime = SystemClock.uptimeMillis();
         KeyEvent event = new KeyEvent(downTime, eventTime, KeyEvent.ACTION_DOWN,
         KeyEvent.KEYCODE_MENU, 1);
         solo.sendKeySync(event);
         */

        //Logout
        solo.sleep(200);
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.clickOnMenuItem("Logout");
        solo.sleep(100);
        assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
        // TODO: проверить выход Quit по хардвару
        solo.goBack();

        /**
         // проверка авторизации под новым паролем
         solo.clickOnView(solo.getView("tv_is_have_account"));
         solo.sleep(2000);
         solo.clickOnView(solo.getView("et_videochat_id"));
         solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
         solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");
         // Enter the text: the new password 'pop2234'
         solo.clickOnView(solo.getView("et_password"));
         solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
         solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2234");
         // 1.4 Click on Login button
         solo.pressSoftKeyboardNextButton();
         solo.clickOnView(solo.getView("btn_login_ll"));
         assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
         */


    }


}
