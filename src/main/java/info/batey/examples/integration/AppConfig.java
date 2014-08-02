package info.batey.examples.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.tenacity.core.config.BreakerboxConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
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

    @Valid
    @NotNull
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @JsonProperty
    public TenacityConfiguration getDeviceServiceTenacityConfig() {
        return deviceServiceTenacityConfig;
    }

    @JsonProperty
    public HttpClientConfiguration getHttpClient() {
        return httpClient;
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
