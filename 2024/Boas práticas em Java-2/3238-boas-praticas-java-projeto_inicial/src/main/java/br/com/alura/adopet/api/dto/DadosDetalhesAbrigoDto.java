package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DadosDetalhesAbrigoDto(Long id, String nome, String telefone, String email, List<DadosDetalhesPetDto> pets) {
    public DadosDetalhesAbrigoDto(Abrigo abrigo){
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail(), abrigo.getPets().stream().map(DadosDetalhesPetDto::new).toList());
    }
}
