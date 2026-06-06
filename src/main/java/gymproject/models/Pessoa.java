package gymproject.models;
import jakarta.persistence.*;
//import jakarta.persistence.Id;
//import jakarta.persistence.PostInsert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity

public abstract class Pessoa {
    private String nome;
    @Id
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private String nomeEmerg;
    private String telefoneEmerg;
}
