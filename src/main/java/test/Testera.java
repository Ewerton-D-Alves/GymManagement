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
            //cadastra pessoa, pode ser puxado para quaisquer menus.
            pessoaService.cadastrarPessoa();
            System.out.println("Deseja cadastrar outra pessoa?");
            System.out.println("S - Sim\nX - Não");
            String finalizar = sc.nextLine().trim();
            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")){
             cadastroPessoa = false;
            }
        }

        while(cadastroStaff) {

            //cadastra o usuário da Staff
            pessoaService.cadastrarStaff();

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
