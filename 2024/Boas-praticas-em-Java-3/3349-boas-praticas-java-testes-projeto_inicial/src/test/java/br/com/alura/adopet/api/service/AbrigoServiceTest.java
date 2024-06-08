package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService service;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Abrigo abrigo;

    private List<Abrigo> abrigos = new ArrayList<>();

    private List<Pet> pets = new ArrayList<>();

    private CadastroAbrigoDto cadastroAbrigoDto;

    @Captor
    private ArgumentCaptor<Abrigo> abrigoCaptor;

    @Test
    void deveriaRetornarTodosOsAbrigosCadastrados(){
        //ARRANGE
        Abrigo abrigo = new Abrigo(1l, "Abrigo xpto", "(11)1234-5678", "teste@teste.com");
        abrigos.add(abrigo);
        BDDMockito.given(abrigoRepository.findAll()).willReturn(abrigos);

        //ACT
        List<AbrigoDto> dtos = service.listar();

        //ASSERT
        Assertions.assertEquals(abrigo.getId(), dtos.get(0).id());
        Assertions.assertEquals(abrigo.getNome(), dtos.get(0).nome());
    }

    @Test
    void deveriaDarExceptionQuandoJaExisteAbrigoPorNomeOuTelefoneOuEmail(){
        //ARRANGE
        this.cadastroAbrigoDto = new CadastroAbrigoDto("Xpto", "(11)1234-5678", "teste@teste.com");
        BDDMockito.given(abrigoRepository.existsByNomeOrTelefoneOrEmail(cadastroAbrigoDto.nome(), cadastroAbrigoDto.telefone(), cadastroAbrigoDto.email())).willReturn(true);

        //ASSERT + ACT
        Assertions.assertThrows(ValidacaoException.class, () -> service.cadastrar(cadastroAbrigoDto));
    }

    @Test
    void deveriaCadastrarAbrigoQuandoNaoExistePorNomeOuTelefoneOuEmail(){
        //ARRANGE
        this.cadastroAbrigoDto = new CadastroAbrigoDto("Abrigo Xpto", "(11)91234-5678", "teste@abrigo.com");
        BDDMockito.given(abrigoRepository.existsByNomeOrTelefoneOrEmail(cadastroAbrigoDto.nome(), cadastroAbrigoDto.telefone(), cadastroAbrigoDto.email())).willReturn(false);

        // ACT
        service.cadastrar(cadastroAbrigoDto);

        //ASSERT
        BDDMockito.then(abrigoRepository).should().save(abrigoCaptor.capture());
        Abrigo abrigo = abrigoCaptor.getValue();
        Assertions.assertEquals(cadastroAbrigoDto.nome(), abrigo.getNome());
        Assertions.assertEquals(cadastroAbrigoDto.telefone(), abrigo.getTelefone());
        Assertions.assertEquals(cadastroAbrigoDto.email(), abrigo.getEmail());
    }

    @Test
    void deveriaDarExceptionQuandoAbrigoNaoEncontrado(){
        //ARRANGE
        BDDMockito.given(abrigoRepository.findByNome(any())).willReturn(Optional.empty());

        //ASSERT + ACT
        Assertions.assertThrows(ValidacaoException.class, () -> service.listarPetsDoAbrigo("sopa"));
    }

    @Test
    void deveriaRetornarTodosOsPetsCadastradosPorIdDoAbrigo(){
        //ARRANGE
        Pet pet = new Pet(1l, TipoPet.GATO, "Fofinho", "Vira-lata", 7);
        pets.add(pet);
        BDDMockito.given(abrigoRepository.findById(any())).willReturn(Optional.of(abrigo));
        BDDMockito.given(petRepository.findByAbrigo(abrigo)).willReturn(pets);

        //ACT
        List<PetDto> dtos = service.listarPetsDoAbrigo("1");

        //ASSERT
        Assertions.assertEquals(pet.getId(), dtos.get(0).id());
        Assertions.assertEquals(pet.getTipo(), dtos.get(0).tipo());
        Assertions.assertEquals(pet.getNome(), dtos.get(0).nome());
        Assertions.assertEquals(pet.getRaca(), dtos.get(0).raca());
        Assertions.assertEquals(pet.getIdade(), dtos.get(0).idade());
    }

    @Test
    void deveriaRetornarTodosOsPetsCadastradosPorNomeDoAbrigo(){
        //ARRANGE
        Pet pet = new Pet(1l, TipoPet.GATO, "Fofinho", "Vira-lata", 7);
        pets.add(pet);
        BDDMockito.given(abrigoRepository.findByNome(any())).willReturn(Optional.of(abrigo));
        BDDMockito.given(petRepository.findByAbrigo(abrigo)).willReturn(pets);

        //ACT
        List<PetDto> dtos = service.listarPetsDoAbrigo("Abrigo xpto");

        //ASSERT
        Assertions.assertEquals(pet.getId(), dtos.get(0).id());
        Assertions.assertEquals(pet.getTipo(), dtos.get(0).tipo());
        Assertions.assertEquals(pet.getNome(), dtos.get(0).nome());
        Assertions.assertEquals(pet.getRaca(), dtos.get(0).raca());
        Assertions.assertEquals(pet.getIdade(), dtos.get(0).idade());
    }
}