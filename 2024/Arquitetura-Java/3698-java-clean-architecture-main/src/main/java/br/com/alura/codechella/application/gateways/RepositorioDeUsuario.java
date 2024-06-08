package br.com.alura.codechella.application.gateways;

import br.com.alura.codechella.domain.entities.usuario.Usuario;

import java.util.List;

// Gateways na aplicação serve para definir um contrato ou uma interface abstrata para interações externas necessárias que os casos de uso possam usar.
// Eles representam a maneira como os casos de uso interagem com o mundo externo e são definidos em termos de operações de alto nível que o sistema precisa realizar
// Esse pacote pode ser encontrado tanto na camada da aplicação (a parte mais genérica) como na parte de infra (a parte mais específica)
public interface RepositorioDeUsuario {
    Usuario cadastrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();
}
