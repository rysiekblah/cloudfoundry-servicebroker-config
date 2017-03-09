package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Tomasz_Kozlowski on 3/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@ActiveProfiles("test")
public class ServiceConfigTwoSvcTest {

    @Autowired
    private ServicesConfig servicesConfig;

    @Test
    public void test() throws JsonProcessingException {

    }

}
