package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.AdocaoService;
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
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<SolicitacaoAdocaoDto> solicitacaoAdocaoJson;

    @Autowired
    private JacksonTester<AprovacaoAdocaoDto> aprovacaoAdocaoJson;

    @Autowired
    private JacksonTester<ReprovacaoAdocaoDto> reprovacaoAdocaoJson;

    @MockBean
    private AdocaoService service;

    @Test
    void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/adocoes")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErrosDeNegocio() throws Exception {
        //ARRANGE
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(2l, 2l, "Motivo qualquer");


        BDDMockito.willThrow(ValidacaoException.class).given(service).solicitar(any());

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/adocoes")
                .content(solicitacaoAdocaoJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {
        //ARRANGE
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1l, 1l, "Motivo qualquer");

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/adocoes")
                .content(solicitacaoAdocaoJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaSolicitacaoDaAprovacaoDeAdocao() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        MockHttpServletResponse response = mvc.perform(
            put("/adocoes/aprovar")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaSolicitacaoDaAprovacaoDeAdocaoSemErros() throws Exception {
        //ARRANGE
        AprovacaoAdocaoDto dto = new AprovacaoAdocaoDto(1l);
        String json = """
            {
                    "idAdocao": 1
            }
           """;

        //ACT
        MockHttpServletResponse response = mvc.perform(
            put("/adocoes/aprovar")
                .content(aprovacaoAdocaoJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaSolicitacaoDaReprovacaoDeAdocao() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        MockHttpServletResponse response = mvc.perform(
            put("/adocoes/reprovar")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaSolicitacaoDaReprovacaoDeAdocaoSemErros() throws Exception {
        //ARRANGE
        ReprovacaoAdocaoDto dto = new ReprovacaoAdocaoDto(1l, "Algum motivo");

        //ACT
        MockHttpServletResponse response = mvc.perform(
            put("/adocoes/reprovar")
                .content(reprovacaoAdocaoJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }
}