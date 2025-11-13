package com.example.framework.utils;

import com.example.framework.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtils {
    public static String base64() {
        try { return ((TakesScreenshot) DriverManager.get()).getScreenshotAs(OutputType.BASE64); }
        catch (Throwable t){ return ""; }
    }
}
