package com.andersonlira.sentry9;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

/**
 * Configuration for Sentry9 logging.
 */
@ConfigRoot(phase = ConfigPhase.RUN_TIME, name = "sentry9")
public class Sentry9Config {

    /**
     * Sentry enable
     * Determine whether to enable the Sentry9 logging extension.
     */
    @ConfigItem(name = "enabled")
    public boolean enable;

    /**
     * Sentry DSN
     *
     * The DSN is the first and most important thing to configure because it tells the SDK where to send events. You can find
     * your project’s DSN in the "Client Keys" section of your "Project Settings" in Sentry.
     */
    @ConfigItem(name = "dsn")
    public Optional<String> dsn;

    /**
     * The sentry log level
     */
    @ConfigItem(name = "level", defaultValue = "WARN")
    public Level level;

    /**
     * Sentry inAppPackages
     * To diferentiate non application packages, use inAppPackages property the packages that should scanned by Sentry
     */
    @ConfigItem(name = "inAppPackages")
    public Optional<List<String>> inAppPackages;

}