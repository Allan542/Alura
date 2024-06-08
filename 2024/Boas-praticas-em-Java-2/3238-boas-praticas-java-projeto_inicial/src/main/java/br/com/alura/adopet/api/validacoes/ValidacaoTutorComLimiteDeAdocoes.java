package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// Aplicando a letra S do SOLID de Single Responsibility, no qual diz que cada classe deve ter apenas uma responsabilidade.
// Também, está sendo aplicado o padrão de orientação de objetos Strategy, no qual cada validação foi separada em uma classe diferente, cada uma seguindo uma estratégia diferente.
@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoesComStatusAprovado = adocaoRepository.findAllByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);

        if (adocoesComStatusAprovado.size() >= 5) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
