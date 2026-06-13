package gymproject.service;

import gymproject.exceptions.StaffNotFoundException;
import gymproject.models.Staff;
import gymproject.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public void cadastrarUsuario(Staff staffNovo) throws StaffNotFoundException {
        if (staffNovo.getLoginAcesso() == null || staffNovo.getLoginAcesso().isBlank()) {
            throw new StaffNotFoundException("O login é obrigatório.");
        }
        if (staffNovo.getSenhaAcesso() == null || staffNovo.getSenhaAcesso().isBlank()) {
            throw new StaffNotFoundException("A senha é obrigatória.");
        }

        verificarLogin(staffNovo.getLoginAcesso());
        staffRepository.cadastrarUsuario(staffNovo);
        System.out.println("Usuário cadastrado.");

    }

    private void verificarLogin(String loginAcesso) throws StaffNotFoundException {
        Optional<Staff> loginCadastrado = staffRepository.buscarLogin(loginAcesso);
        if (loginCadastrado.isPresent()) {
            throw new StaffNotFoundException("Já existe um usuário com o mesmo login cadastrado.");
        }
    }

}
