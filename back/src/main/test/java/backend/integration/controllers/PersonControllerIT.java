package backend.integration.controllers;
import backend.integration.IntegrationTestBase;
import backend.persist.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
@AutoConfigureMockMvc
public class PersonControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testShowPersonsList() throws Exception {

        int expectedSize = 10;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetById() throws Exception {

        int userId = 1;
        var firstName = "Александр";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(userId)))
                .andExpect(jsonPath("$.firstName", equalTo(firstName)));
    }

    @Test
    @Transactional
    public void testAddPerson() throws Exception {

        var firstName = "Test first name";
        var lastName = "Test last name";
        var education = "Высшее";
        var expectedSize = 11;
        PersonEntity mockPerson = PersonEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .education(education)
                .birth(Date.valueOf("1991-11-10"))
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(mockPerson)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", equalTo(mockPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(mockPerson.getLastName())));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    @Transactional
    public void testDeletePerson() throws Exception {

        int id = 2;
        var expectedSize = 9;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/persons/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", equalTo(null)))
                .andExpect(jsonPath("$.lastName", equalTo(null)));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testShowPersonsListWhereNameStartWith() throws Exception {

        String startWith = "А";
        int expectedSize = 4;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/pattern={startWith}", startWith))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetDocTrade() throws Exception {

        int person_id = 3;
        int expectedSize = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{userId}/doc_member", person_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].membershipCard", is(123)))
                .andExpect(jsonPath("$[1].membershipCard", is(23)))
                .andExpect(jsonPath("$[0].person_id", is(person_id)))
                .andExpect(jsonPath("$[1].person_id", is(person_id)))
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetDocPayments() throws Exception {

        int userId = 1;
        int expectedSize = 3;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{userId}/doc_payment", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetEducation() throws Exception {

        int userId = 1;
        String expectedEducation = "Высшее";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{userId}/class_education", userId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedEducation));
    }

    @Test
    public void testGetWorkPlace() throws Exception {

        int personId = 1;
        int expectedSize = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/{personId}/workplace", personId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

}
