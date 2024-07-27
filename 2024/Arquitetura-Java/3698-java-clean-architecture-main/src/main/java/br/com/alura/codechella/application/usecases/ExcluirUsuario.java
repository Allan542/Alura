package br.com.alura.codechella.application.usecases;


import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;


// UseCases são as regras de negócio da aplicação encapsuladas nessa classe. Aqui são definidos os fluxos que serão feitos na nossa aplicação
public class ExcluirUsuario {

    private final RepositorioDeUsuario repositorio;

    public ExcluirUsuario(RepositorioDeUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public void excluirUsuario(String cpf) {
        repositorio.excluirUsuario(cpf);
    }
}
