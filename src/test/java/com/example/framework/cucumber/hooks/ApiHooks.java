package com.example.framework.cucumber.hooks;

import io.cucumber.java.*;

public class ApiHooks {
    @Before(value="@api", order=0)
    public void apiBefore(Scenario sc){
        // Could set up auth tokens, clean spec threadlocal, etc.
    }
    @After(value="@api", order=0)
    public void apiAfter(Scenario sc){
        // Cleanup if needed
    }
}
