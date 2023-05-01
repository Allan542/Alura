package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201 quando informações estão válidas para cadastrar um médico")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DadosCadastroMedico("teste", "teste@voll.med", "1199999999", "123456", Especialidade.CARDIOLOGIA, dadosEndereco());

        Mockito.when(medicoRepository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJson.write(dadosCadastro).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());


        var jsonEsperado = dadosDetalhamentoMedicoJson.write(
                new DadosDetalhamentoMedico(null, "teste", "teste@voll.med", "123456", "1199999999", Especialidade.CARDIOLOGIA, new Endereco(dadosEndereco()))
        ).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosEndereco dadosEndereco(){
        return new DadosEndereco("rua teste", "teste", "00000000", "Sao Paulo", "SP", null, null);
    }
}