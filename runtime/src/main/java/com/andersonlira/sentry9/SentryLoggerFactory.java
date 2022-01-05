package com.andersonlira.sentry9;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.sentry.Sentry;
import io.sentry.jul.SentryHandler;

@Recorder
public class SentryLoggerFactory {
    public RuntimeValue<Optional<Handler>> create(Sentry9Config config){
        if(!config.enable){
            return new RuntimeValue<>(Optional.empty());
        }

        Objects.requireNonNull(config.dsn.get(), "dsn configuration is mandatory");
        initSentry(config);
        SentryHandler handler = new SentryHandler();
        
        handler.setLevel(config.level);
        handler.setFilter(new Filter() {

            @Override
            public boolean isLoggable(LogRecord record) {
                System.out.println("=====================");
                System.out.println("=====================");
                System.out.println(record.getSourceClassName());
                System.out.println("=====================");
                System.out.println("=====================");
                return (record.getSourceClassName().startsWith("com.fontelira"));
            }
            
        });

        return new RuntimeValue<>(Optional.of(handler));
    }

 
    private void initSentry(Sentry9Config config){
        System.out.println(config.inAppPackages.get());
        Sentry.init(config.dsn.get());
    }

}
