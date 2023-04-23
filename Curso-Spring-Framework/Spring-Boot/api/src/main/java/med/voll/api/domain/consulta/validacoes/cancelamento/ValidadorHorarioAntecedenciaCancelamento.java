package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedenciaCancelamento implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        if(dados.motivo() == null) {
            return;
        }

        var agora = LocalDateTime.now();
        var dataConsulta = consultaRepository.findDataById(dados.idConsulta());

        if(dataConsulta == null){
            throw new ValidacaoException("Consulta já foi cancelada anteriormente!");
        }

        var diferenciaEmHoras = Duration.between(agora, dataConsulta).toHours();

        if(diferenciaEmHoras < 24){
            throw new ValidacaoException("Consulta deve ser cancelada com antecedência mínima de 24 horas e não pode ter sido cancelada anteriormente");
        }
    }
}
