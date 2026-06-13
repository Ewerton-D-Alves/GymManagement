package gymproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter @Setter
@Entity
public class Aula {

    @Id
    private String idAula;
    @Column(length = 255)
    private String tipo;
    @Column(length = 255)
    private String sala;
    @Column(length = 255)
    private LocalDateTime dataHora;

}
