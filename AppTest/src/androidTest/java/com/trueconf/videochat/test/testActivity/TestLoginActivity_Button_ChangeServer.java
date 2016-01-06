package com.trueconf.videochat.test.testActivity;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Описание, что делает тест
 */
public class TestLoginActivity_Button_ChangeServer {
    private Solo solo;

    public TestLoginActivity_Button_ChangeServer(Solo solo) {
        this.solo = solo;
    }

    //Test1
    public void testButtonChangeServer() {
        solo.sleep(3000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);
        //Определить RadioButton по ID
        RadioButton r_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        RadioButton r_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");

        /** Test 1 */
        //Первая проверка
        //Радио баттон первая нажата, вторая нет
        assertTrue(r_default_server.isChecked());
        assertFalse(r_custom_server.isChecked());

        /** Test 2 */
        //При нажатии на вторую кнопку она становиться активной
        solo.clickOnView(r_custom_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_custom_server.isChecked()); //вторая активная
        solo.sleep(500);
        assertFalse(r_default_server.isChecked()); //первая не активная
        solo.sleep(500);

        /** Test 3 */
        //При нажатии на первую кнопку она становиться активной
        solo.clickOnView(r_default_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_default_server.isChecked()); //первая активная
        solo.sleep(500);
        assertFalse(r_custom_server.isChecked()); //вторая не активная
        solo.sleep(500);

        /** Test 4 */
        //При нажатии на вторую кнопку становиться активной поле ввода
        solo.clickOnView(r_custom_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_custom_server.isChecked()); //вторая активная
        solo.sleep(500); //небольшая пауза
        EditText editText = (EditText) solo.getView("et_server_ip");
        solo.sleep(500); //небольшая пауза
        //На полле ввода можно нажать
        assertTrue(editText.isEnabled());
        solo.sleep(500); //небольшая пауза
        solo.clearEditText(editText);
        solo.sleep(100);
        //Вписать сервер
        solo.enterText(editText, "kovalek");
        solo.sleep(1000);
        solo.clearEditText(editText); //очистить по окончанию теста

        /** Test 5 */
        //При нажатии на первую кнопку она становиться активной, а полле воода нет
        solo.clickOnView(r_default_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_default_server.isChecked()); //первая активная
        solo.sleep(500);
        assertFalse(editText.isEnabled());
        solo.sleep(1000);
        solo.goBack();

    }

    //Test2
    public void testButtonChangeServer_2() {
        solo.sleep(3000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);

        //Определить Елементы по ID
        RadioButton r_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        RadioButton r_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");
        EditText editText = (EditText) solo.getView("et_server_ip");


        /** Test 6 */
        //При нажатии на вторую кнопку становиться активной поле ввода
        solo.clickOnView(r_custom_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_custom_server.isChecked()); //вторая активная
        solo.sleep(500); //небольшая пауза
        EditText eT = (EditText) solo.getView("et_server_ip");
        solo.sleep(500); //небольшая пауза
        //На полле ввода можно нажать
        assertTrue(eT.isEnabled());
        solo.sleep(500); //небольшая пауза
        solo.clearEditText(editText);
        solo.sleep(100);
        //Вписать сервер
        //Нужно предусмотреть условие когда отсутствует соединение (проблемы с соединением) с сервером (сервисом)
        solo.enterText(editText, "95.169.190.182");
        solo.sleep(1000);
        //Динамическое ID 501, нажатие по названию
        solo.clickOnText("Connect");
        solo.sleep(2000);
        //solo.goBack();
        // как проверить что подключились к серверу

        /** Test 8*/
// проверить что при активной радиобаттон 2 и пустом поле сервера нельзя нажать Connect
// залогинивание при подключенном к серверу video.trueconf.com
        solo.waitForActivity("Login", 10000);
        try {
            Timeout.setSmallTimeout(10000);
            solo.clickOnView(solo.getView("et_videochat_id"));
        } catch (AssertionFailedError e) {
            throw new IllegalArgumentException("Not connect to internet");
        }


        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");

        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "kovalek");

        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
        solo.sleep(2000);
        //solo.goBack();

        //Logout
        solo.clickOnActionBarHomeButton();
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.scrollListToLine(homeListView, 3);
        //Click on Logout
        solo.clickInList(7);
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
        solo.sleep(2000);

        // Change server
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);
        solo.clickOnView(r_default_server);
        solo.sleep(500);
        assertTrue(r_default_server.isChecked()); //первая активная
        solo.sleep(500);
        solo.clickOnText("Connect");
        assertTrue("Login activity is not found", solo.waitForActivity("Login"));
        solo.goBack();

    }

    //Test8
    public void testButtonChangeServer_222222() {
        solo.sleep(3000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);

        //Определить Елементы по ID
        RadioButton r_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        RadioButton r_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");
        EditText editText = (EditText) solo.getView("et_server_ip");


        /** Test 6 */
        //При нажатии на вторую кнопку становиться активной поле ввода
        solo.clickOnView(r_custom_server);
        solo.sleep(500); //небольшая пауза
        assertTrue(r_custom_server.isChecked()); //вторая активная
        solo.sleep(500); //небольшая пауза
        EditText eT = (EditText) solo.getView("et_server_ip");
        solo.sleep(500); //небольшая пауза
        //На полле ввода можно нажать
        assertTrue(eT.isEnabled());
        solo.sleep(500); //небольшая пауза
        solo.clearEditText(editText);
        solo.sleep(100);
        //Вписать сервер
        //Нужно предусмотреть условие когда отсутствует соединение (проблемы с соединением) с сервером (сервисом)
        solo.enterText(editText, "95.169.190.182");
        solo.sleep(1000);
        //Динамическое ID 501, нажатие по названию
        solo.clickOnText("Connect");
        solo.sleep(2000);
        //solo.goBack();
        // как проверить что подключились к серверу

    }

}
