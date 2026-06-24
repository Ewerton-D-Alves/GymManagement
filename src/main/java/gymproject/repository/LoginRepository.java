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

    @Query("SELECT s FROM Staff s WHERE s.loginAcesso = :loginAcesso AND s.senhaAcesso = :senhaAcesso")
    Optional<Staff> buscarUsuario(String loginAcesso, String senhaAcesso);

    @Query("UPDATE Staff s SET s.loginAcesso = :loginAcesso WHERE s.cpf = :cpf")
    void alterarLogin(String loginAcesso, String cpf);

    @Query("UPDATE Staff s SET s.senhaAcesso = :senhaAcesso WHERE s.cpf = :cpf")
    void alterarSenha(String senhaAcesso, String cpf);

    @Query("DELETE FROM Staff s WHERE s.loginAcesso = :loginAcesso AND s.senhaAcesso = :senhaAcesso")
    void removerUsuario(String loginAcesso,String senhaAcesso);
}
