package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adocoes")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    // Todos os relacionamentos que são ToOne, por padrão, a JPA carrega junto esse relacionamento. Ou seja, se fizer uma consulta para carregar uma adoção a JPA vai automaticamente fazer um join para carregar um tutor.
    // Então é feito em ambas as tabelas, um select de adoção e tutor. O mesmo vale para Pet. Isso acaba impactando no desempenho da aplicação quando você quiser buscar apenas um campo que não tem relação com esses atributos ToOne
    // A relação entre Adocao e Tutor é muitas para um, ou seja, muitas adoções podem estar relacionadas a apenas um tutor e um tutor pode ter várias adoções
    @ManyToOne(fetch = FetchType.LAZY) // Por padrão, a busca de um atributo ToOne é uma busca precoce (Eager). Temos que mudar para uma busca preguiçosa (Lazy) por questões de performance.
    private Tutor tutor;

    // A relação entre Adocao e Pet é um para um, ou seja, um pet para uma adoção e vice-versa
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativaStatus;

    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
        this.data = LocalDateTime.now();
    }

    // Curiosidade: A entidade JPA precisa ter um construtor padrão
    public Adocao(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adocao adocao = (Adocao) o;
        return Objects.equals(id, adocao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Pet getPet() {
        return pet;
    }

    public String getMotivo() {
        return motivo;
    }

    public StatusAdocao getStatus() {
        return status;
    }

    public String getJustificativaStatus() {
        return justificativaStatus;
    }

    // Não usar sets e sim, usar métodos mais específicos que estão dentro da entidade JPA e mexem na mesma, são boas práticas de OOP e Encapsulamento
    // Os métodos é que deveriam mexer nos atributos da classe e não de fora da classe
    public void marcarComoAprovado() {
        this.status = StatusAdocao.APROVADO;
    }

    public void marcarComoReprovada(String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;
    }
}
