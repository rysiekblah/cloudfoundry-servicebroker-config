package com.simple.cloudfoundry.broker;

import com.github.rysiekblah.cloudfoundry.servicebroker.config.CatalogConfig;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.PlanConfig;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.ServicesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomasz_Kozlowski on 3/8/2017.
 */
@SpringBootApplication
public class ServiceBrokerApplication {

//    @Bean
//    public ServicesConfig servicesConfig() {
//        return new ServicesConfig();
//    }

//    @Bean
//    @Autowired
//    public Catalog catalog(ServicesConfig servicesConfig) {
//        return new Catalog((List)servicesConfig.getServices().stream().map((svc) -> {
//            return svcDef(svc);
//        }).collect(Collectors.toList()));
//    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceBrokerApplication.class, args);
    }
//
//    private static ServiceDefinition svcDef(CatalogConfig catalogConfig) {
//        return new ServiceDefinition(catalogConfig.getId(), catalogConfig.getName(), catalogConfig.getDescription(), catalogConfig.isBindable(), convertPlanConfig(catalogConfig.getPlans()));
//    }
//
//    private static List<Plan> convertPlanConfig(List<PlanConfig> planConfigs) {
//        return (List)planConfigs.stream().map((pc) -> {
//            return new Plan(pc.getId(), pc.getName(), pc.getDescription());
//        }).collect(Collectors.toList());
//    }
}
