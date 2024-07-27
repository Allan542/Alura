package br.com.alura.codechella.infra.persistence;

import br.com.alura.codechella.domain.entities.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Persistence continua sendo a classe Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByCpf(String cpf);

    List<UsuarioEntity> findAllByCpf(String cpf);
}
