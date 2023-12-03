package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoDoCancelamentoFoiInformado implements ValidadorCancelamentoDeConsulta {

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        if(dados.motivo() == null){
            throw new ValidacaoException("Campo motivo do cancelamento é obrigatório ser informado!");
        }
    }
}
