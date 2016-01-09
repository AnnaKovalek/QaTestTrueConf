package com.trueconf.videochat.test.testActivity;

import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки валидности всех компонентов (myprofile, address_book, chat, call_history, dial, create_conference,
 * invite_friends, search, settings, about, share, logout)
 * при переходе по списку navigation drawer
 */
public class TestListNavigationDrawer {
    private Solo solo;

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

        // 1.5 Enter the text: '2687484'
        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"), "pop2233");

        solo.pressSoftKeyboardNextButton();

        // 1.6 Click on Login
        solo.clickOnView(solo.getView("btn_login_ll"));
        assertTrue("Неверный TrueConf ID или пароль", solo.waitForActivity("ContactTabs"));
        solo.sleep(1000);

        /** 2. Переход на navigation drawer */
        solo.clickOnActionBarHomeButton();
        //TODO проверка перехода на navigation drawer

        solo.sleep(1000);
        android.widget.ListView homeListView = solo.getView(ListView.class, 2);
        solo.sleep(1000);

        /** 3. Определить Елементы по ID */
        // 3.1 получает названние кнопок, независимо от локализации
        String myProfile = (String) homeListView.getItemAtPosition(0);
        String addressBook = (String) homeListView.getItemAtPosition(1);
        String chat = (String) homeListView.getItemAtPosition(2);
        String callHistory = (String) homeListView.getItemAtPosition(3);
        String dial = (String) homeListView.getItemAtPosition(4);
        String createConference = (String) homeListView.getItemAtPosition(5);
        String inviteFriends = (String) homeListView.getItemAtPosition(6);
        String search = (String) homeListView.getItemAtPosition(7);
        String setting = (String) homeListView.getItemAtPosition(8);
        String about = (String) homeListView.getItemAtPosition(9);
        String share = (String) homeListView.getItemAtPosition(10);
        String logout = (String) homeListView.getItemAtPosition(11);
        String quit = (String) homeListView.getItemAtPosition(12);

        /** 4. Запуск теста */
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
        // прокручиваем список
        solo.scrollListToLine(homeListView, 5);
        // 4.6 createConference
        clickHomeButtonOnce(createConference, "ConferenceSettings");
        // 4.7 inviteFriends
        clickBackAndHomeButton(inviteFriends, "ContactTabs");
        // 4.8 search
        clickHomeButtonOnce(search, "ContactTabs");
        // 4.9 setting
        clickBackAndHomeButton(setting, "SettingsHoneycomb");
        // прокручиваем список
        solo.scrollListToLine(homeListView, 8);
        // 4.10 about
        clickHomeButtonOnce(about, "About");
        // 4.11 share
        clickHomeButtonOnce(share, "ChooserActivity");
        // 4.12 Logout
        clickHomeButtonOnce(logout, "Login");
    }

    /**
     * Метод осуществляет проверку нажатие выбранного item и нажатие кнопки назад
     *
     * @param nameButton
     * @param activityId
     */
    private void clickHomeButtonTwice(String nameButton, String activityId) {
        solo.sleep(1000);
        // 1. нажимает на выбранную кнопку
        solo.clickOnText(java.util.regex.Pattern.quote(nameButton));
        solo.sleep(2000);
        // 2. проверка перехода на заданное активити
        assertTrue(" Ожидался вызов активити: " + activityId, solo.waitForActivity(activityId));
        solo.sleep(500);
        // 3. нажатие на кнопку возврата назад (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        // 4. нажатие на кнопку вызова navigation drawer (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(1000);
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
        solo.sleep(1000);
        // 1. нажимает на выбранную кнопку
        solo.clickOnText(java.util.regex.Pattern.quote(nameButton));
        solo.sleep(2000);
        // 2. проверка перехода на заданное активити
        assertTrue(" Ожидался вызов активити: " + activityId, solo.waitForActivity(activityId));
        solo.sleep(500);
        // 3. нажатие на кнопку вызова navigation drawer (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        // 4. проверка перехода на navigation drawer
        //TODO проверка перехода на navigation drawer
    }

    private void clickBackAndHomeButton(String nameButton, String activityId) {
        solo.sleep(2000);
        // 1. нажимает на выбранную кнопку
        solo.clickOnText(java.util.regex.Pattern.quote(nameButton));
        solo.sleep(2000);
        // 2. проверка перехода на заданное активити
        assertTrue(" Ожидался вызов активити: " + activityId, solo.waitForActivity(activityId));
        solo.sleep(500);
        // 3. нажатие на кнопку возврата назад (HomeButton)
        solo.goBack();
        solo.sleep(1000);
        // 4. нажатие на кнопку вызова navigation drawer (HomeButton)
        solo.clickOnActionBarHomeButton();
        solo.sleep(2000);
        // 5. проверка перехода на navigation drawer
        //TODO проверка перехода на navigation drawer
    }


}
