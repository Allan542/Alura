package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosCadastroAbrigo;
import br.com.alura.adopet.api.dto.DadosCadastroPetDto;
import br.com.alura.adopet.api.dto.DadosDetalhesAbrigoDto;
import br.com.alura.adopet.api.dto.DadosDetalhesPetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    public List<DadosDetalhesAbrigoDto> listar() {
        return abrigoRepository.findAll().stream().map(DadosDetalhesAbrigoDto::new).toList();
    }

    public void cadastrar(DadosCadastroAbrigo dto) {
        boolean abrigoJaCadastrado = abrigoRepository.existsByNomeOrExistsByTelefoneOrExistsByEmail(dto.nome(), dto.telefone(), dto.email());

        if (abrigoJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }

        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        abrigoRepository.save(abrigo);
    }

    public List<DadosDetalhesPetDto> listarPets(Object idOuNome) {
        Abrigo abrigo = carregarAbrigo(idOuNome);

        return abrigo.getPets().stream().map(DadosDetalhesPetDto::new).toList();
    }

    public void cadastrarPet(Object idOuNome, DadosCadastroPetDto dto) {
        Abrigo abrigo = carregarAbrigo(idOuNome);

        Pet pet = new Pet(dto.tipo(), dto.nome(), dto.raca(), dto.idade(), dto.cor(), dto.peso(), abrigo);
        abrigo.getPets().add(pet);

        abrigoRepository.save(abrigo);
    }

    private Abrigo carregarAbrigo(Object idOuNome){
        Abrigo abrigo = null;

        if (idOuNome instanceof Long) abrigo = abrigoRepository.getReferenceById((Long) idOuNome);
        else if (idOuNome instanceof String) abrigo = abrigoRepository.findByNome((String) idOuNome);

        return abrigo;
    }
}
