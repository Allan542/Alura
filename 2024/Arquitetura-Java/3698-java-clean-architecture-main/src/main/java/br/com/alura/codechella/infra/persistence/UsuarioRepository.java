package br.com.alura.codechella.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

// Persistence continua sendo a classe Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
