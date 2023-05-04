package com.world.hello.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.world.hello.controllers.NameController;
import com.world.hello.models.Name;
import com.world.hello.models.NameView;
import com.world.hello.services.NameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        List<NameView> names = new ArrayList<>();
        NameView nameView = NameView.builder().id(1).firstName("Jhon").build();
        names.add(nameView);

        given(nameService.findAll()).willReturn(names);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("nameList"))
                .andExpect(model().attribute("nameList", hasSize(1)))
                .andExpect(model().attribute("nameList", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("firstName", is("Jhon"))
                            )
                        )
                    ))
                .andDo(MockMvcResultHandlers.print())
                ;
    }

    @Test
    @DisplayName("Integration - FindAllNothing")
    public void testFindAllNothing() throws Exception {
        given(nameService.findAll()).willReturn(null);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeDoesNotExist("nameList"))
//                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    @DisplayName("Integration - New")
    public void testNewNameForm() throws Exception {

        mockMvc.perform(get("/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("nameForm"))
                .andExpect(model().attribute("nameForm", hasProperty("id", is(0))));
    }

    @Test
    @DisplayName("Integration - Find By Id")
    public void testFindById() throws Exception {
        Integer id = 1;
        NameView nameView = NameView.builder().id(id).firstName("Jhon").build();

        given(nameService.findById(1L)).willReturn(nameView);

        mockMvc.perform(get("/edit/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attribute("nameForm", nameView))
//                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    @DisplayName("Integration - Not Find By Id")
    public void testNotFindById() throws Exception {

        given(nameService.findById(1L)).willReturn(null);

        mockMvc.perform(get("/edit/{id}", 0L))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/new"))
//                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    @DisplayName("Integration - New Name Submit")
    void testNewNameSubmit() throws Exception {

        Name name = new Name.Builder()
                .setId(1L)
                .setFirstName("John")
                .build();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", name.getId().toString());
        formData.add("firstName", name.getFirstName());

        mockMvc.perform(post("/new/submit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                        .andExpect(status().isFound())
                        .andExpect(view().name("redirect:/"))
        //                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    @DisplayName("Integration - Edit Name Submit")
    void testEditNameSubmit() throws Exception {

        NameView name = NameView.builder()
                .id(1)
                .firstName("John")
                .build();

        mockMvc.perform(post("/edit/submit")
                        .content(new ObjectMapper().writeValueAsString(name))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"))
        //                .andDo(MockMvcResultHandlers.print())
        ;
    }

    @Test
    @DisplayName("Integration - Delete Name Submit")
    void testDeleteNameSubmit() throws Exception {

        Name name = new Name.Builder()
                .setId(1L)
                .setFirstName("John")
                .build();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", name.getId().toString());
        formData.add("firstName", name.getFirstName());

        mockMvc.perform(post("/edit/delete")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"))
        //                .andDo(MockMvcResultHandlers.print())
        ;
    }




}
