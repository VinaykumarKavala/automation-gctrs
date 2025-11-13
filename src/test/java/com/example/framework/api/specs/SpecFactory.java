package com.example.framework.api.specs;

import com.example.framework.config.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecFactory {
    private static final ThreadLocal<RequestSpecification> TL = ThreadLocal.withInitial(() ->
            new RequestSpecBuilder()
                    .setBaseUri(Config.get().api().baseUrl())
                    .addHeader("Accept","application/json")
                    .build()
    );
    public static RequestSpecification spec(){ return TL.get(); }
}
