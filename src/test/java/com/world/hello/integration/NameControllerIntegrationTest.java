package com.world.hello.integration;

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
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        NameView nameView = new NameView.Builder().setId(1L).setFirstName("Jhon").build();
        names.add(nameView);

        given(nameService.findAll()).willReturn(names);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
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
    @DisplayName("Integration - New")
    public void testNewNameForm() throws Exception {

        mockMvc.perform(get("/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("nameForm"))
                .andExpect(model().attribute("nameForm", hasProperty("id", is(0L))));
    }

    @Test
    @DisplayName("Integration - Find By Id")
    public void testFindById() throws Exception {
        Long id = 1L;
        NameView nameView = new NameView.Builder().setId(id).setFirstName("Jhon").build();

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

        Name name = new Name.Builder()
                .setId(1L)
                .setFirstName("John")
                .build();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", name.getId().toString());
        formData.add("firstName", name.getFirstName());

        mockMvc.perform(post("/edit/submit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(formData))
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
