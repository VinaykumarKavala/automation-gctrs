package com.example.framework.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;



// API parallel
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = { "com.example.framework.cucumber.steps.api", "com.example.framework.cucumber.hooks" },
        plugin = {"pretty","summary","html:build/reports/cuke-api/html-par","json:build/reports/cuke-api/report-par.json","junit:build/reports/cuke-api/report-par.xml"},
        monochrome = true,
        tags = "@api and not @wip"
)
class RunApiCukesParallel extends AbstractTestNGCucumberTests {
    @Override @DataProvider(parallel = true) public Object[][] scenarios(){ return super.scenarios(); }
}

