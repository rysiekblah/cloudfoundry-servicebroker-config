package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.json.EmptyListSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by Tomasz_Kozlowski on 3/4/2017.
 */
//cloudfoundry.servicebroker.catalog.services
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Configuration
@ConfigurationProperties(prefix = "cloudfoundry.servicebroker.catalog")
public class ServicesConfig {

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private List<CatalogConfig> services;

    public List<CatalogConfig> getServices() {
        return services;
    }

    public void setServices(List<CatalogConfig> services) {
        this.services = services;
    }
}
