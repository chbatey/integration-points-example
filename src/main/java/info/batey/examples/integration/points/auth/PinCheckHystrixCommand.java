package info.batey.examples.integration.points.auth;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class PinCheckHystrixCommand extends HystrixCommand<Boolean> {

    public PinCheckHystrixCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup")););
    }

    @Override
    protected Boolean run() throws Exception {
        return true;
    }
}
