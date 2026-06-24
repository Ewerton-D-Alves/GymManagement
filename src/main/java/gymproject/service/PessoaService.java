package gymproject.service;

import gymproject.exceptions.PessoaException;
import gymproject.models.*;
import gymproject.repository.LoginRepository;
import gymproject.repository.PessoaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;


    Scanner sc = new Scanner(System.in);
    LoginRepository loginRepository = new LoginRepository() {
        @Override
        public void cadastrarUsuario(Staff staff) {

        }

        @Override
        public void atualizarUsuario(Staff staff) {

        }

        @Override
        public Optional<Staff> buscarLogin(String loginAcesso) {
            return Optional.empty();
        }

        @Override
        public Optional<Staff> buscarSenha(String senhaAcesso) {
            return Optional.empty();
        }

        @Override
        public Optional<Staff> buscarCpfStaff(String cpf) {
            return Optional.empty();
        }

        @Override
        public Optional<Staff> buscarUsuario(String loginAcesso, String senhaAcesso) {
            return Optional.empty();
        }

        @Override
        public void alterarLogin(String loginAcesso, String cpf) {

        }

        @Override
        public void alterarSenha(String senhaAcesso, String cpf) {

        }

        @Override
        public void removerUsuario(String loginAcesso, String senhaAcesso) {

        }
    };
    StaffService staffService = new StaffService(loginRepository);

    //é o que vai mandar os dados para o banco
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
    EntityManager em = emf.createEntityManager();

    DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //Para verificar se o CPF ta prenchido corretamente, pois ele é obrigatório.

    //Para verificar se a pessoa existe no Banco de dados, ideal utilizar o VerificarCpfPessoa antes.
    public void verificarPessoa(String cpf) throws PessoaException {
        verificarCpfPessoa(cpf);
        Optional<Pessoa> pessoaPresente = pessoaRepository.buscarCpf(cpf);
        if (pessoaPresente.isPresent()) {
            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
        }
    }
    public void cadastrarAluno(Aluno aluno) throws PessoaException {
        verificarPessoa(aluno.getCpf());
        try {
            pessoaRepository.cadastrarAluno(aluno);
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (PessoaException erro) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public void cadastrarGerente(Gerente gerente) throws PessoaException {
        verificarPessoa(gerente.getCpf());
        pessoaRepository.cadastrarGerente(gerente);
        System.out.println("Gerente cadastrado com sucesso");
    }

    public void cadastrarProfessor(Professor professor) throws  PessoaException {
        verificarPessoa(professor.getCpf());
        pessoaRepository.cadastrarProfessor(professor);
        System.out.println("Professor cadastrado com sucesso");
    }

    public void cadastrarRecepcionista(Recepcionista recepcionista) throws PessoaException {
        verificarPessoa(recepcionista.getCpf());
        pessoaRepository.cadastrarRecepcionista(recepcionista);
        System.out.println("Recepcionista cadastrado com sucesso");
    }
    private void verificarCpfPessoa(String cpf) throws PessoaException {
        if (cpf == null || cpf.isBlank()) {
            throw new PessoaException("O CPF é obrigatório.");
        }
    }

    public void cadastrarPessoa() throws PessoaException {

        System.out.println("><>< Bem-vindo a área de cadastro ><><");
        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();
        verificarPessoa(cpf);
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite o sobrenome: ");
        String sobrenome = sc.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Digite a data de nascimento (dd/mm/aaaa): ");
        String dataNascimento = sc.nextLine();
        LocalDate dataHora = LocalDate.parse(dataNascimento, formatar);
        System.out.print("Digite o telefone de emergencia: ");
        String telEmerg = sc.nextLine();
        System.out.print("Digite o nome do contato de emergência: ");
        String contatoEmerg = sc.nextLine();
        String login = null;
        String senha = null;
        String matricula = UUID.randomUUID().toString();
        var gerente = new Gerente(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha);
        var recepcionista = new Recepcionista(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha);
        var professor = new Professor(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha);
        var aluno = new Aluno(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, matricula);
        System.out.println("Diga abaixo qual atividade o integrante faz parte:");
        System.out.println("Digite: \n 1. Aluno \n 2. Professor \n 3. Recepcionista \n 4. Gerente");
        String funcao = sc.nextLine().trim();

        if (funcao.equalsIgnoreCase("Aluno") || funcao.equalsIgnoreCase("1")) {
            cadastrarAluno(aluno);
        }
        if (funcao.equalsIgnoreCase("Professor") || funcao.equalsIgnoreCase("2")) {
            cadastrarProfessor(professor);
        }
        if (funcao.equalsIgnoreCase("Recepcionista") || funcao.equalsIgnoreCase("3")) {
            cadastrarRecepcionista(recepcionista);

        }
        if (funcao.equalsIgnoreCase("Gerente") || funcao.equalsIgnoreCase("4")) {
            System.out.print("Digite seu login: ");
            String loginGerente = sc.nextLine();
            System.out.print("Digite sua Senha: ");
            String senhaGerente = sc.nextLine();
            Staff gerenteExiste = staffService.verificarAcesso(loginGerente, senhaGerente);
            if (gerenteExiste instanceof Gerente) {
                 cadastrarGerente((Gerente) gerenteExiste);
              //staffService.cadastrarUsuario(gerenteExiste);
            }
        }
    }

    public void cadastrarStaff () throws PessoaException{
        System.out.println("><>< Bem-vindo ao cadastro de usuário ><><");
        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();
        verificarPessoa(cpf);
        System.out.print("Digite seu nome: ");
        String nomeInutil = sc.nextLine();
//            System.out.print("Digite seu CPF :");
//            String cpf = sc.nextLine();
        System.out.print("Digite o login: ");
        String login = sc.nextLine();
        System.out.print("Digite a senha: ");
        String senha = sc.nextLine();
        try {
            staffService.cadastrarAcesso(cpf, login, senha);
        } catch (PessoaException erro) {
            System.out.println(erro.getMessage());
        }
    }

}

//        ⠄⠄⠄⣠⢴⢴⡴⣤⢤⣄⠄⠄⢀⠄⣀⡤⣴⣺⡽⣯⡷⣦⣄⠄⠄⠄
//        ⠄⣔⢞⢝⢝⠽⡽⣽⣳⢿⡽⣏⣗⢗⢯⢯⣗⡯⡿⣽⢽⣷⣟⣷⣄⠄
//        ⠄⡗⡟⡼⣸⣁⢋⠎⠎⢯⢯⡧⡫⣎⡽⡹⠊⢍⠙⠜⠽⣳⢯⣿⣳⠄
//        ⠄⢕⠕⠁⣁⢬⢬⣌⠆⠅⢯⡻⣜⢷⠁⠌⡼⠲⠺⢮⡆⡉⢹⣺⣽⠄
//        ⠄⠄⡀⢐⠄⠄⠄⠈⠳⠁⡂⢟⣞⡏⠄⡹⠄⠄⠄⠄⠈⣺⡐⣞⣾⠄
//        ⠄⢰⡳⡹⢦⣀⣠⡠⠤⠄⡐⢝⣾⣳⣐⣌⠳⠦⠤⠤⣞⢼⢽⣻⡷⠄
//        ⠄⢸⣚⢆⢄⣈⠨⢊⢐⢌⠞⣞⣞⡗⡟⡾⣝⢦⣳⡳⣯⢿⣻⣽⣟⠄
//        ⠄⠘⡢⡫⢒⠒⣘⠰⣨⢴⣸⣺⣳⢥⢷⣳⣽⣳⢮⢝⢽⡯⣿⣺⡽⠄
//        ⠄⠄⠁⠪⠤⢑⢄⢽⡙⢽⣺⢾⢽⢯⡟⡽⣾⣎⡿⣮⡳⣹⣳⣗⠇⠄
//        ⠄⠄⠄⠁⠄⡸⡡⠑⠤⣠⡑⠙⠍⡩⡴⣽⡗⣗⣟⣷⣫⢳⢕⡏⠄⠄
//        ⠄⠄⠄⠄⢈⡇⡇⡆⡌⡀⡉⠫⡯⢯⡫⡷⣽⣺⣗⣟⡾⡼⡺⠄⠄⠄
//        ⠄⠄⠄⠄⡮⡎⡎⡎⣞⢲⡹⡵⡕⣇⡿⣽⣳⣟⣾⣳⡯⠉⠄⠄⠄⠄
//        ⠄⠄⠄⠄⢯⡣⡣⡣⡣⡣⣗⡽⣽⣳⢯⢷⣳⣻⣺⣗⡇⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠰⡙⠺⢪⢪⡺⡵⣯⣗⡯⡿⣽⢽⢾⣳⠏⠄⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠄⠐⠢⠄⣀⣀⢉⠊⣊⣉⡬⡶⡻⣝⡞⠄⠄⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⠙⢙⢑⢹⣘⠮⠛⠈⠄⠄⠄⠄⠄⠄⠄⠄
//        ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠂⠁⠑⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄