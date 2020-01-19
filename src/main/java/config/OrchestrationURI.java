package config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class OrchestrationURI {

    private String URI;

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }
}
