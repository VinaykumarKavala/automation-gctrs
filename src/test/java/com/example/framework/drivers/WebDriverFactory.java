package com.example.framework.drivers;

import com.example.framework.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;

public class WebDriverFactory {
    public static WebDriver create(String browser){
        var cfg = Config.get().selenium();
        if (cfg.remoteEnabled() && !cfg.remoteUrl().isBlank()){
            // add Grid support here if you want (RemoteWebDriver) – left local by default
        }
        switch (browser.trim().toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions o = new ChromeOptions();
                if (cfg.headless()) o.addArguments("--headless=new");
                o.addArguments("--window-size=1920,1080");
                return new ChromeDriver(o);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions o = new FirefoxOptions();
                if (cfg.headless()) o.addArguments("-headless");
                return new FirefoxDriver(o);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions o = new EdgeOptions();
                if (cfg.headless()) o.addArguments("--headless=new");
                return new EdgeDriver(o);
            }
            default -> throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }
}
