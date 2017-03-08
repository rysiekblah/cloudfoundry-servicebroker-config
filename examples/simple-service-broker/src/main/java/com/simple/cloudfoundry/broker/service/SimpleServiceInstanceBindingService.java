package com.simple.cloudfoundry.broker.service;

import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

/**
 * Created by Tomasz_Kozlowski on 3/8/2017.
 */
@Service
public class SimpleServiceInstanceBindingService implements ServiceInstanceBindingService {
    @Override
    public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {
        return new CreateServiceInstanceAppBindingResponse();
    }

    @Override
    public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {

    }
}
