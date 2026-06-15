package gymproject.service;

import gymproject.exceptions.PessoaException;
import gymproject.models.Pessoa;
import gymproject.models.Staff;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LoginService {
    private final PessoaRepository pessoaRepository;

    public void cadastrarAcesso (String cpf, String loginAcesso, String senhaAcesso) {

        //Aqui verifica se o usuario com o cpf de entrada existe.

        Optional<Pessoa> usuario = pessoaRepository.buscarCpf(cpf);
        if (usuario.isEmpty()) {
            throw new PessoaException("Nenhum funcionário cadastrado com esse CPF: " + cpf);
        }   if (loginAcesso == null || loginAcesso.isBlank()) {
            throw new PessoaException("O login é obrigatório.");
        }   if (senhaAcesso == null || senhaAcesso.isBlank()) {
            throw new PessoaException("A senha é obrigatória.");
        }   //verifica se o login ja existe para outro usuário;

        verificarLogin(loginAcesso);

        //Retira a pessoa do "Optional" para podermos manipular
        //E adicionando o login e a senha nova ao "existeSim" e mandando para o repositório.

        Pessoa existeSim = usuario.get();
        existeSim.setLoginAcesso(loginAcesso);
        existeSim.setSenhaAcesso(senhaAcesso);
        pessoaRepository.atualizarUsuario(existeSim);
    }
    //Metodo para verificar se o login ja existe.

    private void verificarLogin(String loginAcesso) throws PessoaException {
        Optional<Staff> loginCadastrado = staffRepository.buscarLogin(loginAcesso);
        if (loginCadastrado.isPresent()) {
            throw new PessoaException("Já existe um usuário com o mesmo login cadastrado.");
        }
    }
    //Metodo para verificar se um cadastro já existe atraves do CPF.

    public void verificarUsuario(String cpf) {
        Optional<Staff> staffCadastrado = staffRepository.buscarCpf(cpf);
        if (staffCadastrado.isPresent()) {
            throw new PessoaException("Já existe um usuário cadastrado.");
        }
    }

}
