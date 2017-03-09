package com.github.rysiekblah.cloudfoundry.servicebroker.config.springcloud;

import com.github.rysiekblah.cloudfoundry.servicebroker.config.CatalogConfig;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.DashboardClientConfig;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.PlanConfig;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.ServicesConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.DashboardClient;
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
@ConditionalOnWebApplication
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class SpringCloudCatalogConfig {

    @Bean
    public ServicesConfig servicesConfig() {
        return new ServicesConfig();
    }

    @Bean
    public Catalog catalog(ServicesConfig servicesConfig) {
        return new Catalog(
                servicesConfig
                        .getServices()
                        .stream()
                        .map(svc -> convertCatalogConfig(svc))
                        .collect(Collectors.toList())
        );
    }

    private ServiceDefinition convertCatalogConfig(CatalogConfig catalogConfig) {
        return new ServiceDefinition(
                catalogConfig.getId(),
                catalogConfig.getName(),
                catalogConfig.getDescription(),
                catalogConfig.isBindable(),
                catalogConfig.isPlan_updatable(),
                convertPlanConfig(catalogConfig.getPlans()),
                catalogConfig.getTags(),
                catalogConfig.getMetadata(),
                catalogConfig.getRequires(),
                convertDashboard(catalogConfig.getDashboard())
        );
    }

    private List<Plan> convertPlanConfig(List<PlanConfig> planConfigs) {
        return planConfigs
                .stream()
                .map(pc -> new Plan(
                        pc.getId(),
                        pc.getName(),
                        pc.getDescription(),
                        pc.getMetadata().get(),
                        pc.isFree()))
                .collect(Collectors.toList());
    }

    private DashboardClient convertDashboard(DashboardClientConfig dashboard) {
        return dashboard == null ? null :
                new DashboardClient(dashboard.getId(), dashboard.getSecret(), dashboard.getRedirect_uri());
    }


}
