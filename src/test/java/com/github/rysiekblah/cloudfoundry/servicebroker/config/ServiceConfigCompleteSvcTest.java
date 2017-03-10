package com.github.rysiekblah.cloudfoundry.servicebroker.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Tomasz_Kozlowski on 3/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@ActiveProfiles("one-complete-service")
public class ServiceConfigCompleteSvcTest {

    @Autowired
    private ServicesConfig servicesConfig;

    public CatalogConfig catalogConfig;

    @Before
    public void setUp() {
        catalogConfig = servicesConfig.getServices().get(0);
    }

    @Test
    public void testCatalogMandatoryFields() throws JsonProcessingException {

        assertThat(
                "services count",
                servicesConfig.getServices().size(),
                is(1));

        assertThat(
                "services.name",
                catalogConfig.getName(),
                equalTo("service_1"));

        assertThat(
                "services.id",
                catalogConfig.getId(),
                equalTo("f7798cc3-b2fd-426f-9d7d-0deaf6b80365"));

        assertThat(
                "services.description",
                catalogConfig.getDescription(),
                equalTo("service_1 description"));

        assertThat(
                "services.bindable",
                catalogConfig.isBindable(),
                is(false)
        );

    }

    @Test
    public void testCatalogOptionalFields() {

        List<String> tags = catalogConfig.getTags();
        assertThat(
                "services.tags size",
                tags.size(),
                is(3));
        assertThat(
                "services.tags content",
                tags,
                hasItems("tag1_s1", "tag2_s1", "tag3_s1"));

        List<String> requires = catalogConfig.getRequires();
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
                catalogConfig.isPlan_updatable(),
                is(true));
    }

    @Test
    public void testServiceMetadata() {

        Map<String, Object> metadata = catalogConfig.getMetadata();
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

        DashboardClientConfig dashboardClient = catalogConfig.getDashboard();

        assertThat("services.dashboard.id",
                dashboardClient.getId(),
                is("dashboard_1"));

        assertThat("services.dashboard.secret",
                dashboardClient.getSecret(),
                is("SKJHI%^%$!!!"));

        assertThat("services.dashboard.redirect_url",
                dashboardClient.getRedirect_uri(),
                is("examplw11.com"));
    }

    @Test
    public void testPlan() {
        List<PlanConfig> planConfigs = catalogConfig.getPlans();
        assertThat(planConfigs, hasSize(1));

        PlanConfig planConfig = planConfigs.get(0);

        assertThat(planConfig.getId(), equalTo("1d2b8576-88df-40d9-a1d3-0fa3ed72228c"));
        assertThat(planConfig.getName(), equalTo("basic plan for map"));
        assertThat(planConfig.getDescription(), equalTo("this is basic plan"));
        assertThat(planConfig.getBindable(), is(true));
        assertThat(planConfig.isFree(), is(true));
    }

    @Test
    public void testPlanMetadata() {
        Map<String, Object> metadata = catalogConfig.getPlans().get(0).getMetadata().get();
        assertThat((List<String>) metadata.get("bullets"), hasItems("feature#1", "feature#2"));
        assertThat((List<PlanCost>) metadata.get("costs"), hasSize(2));

        PlanCost planCost1 = ((List<PlanCost>) metadata.get("costs")).get(0);
        PlanCost planCost2 = ((List<PlanCost>) metadata.get("costs")).get(1);

        assertThat(planCost1.getAmount(), hasEntry("cny", "120"));
        assertThat(planCost1.getUnit(), equalTo("MONTHLY"));

        assertThat(planCost2.getAmount(), hasEntry("cny", "699"));
        assertThat(planCost2.getUnit(), equalTo("YEARLY"));
    }

}
