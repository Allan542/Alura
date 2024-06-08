package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhesPetDto(
    Long id,
    TipoPet tipo,
    String nome,
    String raca,
    Integer idade,
    String cor,
    Float peso,
    Boolean adotado,
    Long idAbrigo,
    Long idAdocao) {

    public DadosDetalhesPetDto(Pet pet) {
        this(pet.getId(),
             pet.getTipo(),
             pet.getNome(),
             pet.getRaca(),
             pet.getIdade(),
             pet.getCor(),
             pet.getPeso(),
             pet.getAdotado(),
             pet.getAbrigo().getId(),
             pet.getAdocao().getId());
    }
}
