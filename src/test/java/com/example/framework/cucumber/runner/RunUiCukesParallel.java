package com.example.framework.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;



// UI parallel
@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = { "com.example.framework.cucumber.steps.ui", "com.example.framework.cucumber.hooks" },
        plugin = {"pretty","summary","html:build/reports/cuke-ui/html-par","json:build/reports/cuke-ui/report-par.json","junit:build/reports/cuke-ui/report-par.xml"},
        monochrome = true,
        tags = "@ui and not @wip"
)
class RunUiCukesParallel extends AbstractTestNGCucumberTests {
    @Override @DataProvider(parallel = true) public Object[][] scenarios(){ return super.scenarios(); }
}
