package gymproject.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Professor extends Pessoa {

    public Professor (String primeiroNome, String nomeMeio, String sobrenome, String cpf, String telefone, LocalDate dataNascimento,
                      String nomeEmerg, String telefoneEmerg) {
        super(primeiroNome, nomeMeio, sobrenome, cpf, telefone, dataNascimento, nomeEmerg, telefoneEmerg);
    }
}

