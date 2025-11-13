package com.example.framework.ui.pages;

import com.example.framework.config.Config;
import com.example.framework.drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver d(){ return DriverManager.get(); }
    protected WebDriverWait w(){ return new WebDriverWait(d(), Duration.ofSeconds(Config.get().ui().explicitWaitSec())); }

    protected WebElement el(By locator){ return w().until(ExpectedConditions.visibilityOfElementLocated(locator)); }
    protected void click(By locator){ w().until(ExpectedConditions.elementToBeClickable(locator)).click(); }
    protected void type(By locator,String txt){ var e = el(locator); e.clear(); e.sendKeys(txt); }
    protected void open(String path){ d().get(Config.get().ui().baseUrl() + path); }
}
