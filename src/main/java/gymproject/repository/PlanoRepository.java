package gymproject.repository;

import gymproject.models.Plano;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Update;

import java.util.List;
import java.util.Optional;

public interface PlanoRepository {
    @Insert
    void cadastrar(Plano plano);

    @Find
    List<Plano> listarPlano();

    @Find
    Optional<Plano> buscarPlano(String codPlano);

    @Update
    void atualizar(Plano plano);

    @Delete
    void excluir(Plano plano);
}
