package gymproject.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@Getter @Setter
@Entity
public class Aluno extends Pessoa {

 @Column(nullable = false)
 private UUID matricula;

 public Aluno (String primeiroNome, String nomeMeio, String sobrenome, String cpf, String telefone, LocalDate dataNascimento,
         String nomeEmerg, String telefoneEmerg, UUID matricula) {
  super(primeiroNome, nomeMeio,sobrenome, cpf, telefone, dataNascimento, nomeEmerg, telefoneEmerg);

  this.matricula = matricula;
 }

 @Override
 public String toString() {
  return "Aluno (" +
          "Matricula = " + matricula +
          ")";
 }
}

