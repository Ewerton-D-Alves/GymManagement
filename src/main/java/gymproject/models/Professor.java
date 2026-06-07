package gymproject.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Professor extends Pessoa {

    public Professor (String nome, String cpf, String telefone, LocalDate dataNascimento,
                      String nomeEmerg, String telefoneEmerg) {
        super(nome, cpf, telefone, dataNascimento, nomeEmerg, telefoneEmerg);
    }

}

