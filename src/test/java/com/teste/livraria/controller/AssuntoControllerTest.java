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
import com.teste.livraria.dto.AssuntoDTO;
import com.teste.livraria.service.AssuntoService;

@WebMvcTest(AssuntoController.class)
public class AssuntoControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssuntoService assuntoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAssuntos() throws Exception {
        AssuntoDTO assunto1 = new AssuntoDTO();
        assunto1.setCodAs(1);
        assunto1.setDescricao("Assunto 1");

        AssuntoDTO assunto2 = new AssuntoDTO();
        assunto2.setCodAs(2);
        assunto2.setDescricao("Assunto 2");

        List<AssuntoDTO> assuntos = Arrays.asList(assunto1, assunto2);

        given(assuntoService.findAll()).willReturn(assuntos);

        mockMvc.perform(MockMvcRequestBuilders.get("/assuntos/findAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].codAs").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Assunto 1"))
                .andExpect(jsonPath("$[1].codAs").value(2))
                .andExpect(jsonPath("$[1].descricao").value("Assunto 2"));
    }
    
    @Test
    void testGetAssuntoById() throws Exception {
        AssuntoDTO assuntoDTO = new AssuntoDTO();
        assuntoDTO.setCodAs(1);
        assuntoDTO.setDescricao("Assunto 1");

        given(assuntoService.findById(1)).willReturn(assuntoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/assuntos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codAs").value(1))
                .andExpect(jsonPath("$.descricao").value("Assunto 1"));
    }

    @Test
    void testCreateAssunto() throws Exception {
        AssuntoDTO assuntoDTO = new AssuntoDTO();
        assuntoDTO.setCodAs(1);
        assuntoDTO.setDescricao("Novo Assunto");

        given(assuntoService.create(assuntoDTO)).willReturn(assuntoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/assuntos/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(assuntoDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codAs").value(1))
                .andExpect(jsonPath("$.descricao").value("Novo Assunto"));
    }

    @Test
    void testUpdateAssunto() throws Exception {
        AssuntoDTO assuntoDTO = new AssuntoDTO();
        assuntoDTO.setCodAs(1);
        assuntoDTO.setDescricao("Assunto Atualizado");

        given(assuntoService.update(1, assuntoDTO)).willReturn(assuntoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/assuntos/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(assuntoDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codAs").value(1))
                .andExpect(jsonPath("$.descricao").value("Assunto Atualizado"));
    }

    @Test
    void testDeleteAssunto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/assuntos/1"))
                .andExpect(status().isNoContent());
    }
}
