package com.sagawalabs.springmvc.controller;

import com.sagawalabs.springmvc.domain.Capybara;
import com.sagawalabs.springmvc.services.CapybaraService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.matchers.Matches;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;




/**
 * Created by gustavo on 03/01/2018.
 */
public class CapybaraControllerTest {
    @Mock
    private CapybaraService capybaraService;

    @InjectMocks
    private CapybaraController capybaraController;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(capybaraController).build();
    }

    @Test
    public void testListCapybaras() throws Exception {
        List<Capybara> capybaras = new ArrayList<>();
        capybaras.add(new Capybara());
        capybaras.add(new Capybara());

        Mockito.when(capybaraService.listCapybaras()).thenReturn(capybaras);
        mockMvc.perform(get("/capybaras"))
                .andExpect(status().isOk())
                .andExpect(view().name("capybara/list"))
                .andExpect(model().attribute("capybaras", Matchers.hasSize(2)));
    }

    @Test
    public void testGetCapybaraById() throws Exception {
        Integer id = 1;
        Mockito.when(capybaraService.getCapybaraById(id)).thenReturn(new Capybara());

        mockMvc.perform(get("/capybara/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("capybara/detail"))
                .andExpect(model().attribute("capybara", instanceOf(Capybara.class)));
    }

    @Test
    public void testCreateCapybara() throws Exception {
        verifyZeroInteractions(capybaraService);
        mockMvc.perform(get("/capybara/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("capybara/create"))
                .andExpect(model().attribute("capybara", instanceOf(Capybara.class)));
    }

    @Test
    public void testDeleteCapybara() throws Exception {
        Integer id = 1;
        doNothing().when(capybaraService).deleteCapybara(id);

        mockMvc.perform(get("/capybara/delete/"+id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/capybaras"));
    }

    @Test
    public void testSaveOrUpdateCapybara() throws Exception {
        Capybara capy = new Capybara();
        Integer id = -1;
        String car = "fusca";
        String imageUrl = "google.com?q=fusca";
        String name = "Capy Name";
        capy.setId(id);
        capy.setCar(car);
        capy.setImageUrl(imageUrl);
        capy.setName(name);
        Mockito.when(capybaraService.saveOrUpdateCapybara(org.mockito.Matchers.any())).thenReturn(capy);

        mockMvc.perform(post("/capybara")
        .param("id", id+"")
        .param("car", car)
        .param("imageUrl", imageUrl)
        .param("name", name))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/capybara/-1"))
                .andExpect(model().attribute("capybara", instanceOf(Capybara.class)))
                .andExpect(model().attribute("capybara", hasProperty("id", CoreMatchers.is(-1))))
                .andExpect(model().attribute("capybara", hasProperty("car", CoreMatchers.is("fusca"))))
                .andExpect(model().attribute("capybara", hasProperty("imageUrl", CoreMatchers.is("google.com?q=fusca"))))
                .andExpect(model().attribute("capybara", hasProperty("name", CoreMatchers.is("Capy Name"))));


        ArgumentCaptor<Capybara> capybaraArgumentCaptor = ArgumentCaptor.forClass(Capybara.class);
        verify(capybaraService).saveOrUpdateCapybara(capybaraArgumentCaptor.capture());

        Capybara boundCapybara = capybaraArgumentCaptor.getValue();
        assertEquals(id, boundCapybara.getId());
        assertEquals(name, boundCapybara.getName());
        assertEquals(car, boundCapybara.getCar());
        assertEquals(imageUrl, boundCapybara.getImageUrl());

    }
}
