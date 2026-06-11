package gymproject.repository;

import gymproject.models.Gerente;
import jakarta.data.repository.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface GerenteRepository {
    @Insert
    void cadastrar(Gerente gerente);

    @Find
    List<Gerente> listarGerente();

    @Find
    Optional<Gerente> buscarCpf(String cpf);

    @Update
    void atualizar(Gerente gerente);

    @Delete
    void excluir(Gerente gerente);
}
