package gymproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter


public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private String nomeEmerg;
    private String telefoneEmerg;

}
