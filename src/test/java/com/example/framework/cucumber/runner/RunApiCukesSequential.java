package com.example.framework.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

// API sequential
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = { "com.example.framework.cucumber.steps.api", "com.example.framework.cucumber.hooks" },
        plugin = {"pretty","summary","html:build/reports/cuke-api/html","json:build/reports/cuke-api/report.json","junit:build/reports/cuke-api/report.xml"},
        monochrome = true,
        tags = "@api and not @wip"
)
public class RunApiCukesSequential extends AbstractTestNGCucumberTests {}

