package com.andersonlira.sentry9.extension.deployment;


import com.andersonlira.sentry9.extension.runtime.Logger;


import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class Sentry9ExtensionProcessor {

    private static final String FEATURE = "sentry9-extension";


    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep 
    AdditionalBeanBuildItem prepareSentry(){
       return new AdditionalBeanBuildItem.Builder().addBeanClass(Logger.class).build();
    }

}
