package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;

// Interface para ser implementada por validações que serão criadas no futuro, para que seja possível criar e excluir validações sem mexer em pontos do projeto que utilizam a execução desta interface
// Isso entra no padrão Strategy de OOP e o Chain of Responsibility usando interface e polimorfismo
public interface ValidacaoSolicitacaoAdocao {

    void validar(SolicitacaoAdocaoDto dto);
}
