package info.batey.examples.integration;

import com.google.common.collect.ImmutableMap;
import com.yammer.tenacity.core.bundle.TenacityBundleBuilder;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyRegister;
import info.batey.examples.integration.points.IntegrationPoints;
import info.batey.examples.integration.points.IntegrationPointsFactory;
import info.batey.examples.integration.resources.IntegrationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Map;

/**
 * Created by chbatey on 01/08/2014.
 */
public class IntegrationApplication extends Application<AppConfig> {
    @Override
    public void initialize(Bootstrap<AppConfig> appConfigBootstrap) {
        appConfigBootstrap.addBundle(TenacityBundleBuilder.newBuilder()
                .propertyKeyFactory(new IntegrationPointsFactory())
                .propertyKeys(IntegrationPoints.values())
                .build());
    }

    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        Map<TenacityPropertyKey, TenacityConfiguration> map = ImmutableMap.<TenacityPropertyKey, TenacityConfiguration>of(
                IntegrationPoints.DEVICE_SERVICE_GET, appConfig.getDeviceServiceTenacityConfig()
        );
        new TenacityPropertyRegister(map, appConfig.getBreakerboxConfiguration()).register();
        environment.jersey().register(new IntegrationResource());
    }

    public static void main(String[] args) throws Exception {
        new IntegrationApplication().run(args);
    }
}
