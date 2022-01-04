package com.andersonlira.sentry9.extension.runtime;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.ConfigProvider;
import io.sentry.Sentry;

@ApplicationScoped
public class Logger {


    public Logger(){
        String dsn  = ConfigProvider.getConfig().getValue("quarkus.sentry9.dsn", String.class);
        Sentry.init(dsn);
    }

    public void info(String msg){
        Sentry.capture(msg);
    }
}
