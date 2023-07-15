package backend.integration.controllers;
import backend.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
public class ClassEducationControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllWhoTakeThisTypeEducation() throws Exception {

        int typeEducationId = 2;
        int expectedSize = 6;
        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/education/{typeEducationId}/allpersons", typeEducationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));

    }

    @Test
    public void countOfClassEducation() throws Exception {

        int expectedSize = 10;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/education")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(expectedSize)));

    }

    @Test
    public void findByIdShouldReturnBasic() throws Exception {

        var classEducationId = 1;
        String educationName = "Базовое";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/education/{id}",classEducationId ))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo(educationName)));

    }

    @Test
    public void findByIdShouldReturnAnNotFoundException() throws Exception {
        int classEducationId = 100; // Assuming 100 is an incorrect ID

        mockMvc.perform(MockMvcRequestBuilders.get("/api/education/{id}", classEducationId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
