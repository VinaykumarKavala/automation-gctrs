package com.example.framework.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckboxesPage extends BasePage {
    private final By cb = By.cssSelector("#checkboxes input[type='checkbox']");
    public CheckboxesPage open(){ open("/checkboxes"); return this; }
    public int count(){ return d().findElements(cb).size(); }
    public boolean isChecked(int index){ return d().findElements(cb).get(index).isSelected(); }
    public CheckboxesPage toggle(int index){
        List<WebElement> boxes = d().findElements(cb);
        boxes.get(index).click(); return this;
    }
}


