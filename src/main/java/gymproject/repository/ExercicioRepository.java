package gymproject.repository;

import gymproject.models.Aula;
import gymproject.models.Treino;
import jakarta.data.repository.*;


import java.util.Optional;

@Repository
public interface ExercicioRepository {

    @Insert
    void cadastrarAula(Aula aula);

    @Update
    void atualizarAula(Aula aula);

    @Query("SELECT a FROM Aula a WHERE a.tipo = :tipo")
    Optional<Aula> buscarAula(String tipo);

    @Delete
    void deletarAula(Aula aula);

    @Insert
    void cadastrarTreino(Treino treino);

    @Update
    void atualizarTreino(Treino treino);

    @Query("SELECT t FROM Treino t WHERE t.tipo = :tipo")
    Optional<Treino> buscarTreino(String tipo);

    @Delete
    void deletarTreino(Treino treino);

}
