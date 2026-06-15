package gymproject.repository;

import gymproject.models.Staff;
import jakarta.data.repository.*;

import java.util.Optional;

@Repository
public interface LoginRepository {
    @Insert
    void cadastrarUsuario(Staff staff);

    @Update
    void atualizarUsuario(Staff staff);

    @Find
    Optional<Staff> buscarLogin(String loginAcesso);

    @Find
    Optional<Staff> buscarSenha(String senhaAcesso);

    @Find
    Optional<Staff> buscarCpfStaff(String cpf);

    @Update
    void alterarLogin(String loginAcesso);

    @Update
    void alterarSenha(String senhaAcesso);

    @Delete
    void removerUsuario(String loginAcesso, String senhaAcesso);
}
