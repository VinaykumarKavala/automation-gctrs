
package com.example.framework.ui.tests;

import com.example.framework.config.Config;
import com.example.framework.drivers.*;
import com.example.framework.ui.pages.DropdownPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class DropdownTest {
    @BeforeMethod public void setup(){ DriverManager.set(WebDriverFactory.create(Config.get().selenium().browsers().get(0))); }
    @AfterMethod public void teardown(){ DriverManager.remove(); }

    @Test(groups={"ui"})
    public void select_dropdown_options(){
        var p = new DropdownPage().open().selectByVisible("Option 2");
        Assert.assertEquals(p.selected(), "Option 2");
    }
}