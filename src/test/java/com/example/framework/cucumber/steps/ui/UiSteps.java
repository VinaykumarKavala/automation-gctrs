package com.example.framework.cucumber.steps.ui;

import com.example.framework.config.Config;
import com.example.framework.ui.pages.*;
import io.cucumber.java.en.*;

import static org.testng.Assert.*;

public class UiSteps {
    @Given("I open the Login page") public void openLogin(){ new LoginPage().open(); }
    @When("I login with configured credentials")
    public void loginCfg(){
        new LoginPage().enterUsername(Config.get().ui().username())
                .enterPassword(Config.get().ui().password()).submit();
    }
    @Then("I should reach Secure Area")
    public void assertSecure(){ assertTrue(new SecureAreaPage().subHeader().toLowerCase().contains("secure area")); }

    @Given("I open Checkboxes page") public void openCb(){ new CheckboxesPage().open(); }
    @When("I toggle checkbox at index {int}") public void toggleCb(int idx){ new CheckboxesPage().toggle(idx); }
    @Then("checkbox at index {int} should be toggled") public void cbToggled(int idx){ assertTrue(new CheckboxesPage().isChecked(idx)); }

    @Given("I open Dropdown page") public void openDd(){ new DropdownPage().open(); }
    @When("I choose {string}") public void choose(String opt){ new DropdownPage().selectByVisible(opt); }
    @Then("selected option should be {string}") public void selectedIs(String opt){ assertEquals(new DropdownPage().selected(), opt); }
}
