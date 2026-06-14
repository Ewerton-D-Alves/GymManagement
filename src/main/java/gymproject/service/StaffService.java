package gymproject.service;

import gymproject.exceptions.ProfessorNotFoundException;
import gymproject.exceptions.StaffNotFoundException;
import gymproject.models.Professor;
import gymproject.models.Staff;
import gymproject.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    //Metodo para cadastrar o usuario
    public void cadastrarUsuario(Staff staffNovo) throws StaffNotFoundException {
        if (staffNovo.getCpf() == null || staffNovo.getCpf().isBlank()) {
            throw new StaffNotFoundException("O CPF é obrigatório.");
    }
        verificarUsuario(staffNovo.getCpf());
        staffRepository.cadastrarUsuario(staffNovo);
        System.out.println("Usuário cadastrado.");
    }
    //Metodo para cadastrar o acesso

    public void cadastrarAcesso (String cpf, String loginAcesso, String senhaAcesso) {

        //Aqui verifica se o usuario com o cpf de entrada existe.

        Optional<Staff> usuario = staffRepository.buscarCpf(cpf);
            if (usuario.isEmpty()) {
            throw new StaffNotFoundException("Nenhum funcionário cadastrado com esse CPF: " + cpf);
        }   if (loginAcesso == null || loginAcesso.isBlank()) {
            throw new StaffNotFoundException("O login é obrigatório.");
        }   if (senhaAcesso == null || senhaAcesso.isBlank()) {
            throw new StaffNotFoundException("A senha é obrigatória.");

        }   //verifica se o login ja existe para outro usuário;

            verificarLogin(loginAcesso);

            //Retira a pessoa do "Optional" para podermos manipular
            //E adicionando o login e a senha nova ao "existeSim" e mandando para o repositório.

            Staff existeSim = usuario.get();
            existeSim.setLoginAcesso(loginAcesso);
            existeSim.setSenhaAcesso(senhaAcesso);
            staffRepository.atualizarUsuario(existeSim);
        }
        //Metodo para verificar se o login ja existe.

    private void verificarLogin(String loginAcesso) throws StaffNotFoundException {
        Optional<Staff> loginCadastrado = staffRepository.buscarLogin(loginAcesso);
        if (loginCadastrado.isPresent()) {
            throw new StaffNotFoundException("Já existe um usuário com o mesmo login cadastrado.");
        }
    }
        //Metodo para verificar se um cadastro já existe atraves do CPF.

    public void verificarUsuario(String cpf) {
        Optional<Staff> staffCadastrado = staffRepository.buscarCpf(cpf);
        if (staffCadastrado.isPresent()) {
            throw new ProfessorNotFoundException("Já existe um usuário cadastrado.");
        }
    }

}

