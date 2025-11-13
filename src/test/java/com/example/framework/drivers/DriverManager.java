package com.example.framework.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();
    public static void set(WebDriver d){ TL.set(d); }
    public static WebDriver get(){ var d=TL.get(); if(d==null) throw new IllegalStateException("Driver not set"); return d; }
    public static void remove(){ try{ var d=TL.get(); if(d!=null) d.quit(); } finally { TL.remove(); } }
}
