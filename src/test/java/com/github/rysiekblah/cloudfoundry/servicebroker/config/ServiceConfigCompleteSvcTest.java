package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Tomasz_Kozlowski on 3/6/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("one-complete-service")
public class ServiceConfigCompleteSvcTest {

    @Autowired
    private ServicesConfig servicesConfig;

    private static String json;

    @Before
    public void setUpClass() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        System.out.println(mapper.writeValueAsString(servicesConfig));
        json = mapper.writeValueAsString(servicesConfig);
    }

    @Test
    public void testCatalogMandatoryFields() throws JsonProcessingException {

        assertThat(
                "services count",
                ((List<Object>) JsonPath.read(json, "$.services[*]")).size(),
                is(1));

        assertThat(
                "services.name",
                JsonPath.read(json, "$.services[0].name"),
                equalTo("service_1"));

        assertThat(
                "services.id",
                JsonPath.read(json, "$.services[0].id"),
                equalTo("f7798cc3-b2fd-426f-9d7d-0deaf6b80365"));

        assertThat(
                "services.description",
                JsonPath.read(json, "$.services[0].description"),
                equalTo("service_1 description"));

        assertThat(
                "services.bindable",
                JsonPath.read(json, "$.services[0].bindable"),
                is(false)
        );

    }

    @Test
    public void testCatalogOptionalFields() {

        List<String> tags = JsonPath.read(json, "$.services[0].tags");
        assertThat(
                "services.tags size",
                tags.size(),
                is(3));
        assertThat(
                "services.tags content",
                tags,
                hasItems("tag1_s1", "tag2_s1", "tag3_s1"));

        List<String> requires = JsonPath.read(json, "$.services[0].requires");
        assertThat(
                "services.requires count",
                requires.size(),
                is(3));
        assertThat(
                "services.requires content",
                requires,
                hasItems("syslog_drain", "route_forwarding", "volume_mount"));

        assertThat(
                "services.plan_updateable",
                JsonPath.read(json, "$.services[0].plan_updateable"),
                is(true));
    }

    @Test
    public void testServiceMetadata() {

        Map<String, Object> metadata = JsonPath.read(json, "$.services[0].metadata");
        Map<String, String> provider = (Map<String, String>) metadata.get("provider");
        Map<String, String> sales = (Map<String, String>) metadata.get("sales");

        assertThat("services.metadata.displayName",
                metadata,
                hasEntry("displayName", "Service_1 Display name"));

        assertThat("services.metadata.imageUrl",
                metadata,
                hasEntry("imageUrl", "http://example1.com/image.png"));

        assertThat("services.metadata.longDescription",
                metadata,
                hasEntry("longDescription", "servce long description"));

        assertThat("services.metadata.providerDisplayName",
                metadata,
                hasEntry("providerDisplayName", "service_1 provices's name"));

        assertThat("services.metadata.documentationUrl",
                metadata,
                hasEntry("documentationUrl", "github.com/rysiekblah"));

        assertThat("services.metadata.supportUrl",
                metadata,
                hasEntry("supportUrl", "www.rysiekblah.com/support"));

        assertThat("services.metadata.provider.name",
                provider,
                hasEntry("name", "Service provider - name"));

        assertThat("services.metadata.provider.wechat",
                provider,
                hasEntry("wechat", "kozlowst"));

        assertThat("services.metadata.sales.phone",
                sales,
                hasEntry("phone", "+86 186 2345 6778"));

        assertThat("services.metadata.sales.email",
                sales,
                hasEntry("emial", "tkozlowski007@gmail.com"));
    }

    @Test
    public void testDashboardClient() {

        Map<String, String> dashboardClient = JsonPath.read(json, "$.services[0].dashboard");

        assertThat("services.dashboard.id",
                dashboardClient,
                hasEntry("id", "dashboard_1"));

        assertThat("services.dashboard.secret",
                dashboardClient,
                hasEntry("secret", "SKJHI%^%$!!!"));

        assertThat("services.dashboard.redirect_url",
                dashboardClient,
                hasEntry("redirect_uri", "examplw11.com"));
    }

    @Test
    public void testPlan() {

    }

}
