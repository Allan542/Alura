package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.TipoPet;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PetControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<List<PetDto>> petResponse;

    @MockBean
    private PetService service;

    @Test
    void deveriaDevolverCodigo200ParaListarPetsDisponiveis() throws Exception {
        //ARRANGE
        List<PetDto> pets = new ArrayList<>();
        pets.add(new PetDto(1l, TipoPet.GATO, "Bichano", "Siames", 10));
        BDDMockito.given(service.buscarPetsDisponiveis()).willReturn(pets);

        //ACT
        MockHttpServletResponse response = mvc.perform(
            MockMvcRequestBuilders.get("/pets")
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(petResponse.write(pets).getJson(), response.getContentAsString());
    }

}