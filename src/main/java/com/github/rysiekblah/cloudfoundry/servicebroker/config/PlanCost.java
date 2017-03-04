package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.json.EmptyMapSerializer;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/4/2017.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanCost {

    @JsonSerialize(nullsUsing = EmptyMapSerializer.class)
    private Map<String,String> amount;

    @JsonSerialize
    private String unit;

    public Map<String, String> getAmount() {
        return amount;
    }

    public void setAmount(Map<String, String> amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
