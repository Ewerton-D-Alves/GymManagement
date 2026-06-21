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
 private String matricula;

 public Aluno (String primeiroNome, String sobrenome, String cpf, String telefone, LocalDate dataNascimento,
         String nomeEmerg, String telefoneEmerg, String matricula) {
  super(primeiroNome,sobrenome, cpf, telefone, dataNascimento, nomeEmerg, telefoneEmerg);

  this.matricula = matricula;
 }

 @Override
 public String toString() {
  return "Aluno (" +
          "Matricula = " + matricula +
          ")";
 }
}

