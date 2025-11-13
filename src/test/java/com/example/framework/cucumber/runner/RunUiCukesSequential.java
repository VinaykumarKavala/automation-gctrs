package com.example.framework.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;



// UI sequential
@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = { "com.example.framework.cucumber.steps.ui", "com.example.framework.cucumber.hooks" },
        plugin = {"pretty","summary","html:build/reports/cuke-ui/html","json:build/reports/cuke-ui/report.json","junit:build/reports/cuke-ui/report.xml"},
        monochrome = true,
        tags = "@ui and not @wip"
)
class RunUiCukesSequential extends AbstractTestNGCucumberTests {}


