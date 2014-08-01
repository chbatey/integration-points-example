package info.batey.examples.integration.resources;

import com.codahale.metrics.annotation.Timed;
import info.batey.examples.integration.points.device.DeviceServiceDependency;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by chbatey on 01/08/2014.
 */
@Path("integrate")
public class IntegrationResource {

    @GET
    @Timed
    public String integrate() {
        return new DeviceServiceDependency().execute();
    }
}
