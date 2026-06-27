package gymproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo_treino", length = 100, nullable = false)
    private String tipo;
    @Column(name = "data_aula", nullable = false, length = 25)
    private LocalDateTime dataAula;
    @ManyToOne
    @JoinColumn(name = "professor_cpf")
    private Professor professor;

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", dataAula=" + dataAula +
                ", professor=" + professor +
                '}';
    }

    public Aula(String tipo, LocalDateTime dataAula, Professor professor) {
        this.id = id;
        this.tipo = tipo;
        this.dataAula = dataAula;
        this.professor = professor;


    }
}
