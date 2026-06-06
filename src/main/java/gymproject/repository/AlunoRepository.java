package gymproject.repository;
import gymproject.models.Aluno;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;

import java.util.List;


@Repository
public interface AlunoRepository {

 @Insert
 void cadastrar(Aluno aluno);

 @Find
 List<Aluno> listar();

 @Update
 void atualizar(Aluno aluno);

}