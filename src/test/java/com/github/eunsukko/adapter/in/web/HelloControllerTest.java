package com.github.eunsukko.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.core.Is.is;

// SpringExtension integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
// To use this extension, simply annotate a JUnit Jupiter based test class with @ExtendWith(SpringExtension.class), @SpringJUnitConfig, or @SpringJUnitWebConfig.
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        mvc.perform(
                        get("/hello")
                                .param("name", "hi")
                                .param("amount", String.valueOf(10))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("hi")))
                .andExpect(jsonPath("$.amount", is(10)));
    }
}