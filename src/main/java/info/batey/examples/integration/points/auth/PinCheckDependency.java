package info.batey.examples.integration.points.auth;

import com.yammer.tenacity.core.TenacityCommand;
import info.batey.examples.integration.points.IntegrationPoints;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class PinCheckDependency extends TenacityCommand<Boolean> {

    private HttpClient httpClient;

    public PinCheckDependency(HttpClient httpClient) {
        super(IntegrationPoints.PIN_CHECK);
        this.httpClient = httpClient;
    }

    @Override
    protected Boolean run() throws Exception {
        HttpGet pinCheck = new HttpGet("http://localhost:9090/pincheck");
        HttpResponse pinCheckResponse = httpClient.execute(pinCheck);
        int statusCode = pinCheckResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Oh dear no device information, status code " + statusCode);
        }
        String pinCheckInfo = EntityUtils.toString(pinCheckResponse.getEntity());
        return Boolean.valueOf(pinCheckInfo);
    }

    @Override
    public Boolean getFallback() {
        return true;
    }
}
