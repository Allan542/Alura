package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Essa DTO serviu para substituir o uso de Entities na Controller e Service, por questões de falha de segurança. Deixa mais complexo o código como desvantagem, porém é mais seguro
public record SolicitacaoAdocaoDto(@NotNull Long idPet, @NotNull Long idTutor, @NotBlank String motivo) {
}
