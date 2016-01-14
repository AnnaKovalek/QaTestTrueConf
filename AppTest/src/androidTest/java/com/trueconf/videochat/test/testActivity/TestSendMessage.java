package com.trueconf.videochat.test.testActivity;

import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки отправки сообщения пользователю по поиску по его ID
 * проверка перехода на активити Chat
 */
public class TestSendMessage {
    private Solo solo;
    private String logOut;
    private String search;
    private ListView homeListView;

    public TestSendMessage(Solo solo) {
        this.solo = solo;
    }

    /**
     * Метод проверки поиска по ID и full name, нажатие на ImageView Chat, проверка перехода на активити "Chat",
     * отправка сообщения пользователю
     */
    public void testSendMessage() {
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
/**
        solo.clickOnActionBarHomeButton();
     // homeListView = defineHomeList;
        //TODO проверка перехода на navigation drawer
        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        solo.sleep(500);
        // 4.7  переопределяем HomeList
               // 4.8 получает названние кнопок, независимо от локализации
               search = (String) homeListView.getItemAtPosition(7);
        solo.clickOnText(search);
        /**
                Timeout.setSmallTimeout(12000);
        // Enter the text for search by ID: "anna_m"
        solo.clearEditText((android.widget.EditText) solo.getView("search_src_text"));
        solo.enterText((android.widget.EditText) solo.getView("search_scr_text"), "anna_m");
        solo.sleep(2000);
        //TODO: проверить удаление введенного имени или ID
        // English - 2 in list, Russian -1
        //TODO: сделать try/catch при условии если не найдены, продублированы, проверка не найден
        solo.clickInList(0, 0);
        //click on ImageView
        solo.clickLongOnView(solo.getView(android.widget.ImageView.class, 12));
        //wait for activity "Chat"
        assertTrue("Activity Chat is not found", solo.waitForActivity("Chat"));
        // click on empty text view
        solo.clickOnView(solo.getView("et_chat_message"));
        int a = 0;
        while (a < 8) {
            // Enter text message "Test message"
            solo.clearEditText((android.widget.EditText) solo.getView("et_chat_message"));
            solo.enterText((android.widget.EditText) solo.getView("et_chat_message"), "Test message" + a);
            // click on button "send"
            solo.clickOnView(solo.getView("btn_chat_message_send"));
            a++;

        }

        private ListView defineHomeList() {
            solo.sleep(1500);
            android.widget.ListView homeListView = solo.getView(ListView.class, 2);
            solo.sleep(1500);
            return homeListView;
        }

        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        solo.clickOnActionBarHomeButton();
        //TODO: проверка открытия Navigation Drawer
        solo.sleep(2000);
        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        solo.sleep(1000);
        // get name button
        logOut = (String) homeListView.getItemAtPosition(11);
        solo.clickOnText(logOut);
        solo.sleep(2000);
        assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
        solo.sleep(2000);
        solo.goBack();
        */

        //Click on HomeView
        solo.clickOnView(solo.getView(android.widget.FrameLayout.class, 13));
        //Click on Search LinearLayout
        solo.clickInList(8, 2);
        // Set small timeout to 13130 millisecond
        Timeout.setSmallTimeout(13130);
        //Enter the text for search by name: 'вирол'
        solo.clearEditText((android.widget.EditText) solo.getView("search_src_text"));
        solo.enterText((android.widget.EditText) solo.getView("search_src_text"), "вирол");
        //Wait for 2 second
        solo.sleep(2000);
        // English - 2 in list, Russian -1
        solo.clickInList(1, 0);
        //Click on ImageView
        solo.clickOnView(solo.getView(android.widget.ImageView.class, 12));
        //Wait for activity: 'com.vc.gui.activities.Chat' (check that we have the right activity)
        assertTrue("Chat is not found!", solo.waitForActivity("Chat"));
        //Click on Empty Text View
        solo.clickOnView(solo.getView("et_chat_message"));
        // Send messages n=9
        int i = 0;
        while (i < 5) {
            //Enter the text: 'Test message test'
            solo.clearEditText((android.widget.EditText) solo.getView("et_chat_message"));
            solo.enterText((android.widget.EditText) solo.getView("et_chat_message"), "Test message message " + i);
            //Click on ImageView
            solo.clickOnView(solo.getView("btn_chat_message_send"));
            i++;
        }

        //Press menu back key
        solo.goBack();
        //Click on HomeView
        solo.clickOnView(solo.getView(android.widget.FrameLayout.class, 13));
        //Scroll to Logout fear724@trueconf.com
        android.widget.ListView listView0 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 2);
        solo.scrollListToLine(listView0, 3);
        //Click on Logout kovalek@trueconf.com
        solo.clickOnText(java.util.regex.Pattern.quote("Logout"));
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        assertTrue("Login is not found!", solo.waitForActivity("Login"));
        //Press menu back key
        solo.goBack();





    }

}
