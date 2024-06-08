package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TutorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CadastroTutorDto> cadastroTutorJson;

    @Autowired
    private JacksonTester<AtualizacaoTutorDto> atualizacaoTutorJson;

    @MockBean
    private TutorService service;

    @Test
    void deveriaDevolverCodigo400ParaCadastroDeTutorComErros() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/tutores")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaCadastroDeTutorComErrosDeNegocio() throws Exception {
        //ARRANGE
        CadastroTutorDto dto = new CadastroTutorDto("Ja existe", "(11)91234-5678", "teste@teste.com");
        BDDMockito.willThrow(ValidacaoException.class).given(service).cadastrar(any());

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/tutores")
                .content(cadastroTutorJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaCadastroDeTutorSemErros() throws Exception {
        //ARRANGE
        CadastroTutorDto dto = new CadastroTutorDto("Tutor xpto", "(11)91234-5678", "teste@teste.com");

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/tutores")
                .content(cadastroTutorJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaAtualizarTutorComErros() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        MockHttpServletResponse response = mvc.perform(
            put("/tutores")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaAtualizarTutorComErrosDeNegocio() throws Exception {
        //ARRANGE
        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(999l, "Tutor erro", "(21)95555-4444", "teste@teste.com");
        BDDMockito.willThrow(ValidacaoException.class).given(service).atualizar(any());

        //ACT
        MockHttpServletResponse response = mvc.perform(
            put("/tutores")
                .content(atualizacaoTutorJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaParaAtualizarTutorSemErros() throws Exception {
        //ARRANGE
        AtualizacaoTutorDto dto = new AtualizacaoTutorDto(1l, "Tutor xpto", "(21)95555-4444", "teste@teste.com");

        //ACT
        MockHttpServletResponse response = mvc.perform(
            put("/tutores")
                .content(atualizacaoTutorJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }
}