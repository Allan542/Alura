package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AbrigoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CadastroAbrigoDto> cadastroAbrigoJson;

    @Autowired
    private JacksonTester<CadastroPetDto> cadastroPetJson;

    @Autowired
    private JacksonTester<List<AbrigoDto>> abrigoResponse;

    @Autowired
    private JacksonTester<List<PetDto>> petResponse;

    @MockBean
    private AbrigoService abrigoService;

    @MockBean
    private PetService petService;

    @MockBean
    private Abrigo abrigo;

    @Test
    void deveriaDevolverCodigo200ParaListarAbrigos() throws Exception {
        //ARRANGE
        List<AbrigoDto> abrigos = new ArrayList<>();
        abrigos.add(new AbrigoDto(1l, "Abrigo xpto"));
        BDDMockito.given(abrigoService.listar()).willReturn(abrigos);

        //ACT
        MockHttpServletResponse response = mvc.perform(
            MockMvcRequestBuilders.get("/abrigos")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(abrigoResponse.write(abrigos).getJson(), response.getContentAsString());
    }

    @Test
    void deveriaDevolverCodigo400ParaCadastroDeAbrigoComErros() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/abrigos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaCadastroDeAbrigoComErrosDeNegocio() throws Exception {
        //ARRANGE
        CadastroAbrigoDto dto = new CadastroAbrigoDto("Xpto", "(11)1234-5678", "teste@teste.com");


        BDDMockito.willThrow(ValidacaoException.class).given(abrigoService).cadastrar(any());

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/abrigos")
                .content(cadastroAbrigoJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaParaCadastroDeAbrigoSemErros() throws Exception {
        //ARRANGE
        CadastroAbrigoDto dto = new CadastroAbrigoDto("Xpto", "(11)1234-5678", "teste@teste.com");
        String json = """
            {
                    "nome": "Xpto",
                    "telefone": "(11)1234-5678",
                    "email": "teste@teste.com"
            }
           """;

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/abrigos")
                .content(cadastroAbrigoJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo404ParaListarPets() throws Exception {
        //ARRANGE
        BDDMockito.given(abrigoService.listarPetsDoAbrigo(String.valueOf(999l))).willThrow(ValidacaoException.class);

        //ACT
        MockHttpServletResponse response = mvc.perform(
            MockMvcRequestBuilders.get("/abrigos/999/pets")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(404, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaListarPetsPorId() throws Exception {
        //ARRANGE
        List<PetDto> pets = new ArrayList<>();
        pets.add(new PetDto(1l, TipoPet.CACHORRO, "Luluzinho", "Pit bull", 4));
        BDDMockito.given(abrigoService.listarPetsDoAbrigo(String.valueOf(1l))).willReturn(pets);

        //ACT
        MockHttpServletResponse response = mvc.perform(
            MockMvcRequestBuilders.get("/abrigos/1/pets")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(petResponse.write(pets).getJson(), response.getContentAsString());
    }

    @Test
    void deveriaDevolverCodigo200ParaListarPetsPorNome() throws Exception {
        //ARRANGE
        List<PetDto> pets = new ArrayList<>();
        pets.add(new PetDto(1l, TipoPet.CACHORRO, "Luluzinho", "Pit bull", 4));
        BDDMockito.given(abrigoService.listarPetsDoAbrigo("Luluzinho")).willReturn(pets);

        //ACT
        MockHttpServletResponse response = mvc.perform(
            MockMvcRequestBuilders.get("/abrigos/Luluzinho/pets")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(petResponse.write(pets).getJson(), response.getContentAsString());
    }

    @Test
    void deveriaDevolverCodigo400ParaCadastroDePetComErros() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/abrigos/1/pets")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo404ParaCadastroDePetQuandoAbrigoNaoEncontrado() throws Exception {
        //ARRANGE
        CadastroPetDto dto = new CadastroPetDto(TipoPet.CACHORRO, "Xpto", "Pit Bull", 4, "Preto", 4.4f);

        BDDMockito.willThrow(ValidacaoException.class).given(abrigoService).carregarAbrigo(any());

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/abrigos/999/pets")
                .content(cadastroPetJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(404, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaParaCadastroDePetSemErros() throws Exception {
        //ARRANGE
        CadastroPetDto dto = new CadastroPetDto(TipoPet.CACHORRO, "Xpto", "Pit Bull", 4, "Preto", 4.4f);
        BDDMockito.given(abrigoService.carregarAbrigo("1l")).willReturn(abrigo);

        //ACT
        MockHttpServletResponse response = mvc.perform(
            post("/abrigos/1/pets")
                .content(cadastroPetJson.write(dto).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }
}