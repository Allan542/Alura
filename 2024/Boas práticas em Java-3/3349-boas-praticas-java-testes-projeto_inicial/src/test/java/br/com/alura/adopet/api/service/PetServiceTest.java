package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService service;

    @Mock
    private PetRepository repository;

    @Mock
    private Pet pet;

    @Mock
    private Abrigo abrigo;

    private List<Pet> pets = new ArrayList<>();

    private CadastroPetDto dto;

    @Captor
    private ArgumentCaptor<Pet> petCaptor;

    @Test
    void deveriaRetornarTodosOsPetsDisponiveis(){
        //ARRANGE
        Pet pet = new Pet(1l, TipoPet.CACHORRO, "Crodoaldo", "Vira-lata", 12);
        pets.add(pet);
        BDDMockito.given(repository.findAllByAdotadoFalse()).willReturn(pets);

        //ACT
        List<PetDto> dtos = service.buscarPetsDisponiveis();

        //ASSERT
        Assertions.assertEquals(pet.getId(), dtos.get(0).id());
        Assertions.assertEquals(pet.getTipo(), dtos.get(0).tipo());
        Assertions.assertEquals(pet.getNome(), dtos.get(0).nome());
        Assertions.assertEquals(pet.getRaca(), dtos.get(0).raca());
        Assertions.assertEquals(pet.getIdade(), dtos.get(0).idade());
    }

    @Test
    void deveriaCadastrarUmPetNoAbrigo(){
        //ARRANGE
        this.dto = new CadastroPetDto(TipoPet.CACHORRO, "Xpto", "Pit Bull", 4, "Preto", 4.4f);

        //ACT
        service.cadastrarPet(abrigo, dto);

        //ASSERT
        BDDMockito.then(repository).should().save(petCaptor.capture());
        Pet pet = petCaptor.getValue();
        Assertions.assertEquals(dto.tipo(), pet.getTipo());
        Assertions.assertEquals(dto.nome(), pet.getNome());
        Assertions.assertEquals(dto.raca(), pet.getRaca());
        Assertions.assertEquals(dto.idade(), pet.getIdade());
        Assertions.assertEquals(dto.cor(), pet.getCor());
        Assertions.assertEquals(dto.peso(), pet.getPeso());
    }
}