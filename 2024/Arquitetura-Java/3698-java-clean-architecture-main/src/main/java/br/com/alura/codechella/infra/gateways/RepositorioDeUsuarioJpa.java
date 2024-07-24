package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infra.persistence.UsuarioEntity;
import br.com.alura.codechella.infra.persistence.UsuarioRepository;

import java.util.List;
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
        UsuarioEntity entity = mapper.toEntity(usuario);
        repositorio.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repositorio.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }
}
