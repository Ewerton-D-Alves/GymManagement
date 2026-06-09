package gymproject.repository;
import gymproject.models.Aluno;
import jakarta.data.repository.*;

import java.util.Optional;

@Repository
public interface AlunoRepository {

 @Insert
 void cadastrar(Aluno aluno);

 @Find
 Optional<Aluno> listarAlunos();

 @Find
 Optional<Aluno> buscarCpf(String cpf);

 @Update
 void atualizar(Aluno aluno);

 @Delete
 void excluir(Aluno aluno);
}