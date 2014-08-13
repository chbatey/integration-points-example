package info.batey.examples.integration.points;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyKeyFactory;

public class IntegrationPointsFactory implements TenacityPropertyKeyFactory {
    @Override
    public TenacityPropertyKey from(String s) {
        return IntegrationPoints.valueOf(s.toUpperCase());
    }
}
