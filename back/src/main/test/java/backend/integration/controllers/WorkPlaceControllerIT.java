package backend.integration.controllers;
import backend.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
public class WorkPlaceControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowAll() throws Exception {
        int expectedSize = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/workplace/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetById() throws Exception {
        int workPlaceId = 1;
        String workPlaceName = "завод Лихачева";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/workplace/{workPlaceId}", workPlaceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(workPlaceId)))
                .andExpect(jsonPath("$.nameWokPlace", equalTo(workPlaceName)));
    }
}
