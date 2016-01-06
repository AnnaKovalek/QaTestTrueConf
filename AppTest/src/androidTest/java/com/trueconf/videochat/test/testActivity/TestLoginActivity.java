package com.trueconf.videochat.test.testActivity;


import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.RadioButton;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Описание, что делает тест
 */

public class TestLoginActivity {
    private Solo solo;

    public TestLoginActivity(Solo solo) {
        this.solo = solo;
    }

    /**
     * Test 1
     */
    // Описание теста
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
     */
    public void testLoginActivityLoginIn() {
        solo.sleep(2000);
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 3000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on "Have an account Log in0"
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(3000);
        assertTrue("Login Activity is not found", solo.waitForActivity("Login"));
        solo.goBack();
    }

    /**
     * Test 3
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
     */
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
     */
    public void testButtonChangeServer() {

        /** 1. Подготовка к запуску приложения */
        solo.sleep(3000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);

        /** 2. Переход на TRATATATATATAT  */
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);
        System.out.println(solo.getCurrentActivity());



        /** 3. Определение компонентов: RadioButton по ID */
        RadioButton r_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        RadioButton r_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");

        /** Test 7.1 */
        //Радио баттон первая нажата, вторая нет
        assertTrue(r_default_server.isChecked());
        assertFalse(r_custom_server.isChecked());

        /** Test 7.2 */
        //При нажатии на вторую кнопку она становиться активной
        solo.clickOnView(r_custom_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_custom_server.isChecked()); //вторая активная
        solo.sleep(500);
        assertFalse(r_default_server.isChecked()); //первая не активная
        solo.sleep(500);

        /** Test 7.3 */
        //При нажатии на первую кнопку она становиться активной
        solo.clickOnView(r_default_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_default_server.isChecked()); //первая активная
        solo.sleep(500);
        assertFalse(r_custom_server.isChecked()); //вторая не активная
        solo.sleep(500);

        /** Test 7.4 */
        //При нажатии на вторую кнопку становиться активной поле ввода
        solo.clickOnView(r_custom_server);
        solo.sleep(500);
        assertTrue(r_custom_server.isChecked()); //вторая активная
        solo.sleep(500);
        EditText editText = (EditText) solo.getView("et_server_ip");
        solo.sleep(500);
        assertTrue(editText.isEnabled()); //На полле ввода можно нажать
        solo.sleep(500);
        solo.clearEditText(editText);
        solo.sleep(100);
        //Вписать сервер
        solo.enterText(editText, "kovalek");
        solo.sleep(1000);
        solo.clearEditText(editText); //очистить по окончанию теста

        /** Test 7.5 */
        //При нажатии на первую кнопку она становиться активной, а полле воода нет
        solo.clickOnView(r_default_server);
        solo.sleep(500);
        assertTrue(r_default_server.isChecked()); //первая активная
        solo.sleep(500);
        assertFalse(editText.isEnabled());
        solo.sleep(1000);
        solo.goBack();
    }

}
