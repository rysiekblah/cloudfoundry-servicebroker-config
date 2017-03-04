package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Tomasz_Kozlowski on 3/4/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
public class ServicesConfigTest {

    @Autowired
    private ServicesConfig servicesConfig;

    @Test
    public void test() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        System.out.println(mapper.writeValueAsString(servicesConfig));

        String json = mapper.writeValueAsString(servicesConfig);

        List<String> names = JsonPath.read(json, "$.services[*].name");
        System.out.println("name: " + names.size());

    }

}
