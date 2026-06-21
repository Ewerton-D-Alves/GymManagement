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
import java.util.UUID;

public class Testera {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean ativo = true;
        boolean autenticadoAdm = false;
        boolean autenticadoSect = false;
        boolean autenticadoProf = false;
        boolean cadastroPessoa = true;
        boolean cadastroStaff = true;
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
        while (cadastroPessoa) {
            System.out.println("><>< Bem-vindo a área de cadastro ><><");
            System.out.println("Digite o CPF");
            String cpf = sc.nextLine();
            pessoaService.verificarPessoa(cpf);
            System.out.println("Digite o nome");
            String nome = sc.nextLine();
            System.out.println("Digite o sobrenome");
            String sobrenome = sc.nextLine();
            System.out.println("Digite o telefone");
            String telefone = sc.nextLine();
            System.out.println("Digite a data de nascimento");
            String dataNascimento = sc.nextLine();
            LocalDate dataHora = LocalDate.parse(dataNascimento, formatar);
            System.out.println("Digite o telefone de emergencia");
            String telEmerg = sc.nextLine();
            System.out.println("Digite o nome do contato de emergência");
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
                try {
                    pessoaService.cadastrarAluno(aluno);
                } catch (PessoaException erro) {
                    System.out.println(erro.getMessage());
                }
            } if (funcao.equalsIgnoreCase("Professor") || funcao.equalsIgnoreCase("2")) {
                try {
                    pessoaService.cadastrarProfessor(professor);
                } catch (PessoaException erro) {
                    System.out.println(erro.getMessage());
                }
            } if (funcao.equalsIgnoreCase("Recepcionista") || funcao.equalsIgnoreCase("3")) {
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
                System.out.print("Digite seu login: ");
                String loginGerente = sc.nextLine();
                System.out.print("Digite sua Senha: ");
                String senhaGerente = sc.nextLine();
                Staff gerenteExiste = staffService.verificarAcesso(loginGerente, senhaGerente);
                if (gerenteExiste instanceof Gerente) {
                    try {
                        pessoaService.cadastrarGerente(gerente);
                    } catch (PessoaException erro) {
                        System.out.println(erro.getMessage());
                    }
                }
            }
        }

        while(cadastroStaff) {

            System.out.println("><>< Bem-vindo ao cadastro de usuário ><><");
            System.out.print("Digite seu nome: ");
            String nomeInutil = sc.nextLine();
            System.out.print("Digite seu CPF :");
            String cpf = sc.nextLine();
            System.out.print("Digite o login: ");
            String login = sc.nextLine();
            System.out.print("Digite a senha: ");
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
                Staff usuarioExiste = staffService.verificarAcesso(login, senha);
                if (usuarioExiste instanceof Gerente) {
                    menuTipo = 1;
                }
                if (usuarioExiste instanceof Professor) {
                    menuTipo = 2;
                }
                if (usuarioExiste instanceof Recepcionista) {
                    menuTipo = 3;
                }
                switch (menuTipo) {
                    case 1:
                        autenticadoAdm = true;
                    case 2:
                        autenticadoProf = true;
                    case 3:
                        autenticadoSect = true;
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
