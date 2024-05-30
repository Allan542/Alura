package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.*;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService adocaoService;

    @Mock
    private AdocaoRepository repository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    // Se deixar o Spy sem instanciar a lista, o Mockito implementará uma lista, já que List é uma interface. A lista ainda é controlada pelo Mockito usando Spy, mas agora é possível manipular o objeto e deixa de ser um mock
    @Spy // Mock é um objeto que não faz nada por si só e sim faz coisas controladas pelo Mockito. Diferente do Spy, que faz coisas independentes do Mockito, fazendo as chamadas reais do objeto, a não ser que seja especificado uma substituição para determinado método.
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();

    @Mock
    private ValidacaoSolicitacaoAdocao validador1;
    @Mock
    private ValidacaoSolicitacaoAdocao validador2;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    @Spy
    private Adocao adocao;

    private SolicitacaoAdocaoDto solicitacaoAdocaoDto;

    private AprovacaoAdocaoDto aprovacaoAdocaoDto;

    private ReprovacaoAdocaoDto reprovacaoAdocaoDto;

    @Captor // Para capturar o objeto argumento que é passado como mock, usa-se um ArgumentCaptor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    void deveriaSalvarAdocaoAoSolicitar(){
        //ARRANGE
        this.solicitacaoAdocaoDto = new SolicitacaoAdocaoDto(10l, 20l, "Motivo qualquer");
        given(petRepository.getReferenceById(solicitacaoAdocaoDto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(solicitacaoAdocaoDto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        //ACT
        adocaoService.solicitar(solicitacaoAdocaoDto);

        //ASSERT
        then(repository).should().save(adocaoCaptor.capture());
        Adocao adocaoSalva = adocaoCaptor.getValue();
        assertEquals(pet, adocaoSalva.getPet());
        assertEquals(tutor, adocaoSalva.getTutor());
        assertEquals(solicitacaoAdocaoDto.motivo(), adocaoSalva.getMotivo());
    }

    @Test
    void deveriaChamarValidadoresDeAdocaoAoSolicitar(){
        //ARRANGE
        this.solicitacaoAdocaoDto = new SolicitacaoAdocaoDto(10l, 20l, "Motivo qualquer");
        given(petRepository.getReferenceById(solicitacaoAdocaoDto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(solicitacaoAdocaoDto.idTutor())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        validacoes.add(validador1);
        validacoes.add(validador2);

        //ACT
        adocaoService.solicitar(solicitacaoAdocaoDto);

        //ASSERT
        BDDMockito.then(validador1).should().validar(solicitacaoAdocaoDto);
        BDDMockito.then(validador2).should().validar(solicitacaoAdocaoDto);
    }

    @Test
    void deveriaAprovarSolicitacaoDeAdocao(){
        //ARRANGE
        this.aprovacaoAdocaoDto = new AprovacaoAdocaoDto(1l);
        given(repository.getReferenceById(aprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        given(adocao.getTutor()).willReturn(tutor);
        given(adocao.getData()).willReturn(LocalDateTime.now());
        given(adocao.getPet()).willReturn(pet);
        given(pet.getAbrigo()).willReturn(abrigo);

        //ACT
        adocaoService.aprovar(aprovacaoAdocaoDto);

        //ASSERT
        Assertions.assertEquals(StatusAdocao.APROVADO, adocao.getStatus());
        then(emailService).should().enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Adoção aprovada", "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");
    }

    @Test
    void deveriaReprovarSolicitacaoDeAdocao(){
        //ARRANGE
        this.reprovacaoAdocaoDto = new ReprovacaoAdocaoDto(1l, "Reprovação justa");
        given(repository.getReferenceById(reprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        given(adocao.getTutor()).willReturn(tutor);
        given(adocao.getData()).willReturn(LocalDateTime.now());
        given(adocao.getPet()).willReturn(pet);
        given(pet.getAbrigo()).willReturn(abrigo);

        //ACT
        adocaoService.reprovar(reprovacaoAdocaoDto);

        //ASSERT
        Assertions.assertEquals(StatusAdocao.REPROVADO, adocao.getStatus());
        Assertions.assertEquals(reprovacaoAdocaoDto.justificativa(), adocao.getJustificativaStatus());
        then(emailService).should().enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Solicitação de adoção", "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus());
    }

}