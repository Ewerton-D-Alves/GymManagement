package gymproject.repository;

import gymproject.models.Staff;
import jakarta.data.repository.*;

import java.util.Optional;

@Repository
public interface StaffRepository {

    @Insert
    void cadastrarUsuario(Staff staff);

    @Find
    Optional<Staff> buscarLogin(String loginAcesso);

    @Find
    Optional<Staff> buscarSenha(String senhaAcesso);

    @Update
    void alterarLogin(String loginAcesso);

    @Update
    void alterarSenha(String senhaAcesso);

    @Delete
    void removerUsuario(String loginAcesso, String senhaAcesso);
}
