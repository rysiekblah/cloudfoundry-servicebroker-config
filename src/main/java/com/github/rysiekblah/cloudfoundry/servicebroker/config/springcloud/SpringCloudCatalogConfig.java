package com.github.rysiekblah.cloudfoundry.servicebroker.config.springcloud;

import com.github.rysiekblah.cloudfoundry.servicebroker.config.CatalogConfig;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.PlanConfig;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.ServicesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomasz_Kozlowski on 3/7/2017.
 */
@Configuration
public class SpringCloudCatalogConfig {

    @Bean
    public ServicesConfig servicesConfig() {
        return new ServicesConfig();
    }

    @Bean
    @Autowired
    public static Catalog catalog(ServicesConfig servicesConfig) {
        return new Catalog(
                servicesConfig
                        .getServices()
                        .stream()
                        .map(svc -> svcDef(svc))
                        .collect(Collectors.toList())
        );
    }

    private static ServiceDefinition svcDef(CatalogConfig catalogConfig) {
        return new ServiceDefinition(
                catalogConfig.getId(),
                catalogConfig.getName(),
                catalogConfig.getDescription(),
                catalogConfig.isBindable(),
                convertPlanConfig(catalogConfig.getPlans())
        );
    }

    private static List<Plan> convertPlanConfig(List<PlanConfig> planConfigs) {
        return planConfigs
                .stream()
                .map(pc -> new Plan(pc.getId(), pc.getName(), pc.getDescription()))
                .collect(Collectors.toList());
    }

}
