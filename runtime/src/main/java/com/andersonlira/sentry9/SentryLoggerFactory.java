package com.andersonlira.sentry9;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Handler;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.sentry.Sentry;
import io.sentry.jul.SentryHandler;

@Recorder
public class SentryLoggerFactory {
    public RuntimeValue<Optional<Handler>> create(Sentry9Config config){

        Objects.requireNonNull(config.dsn.get(), "dsn configuration is mandatory");
        initSentry(config);
        SentryHandler handler = new SentryHandler();
        
        handler.setLevel(config.level);

        return new RuntimeValue<>(Optional.of(handler));
    }

 
    private void initSentry(Sentry9Config config){
        Sentry.init(config.dsn.get());
    }

}
