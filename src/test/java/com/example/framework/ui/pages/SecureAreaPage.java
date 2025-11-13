package com.example.framework.ui.pages;
import org.openqa.selenium.By;
public class SecureAreaPage extends BasePage {
    private final By subHeader = By.cssSelector(".subheader");
    public String subHeader(){ return el(subHeader).getText(); }
}