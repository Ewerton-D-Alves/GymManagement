package test;

import gymproject.exceptions.PessoaException;
import gymproject.models.*;
import gymproject.repository.LoginRepository;
import gymproject.repository.PessoaRepository;
import gymproject.service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Testera {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean ativo = true;
        boolean autenticadoAdm = false;
        boolean autenticadoSect = false;
        boolean autenticadoProf = false;
        boolean cadastroboo = false;
        boolean cadastrologin = true;

        //Repositórios
        PessoaRepository pessoaRepository = new PessoaRepository() {
            @Override
            public Optional<Pessoa> buscarCpf(String cpf) {
                return Optional.empty();
            }

            @Override
            public void cadastrarAluno(Aluno aluno) {

            }

            @Override
            public List<Aluno> listarAlunos() {
                return List.of();
            }

            @Override
            public Optional<Aluno> buscarCpfAluno(String cpf) {
                return Optional.empty();
            }

            @Override
            public void atualizarAluno(Aluno aluno) {

            }

            @Override
            public void excluirAluno(Aluno aluno) {

            }

            @Override
            public void cadastrarGerente(Gerente gerente) {

            }

            @Override
            public List<Gerente> listarGerente() {
                return List.of();
            }

            @Override
            public void atualizarGerente(Gerente gerente) {

            }

            @Override
            public void excluirGerente(Gerente gerente) {

            }

            @Override
            public void cadastrarProfessor(Professor professor) {

            }

            @Override
            public List<Professor> listarProfessor() {
                return List.of();
            }

            @Override
            public Optional<Professor> buscarCpfprofesor(String cpf) {
                return Optional.empty();
            }

            @Override
            public void atualizarProfessor(Professor professor) {

            }

            @Override
            public void excluirProfessor(Professor professor) {

            }

            @Override
            public void cadastrarRecepcionista(Recepcionista recepcionista) {

            }

            @Override
            public List<Recepcionista> listarRecepcionista() {
                return List.of();
            }

            @Override
            public void atualizarRecepcionista(Recepcionista recepcionista) {

            }

            @Override
            public void excluirRecepcionista(Recepcionista recepcionista) {

            }

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
            public void alterarLogin(String loginAcesso) {

            }

            @Override
            public void alterarSenha(String senhaAcesso) {

            }

            @Override
            public void removerUsuario(String loginAcesso, String senhaAcesso) {
            }
        };
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
            public void alterarLogin(String loginAcesso) {

            }

            @Override
            public void alterarSenha(String senhaAcesso) {

            }

            @Override
            public void removerUsuario(String loginAcesso, String senhaAcesso) {

            }
        };

        //Serviços
        PessoaService pessoaService = new PessoaService(pessoaRepository);
        GerenteService gerenteService = new GerenteService(pessoaRepository);
        ProfessorService professorService = new ProfessorService(pessoaRepository);
        RecepcionistaService recepcionistaService = new RecepcionistaService(pessoaRepository);
        AlunoService alunoService = new AlunoService(pessoaRepository);
        StaffService staffService = new StaffService(loginRepository);

        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while(cadastroboo = true) {
            System.out.println("><>< Bem-vindo ao cadastro de funcionários. ><><");
            System.out.println("Digite o nome");
            String nome = sc.nextLine();
            System.out.println("Digite o sobrenome");
            String sobrenome = sc.nextLine();
            System.out.println("Digite o CPF");
            String cpf = sc.nextLine();
            System.out.println("Digite o telefone");
            String telefone = sc.nextLine();
            System.out.println("Digite a data de nascimento");
            String dataNascimento = sc.nextLine();
            LocalDate dataHora = LocalDate.parse(dataNascimento, formatar);
            System.out.println("Digite o telefone de emergencia");
            String telEmerg = sc.nextLine();
            System.out.println("Digite o nome do contato de emergência");
            String contatoEmerg = sc.nextLine();
            System.out.println("Digite a função: \n 1. Professor. \n 2. Recepcionista.");
            String funcao = sc.nextLine();
            String login = null;
            String senha = null;

            var gerente = new Gerente(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha);
            var recepcionista = new Recepcionista(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha);
            var professor = new Professor(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha);

            if (funcao.equalsIgnoreCase("Professor") || funcao.equalsIgnoreCase("1")) {
                try {
                    pessoaService.cadastrarProfessor(professor);
                } catch (PessoaException erro) {
                    System.out.println(erro.getMessage());
                }
            }
            if (funcao.equalsIgnoreCase("Recepcionista") || funcao.equalsIgnoreCase("2")) {
                try {
                    pessoaService.cadastrarRecepcionista(recepcionista);
                } catch (PessoaException erro) {
                    System.out.println(erro.getMessage());
                }
            }
            //PARA PODER CADASTRAR O LOGIN, SOLICITAR PARA REALIZAR O LOGIN//COLOCAR AQUI APÓS A CRIAÇÃO
            //DO LOGIN, AI AQUI VERIFICA O LOGIN E DEPOOOOIS PERMITE CADASTRAR O LOGIN DE GERENTE.
            //AI A VERIFICAÇÃO FICARÁ APENAS PARA UM OUTRO GERENTE E O ADMIN/ADMIN
            if (funcao.equals("Gerente")) {
                try {
                    pessoaService.cadastrarGerente(gerente);
                } catch (PessoaException erro) {
                    System.out.println(erro.getMessage());
                }
            }
            System.out.println("Deseja cadastrar outro usuário?");
            System.out.println("1. Sim \n2. Não.");
            String loopCadastro = sc.nextLine().trim();
            if (loopCadastro.equals("2") || loopCadastro.equalsIgnoreCase("Não")) {
                break;
            }
            cadastroboo = false;
        }
        while (cadastrologin) {
            System.out.println("><>< Bem-vindo ao cadastro de funcionários. ><><");
            System.out.print("Digite o seu CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Digite seu login: ");
            String login = sc.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = sc.nextLine();
            staffService.cadastrarAcesso(cpf, login, senha);
        }
        while (ativo) {
            int menuTipo = 0;
            int tipo = 0;
            while (!autenticadoAdm || !autenticadoProf || !autenticadoSect) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ====== \n" +
                        "Digite seu Login:");
                String login = sc.nextLine();
                System.out.println("Digite sua Senha;");
                String senha = sc.nextLine();
                staffService.verificarAcesso(login, senha);

                // NESSE LOCAL PRECISO DE MÉTODOS QUE RECEBAM E COMPAREM SE EXISTE PERFIL E SENHA
                // COMPATIVEIS COM DADOS EXISTENTES NO BANCO DE DADOS
                // dados de teste são: Adm letra X, Secretária letra Y, e Professor letra Z.
                if (login.equals("x") && senha.equals("x")) {

                    menuTipo = 1;
                } else if (login.equals("y") && senha.equals("y")) {
                    menuTipo = 2;
                } else if (login.equals("z") && senha.equals("z")) {
                    menuTipo = 3;
                }
                switch (menuTipo) {
                    case 1:
                        autenticadoAdm = true;
                    case 2:
                        autenticadoSect = true;
                    case 3:
                        autenticadoProf = true;
                    default:
                        System.out.println("Login ou senha inválidos");
                }
            }
            // Admin reverterá para aqui até deslogar
            while (autenticadoAdm) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ====== \n" +
                        "1 - Cadastrar Aluno \n" + "2 - Consultar aluno\n" + "3 - Consultar Aulas \n" +
                        "4 - Criar Aula \n" + "5 - Cadastrar Professor \n" + "6 - Consultar Professor \n" +
                        "7 - Cadastrar Treino \n" + "8 - Consultar Treino \n" +
                        "9 - Cadastrar Secretário \n" + "10 - Consultar Secretário \n" +
                        ">|< Para maior lucidez, não seja um usuário de atacadão >|< \n" +
                        "Pressione 'x' para sair \n");
                String input = sc.nextLine().trim();
                int tipoMenu = Integer.parseInt(input);
                if (input.equalsIgnoreCase("x")) {
                    autenticadoAdm = false;
                }
                switch (tipoMenu) {
                }
            }
        }

    }
}
