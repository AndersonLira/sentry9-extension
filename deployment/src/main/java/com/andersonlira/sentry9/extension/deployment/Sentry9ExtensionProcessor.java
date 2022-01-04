package com.andersonlira.sentry9.extension.deployment;

import com.andersonlira.sentry9.Sentry9Config;
import com.andersonlira.sentry9.SentryLoggerFactory;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.LogHandlerBuildItem;

class Sentry9ExtensionProcessor {

    private static final String FEATURE = "sentry9-extension";


    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    LogHandlerBuildItem addSentryLogHandler(final SentryLoggerFactory factory,final Sentry9Config config) {
        return new LogHandlerBuildItem(factory.create(config));
    }

}
