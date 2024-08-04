package com.teste.livraria.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.teste.livraria.dto.LivroDTO;
import com.teste.livraria.service.LivroService;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLivros() throws Exception {
        LivroDTO livro1 = new LivroDTO();
        livro1.setCodL(1);
        livro1.setTitulo("Livro 1");

        LivroDTO livro2 = new LivroDTO();
        livro2.setCodL(2);
        livro2.setTitulo("Livro 2");

        List<LivroDTO> livros = Arrays.asList(livro1, livro2);

        given(livroService.findAll()).willReturn(livros);

        mockMvc.perform(MockMvcRequestBuilders.get("/livros/findAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].codL").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Livro 1"))
                .andExpect(jsonPath("$[1].codL").value(2))
                .andExpect(jsonPath("$[1].titulo").value("Livro 2"));
    }
    
    @Test
    void testGetLivroById() throws Exception {
        // Cria um exemplo de DTO que será retornado pelo serviço
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setCodL(1);
        livroDTO.setTitulo("Exemplo de Livro");

        // Configura o comportamento do mock do serviço
        given(livroService.findById(1)).willReturn(livroDTO);

        // Faz a requisição e verifica a resposta
        mockMvc.perform(get("/livros/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codL").value(1))
                .andExpect(jsonPath("$.titulo").value("Exemplo de Livro"));
    }
    
    @Test
    void testCreateLivro() throws Exception {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setCodL(1);
        livroDTO.setTitulo("Novo Livro");

        given(livroService.create(livroDTO)).willReturn(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livroDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codL").value(1))
                .andExpect(jsonPath("$.titulo").value("Novo Livro"));
    }

    @Test
    void testUpdateLivro() throws Exception {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setCodL(1);
        livroDTO.setTitulo("Livro Atualizado");

        given(livroService.update(1, livroDTO)).willReturn(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/livros/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livroDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codL").value(1))
                .andExpect(jsonPath("$.titulo").value("Livro Atualizado"));
    }

    @Test
    void testDeleteLivro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/livros/1"))
                .andExpect(status().isNoContent());
    }
    
}
