package br.com.alura.codechella;

import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infra.gateways.RepositorioDeUsuarioEmArquivo;

import java.time.LocalDate;
import java.util.List;

public class UtilizaUsuariosComArquivos {
    public static void main(String[] args) {
        RepositorioDeUsuarioEmArquivo repositorioDeUsuarioEmArquivo = new RepositorioDeUsuarioEmArquivo();
        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("456.789.888-88", "João", LocalDate.parse("2000-10-15"), "joao@email.com"));
        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("456.789.888-88", "Maria", LocalDate.parse("2000-10-15"), "maria@email.com"));
        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("456.789.888-88", "Vinicius", LocalDate.parse("2000-10-15"), "vinicius@email.com"));
        repositorioDeUsuarioEmArquivo.cadastrarUsuario(new Usuario("456.789.888-88", "Raphael", LocalDate.parse("2000-10-15"), "raphael@email.com"));
        List<Usuario> usuarios = repositorioDeUsuarioEmArquivo.listarTodos();
        repositorioDeUsuarioEmArquivo.gravaEmArquivo("usuarios.txt");
//        System.out.println(usuarios);
    }
}
