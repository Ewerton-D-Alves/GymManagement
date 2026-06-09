package gymproject.models;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@MappedSuperclass
public abstract class Pessoa {
    @Column(name = "primeiro_nome", nullable = false)
    private String primeiroNome;
    @Column(name = "nome_do_meio", nullable = false)
    private String meioNome;
    @Column(nullable = false)
    private String sobrenome;
    @Id //@Column(name = "CPF")
    private String cpf;
    @Column(nullable = false)
    private String telefone;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Column(name = "nome_contato_emergencia", nullable = false)
    private String telefoneEmerg;
    @Column(name = "numero_contato_emergencia", nullable = false)
    private String nomeEmerg;

    @Override
    public String toString() {
        return "Pessoa (" + "Nome = " + primeiroNome + "\n" + "CPF = " + cpf + "\n"
                + "Telefone = " + telefone + "\n" + "Data de nascimento = "
                + dataNascimento + "\n" + "Nome contato de emergência = "
                + nomeEmerg + "\n" + "Contato de emergência = " + telefoneEmerg + ")";
    }
}
