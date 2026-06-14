package gymproject.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@NoArgsConstructor
@Getter @Setter
@Entity
public class Professor extends Staff {

    public Professor(String primeiroNome, String sobrenome,
                     String cpf, String telefone, LocalDate dataNascimento,
                     String telefoneEmerg, String nomeEmerg,
                     String loginAcesso, String senhaAcesso, String funcao) {
        super(primeiroNome, sobrenome, cpf, telefone, dataNascimento,
                telefoneEmerg, nomeEmerg, loginAcesso, senhaAcesso, funcao);
    }
}

