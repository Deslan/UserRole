package za.flatrock.assessment.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.flatrock.assessment.demo.models.RoleENUM;
import za.flatrock.assessment.demo.models.dto.CreateUserRequest;
import za.flatrock.assessment.demo.models.dto.CreateUserResponse;
import za.flatrock.assessment.demo.models.entities.Role;
import za.flatrock.assessment.demo.services.CreateUserService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
public class UserControllerTest {

    public static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService createUserService;

    @Test
    void createUserTest() throws Exception {

        // Given
        CreateUserResponse response = new CreateUserResponse(1L, "TestName", "TestSurname", "+27731832705", new ArrayList<>());
        when(createUserService.create(any())).thenReturn(response);
        CreateUserRequest request = new CreateUserRequest(
                "TestName",
                "TestSurname",
                "+27731832705",
                RoleENUM.ADMIN
        );

        // Then When
        this.mockMvc.perform(
                patch("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(
                print()
        ).andExpect(result -> {
            CreateUserResponse apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    CreateUserResponse.class
            );
            assertEquals(1L, apiResponse.getId());
            assertEquals("TestName", apiResponse.getName());
            assertEquals("TestSurname", apiResponse.getSurname());
        });
    }

}
