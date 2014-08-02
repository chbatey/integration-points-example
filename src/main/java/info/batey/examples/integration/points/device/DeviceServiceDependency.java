package info.batey.examples.integration.points.device;

import com.yammer.tenacity.core.TenacityCommand;
import info.batey.examples.integration.points.IntegrationPoints;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class DeviceServiceDependency extends TenacityCommand<String> {

    private HttpClient httpClient;

    public DeviceServiceDependency(HttpClient httpClient) {
        super(IntegrationPoints.DEVICE_SERVICE_GET);
        this.httpClient = httpClient;
    }

    @Override
    public String run() throws Exception {
        HttpGet getDeviceInfo = new HttpGet("http://localhost:9090/device/chris-iphone");
        HttpResponse deviceResponse = httpClient.execute(getDeviceInfo);
        int statusCode = deviceResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Oh dear no device information, status code " + statusCode);
        }
        String deviceInfo = EntityUtils.toString(deviceResponse.getEntity());
        return deviceInfo;
    }
}
