package info.batey.examples.integration.points.user;

import com.yammer.tenacity.core.TenacityCommand;
import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import info.batey.examples.integration.points.IntegrationPoints;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class UserServiceDependency extends TenacityCommand<String> {

    private HttpClient httpClient;

    public UserServiceDependency(HttpClient httpClient) {
        super(IntegrationPoints.USER_GET);
        this.httpClient = httpClient;
    }

    @Override
    protected String run() throws Exception {
        HttpGet getUserInfo = new HttpGet("http://localhost:9090/user/chris");
        HttpResponse userResponse = httpClient.execute(getUserInfo);
        int statusCode = userResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Oh dear no device information, status code " + statusCode);
        }
        String userInfo = EntityUtils.toString(userResponse.getEntity());
        return userInfo;
    }
}