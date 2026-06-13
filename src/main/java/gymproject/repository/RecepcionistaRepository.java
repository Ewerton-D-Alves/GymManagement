package gymproject.repository;

import gymproject.models.Recepcionista;
import jakarta.data.repository.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecepcionistaRepository {

    @Insert
    void cadastrar(Recepcionista recepcionista);

    @Find
    List<Recepcionista> listarRecepcionista();

    @Find
    Optional<Recepcionista> buscarCpf(String cpf);

    @Update
    void atualizar(Recepcionista recepcionista);

    @Delete
    void excluir(Recepcionista recepcionista);
}

