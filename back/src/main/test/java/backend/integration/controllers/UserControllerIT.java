package backend.integration.controllers;
import backend.integration.IntegrationTestBase;
import backend.security.Role;
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
public class UserControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetByEmail() throws Exception {

        String email = "admin@gmail.com";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/findEmail")
                                .param("email", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalTo(email)));
    }

    @Test
    public void testGetUser() throws Exception {

        String userEmail = "admin@gmail.com";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", equalTo(userEmail)));
    }

    @Test
    public void testGetPermissionList() throws Exception {

        Role role = Role.ADMIN;
        int expectedSize = 19;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/permissions")
                                .param("role", role.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }

}
