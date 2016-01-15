package com.trueconf.videochat.test.testActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки отправки сообщения пользователю по поиску по его ID
 * проверка перехода на активити Chat
 */
public class TestSendMessage_2 {
    private Solo solo;

    public TestSendMessage_2(Solo solo) {
        this.solo = solo;
    }

    /**
     * Метод проверки
     */

    public void testSendMessage_2() {
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
        android.widget.ListView homeListView_1 = solo.getView(ListView.class, 2);
        solo.sleep(300);

        //3 Определяем Search
        String itemSerch = (String) homeListView_1.getItemAtPosition(7);
        solo.sleep(300);

        //4 Прокручиваем список на последнюю позицию
        solo.scrollListToLine(homeListView_1, (int) homeListView_1.getLastVisiblePosition());
        solo.sleep(300);

        //5 Нажимаем на Search
        solo.clickOnText(java.util.regex.Pattern.quote(itemSerch));
        solo.sleep(300);

        //6 Вводим в поле Search имя для поиска : kovalek
        solo.clearEditText((android.widget.EditText) solo.getView("search_src_text"));
        solo.enterText((android.widget.EditText) solo.getView("search_src_text"), "kovalek");
        //Wait for 2 second
        solo.sleep(2000);

        //7 долгое нажатие на поле, для вызова меню
        solo.clickLongInList(1, 0, 1000);
        solo.sleep(2000);

        //8 Определение всех елементов в данном меню
        android.widget.ListView contactListView_n = solo.getView(ListView.class, 0);
        View call = (View) contactListView_n.getChildAt(0);
        View chat = (View) contactListView_n.getChildAt(1);
        View buzz = (View) contactListView_n.getChildAt(2);
        View information = (View) contactListView_n.getChildAt(3);
        solo.sleep(2000);

        //9 Нажимаем на заданную кнопку

        solo.clickOnView(information);
        assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));
        solo.sleep(2000);
        solo.clickOnView(solo.getView("btn_chat"));
        assertTrue("Activity Chat is not Found", solo.waitForActivity("Chat"));
        solo.sleep(1000);
        solo.clickOnView(solo.getView("et_chat_message"));
        int a = 0;
        for (; a < 3; a++) {
            solo.clearEditText((android.widget.EditText) solo.getView("et_chat_message"));
            solo.enterText((android.widget.EditText) solo.getView("et_chat_message"), "Test test " + a);
            solo.clickOnView(solo.getView("btn_chat_message_send"));

        }
        solo.clickOnActionBarHomeButton();
        solo.sleep(1000);
        assertTrue("Activity UserProfile is not found", solo.waitForActivity("UserProfile"));
       // solo.clickOnView(solo.getView("tv_chat_missed_count"));
        //assertTrue("Activity Chat is not found", solo.waitForActivity("Chat"));
       // solo.sleep(300);
        solo.clickOnActionBarHomeButton();
        assertTrue("Activity ContactTabs is not found", solo.waitForActivity("ContactTabs"));
      //  solo.setActivityOrientation(solo.LANDSCAPE);
        //TODO: добавить переключение между fragment layout (address book, chat, call_history)

         /** 2. Выход с приложения  */

         //2.0 Нажатимаем на HomeButton
         solo.clickOnActionBarHomeButton();
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
         solo.sleep(300);

         //2.4 проверка на переход Activity Login
         assertTrue("Login is not found!", solo.waitForActivity("Login"));
         solo.sleep(300);
         solo.goBack();



    }

}
