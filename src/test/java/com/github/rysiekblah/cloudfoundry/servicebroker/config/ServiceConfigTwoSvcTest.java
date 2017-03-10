package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by Tomasz_Kozlowski on 3/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@ActiveProfiles("two-svc")
public class ServiceConfigTwoSvcTest {

    @Autowired
    private ServicesConfig servicesConfig;

    @Test
    public void testServicesSize() throws JsonProcessingException {
        assertThat(
                "services.size",
                servicesConfig.getServices(),
                hasSize(2)
        );
    }

    @Test
    public void testServicesMandatoryFields() {

        List<String> names = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<List<String>> tags = new ArrayList<>();

        for (CatalogConfig catalogConfig : servicesConfig.getServices()) {
            names.add(catalogConfig.getName());
            ids.add(catalogConfig.getId());
            descriptions.add(catalogConfig.getDescription());
            tags.add(catalogConfig.getTags());
        }

        assertThat(
                "services.name",
                names,
                hasItems("service_1", "service_2")
        );

        assertThat(
                "services.id",
                ids,
                hasItems("1d2b8576-88df-40d9-a1d3-0fa3ed72228c", "1d2b8576-88df-40d9-a1d3-eeeeeeeeeeee")
        );

        assertThat(
                "services.description",
                descriptions,
                hasItems("service_1 description", "service_2 description")
        );

        assertThat(
                "",
                tags,
                hasItems(
                        Arrays.asList("tag1_s1", "tag2_s1", "tag3_s1"),
                        Arrays.asList("tag1_s2", "tag2_s2", "tag3_s2")
                )
        );
    }

}
