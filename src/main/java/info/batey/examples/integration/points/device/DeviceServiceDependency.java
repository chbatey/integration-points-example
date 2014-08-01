package info.batey.examples.integration.points.device;

import com.yammer.tenacity.core.TenacityCommand;
import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import info.batey.examples.integration.points.IntegrationPoints;

/**
 * Created by chbatey on 01/08/2014.
 */
public class DeviceServiceDependency extends TenacityCommand<String> {
    public DeviceServiceDependency() {
        super(IntegrationPoints.DEVICE_SERVICE_GET);
    }

    @Override
    public String run() throws Exception {
        Thread.sleep(5  * 1000);
        return "what an awesome device";
    }
}
