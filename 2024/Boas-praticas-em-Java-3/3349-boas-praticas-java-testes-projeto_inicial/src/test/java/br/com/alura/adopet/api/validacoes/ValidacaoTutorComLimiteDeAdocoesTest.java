package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {

    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validacao;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private Tutor tutor;

    @Mock
    private Pet pet;

    @Spy
    private List<Adocao> adocoes = new ArrayList<>();

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoParaTutorQueNaoEstaNoLimiteDeAdocoes() {
        // ARRANGE
        Adocao adocao = new Adocao(tutor, pet, "Motivo qualquer");
        adocoes.add(adocao);
        BDDMockito.given(adocaoRepository.findAll()).willReturn(adocoes);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        // ASSERT + ACT
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoParaTutorQueNaoEstaComMaisDeCincoAdocoesAprodavas() {
        // ARRANGE
        for (int i = 0; i < 7; i++) {
            Adocao adocao = new Adocao(tutor, pet, "Motivo qualquer");
            adocoes.add(adocao);
        }
        BDDMockito.given(adocaoRepository.findAll()).willReturn(adocoes);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        // ASSERT + ACT
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoParaTutorQueEstaNoLimiteDeAdocoesAprovadasDeCinco() {
        // ARRANGE
        for (int i = 0; i < 7; i++) {
            Adocao adocao = new Adocao(tutor, pet, "Motivo qualquer");
            adocao.marcarComoAprovada();
            adocoes.add(adocao);
        }
        BDDMockito.given(adocaoRepository.findAll()).willReturn(adocoes);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        // ASSERT + ACT
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }
}