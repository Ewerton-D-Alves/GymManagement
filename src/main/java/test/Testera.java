package test;

import gymproject.exceptions.GerenteNotFoundException;
import gymproject.exceptions.ProfessorNotFoundException;
import gymproject.models.*;
import gymproject.repository.GerenteRepository;
import gymproject.repository.PessoaRepository;
import gymproject.repository.ProfessorRepository;
import gymproject.repository.RecepcionistaRepository;
import gymproject.service.AlunoService;
import gymproject.service.GerenteService;
import gymproject.service.ProfessorService;
import gymproject.service.RecepcionistaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Testera {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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
            public void alterarLogin(String loginAcesso) {

            }

            @Override
            public void alterarSenha(String senhaAcesso) {

            }

            @Override
            public void removerUsuario(String loginAcesso, String senhaAcesso) {

            }
        };
        GerenteService gerenteService = new GerenteService(pessoaRepository);
        ProfessorService professorService = new ProfessorService(pessoaRepository);
        RecepcionistaService recepcionistaService = new RecepcionistaService(pessoaRepository);
        AlunoService alunoService = new AlunoService(pessoaRepository);

        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.println("Digite seu nome");
            String nome = sc.nextLine();
            System.out.println("Digite seu sobrenome");
            String sobrenome = sc.nextLine();
            System.out.println("Digite seu CPF");
            String cpf = sc.nextLine();
            System.out.println("Digite seu telefone");
            String telefone = sc.nextLine();
            System.out.println("Digite sua data de nascimento");
            String dataNascimento = sc.nextLine();
            LocalDate dataHora = LocalDate.parse(dataNascimento, formatar);
            System.out.println("Digite seu telefone de emergencia");
            String telEmerg = sc.nextLine();
            System.out.println("Digite o nome do seu contáto de emergÊncia");
            String contatoEmerg = sc.nextLine();
            System.out.println("Digite a função: Professor ou Recepcionista.");
            String funcao = sc.nextLine();
            String login = null;
            String senha = null;

            //primeiroNome, sobrenome, cpf, telefone, dataNascimento, telefoneEmerg, nomeEmerg, loginAcesso, senhaAcesso, funcao
            Recepcionista recep1 = new Recepcionista(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha, funcao);
            Professor prof1 = new Professor(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha, funcao);
            Gerente gerente1 = new Gerente(nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha, funcao);

            if (funcao.equals("Professor")) {
                try {
                    professorService.cadastrarProfessor(prof1);
                } catch (ProfessorNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } if (funcao.equals("Recepcionista")) {
                try {
                    recepcionistaService.cadastrarRecepcionista(recep1);
                } catch (GerenteNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } if (funcao.equals("Gerente")) {
                try {
                    gerenteService.cadastrarGerente(gerente1);
                } catch (GerenteNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
