package com.example.framework.cucumber.hooks;

import com.example.framework.config.Config;
import com.example.framework.drivers.DriverManager;
import com.example.framework.drivers.WebDriverFactory;
import com.example.framework.utils.ScreenshotUtils;
import io.cucumber.java.*;

public class UiHooks {
    @Before(value="@ui", order=0)
    public void uiBefore(Scenario sc){
        String browser = Config.get().selenium().browsers().get(0);
        DriverManager.set(WebDriverFactory.create(browser));
    }
    @After(value="@ui", order=0)
    public void uiAfter(Scenario sc){
        if (sc.isFailed()){
            String b64 = ScreenshotUtils.base64();
            if (!b64.isEmpty()) sc.attach(b64, "image/png", "failure-screenshot");
        }
        DriverManager.remove();
    }
}
