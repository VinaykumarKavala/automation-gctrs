package com.example.framework.ui.tests;

import com.example.framework.config.Config;
import com.example.framework.drivers.*;
import com.example.framework.ui.pages.LoginPage;
import com.example.framework.ui.pages.SecureAreaPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;

public class CrossBrowserLoginTest {
    @DataProvider(name="browsers", parallel = true)
    public Object[][] browsers(){
        return Config.get().selenium().browsers().stream()
                .map(String::trim).filter(s->!s.isBlank()).map(b->new Object[]{b})
                .toArray(Object[][]::new);
    }

    @AfterMethod(alwaysRun = true)
    public void tear(){ DriverManager.remove(); }

    @Test(dataProvider="browsers", groups={"ui","cross"})
    public void loginWorks_allBrowsers(String browser){
        DriverManager.set(WebDriverFactory.create(browser));
        String sub = new LoginPage().open()
                .enterUsername(Config.get().ui().username())
                .enterPassword(Config.get().ui().password())
                .submit()
                .subHeader();
        Assert.assertTrue(sub.toLowerCase().contains("secure area"), "Browser="+browser);
    }
}