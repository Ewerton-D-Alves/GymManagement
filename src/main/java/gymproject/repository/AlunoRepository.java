package gymproject.repository;
import gymproject.models.Aluno;
import jakarta.data.repository.*;

import java.util.List;

@Repository
public interface AlunoRepository {

 @Insert
 void cadastrar(Aluno aluno);

 @Find
 List<Aluno> listar(Aluno aluno);

 @Find
 List<Aluno> listarAlunos(String nome, String cpf);

 @Update
 void atualizar(Aluno aluno);

 @Delete
 void desativar(Aluno aluno);
}