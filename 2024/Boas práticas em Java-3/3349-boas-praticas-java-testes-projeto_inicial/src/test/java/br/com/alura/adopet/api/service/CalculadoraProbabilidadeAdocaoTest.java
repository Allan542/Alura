package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    void deveriaRetornarProbabilidadeAltaParaPetsIdadeBaixaEComPesoBaixo(){
        // idade 4 anos e 4kg - ALTA
        // Triple A

        // ARRANGE
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
            "Abrigo feliz",
            "94999999999",
            "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
            TipoPet.GATO,
            "Miau",
            "Siames",
            4,
            "Cinza",
            4.0f
        ), abrigo);

        // ACT
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        // ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    void deveriaRetornarProbabilidadeMediaParaPetsIdadeAltaEComPesoBaixo(){
        // idade 15 anos e 4kg - MEDIA

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
            "Abrigo feliz",
            "94999999999",
            "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
            TipoPet.GATO,
            "Miau",
            "Siames",
            15,
            "Cinza",
            4.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

    @Test
    void deveriaRetornarProbabilidadeBaixaParaPetsIdadeAltaEComPesoAlto(){
        // idade 15 anos e 4kg - MEDIA
        //ARRANGE
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
            "Abrigo feliz",
            "94999999999",
            "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
            TipoPet.CACHORRO,
            "Miau",
            "Siames",
            15,
            "Cinza",
            16.0f
        ), abrigo);

        //ACT
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }

    @Test
    void deveriaRetornarProbabilidadeBaixaParaPetsIdadeMediaEComPesoAlto(){
        // idade 15 anos e 4kg - MEDIA
        //ARRANGE
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
            "Abrigo feliz",
            "94999999999",
            "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
            TipoPet.GATO,
            "Miau",
            "Siames",
            10,
            "Cinza",
            11.0f
        ), abrigo);

        //ACT
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }
}