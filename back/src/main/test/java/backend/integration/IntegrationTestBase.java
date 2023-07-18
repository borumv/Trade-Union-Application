package backend.integration;

import backend.integration.annotation.IntegrationTest;
import backend.security.Permissions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@IntegrationTest
@WithMockUser(username = "admin@gmail.com", password = "123", authorities = {"persons:read", "persons:write", "persons:delete", "tradeunion:read", "tradeunion:edit"})
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.1");

    @BeforeAll
    static void runContainer() {

        container.start();
    }

    @DynamicPropertySource
    static void dockerProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

}

