package com.example.framework.api.tests;

import com.example.framework.api.RestClient;
import com.example.framework.api.models.User;
import com.example.framework.config.Config;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReqResHttpbinTests {
    private final RestClient client = new RestClient();

    @Test(groups={"api","smoke"})
    public void listUsers_page2_hasData(){
        var r = client.get("/api/users?page=2");
        assertThat(r.statusCode(), is(200));
        assertThat(r.jsonPath().getList("data"), not(empty()));
    }

    @Test(groups={"api"})
    public void createUser_morpheus(){
        var r = client.postJson("/api/users", new User("morpheus","leader"));
        assertThat(r.statusCode(), anyOf(is(201), is(200)));
        assertThat(r.jsonPath().getString("name"), equalTo("morpheus"));
    }

    @Test(groups={"api"})
    public void register_unsuccessful_missingPassword(){
        var r = client.postJson("/api/register", "{\"email\":\"sydney@fife\"}");
        assertThat(r.statusCode(), is(400));
        assertThat(r.jsonPath().getString("error").toLowerCase(), containsString("missing"));
    }

    @Test(groups={"api"})
    public void httpbin_headers_echo(){
        var r = client.get("/headers");
        assertThat(r.statusCode(), is(200));
        assertThat(r.jsonPath().getMap("headers"), hasKey("Host"));
    }

    @Test(groups={"api"})
    public void httpbin_delay_2s_ok(){
        // only works when base is httpbin (uat env). On reqres, fall back.
        var base = Config.get().api().baseUrl();
        var r = client.get(base.contains("httpbin") ? "/delay/2" : "/api/users?page=1");
        assertThat(r.statusCode(), is(200));
    }
}
