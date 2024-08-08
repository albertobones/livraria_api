package com.teste.livraria.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.livraria.dto.AutorDTO;
import com.teste.livraria.service.AutorService;

@WebMvcTest(AutorController.class)
public class AutorControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorService autorService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
    @Test
    void testGetAllAutores() throws Exception {
        AutorDTO autor1 = new AutorDTO();
        autor1.setCodAu(1);
        autor1.setNome("Autor 1");

        AutorDTO autor2 = new AutorDTO();
        autor2.setCodAu(2);
        autor2.setNome("Autor 2");

        List<AutorDTO> autores = Arrays.asList(autor1, autor2);

        given(autorService.findAll()).willReturn(autores);

        mockMvc.perform(MockMvcRequestBuilders.get("/autores/findAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].codAu").value(1))
                .andExpect(jsonPath("$[0].nome").value("Autor 1"))
                .andExpect(jsonPath("$[1].codAu").value(2))
                .andExpect(jsonPath("$[1].nome").value("Autor 2"));
    }
    
    @Test
    void testGetAutorById() throws Exception {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setCodAu(1);
        autorDTO.setNome("Autor 1");

        given(autorService.findById(1)).willReturn(autorDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/autores/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codAu").value(1))
                .andExpect(jsonPath("$.nome").value("Autor 1"));
    }
    
    @Test
    void testCreateAutor() throws Exception {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setCodAu(1);
        autorDTO.setNome("Novo Autor");

        given(autorService.create(autorDTO)).willReturn(autorDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/autores/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(autorDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codAu").value(1))
                .andExpect(jsonPath("$.nome").value("Novo Autor"));
    }

    @Test
    void testUpdateAutor() throws Exception {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setCodAu(1);
        autorDTO.setNome("Autor Atualizado");

        given(autorService.update(1, autorDTO)).willReturn(autorDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/autores/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(autorDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codAu").value(1))
                .andExpect(jsonPath("$.nome").value("Autor Atualizado"));
    }

    @Test
    void testDeleteAutor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/autores/1"))
                .andExpect(status().isNoContent());
    }
}
