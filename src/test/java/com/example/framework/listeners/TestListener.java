package com.example.framework.listeners;

import com.example.framework.utils.ScreenshotUtils;
import org.testng.*;

public class TestListener implements ITestListener {
    @Override public void onTestFailure(ITestResult result) {
        try {
            String b64 = ScreenshotUtils.base64();
            if (!b64.isEmpty()) Reporter.log("<img src='data:image/png;base64,"+b64+"'/>");
        } catch (Throwable ignored) {}
    }
}
