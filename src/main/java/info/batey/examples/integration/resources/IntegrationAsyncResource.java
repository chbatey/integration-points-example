package info.batey.examples.integration.resources;

import com.codahale.metrics.annotation.Timed;
import info.batey.examples.integration.points.auth.PinCheckDependency;
import info.batey.examples.integration.points.device.DeviceServiceDependency;
import info.batey.examples.integration.points.user.UserServiceDependency;
import org.apache.http.client.HttpClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.Future;

@Path("async")
public class IntegrationAsyncResource {

    private HttpClient httpClient;

    public IntegrationAsyncResource(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @GET
    @Timed
    public String integrateAsync() throws Exception {
        Future<String> user = new UserServiceDependency(httpClient).queue();
        Future<String> device = new DeviceServiceDependency(httpClient).queue();
        Future<Boolean> pinCheck = new PinCheckDependency(httpClient).queue();

        return String.format("[User info: %s] \n[Device info: %s] \n[Pin check: %s] \n", user.get(), device.get(), pinCheck.get());
    }
}
