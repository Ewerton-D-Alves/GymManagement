package gymproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo_exercicio", length = 100)
    private String tipo; //biceps, triceps, perna, costa etc
    @Column(name = "serie_exercico", length = 100)
    private String serie;
    @ManyToOne
    @JoinColumn(name = "professor_cpf")
    private Professor professor;

    public Treino(int id, String tipo, String serie, Professor professor) {
        this.id = id;
        this.tipo = tipo;
        this.serie = serie;
        this.professor = professor;
    }

    public Treino(String aulaTipo, String exercicio, Professor professorResponsavel) {
    }

    @Override
    public String toString() {
        return "Treino{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", serie='" + serie + '\'' +
                ", professor=" + professor +
                '}';
    }
}
