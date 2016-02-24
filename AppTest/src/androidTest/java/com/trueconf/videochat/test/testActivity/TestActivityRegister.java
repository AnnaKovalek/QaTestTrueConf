package com.trueconf.videochat.test.testActivity;


import android.webkit.WebView;

import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;



/**
 * Класс проверки возможности регистрации (заполнения) нового пользователя
 */


public class TestActivityRegister {
    private Solo solo;

    public TestActivityRegister(Solo solo) {
        this.solo = solo;
    }

    /**
     * Метод проверки регистрации при вводе
     */
    public void testActivityRegister() {
        solo.sleep(2000);
        //Wait for activity: 'com.trueconf.gui.activities.Login'
        solo.waitForActivity("Login", 10000);
        //Set default small timeout to 12000 milliseconds
        Timeout.setSmallTimeout(12000);
        //Click on "Sign up for TrueConf"
        solo.clickOnView(solo.getView("tv_registrate"));
        assertTrue("Register is not found!", solo.waitForActivity("Register"));
        solo.sleep(2000);

        //By.id("person-reg-login");
        /**
         ArrayList<View> currentViews = solo.getCurrentViews();
         assertEquals(currentViews, 1);
         */
        WebView webview = solo.getView(WebView.class, 0);
        solo.sleep(500);
        solo.waitForWebElement(By.id("wv_register_enter_register_info"));
        solo.sleep(500);
        solo.clickOnWebElement(By.id("person-reg-login"));
        solo.clearTextInWebElement(By.id("person-reg-login"));
        solo.enterTextInWebElement(By.id("person-reg-login"), "lorent");
        solo.sleep(2000);
        //TODO: сделать проверку валидности введенного логина (неиспользуется ли логин, валидность)
        //6=<логин<255
        // solo.getText(0).toString();

        solo.clickOnWebElement(By.id("person-reg-password"));
        solo.clearTextInWebElement(By.id("person-reg-password"));
        solo.enterTextInWebElement(By.id("person-reg-password"), "康熙字典體康熙字典體");
        solo.sleep(2000);
        // TODO: сделать проверку валидности введенного пароля
        // 6=<пароль<255

        solo.clickOnWebElement(By.id("person-reg-email"));
        solo.clearTextInWebElement(By.id("person-reg-email"));
        solo.enterTextInWebElement(By.id("person-reg-email"), "kovalekann@gmail.comu");
        solo.sleep(200);
        //TODO: сделать проверку валидности введенного email (неиспользуется ли)
        // проверяет наличие точки (.) и @
        // email<64

        solo.clickOnWebElement(By.id("person-reg-full_name"));
        solo.sleep(3000);
        solo.clearTextInWebElement(By.id("person-reg-full_name"));
        solo.sleep(200);
        solo.enterTextInWebElement(By.id("person-reg-full_name"), "Lululu Bububu");
        solo.sleep(200);
        // full_name <80
        solo.clickOnWebElement(By.className("line corporate_key"));
        solo.sleep(2000);
        solo.clickOnWebElement(By.id("person-reg-corp_code"));
        solo.sleep(100);
        solo.clearTextInWebElement(By.id("person-reg-corp_code"));
        solo.enterTextInWebElement(By.id("person-reg-corp_code"), "nfnfnn");
        solo.sleep(200);

        solo.clickOnWebElement(By.className("checkbox"));
        solo.sleep(500);
        solo.clickOnWebElement(By.className("checkbox"));
        solo.sleep(200);
        // TODO: не нажимает на ссылку  Terms of use
        // solo.clickOnWebElement(By.className("line terms"));
        solo.clickOnWebElement(By.textContent("Terms of Use"));


        solo.sleep(1000);

        //кнопка нажимается
        solo.clickOnWebElement(By.className("line btn_submit"));
        assertFalse("Activity Contact Tabs is found", solo.waitForActivity("ContactTabs"));
        solo.sleep(1000);
        solo.clickOnView(solo.getView("action_bar_title"));
        solo.sleep(500);
        assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
        solo.sleep(500);
        solo.goBack();


    }


}
