package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DadosCadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(DadosCadastroTutorDto dto) {
        boolean tutorJaCadastrado = repository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

        if (tutorJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro dto!");
        }

        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
    }

    public ResponseEntity<String> atualizar(DadosCadastroTutorDto dto) {
        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
        return ResponseEntity.ok().build();
    }
}
