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
public class DocMemberControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindDocMembersByLeaveDateIsNull() throws Exception {
        int expectedSize = 8;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/docmember/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testFindByNum() throws Exception {
        int num = 123;
        int personId = 3;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/docmember/{num}", num))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.membershipCard", equalTo(num)))
                .andExpect(jsonPath("$.person_id", equalTo(personId)));
    }

    @Test
    public void findByIdShouldReturnAnNotFoundException() throws Exception {
        int classEducationId = 100; // Assuming 100 is an incorrect ID

        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/docmember/{num}", classEducationId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
