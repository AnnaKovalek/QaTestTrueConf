package com.trueconf.videochat.test.testActivity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки валидности всех компонентов (myprofile, address_book, chat, call_history, dial, create_conference,
 * invite_friends, search, settings, about, share, logout)
 * при переходе по списку navigation drawer
 */
public class TestListNavigationDrawer {
    private Solo solo;
    private String myProfile;
    private String addressBook;
    private String chat;
    private String callHistory;
    private String dial;
    private String createConference;
    private String inviteFriends;
    private String search;
    private String setting;
    private String about;
    private String share;
    private String logout;
    private String quit;
    private ListView homeListView;

    public TestListNavigationDrawer(Solo solo) {
        this.solo = solo;
    }


    /**
     * Метод проверки
     */
    public void testListNavigationDrawer() {

        /** 1. Подготовка к запуску приложения */
        solo.sleep(2000);
        // 1.1 Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 3000);
        // 1.2 Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        solo.sleep(2000);
        // 1.3 Click on Have an account Log in
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(3000);

        /** Ввод логина/пароля для авторизации*/
        // 1.4 Click on Empty Text View
        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "kovalek");

        // 1.5 Enter the text: 'pop2233'
        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");

        solo.pressSoftKeyboardNextButton();
        // 1.6 Click on Login
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
        solo.sleep(1500);

        // 1.7 Проверка появляющегося при первом запуске уведомления "Invite friends via" по id
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

        /** 2. Переход на navigation drawer */
        solo.clickOnActionBarHomeButton();
        //TODO проверка перехода на navigation drawer
        // Проверка перехода на navigation drawer
        // assertTrue("Navigation drawer is not opened", solo
        // solo.getCurrentActivity().getLocalClassName();

        homeListView = defineHomeList();


        /** 4. Запуск теста */
        // 4.0 получает названние кнопок, независимо от локализации
        myProfile = (String) homeListView.getItemAtPosition(0);
        addressBook = (String) homeListView.getItemAtPosition(1);
        chat = (String) homeListView.getItemAtPosition(2);
        callHistory = (String) homeListView.getItemAtPosition(3);
        dial = (String) homeListView.getItemAtPosition(4);

        // 4.1 My Profile
        clickHomeButtonTwice(myProfile, "UserProfile");
        // 4.2 Address Book
        clickHomeButtonOnce(addressBook, "ContactTabs");
        // 4.3 Chat
        clickHomeButtonOnce(chat, "ContactTabs");
        // 4.4 CallHistory
        clickHomeButtonOnce(callHistory, "ContactTabs");
        // 4.5 Dial
        clickHomeButtonTwice(dial, "Dialer");
        //4.6  прокручиваем список
        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        solo.sleep(500);
        // 4.7  переопределяем HomeList
        homeListView = defineHomeList();
        // 4.8 получает названние кнопок, независимо от локализации
        createConference = (String) homeListView.getItemAtPosition(5);
        inviteFriends = (String) homeListView.getItemAtPosition(6);
        search = (String) homeListView.getItemAtPosition(7);
        setting = (String) homeListView.getItemAtPosition(8);
        about = (String) homeListView.getItemAtPosition(9);
        share = (String) homeListView.getItemAtPosition(10);
        logout = (String) homeListView.getItemAtPosition(11);
        quit = (String) homeListView.getItemAtPosition(12);
        // 4.9 createConference
        clickHomeButtonTwice(createConference, "ConferenceSettings");
        // 4.10 inviteFriends
        clickBackAndHomeButtonInviteFriends(inviteFriends, "ContactTabs");
        // 4.11 search
        clickHomeButtonOnce(search, "ContactTabs");
        // 4.12 setting
        clickBackAndHomeButton(setting, "SettingsHoneycomb");
        // прокручиваем список
        solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
        // 4.13 about
        clickHomeButtonTwice(about, "About");
        // 4.14 share
        clickBackAndHomeButton(share, "ChooserActivity"); //ChooserActivity, ContactTabs
        // 4.15 Logout
        clickHomeButtonOnce(logout, "Login");
    }

    /**
     * Метод осуществляет проверку нажатие выбранного item и нажатие кнопки назад
     *
     * @param nameButton
     * @param activityId
     */
    private void clickHomeButtonTwice(String nameButton, String activityId) {
        solo.sleep(2000);
        // 1. нажимает на выбранную кнопку
        solo.clickOnText(nameButton);
        solo.sleep(3000);
        // 2. проверка перехода на заданное активити
        assertTrue(" Ожидался вызов активити: " + activityId + " при нажатии: " + nameButton + ", а вызвалось: " + solo.getCurrentActivity().getLocalClassName(),
                solo.waitForActivity(activityId));
        solo.sleep(2000);
        // 3. нажатие на кнопку возврата назад (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        // 4. нажатие на кнопку вызова navigation drawer (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        // 5. проверка перехода на navigation drawer
        //TODO проверка перехода на navigation drawer
    }

    /**
     * Метод осуществляет проверку нажатие выбранного item и нажатие кнопки назад
     *
     * @param nameButton
     * @param activityId
     */
    private void clickHomeButtonOnce(String nameButton, String activityId) {
        solo.sleep(2000);
        // 1. нажимает на выбранную кнопку
        solo.clickOnText(nameButton);
        solo.sleep(3000);
        // 2. проверка перехода на заданное активити
        assertTrue(" Ожидался вызов активити: " + activityId + " при нажатии: " + nameButton + " ,а вызвалось: " + solo.getCurrentActivity().getLocalClassName(),
                solo.waitForActivity(activityId));
        solo.sleep(2000);
        // 3. нажатие на кнопку вызова navigation drawer (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        // 4. проверка перехода на navigation drawer
        //TODO проверка перехода на navigation drawer
    }

    /**
     * Метод осуществляет проверку нажатия выбранного item и нажатие кнопки назад
     *
     * @param nameButton
     * @param activityId
     */

    private void clickBackAndHomeButton(String nameButton, String activityId) {
        solo.sleep(2000);
        // 1. нажимает на выбранную кнопку
        solo.clickOnText(nameButton);
        solo.sleep(3000);
        // 2. проверка перехода на заданное активити
        assertTrue(" Ожидался вызов активити: " + activityId + " при нажатии: " + nameButton + " ,а вызвалось: " + solo.getCurrentActivity().getLocalClassName(),
                solo.waitForActivity(activityId));
        solo.sleep(2000);
        // 3. нажатие на кнопку возврата назад (HomeButton)
        solo.goBack();
        solo.sleep(2000);
        // 4. нажатие на кнопку вызова navigation drawer (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        // 5. проверка перехода на navigation drawer
        //TODO проверка перехода на navigation drawer


    }

    private void clickBackAndHomeButtonInviteFriends(String nameButton, String activityId) {
        solo.sleep(2000);
        solo.clickOnMenuItem(nameButton);
        solo.sleep(3000);
        assertTrue(" Ожидался вызов активити: " + activityId + " при нажатии: " + nameButton + " ,а вызвалось: " + solo.getCurrentActivity().getLocalClassName(),
                solo.waitForActivity(activityId));
        solo.sleep(2000);
        solo.goBack();
        solo.sleep(2000);
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        //TODO проверка перехода на navigation drawer

    }

    private ListView defineHomeList() {
        solo.sleep(1500);
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.sleep(1500);
        return homeListView;
    }

    private void clickExitNameButton(String nameButton) {
        solo.sleep(2000);


    }

}
