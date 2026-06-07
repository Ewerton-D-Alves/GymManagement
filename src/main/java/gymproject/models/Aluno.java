package gymproject.models;
import jakarta.persistence.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Aluno extends Pessoa /* implements AlunoService */ {

 private UUID matricula;

 public Aluno (String nome, String cpf, String telefone, LocalDate dataNascimento,
         String nomeEmerg, String telefoneEmerg, UUID matricula) {
  super(nome, cpf, telefone, dataNascimento, nomeEmerg, telefoneEmerg);

  this.matricula = matricula;
 }

 @Override
 public String toString() {
  return "Aluno (" +
          "Matricula = " + matricula +
          ")";
 }
}

