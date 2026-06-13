package gymproject.repository;

import gymproject.models.Aula;
import jakarta.data.repository.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository {

    @Insert
    void cadastrar(Aula aula);

    @Find
    List<Aula> listarAula();

    @Find
    Optional<Aula> buscarAula(String idAula);

    @Update
    void atualizar(Aula aula);

    @Delete
    void excluir(Aula aula);

}
