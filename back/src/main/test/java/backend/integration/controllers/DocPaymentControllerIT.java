package backend.integration.controllers;

import backend.integration.IntegrationTestBase;
import backend.services.DocPaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
public class DocPaymentControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {

        int expectedSize = 16;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/docpayments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGetById() throws Exception {

        int payId = 6;
        int personId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/docpayments/{payId}", payId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.person_id", equalTo(personId)));
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {

        int payId = 27;
        var expectedSize = 15;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/docpayments/{payId}", payId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(0)))
                .andExpect(jsonPath("$.person_id", equalTo(0)));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/docpayments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

    @Test
    public void testGeQuantityPay() throws Exception {

        var expectedSize = 2;
        var count_first = 3;
        var count_second = 13;
        var expect_first_name = "Городской профсоюз";
        var expect_second_name = "Центральный профсоюз";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/docpayments/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)))
                .andExpect(jsonPath("$[0].name", is(expect_first_name)))
                .andExpect(jsonPath("$[1].name", is(expect_second_name)))
                .andExpect(jsonPath("$[0].count", is(count_first)))
                .andExpect(jsonPath("$[1].count", is(count_second)));
    }

    @Test
    public void testGetCountNotPaied() throws Exception {

        int expectedSize = 2;
        String pay = "Paied";
        String not_pay = "Not Paied";
        int count_pay = 11;
        int count_not_pay = 5;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/docpayments/count_statistic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)))
                .andExpect(jsonPath("$[0].name", is(pay)))
                .andExpect(jsonPath("$[1].name", is(not_pay)))
                .andExpect(jsonPath("$[0].count", is(count_pay)))
                .andExpect(jsonPath("$[1].count", is(count_not_pay)));

    }
}
