package com.sagawalabs.springmvc.controller;

import com.sagawalabs.springmvc.domain.Capybara;
import com.sagawalabs.springmvc.services.CapybaraService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by gustavo on 03/01/2018.
 */
public class IndexControllerTest {

    private MockMvc mockMvc;

    private IndexController indexController;

    @Before
    public void setup(){
        indexController = new IndexController();
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

    }

    @Test
    public void testIndex() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
