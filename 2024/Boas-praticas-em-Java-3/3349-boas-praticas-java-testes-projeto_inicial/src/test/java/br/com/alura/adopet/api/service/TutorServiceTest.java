package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService tutorService;

    @Mock
    private TutorRepository repository;

    @Spy
    private Tutor tutor;

    private CadastroTutorDto cadastroDto;

    private AtualizacaoTutorDto atualizarDto;

    @Captor
    private ArgumentCaptor<Tutor> tutorCaptor;

    @Test
    void deveriaJogarExceptionSeExisteUmTutorPeloTelefoneOuEmail(){
        //ARRANGE
        this.cadastroDto = new CadastroTutorDto("Ja existe", "(11)91234-5678", "teste@teste.com");
        BDDMockito.given(repository.existsByTelefoneOrEmail(cadastroDto.telefone(), cadastroDto.email())).willReturn(true);

        // ASSERT + ACT
        Assertions.assertThrows(ValidacaoException.class, () -> tutorService.cadastrar(cadastroDto));
    }

    @Test
    void deveriaCadastrarUmTutorSeNaoExistePeloTelefoneOuEmail(){
        //ARRANGE
        this.cadastroDto = new CadastroTutorDto("Novo tutor", "(11)91234-5678", "teste@teste.com");
        BDDMockito.given(repository.existsByTelefoneOrEmail(cadastroDto.telefone(), cadastroDto.email())).willReturn(false);

        // ACT
        tutorService.cadastrar(cadastroDto);

        //ASSERT
        BDDMockito.then(repository).should().save(tutorCaptor.capture());
        Tutor tutor = tutorCaptor.getValue();
        Assertions.assertEquals(cadastroDto.nome(), tutor.getNome());
        Assertions.assertEquals(cadastroDto.email(), tutor.getEmail());
        Assertions.assertEquals(cadastroDto.telefone(), tutor.getTelefone());
    }

    @Test
    void deveriaAtualizarUmTutorJaExistente(){
        //ARRANGE
        this.atualizarDto = new AtualizacaoTutorDto(1l, "Tutor xpto", "(21)95555-4444", "teste@teste.com");
        BDDMockito.given(repository.getReferenceById(atualizarDto.id())).willReturn(tutor);

        // ACT
        tutorService.atualizar(atualizarDto);

        //ASSERT
        Assertions.assertEquals(atualizarDto.nome(), tutor.getNome());
        Assertions.assertEquals(atualizarDto.email(), tutor.getEmail());
        Assertions.assertEquals(atualizarDto.telefone(), tutor.getTelefone());
    }

}