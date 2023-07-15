package backend.integration.controllers;
import backend.integration.IntegrationTestBase;
import backend.persist.entity.TradeUnion;
import backend.persist.models.TradeUnionModel;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
public class TradeUnionControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetById() throws Exception {

        int unionId = 1;
        var nameUnion = "Центральный профсоюз";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/{unionId}", unionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(unionId)))
                .andExpect(jsonPath("$.nameUnion", equalTo(nameUnion)));
    }

    @Test
    public void findByIdShouldReturnAnNotFoundException() throws Exception {

        int unionId = 100; // Assuming 100 is an incorrect ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/{unionId}", unionId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAllDocPayment() throws Exception {

        int unionId = 2;
        int expectedSize = 3;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/{unionId}/docpayments", unionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetAllMembersWithPageNumber() throws Exception {

        int unionId = 1;
        int pageNumber = 0;
        int expectedSize = 5;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/{unionId}/members{pageNumber}", unionId, pageNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetAllMembersWithoutPageNumber() throws Exception {

        int unionId = 1;
        int expectedSize = 5;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union/{unionId}/members", unionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetAllTradeUnion() throws Exception {

        int expectedSize = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {

        int unionId = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/union/{unionId}", unionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(0)));

        int expectedSize = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    @Transactional
    public void testAdd() throws Exception {

        TradeUnionModel tradeUnionModel = TradeUnionModel.builder()
                .nameUnion("Test Union")
                .city("Minsk")
                .address("Test adress, test street")
                .build();
        // Set properties of the tradeUnionModel
        mockMvc.perform(MockMvcRequestBuilders.post("/api/union")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tradeUnionModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameUnion", equalTo("Test Union")));
        // check size after delete
        int expectedSize = 3;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/union"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

//    @Test
//    public void testUpdate() throws Exception {
//
//        int unionId = 1;
//        TradeUnion tradeUnion = new TradeUnion();
//        // Set properties of the tradeUnion
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/union/{id}", unionId)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(tradeUnion)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", equalTo(unionId)));
//    }

}
