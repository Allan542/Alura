package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

public class ReajusteServiceTest {
    
    private ReajusteService service;
    private Funcionario funcionario;

    @BeforeAll // Antes de todos precisa ser estático
    public static void AntesDeTodos() {
        System.out.println("ANTES DE TODOS!");
    }

    @AfterAll // Depois de todos precisa ser estático
    public static void DepoisDeTodos() {
        System.out.println("DEPOIS DE TODOS!");
    }

    @BeforeEach // Com essa anotação, chama o método para instanciar as variáveis antes de cada um dos métodos de teste (Com a anotação @Test)
    public void inicializar() {
        System.out.println("Inicializar!");
        this.service = new ReajusteService();
        this.funcionario = new Funcionario("Ana", LocalDate.now(), new BigDecimal("1000"));
    }

    @AfterEach // Executa este método depois de cada um dos métodos de teste (Com a anotação @Test)
    public void finalizar() {
        System.out.println("Fim!");
    }

    // Métodos de teste

    @Test
    public void reajusteDeveriaSerDeTresPorCentoQuantoDesempenhoForADesejar() {
        service.concederReajuste(funcionario, Desempenho.A_DESEJAR);
        assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
    }

    @Test
    public void reajusteDeveriaSerDeQuinzePorCentoQuantoDesempenhoForBom() {
        service.concederReajuste(funcionario, Desempenho.BOM);
        assertEquals(new BigDecimal("1150.00"), funcionario.getSalario());
    }

    @Test
    public void reajusteDeveriaSerDeVintePorCentoQuantoDesempenhoForOtimo() {
        service.concederReajuste(funcionario, Desempenho.OTIMO);
        assertEquals(new BigDecimal("1200.00"), funcionario.getSalario());
    }

    @Test
    public void reajusteDeveriaSerDeQuarentaPorCentoQuantoDesempenhoForEspetacular() {
        service.concederReajuste(funcionario, Desempenho.ESPETACULAR);
        assertEquals(new BigDecimal("1400.00"), funcionario.getSalario());
    }
}
