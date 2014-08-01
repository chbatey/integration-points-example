package info.batey.examples.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.tenacity.core.config.BreakerboxConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by chbatey on 01/08/2014.
 */
public class AppConfig extends Configuration {
    @NotEmpty
    private String deviceServiceHost;

    @NotNull
    private BreakerboxConfiguration breakerboxConfiguration;

    @NotNull
    private TenacityConfiguration deviceServiceTenacityConfig;

    @JsonProperty
    public TenacityConfiguration getDeviceServiceTenacityConfig() {
        return deviceServiceTenacityConfig;
    }

    @JsonProperty
    public String getDeviceServiceHost() {
        return deviceServiceHost;
    }

    @JsonProperty
    public BreakerboxConfiguration getBreakerboxConfiguration() {
        return breakerboxConfiguration;
    }
}
