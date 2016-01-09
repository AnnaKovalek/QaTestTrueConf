package com.trueconf.videochat.test.testActivity;


import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;


import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки валидности компонентов (sign up, have an account(log in),
 * change server, menuhardvard) активити Login
 */

public class TestLoginActivity {
    private Solo solo;

    public TestLoginActivity(Solo solo) {
        this.solo = solo;
    }

    /**
     * Test 1
     * Метод проверки перехода при нажатии sign up на активити Register
     */

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

    /**
     * Test 2
     * Метод проверки перехода при нажатии Have an account Log in" на активити Login
     */

    public void testLoginActivityLoginIn() {
        solo.sleep(2000);
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 3000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on "Have an account Log in"
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(3000);
        assertTrue("Login Activity is not found", solo.waitForActivity("Login"));
        solo.goBack();
    }

    /**
     * Test 3
     * Метод проверки - невозможно пройти авторизацию при введении только одного поля: поля TrueConf ID
     */

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

    /**
     * Test 4
     * Метод проверки появляющегося меню хардварной кнопки для Alcatel 4027D
     */

    public void testHardWardButton() {
        solo.sleep(2000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);
        solo.sleep(3000);
        // solo.sendKey(solo.MENU);
        solo.sleep(3000);
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.clickOnMenuItem("About");
        solo.sleep(2000);
        assertTrue("Activity is not found", solo.waitForActivity("About"));
        solo.goBack();
        solo.sleep(5000);
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        solo.clickOnMenuItem("Quit");
        solo.sleep(2000);

    }

    /**
     * Test 5
     * Метод проверки - невозможно пройти авторизацию при введении только одного поля: поля password
     */

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

    /**
     * Test 6
     * Метод проверки - невозможно пройти авторизацию при введении неверного пароля
     */
    //TODO: переименовать метод
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

    /**
     * Test 7
     * Метод проверки на существование всех компонентов activity_select_server
     */

    public void testButtonChangeServer() {

        /** 1. Подготовка к запуску приложения */
        solo.sleep(3000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);

        /** 2. Переход на activity_select_server.xml  */
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);
        System.out.println(solo.getCurrentActivity());

        /** 3. Проверка на существование LinearLayout android:id="@+id/ll_server_options_form"   */
        View ll_server_options_form = solo.getView("ll_server_options_form");
        assertNotNull(ll_server_options_form);

        /** 4. Проверка на существование RadioButton android:id="@+id/rb_select_server_use_default_server"   */
        RadioButton r_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        assertNotNull(r_default_server);

        /** 5. Проверка на существование RadioButton android:id="@+id/rb_select_server_use_custom_server"   */
        RadioButton r_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");
        assertNotNull(r_custom_server);


        /** 6. Проверка на существование поля для ввода адресса сервера*/
        EditText et_server_ip = (EditText) solo.getView("et_server_ip");
        assertNotNull(et_server_ip);

        /** 6. Проверка на существование TextView "Learn more"*/
        TextView tcsAdsMore = (TextView) solo.getView("tcsAdsMore");
        assertNotNull(tcsAdsMore);

        //TODO : Доделать остальные view на экране: второй Learn more, Connect


        solo.goBack();
    }

}
