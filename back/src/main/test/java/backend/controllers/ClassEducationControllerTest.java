package backend.controllers;
import backend.persist.entity.ClassEducation;
import backend.persist.entity.PersonEntity;
import backend.services.ClassEducationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith({MockitoExtension.class})
class ClassEducationControllerTest {

    List<PersonEntity> personModels;


    MockMvc mockMvc;

    List<ClassEducation> classEducations;

    @BeforeEach
    void beforeEach() {
        personModels = Arrays.asList(new PersonEntity(), new PersonEntity(), new PersonEntity());
        classEducations = Arrays.asList(new ClassEducation(), new ClassEducation(), new ClassEducation());

    }

    @Mock
    ClassEducationService classEducationService;

    @InjectMocks
    ClassEducationController classEducationController;

//    @Test
//    void getAllWhoTakeThisTypeEducation_ValidTypeEducationId_ReturnsListOfPersons() throws Exception {
//        // Arrange
//        int typeEducationId = 1;
//        when(classEducationService.getAllWhoTakeThisTypeEducation(typeEducationId)).thenReturn(personModels);
//
//        // Act & Assert
//        mockMvc.perform(get("/api/education/{typeEducationId}/allpersons", typeEducationId))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(personModels.size()));
//
//    }

}