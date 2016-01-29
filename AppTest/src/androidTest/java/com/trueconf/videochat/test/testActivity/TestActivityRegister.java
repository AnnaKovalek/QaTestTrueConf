package com.trueconf.videochat.test.testActivity;


import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import static junit.framework.Assert.assertTrue;

/**
 * Класс проверки регистрации нового пользователя в Андроид клиент
 */


public class TestActivityRegister {
    private Solo solo;

    public TestActivityRegister(Solo solo) {
        this.solo = solo;
    }

    /**
     * Метод проверки
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
        solo.clickOnWebElement(By.name("login"));
        solo.sleep(1000);
        solo.clearTextInWebElement(By.name("login"));
        solo.sleep(1000);
        solo.enterTextInWebElement(By.name("login"), "sanvant");


        //solo.clickOnWebElement(By.id("person-reg-login"));
      //  solo.clearTextInWebElement(By.id("person-reg-login"));
       // solo.sleep(1000);
       // solo.enterTextInWebElement(By.id("person-reg-login"), "sanvant");
       // solo.sleep(1000);


       // solo.clearEditText((android.widget.ArrayAdapter)solo.getWebElements(By.id("person-reg-login")));
        //solo.clickOnView(solo.getView("person-reg-login"));
     //   solo.clearEditText((android.widget.EditText) solo.getView("person-rg-login"));
       // solo.enterText((android.widget.EditText) solo.getView("person-rg-login"), "sanvant");
        solo.sleep(2000);





    }


}
