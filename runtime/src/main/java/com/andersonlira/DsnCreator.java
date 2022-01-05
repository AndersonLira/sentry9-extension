package com.andersonlira;

import java.util.Objects;

import com.andersonlira.sentry9.Sentry9Config;

public class DsnCreator {

    private DsnCreator(){}

    public static String create(Sentry9Config config){
        String dsn = Objects.requireNonNull(config.dsn.get(),"dsn is mandatory");

        return dsn + "?stacktrace.app.packages=" + config.inAppPackages.orElse("*");
    }
}
