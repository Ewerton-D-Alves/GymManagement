package gymproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String funcao;

    public Staff(String primeiroNome, String sobrenome,
                 String cpf, String telefone, LocalDate dataNascimento,
                 String telefoneEmerg, String nomeEmerg,
                 String loginAcesso, String senhaAcesso, String funcao) {
        super(primeiroNome, sobrenome, cpf, telefone, dataNascimento, telefoneEmerg, nomeEmerg);
        this.loginAcesso = loginAcesso;
        this.senhaAcesso = senhaAcesso;
        this.funcao = funcao;
    }
}
