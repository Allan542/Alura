package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotNull;

// Essa DTO serviu para substituir o uso de Entities na Controller e Service, por questões de falha de segurança. Deixa mais complexo o código como desvantagem, porém é mais seguro
public record AprovacaoAdocaoDto(@NotNull Long idAdocao) {
}
