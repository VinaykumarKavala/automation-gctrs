package com.example.framework.ui.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");

    public LoginPage open(){ open("/login"); return this; }
    public LoginPage enterUsername(String u){ type(username,u); return this; }
    public LoginPage enterPassword(String p){ type(password,p); return this; }
    public SecureAreaPage submit(){ click(loginBtn); return new SecureAreaPage(); }
    public String flash(){ return el(flash).getText(); }
}


