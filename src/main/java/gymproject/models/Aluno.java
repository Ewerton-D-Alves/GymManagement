package gymproject.models;
import gymproject.service.PessoaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluno extends Pessoa implements PessoaService {

 private UUID matricula;
}