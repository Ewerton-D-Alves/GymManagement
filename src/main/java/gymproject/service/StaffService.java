package gymproject.service;

import gymproject.exceptions.PessoaException;
import gymproject.models.*;
import gymproject.repository.LoginRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class StaffService {
    private final LoginRepository loginRepository;

    Scanner sc = new Scanner(System.in);

    //Metodo para cadastrar o usuario
    public void cadastrarUsuario(Staff staffNovo) throws PessoaException {
        if (staffNovo.getCpf() == null || staffNovo.getCpf().isBlank()) {
            throw new PessoaException("O CPF é obrigatório.");
        }
        //verificar aqui
        loginRepository.cadastrarUsuario(staffNovo);
        System.out.println("Usuário cadastrado.");
    }
    //Metodo para cadastrar o acesso

    public void cadastrarAcesso(String cpf, String loginAcesso, String senhaAcesso) {

        //Aqui verifica se o usuario com o cpf de entrada existe.

        Optional<Staff> usuario = loginRepository.buscarCpfStaff(cpf);
        if (usuario.isEmpty()) {
            throw new PessoaException("Nenhum funcionário cadastrado com esse CPF: " + cpf);
        }
        if (loginAcesso == null || loginAcesso.isBlank()) {
            throw new PessoaException("O login é obrigatório.");
        }
        if (senhaAcesso == null || senhaAcesso.isBlank()) {
            throw new PessoaException("A senha é obrigatória.");

        }   //verifica se o login ja existe para outro usuário;
        verificarLogin(loginAcesso);
        //Retira a pessoa do "Optional" para podermos manipular
        //E adicionando o login e a senha nova ao "existeSim" e mandando para o repositório.

        Staff existeSim = usuario.get();
        existeSim.setLoginAcesso(loginAcesso);
        existeSim.setSenhaAcesso(senhaAcesso);
        loginRepository.atualizarUsuario(existeSim);
    }
    //Metodo para verificar se o login ja existe.

    private void verificarLogin(String loginAcesso) throws PessoaException {
        Optional<Staff> loginCadastrado = loginRepository.buscarLogin(loginAcesso);
        if (loginCadastrado.isPresent()) {
            throw new PessoaException("Já existe um usuário com o mesmo login cadastrado.");
        }
    }

    public Staff verificarAcesso(String loginAcesso, String senhaAcesso) throws PessoaException {

        if (loginAcesso == null || loginAcesso.isBlank() || senhaAcesso == null || senhaAcesso.isBlank()) {
            throw new PessoaException("O login e senha são obrigatórios.");
        }
        Optional<Staff> usuario = loginRepository.buscarUsuario(loginAcesso, senhaAcesso);

        if (usuario == null || usuario.isEmpty() || usuario.get() == null) {
            throw new PessoaException("Login ou senha estão incorretos, ou funcionário não cadastrado.");
        }
        Staff usuarioExiste = usuario.get();
        System.out.println("Seja bem vindo, " + usuarioExiste.getPrimeiroNome());
            return usuarioExiste;

    }
}



