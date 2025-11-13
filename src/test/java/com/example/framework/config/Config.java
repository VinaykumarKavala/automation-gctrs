package com.example.framework.config;

import com.example.framework.utils.PropertyUtils;
import java.util.List;
import java.util.Locale;

public final class Config {
    private static Config SINGLE;

    public static Config get(){
        if (SINGLE == null) synchronized (Config.class) {
            if (SINGLE == null) SINGLE = build();
        }
        return SINGLE;
    }

    private static Config build(){
        String env = System.getProperty("env","dev").toLowerCase(Locale.ROOT);
        PropertyUtils.init(env);
        PropertyUtils.require("api.base","ui.base","ui.explicitWait.s","sel.browsers",
                "par.mode","par.threads","par.dpThreads","par.forks","par.forkEvery");

        return new Config(
                env,
                new ApiCfg(PropertyUtils.get("api.base"),
                        PropertyUtils.getInt("api.timeout.ms"),
                        keyOrEmpty("api.token.bearer")),
                new UiCfg(PropertyUtils.get("ui.base"),
                        PropertyUtils.get("ui.username"),
                        PropertyUtils.get("ui.password"),
                        PropertyUtils.getLong("ui.explicitWait.s"),
                        PropertyUtils.getLong("ui.implicitWait.s")),
                new SeleniumCfg(List.of(PropertyUtils.get("sel.browsers").split(",")),
                        PropertyUtils.getBool("sel.headless"),
                        PropertyUtils.get("sel.pageLoadStrategy"),
                        PropertyUtils.getBool("sel.remote.enabled"),
                        keyOrEmpty("sel.remote.url")),
                new ParallelCfg(PropertyUtils.get("par.mode"),
                        PropertyUtils.getInt("par.threads"),
                        PropertyUtils.getInt("par.dpThreads"),
                        PropertyUtils.getInt("par.forks"),
                        PropertyUtils.getLong("par.forkEvery")),
                new ReportingCfg(PropertyUtils.getInt("retry.count"),
                        PropertyUtils.get("report.logs.level"))
        );
    }

    private static String keyOrEmpty(String k){
        try { return PropertyUtils.get(k); } catch(Exception e){ return ""; }
    }

    private final String env; private final ApiCfg api; private final UiCfg ui;
    private final SeleniumCfg selenium; private final ParallelCfg parallel; private final ReportingCfg reporting;
    private Config(String env, ApiCfg api, UiCfg ui, SeleniumCfg selenium, ParallelCfg parallel, ReportingCfg reporting){
        this.env=env; this.api=api; this.ui=ui; this.selenium=selenium; this.parallel=parallel; this.reporting=reporting;
    }
    public String env(){ return env; }
    public ApiCfg api(){ return api; }
    public UiCfg ui(){ return ui; }
    public SeleniumCfg selenium(){ return selenium; }
    public ParallelCfg parallel(){ return parallel; }
    public ReportingCfg reporting(){ return reporting; }

    public record ApiCfg(String baseUrl, int timeoutMs, String bearerToken){}
    public record UiCfg(String baseUrl, String username, String password, long explicitWaitSec, long implicitWaitSec){}
    public record SeleniumCfg(List<String> browsers, boolean headless, String pageLoadStrategy, boolean remoteEnabled, String remoteUrl){}
    public record ParallelCfg(String mode, int threads, int dpThreads, int forks, long forkEvery){}
    public record ReportingCfg(int retryCount, String logLevel){}
}
