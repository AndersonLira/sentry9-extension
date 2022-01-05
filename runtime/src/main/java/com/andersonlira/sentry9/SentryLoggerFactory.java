package com.andersonlira.sentry9;

import java.util.Optional;
import java.util.logging.Handler;

import com.andersonlira.DsnCreator;


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

        initSentry(config);
        SentryHandler handler = new SentryHandler();
        
        handler.setLevel(config.level);
             return new RuntimeValue<>(Optional.of(handler));
    }
 
    private void initSentry(Sentry9Config config){
        Sentry.init(DsnCreator.create(config));
    }

}
