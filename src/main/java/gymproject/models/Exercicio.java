package gymproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column (nullable = false)
    private String nome;
    @Column (nullable = false)
    private int repeticao;
    @Column (nullable = false)
    private int serie;
    @Column (nullable = false)
    private int carga;

}
