package com.example.framework.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {
    private final By select = By.id("dropdown");
    public DropdownPage open(){ open("/dropdown"); return this; }
    public DropdownPage selectByVisible(String txt){
        new Select(el(select)).selectByVisibleText(txt); return this;
    }
    public String selected(){
        return new Select(el(select)).getFirstSelectedOption().getText().trim();
    }
}