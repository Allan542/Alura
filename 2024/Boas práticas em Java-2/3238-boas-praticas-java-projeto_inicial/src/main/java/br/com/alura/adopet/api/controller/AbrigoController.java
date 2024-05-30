package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.DadosCadastroAbrigo;
import br.com.alura.adopet.api.dto.DadosCadastroPetDto;
import br.com.alura.adopet.api.dto.DadosDetalhesAbrigoDto;
import br.com.alura.adopet.api.dto.DadosDetalhesPetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<DadosDetalhesAbrigoDto>> listar() {
        return ResponseEntity.ok(this.abrigoService.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroAbrigo dto) {
        try {
            abrigoService.cadastrar(dto);
            return ResponseEntity.ok("Abrigo cadastrado com sucesso!");
        } catch (ValidacaoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<DadosDetalhesPetDto>> listarPets(@PathVariable Object idOuNome) {
        try {
            List<DadosDetalhesPetDto> pets = abrigoService.listarPets(idOuNome);
            return ResponseEntity.ok(pets);
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable Object idOuNome, @RequestBody @Valid DadosCadastroPetDto dto) {
        try {
            abrigoService.cadastrarPet(idOuNome, dto);
            return ResponseEntity.ok("Pet cadastrado no abrigo com sucesso!");
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        }
    }

}
