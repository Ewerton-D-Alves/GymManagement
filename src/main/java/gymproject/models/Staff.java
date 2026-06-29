package gymproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="cpf")
public abstract class Staff extends Pessoa {
    //Atributos que serão de outra tabela;
    @Column(name = "login", nullable = true, length = 255)
    private String loginAcesso;
    @Column(name = "senha", nullable = true,length = 255)
    private String senhaAcesso;

    @OneToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;


    public Staff(String primeiroNome, String sobrenome,
                 String cpf, String telefone, LocalDate dataNascimento,
                 String telefoneEmerg, String nomeEmerg,
                 String loginAcesso, String senhaAcesso) {
        super(primeiroNome, sobrenome, cpf, telefone, dataNascimento, telefoneEmerg, nomeEmerg);
        this.loginAcesso = loginAcesso;
        this.senhaAcesso = senhaAcesso;
    }
}
