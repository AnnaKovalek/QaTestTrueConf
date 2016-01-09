package com.trueconf.videochat.test.testActivity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки валидности компонентов "Change server"
 */
public class TestLoginActivity_Button_ChangeServer {
    private Solo solo;

    public TestLoginActivity_Button_ChangeServer(Solo solo) {
        this.solo = solo;
    }

    /**
     * Test 1
     */
    // Описание теста
    public void testButtonChangeServer() {

        /** 1. Подготовка к запуску приложения */
        solo.sleep(3000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);

        /** 2. Переход на activity_select_server */
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

    /**
     * Test 2
     * Метод проверки подключения к серверу video.trueconf.com, авторизации при введении
     * корректных TrueConfID и password
     */

    public void testButtonChangeServer_2() {

        /** 1. Подготовка к запуску приложения */
        solo.sleep(3000);
        solo.waitForActivity("Login", 5000);
        Timeout.setSmallTimeout(12000);
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);

        /** 2. Определить Елементы по ID */
        RadioButton r_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        RadioButton r_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");
        EditText editText = (EditText) solo.getView("et_server_ip");


        /** 3 Нажимаем на RadioButton   */
        // 3.1 При нажатии на вторую кнопку становиться активной поле ввода
        solo.clickOnView(r_custom_server);
        solo.sleep(500);
        assertTrue(r_custom_server.isChecked()); //вторая активная
        solo.sleep(500);
        EditText eT = (EditText) solo.getView("et_server_ip");
        solo.sleep(500);
        assertTrue(eT.isEnabled()); // Поле ввода можно нажать
        solo.sleep(500);
        solo.clearEditText(editText);
        solo.sleep(100);

        // 3.2 Вписываем сервер
        solo.enterText(editText, "95.169.190.182");
        solo.sleep(1000);
        //Динамическое ID 501, нажатие по названию
        solo.clickOnText("Connect");
        solo.sleep(2000);


        //TODO: проверить что при активной радиобаттон 2 и пустом поле сервера нельзя нажать Connect

        /** 4. проверка авторизации к серверу video.trueconf.com */
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
        solo.sleep(5000);


        /** 5. Выходим с приложения  */
        //5.0 Нажатимаем на HomeButton
        solo.clickOnActionBarHomeButton();
        solo.sleep(1000);
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.sleep(1000);

        //5.1 Определяем Logout
        String itemLogout = (String) homeListView.getItemAtPosition(11);
        solo.sleep(500);

        //5.2 Прокручиваем список на последнюю позицию
        solo.scrollListToLine(homeListView, 5);
        solo.sleep(500);
        solo.scrollListToLine(homeListView, 8);
        solo.sleep(500);


        //5.3 Нажимаем на Logout
        solo.clickOnText(java.util.regex.Pattern.quote(itemLogout));
        solo.sleep(500);

        //5.4 проверка на переход Activity Login
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
        solo.sleep(500);

        //подключение к TrueConf Online service
        solo.pressSoftKeyboardNextButton();
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(2000);
        solo.clearEditText((android.widget.EditText) solo.getView("et_server_ip"));
        solo.sleep(1000);
        //выбор радиобаттон "Use TrueConf Online service"
        solo.clickOnView(r_default_server);
        solo.sleep(100);
        //Подключение к сервису
        solo.clickOnText("Connect");
        solo.sleep(1000);
        assertTrue("Login activity is not found", solo.waitForActivity("Login"));
        solo.sleep(1000);
        solo.goBack();


    }
}


//        assertEquals("Error", homeListView.getLastVisiblePosition(), 9);
//        solo.sleep(2000);
//
//        solo.scrollListToLine(homeListView, 3);
//        solo.sleep(2000);
//        assertEquals("Error", homeListView.getFirstVisiblePosition(), 3);
//        solo.sleep(2000);
//        assertEquals("Error", homeListView.getLastVisiblePosition(), 12);
//        solo.sleep(2000);
//
//        View childAt = homeListView.getChildAt(3);
//
//        solo.clickOnView(childAt);

/**
 * homeListView.getItemAtPosition(3) - показывает имя выбранного пункта
 * homeListView.getItemIdAtPosition(3) - показывает позицию 4
 * List<String> list = new LinkedList<String>();
 * list.add((String) homeListView.getItemAtPosition(0));
 * list.add((String) homeListView.getItemAtPosition(1));
 * list.add((String) homeListView.getItemAtPosition(2));
 * list.add((String) homeListView.getItemAtPosition(3));
 * list.add((String) homeListView.getItemAtPosition(4));
 * list.add((String) homeListView.getItemAtPosition(5));
 * list.add((String) homeListView.getItemAtPosition(6));
 * list.add((String) homeListView.getItemAtPosition(7));
 * list.add((String) homeListView.getItemAtPosition(8));
 * list.add((String) homeListView.getItemAtPosition(9));
 * list.add((String) homeListView.getItemAtPosition(10));
 * list.add((String) homeListView.getItemAtPosition(11));
 * list.add((String) homeListView.getItemAtPosition(12));
 */


//        // Change server
//        solo.pressSoftKeyboardNextButton();
//        solo.clickOnView(solo.getView("select_server_area"));
//        solo.sleep(2000);
//        solo.clickOnView(r_default_server);
//        solo.sleep(500);
//        assertTrue(r_default_server.isChecked()); //первая активная
//        solo.sleep(500);
//        solo.clickOnText("Connect");
//        assertTrue("Login activity is not found", solo.waitForActivity("Login"));
//        solo.goBack();