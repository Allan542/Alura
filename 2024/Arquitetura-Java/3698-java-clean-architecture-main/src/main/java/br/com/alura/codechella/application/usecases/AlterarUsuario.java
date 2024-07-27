package br.com.alura.codechella.application.usecases;


import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;


// UseCases são as regras de negócio da aplicação encapsuladas nessa classe. Aqui são definidos os fluxos que serão feitos na nossa aplicação
public class AlterarUsuario {

    private final RepositorioDeUsuario repositorio;

    public AlterarUsuario(RepositorioDeUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario alterarUsuario(String cpf, Usuario usuario) {
        return repositorio.alterarUsuario(cpf, usuario);
    }
}
