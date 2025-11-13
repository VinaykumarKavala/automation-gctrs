package com.example.framework.ui.tests;

import com.example.framework.config.Config;
import com.example.framework.drivers.*;
import com.example.framework.ui.pages.*;
import com.example.framework.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    @BeforeMethod(alwaysRun = true)
    public void setup(){ DriverManager.set(WebDriverFactory.create(Config.get().selenium().browsers().get(0))); }
    @AfterMethod(alwaysRun = true)
    public void teardown(){ DriverManager.remove(); }

    @Test(groups={"ui","smoke"})
    public void validLogin_secureArea(){
        String sub = new LoginPage().open()
                .enterUsername(Config.get().ui().username())
                .enterPassword(Config.get().ui().password())
                .submit()
                .subHeader();
        Assert.assertTrue(sub.toLowerCase().contains("secure area"));
    }

    @Test(groups={"ui"})
    public void invalidLogin_flashMessage(){
        String flash = new LoginPage().open()
                .enterUsername("bad")
                .enterPassword("creds")
                .submit()
                .subHeader(); // redirected back
        Assert.assertTrue(flash.toLowerCase().contains("your username is invalid!"));
    }
}




