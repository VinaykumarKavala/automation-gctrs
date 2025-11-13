package com.example.framework.ui.tests;

import com.example.framework.config.Config;
import com.example.framework.drivers.*;
import com.example.framework.ui.pages.CheckboxesPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class CheckboxesTest {
    @BeforeMethod public void setup(){ DriverManager.set(WebDriverFactory.create(Config.get().selenium().browsers().get(0))); }
    @AfterMethod public void teardown(){ DriverManager.remove(); }

    @Test(groups={"ui"})
    public void toggle_checkboxes(){
        var p = new CheckboxesPage().open();
        int count = p.count();
        Assert.assertTrue(count >= 2);
        boolean first = p.isChecked(0);
        p.toggle(0);
        Assert.assertNotEquals(p.isChecked(0), first);
    }
}
