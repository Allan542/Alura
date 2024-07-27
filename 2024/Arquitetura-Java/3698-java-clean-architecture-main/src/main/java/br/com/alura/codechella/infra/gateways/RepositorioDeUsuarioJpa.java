package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infra.persistence.UsuarioEntity;
import br.com.alura.codechella.infra.persistence.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Os Gateways na camada de infra são responsáveis por implementar os contratos definidos pelos gateways no pacote da aplicação. É neste momento que há implementações concretas
// para as operações definidas na interface gateway utilizando as tecnologias específicas de infra, como banco de dados, comunicação de rede etc.
// Aqui, tem-se a responsabilidade de traduzir as operações de alto nível definidas na interface gateway em chamadas concretas para o banco de dados
// Nesse Gateway na camada de infra, não é mais necessário ser genérico igual na camada de aplicação, porque aqui eu sei qual implementação eu vou usar, que no caso é JPA,
// mas poderia ser JDBC, Hibernate ou qualquer outro framework de persistência
public class RepositorioDeUsuarioJpa implements RepositorioDeUsuario {

    private final UsuarioRepository repositorio;

    private final UsuarioEntityMapper mapper;

    public RepositorioDeUsuarioJpa(UsuarioRepository repositorio, UsuarioEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity salvo = mapper.toEntity(usuario);
        Optional<UsuarioEntity> entity = repositorio.findByCpf(salvo.getCpf());
        if(entity.isPresent()){
            throw new RuntimeException("Usuario já existe com cpf informado");
        }
        repositorio.save(salvo);
        return mapper.toDomain(salvo);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repositorio.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Usuario alterarUsuario(String cpf, Usuario usuario) {
        Optional<UsuarioEntity> entity = repositorio.findByCpf(cpf);
        if(entity.isEmpty() || !cpf.equals(usuario.getCpf())){
            return null;
        }
        UsuarioEntity alterado = mapper.toEntity(usuario);
        alterado.setId(entity.get().getId());
        repositorio.save(alterado);

        return usuario;
    }

    @Override
    public void excluirUsuario(String cpf) {
        Optional<UsuarioEntity> entity = repositorio.findByCpf(cpf);
        entity.ifPresent(usuarioEntity -> repositorio.deleteById(usuarioEntity.getId()));

    }
}
