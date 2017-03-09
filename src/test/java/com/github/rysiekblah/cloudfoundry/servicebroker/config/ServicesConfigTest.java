package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by Tomasz_Kozlowski on 3/4/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@ActiveProfiles("test")
public class ServicesConfigTest {

    @Autowired
    private ServicesConfig servicesConfig;

    @Test
    public void test() throws JsonProcessingException {

        assertTrue(servicesConfig.getServices().size() == 2);

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        System.out.println(mapper.writeValueAsString(servicesConfig));
//
//        String json = mapper.writeValueAsString(servicesConfig);
//
//        List<String> names = JsonPath.read(json, "$.services[*].name");
//        System.out.println("name: " + names.size());

    }

}
