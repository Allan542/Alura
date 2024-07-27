package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RepositorioDeUsuarioEmArquivo implements RepositorioDeUsuario {

    List<Usuario> usuarios = new ArrayList<>();

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        return this.usuarios;
    }

    @Override
    public Usuario alterarUsuario(String cpf, Usuario usuario) {
        Optional<Usuario> encontrado = usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst();
        if(encontrado.isEmpty()){
            return null;
        }
        int indexEncontrado = usuarios.indexOf(encontrado.get());
        usuarios.set(indexEncontrado, usuario);

        return usuario;
    }

    @Override
    public void excluirUsuario(String cpf) {
        usuarios.removeIf(usuario -> usuario.getCpf().equals(cpf));
    }

    public void gravaEmArquivo(String nomeArquivo){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(nomeArquivo);
            fileWriter.write(this.usuarios.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
