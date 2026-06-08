package gymproject.repository;
import gymproject.models.Aluno;
import jakarta.data.repository.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository {

 @Insert
 void cadastrar(Aluno aluno);

 @Find
 List<Aluno> listar(Aluno aluno);

 @Find
 List<Aluno> listarAlunos(String nome, String cpf);
 @Find
 Optional<Aluno> buscarCpf(String cpf);

 @Update
 void atualizar(Aluno aluno);

 @Delete
 void desativar(Aluno aluno);
}