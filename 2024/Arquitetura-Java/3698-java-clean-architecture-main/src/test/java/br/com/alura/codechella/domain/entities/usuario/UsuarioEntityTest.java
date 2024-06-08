package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioEntityTest {

    @Test
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido(){
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Usuario("123456.789-99", "Allan", LocalDate.parse("1990-09-08"), "email@email.com"));


        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Usuario("12345678999", "Allan", LocalDate.parse("1990-09-08"), "email@email.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Usuario("", "Allan", LocalDate.parse("1990-09-08"), "email@email.com"));
    }

    @Test
    public void deveCriarusuarioUsandoFabricaDeUsuario(){
        FabricaDeUsuario fabrica = new FabricaDeUsuario();
        Usuario usuario = fabrica.comNomeCpfNascimento("Emily", "654.123.897-88", LocalDate.parse("2000-10-01"));

        Assertions.assertEquals("Emily", usuario.getNome());

        usuario = fabrica.incluiEndereco("12345-999", 40, "apto 201");

        Assertions.assertEquals("apto 201", usuario.getEndereco().getComplemento());
    }

    @Test
    public void naoDeveCadastrarUsuarioComMenosDeDezoitoAnos(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Usuario("123.456.789-10", "Allan", LocalDate.parse("2010-02-07"), "email@email.com"));

        Assertions.assertEquals("Usuário não pode ter menos que 18 anos", exception.getMessage());
    }
}
