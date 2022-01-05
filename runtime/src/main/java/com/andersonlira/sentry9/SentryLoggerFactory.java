package com.andersonlira.sentry9;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Handler;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import io.sentry.event.Event;
import io.sentry.event.helper.ShouldSendEventCallback;
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
        String dsn = Objects.requireNonNull(config.dsn.get(),Sentry9Config.DNS +" property is mandatory");
        SentryClient client = SentryClientFactory.sentryClient(dsn);
        client.addShouldSendEventCallback(new ShouldSendPackage(config.inAppPackages.orElse(List.of())));
        Sentry.setStoredClient(client);
    }

    private class ShouldSendPackage implements ShouldSendEventCallback {
        
        private List<String> packages;

        public ShouldSendPackage(List<String> packages){
            this.packages = packages;
        }

        @Override
        public boolean shouldSend(Event event) {
            String className = event.getLogger();
            return packages.isEmpty() || packages.stream()
            .filter(it -> className.startsWith(it)).findFirst().isPresent();
        }

    }

    

}
