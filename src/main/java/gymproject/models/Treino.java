package gymproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Treino {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column
    UUID id;
    @Column(nullable = false)
    String nome;
    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_exercicio")
    private Set<Exercicio> exercicios;

}
