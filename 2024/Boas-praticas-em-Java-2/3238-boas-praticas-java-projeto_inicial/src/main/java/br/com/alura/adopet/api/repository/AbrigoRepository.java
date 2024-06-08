package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    boolean existsByNomeOrExistsByTelefoneOrExistsByEmail(String nome, String telefone, String email);
    Abrigo findByNome(String nome);

}
