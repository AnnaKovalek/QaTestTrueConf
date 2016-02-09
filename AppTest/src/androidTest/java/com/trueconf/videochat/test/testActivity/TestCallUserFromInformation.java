package com.trueconf.videochat.test.testActivity;

import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 *
 */
public class TestCallUserFromInformation {

    private Solo solo;
    private android.widget.ListView homeListView_2;

    public TestCallUserFromInformation(Solo solo) {
        this.solo = solo;
    }


    public void testCallUserFromInformation() {


        //Запуск приложения
        solo.sleep(2000);
        // 1.1 Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 3000);
        Timeout.setSmallTimeout(12000);
        solo.sleep(2000);
        // 1.3 Click on Have an account Log in
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(3000);

        // Edit login and password
        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");

        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");

        solo.pressSoftKeyboardNextButton();
        // 1.6 Click on Login
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
        solo.sleep(1000);

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
        solo.sleep(800);

        solo.clickOnActionBarHomeButton();
        solo.sleep(800);

        //3. Позиция Search № 7 в списке
        String itemSearch = findDrawerListElement(7);

        solo.sleep(300);

        //4 Прокручиваем список на последнюю позицию
        solo.scrollListToLine(homeListView_2, (int) homeListView_2.getLastVisiblePosition());
        solo.sleep(300);

        //5 Нажимаем на Search
        solo.clickOnText(java.util.regex.Pattern.quote(itemSearch));
        solo.sleep(300);

        //6 Вводим в поле Search имя для поиска : anna_m
        solo.clearEditText((android.widget.EditText) solo.getView("search_src_text"));
        solo.enterText((android.widget.EditText) solo.getView("search_src_text"), "anna_m");
        //Wait for 2 second
        solo.sleep(1000);

        //7 долгое нажатие на поле, для вызова меню
        solo.clickLongInList(1, 0, 1000);
        solo.sleep(800);

        //8 Определение всех елементов в данном меню
        android.widget.ListView contactListView_n = solo.getView(ListView.class, 0);
        View call = (View) contactListView_n.getChildAt(0);
        View chat = (View) contactListView_n.getChildAt(1);
        View buzz = (View) contactListView_n.getChildAt(2);
        View information = (View) contactListView_n.getChildAt(3);
        solo.sleep(800);

        //9 Нажимаем на заданную кнопку

        solo.clickOnView(information);
        solo.sleep(200);
        assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));
        solo.sleep(3000);

        int num = 1;
        while (num < 20) {

            solo.pressSoftKeyboardNextButton();
            solo.clickOnView(solo.getView("btn_call"));
            solo.sleep(800);
            assertTrue("Activity Call is not found", solo.waitForActivity("Call"));
            solo.sleep(200);
            solo.pressSoftKeyboardNextButton();
            solo.clickOnView(solo.getView("btn_hangup"));
            solo.sleep(1000);
            assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));

            num++;
        }
        solo.clickOnActionBarHomeButton();
        assertTrue("Activity ContactTabs is not found", solo.waitForActivity("ContactTabs"));
        solo.sleep(200);


        /** 2. Выход с приложения  */
        //2.0 Нажатимаем на HomeButton
        solo.clickOnActionBarHomeButton();
        solo.sleep(300);
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.sleep(1000);

        //2.1  Позиция Logout № 11 в списке
        String itemLogout = findDrawerListElement(11);
        solo.sleep(300);

        //2.2 Прокручиваем список на последнюю позицию
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

    private String findDrawerListElement(int positionElements) {
        int numList = 1;
        String nameElements = null;
        do {
            homeListView_2 = solo.getView(ListView.class, numList);
            solo.sleep(300);
            // Определяем кнопку
            Object obj = homeListView_2.getItemAtPosition(positionElements);
            if (obj instanceof String) {
                return (String) obj;
            }
            numList++;
        } while (numList < 5);
        return "Logout";
    }


}

