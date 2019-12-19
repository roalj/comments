package config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("configuration-properties")
@ApplicationScoped
public class IntegrationProperties {

    @ConfigValue(value = "comments-properties.test-error", watch = true)
    private boolean makeError;

    public boolean isMakeError() {
        return makeError;
    }

    public void setMakeError(boolean makeError) {
        this.makeError = makeError;

    }
}