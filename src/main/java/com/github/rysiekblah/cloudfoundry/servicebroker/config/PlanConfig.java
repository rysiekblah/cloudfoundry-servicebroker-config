package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Tomasz_Kozlowski on 2/25/2017.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanConfig {

    @JsonSerialize
    private String id;

    @JsonSerialize
    private String name;

    @JsonSerialize
    private String description;

    @JsonSerialize
    private PlanMetadata metadata;

    @JsonSerialize
    private boolean bindable;

    @JsonSerialize
    private boolean free;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlanMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PlanMetadata metadata) {
        this.metadata = metadata;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean getBindable() {
        return bindable;
    }

    public void setBindable(boolean bindable) {
        this.bindable = bindable;
    }
}
