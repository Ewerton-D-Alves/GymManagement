package gymproject.models;
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
    private String nome;
    @Id
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private String nomeEmerg;
    private String telefoneEmerg;

    @Override
    public String toString() {
        return "Pessoa (" + "Nome = " + nome + "\n" + "CPF = " + cpf + "\n"
                + "Telefone = " + telefone + "\n" + "Data de nascimento = "
                + dataNascimento + "\n" + "Nome contato de emergência = "
                + nomeEmerg + "\n" + "Contato de emergência = " + telefoneEmerg + ")";
    }
}
