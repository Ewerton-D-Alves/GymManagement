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
    @Column(name = "primeiro_nome", nullable = false, length = 100)
    private String primeiroNome;
    @Column(nullable = false, length = 100)
    private String sobrenome;
    @Id @Column(length = 11)
    private String cpf;
    @Column(nullable = false, length = 9)
    private String telefone;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Column(name = "numero_contato_emergencia", nullable = false, length = 9)
    private String telefoneEmerg;
    @Column(name = "nome_contato_emergencia", nullable = false, length = 255)
    private String nomeEmerg;

    @Override
    public String toString() {
        return "Pessoa (" + "Nome = " + primeiroNome + "\n" + "CPF = " + cpf + "\n"
                + "Telefone = " + telefone + "\n" + "Data de nascimento = "
                + dataNascimento + "\n" + "Nome contato de emergência = "
                + nomeEmerg + "\n" + "Contato de emergência = " + telefoneEmerg + ")";
    }
}
