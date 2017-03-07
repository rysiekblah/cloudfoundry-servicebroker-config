package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.json.EmptyListSerializer;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/4/2017.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanMetadata {

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private List<String> bullets;

    @JsonSerialize(nullsUsing = EmptyListSerializer.class)
    private List<PlanCost> costs;

    public List<String> getBullets() {
        return bullets;
    }

    public void setBullets(List<String> bullets) {
        this.bullets = bullets;
    }

    public List<PlanCost> getCosts() {
        return costs;
    }

    public void setCosts(List<PlanCost> costs) {
        this.costs = costs;
    }

    public Map<String, Object> getMetadata() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("bullets", bullets);
        map.put("costs", costs);
        return map;
    }

//    private List<Map<String, Object>> convertBullets(List<String> b) {
//        List<Map<String, Object>> bltl = Lists.newArrayList();
//        for (String s : b) {
//            bltl.add(ImmutableMap.of("content", s));
//        }
//        return bltl;
//    }
}
