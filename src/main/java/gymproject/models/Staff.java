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
public class Staff extends Pessoa {

    private String loginAcesso;
    private String senhaAcesso;

    public Staff(String primeiroNome, String meioNome, String sobrenome,
                 String cpf, String telefone, LocalDate dataNascimento,
                 String telefoneEmerg, String nomeEmerg,
                 String loginAcesso, String senhaAcesso) {
        super(primeiroNome, meioNome, sobrenome, cpf, telefone,
                dataNascimento, telefoneEmerg, nomeEmerg);
        this.loginAcesso = loginAcesso;
        this.senhaAcesso = senhaAcesso;
    }
}
