package com.world.hello.integration;

import com.world.hello.controllers.NameController;
import com.world.hello.models.Name;
import com.world.hello.services.NameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NameController.class)
public class NameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NameService nameService;

    @Test
    @DisplayName("Integration - FindAll")
    public void testFindAll() throws Exception {
        List<Name> names = new ArrayList<>();
        Name name = new Name.Builder().setId(1L).setFirstName("Jhon").build();
        names.add(name);

        given(nameService.findAll()).willReturn(names);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("nameList"))
                .andExpect(model().attribute("nameList", hasSize(1)))
                .andExpect(model().attribute("nameList", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Jhon"))
                            )
                        )
                    ))
                .andDo(MockMvcResultHandlers.print())
                ;
    }

    @Test
    @DisplayName("Integration - Find By Id")
    public void testFindAById() throws Exception {
        Name name = new Name.Builder().setId(1L).setFirstName("Jhon").build();

        given(nameService.findById(1L)).willReturn(name);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("nameList"))
                .andExpect(model().attribute("nameList", hasSize(1)))
                .andExpect(model().attribute("nameList", hasItem(
                                allOf(
                                        hasProperty("id", is(1L)),
                                        hasProperty("firstName", is("Jhon"))
                                )
                        )
                ))
//                .andDo(MockMvcResultHandlers.print())
        ;
    }


}
