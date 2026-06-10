package gymproject.repository;

import gymproject.models.Professor;
import jakarta.data.repository.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository {

    @Insert
    void cadastrar(Professor professor);

    @Find
    List<Professor> listarProfessor();

    @Find
    Optional<Professor> buscarCpf(String cpf);

    @Update
    void atualizar(Professor professor);

    @Delete
    void excluir(Professor professor);

}