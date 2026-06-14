package test;

import gymproject.exceptions.GerenteNotFoundException;
import gymproject.exceptions.ProfessorNotFoundException;
import gymproject.models.Gerente;
import gymproject.models.Professor;
import gymproject.models.Recepcionista;
import gymproject.repository.GerenteRepository;
import gymproject.repository.ProfessorRepository;
import gymproject.repository.RecepcionistaRepository;
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

        GerenteRepository gerenteRepository = new GerenteRepository() {
            @Override
            public void cadastrar(Gerente gerente) {
            }
            @Override
            public List<Gerente> listarGerente() {
                return List.of();
            }
            @Override
            public Optional<Gerente> buscarCpf(String cpf) {
                return Optional.empty();
            }
            @Override
            public void atualizar(Gerente gerente) {
            }
            @Override
            public void excluir(Gerente gerente) {
            }
        };
        GerenteService gerenteService = new GerenteService(gerenteRepository);
        ProfessorRepository professorRepository = new ProfessorRepository() {
            @Override
            public void cadastrar(Professor professor) {

            }

            @Override
            public List<Professor> listarProfessor() {
                return List.of();
            }

            @Override
            public Optional<Professor> buscarCpf(String cpf) {
                return Optional.empty();
            }

            @Override
            public void atualizar(Professor professor) {

            }

            @Override
            public void excluir(Professor professor) {

            }
        };
        ProfessorService professorService = new ProfessorService(professorRepository);
        RecepcionistaRepository recepcionistaRepository = new RecepcionistaRepository() {
            @Override
            public void cadastrar(Recepcionista recepcionista) {

            }

            @Override
            public List<Recepcionista> listarRecepcionista() {
                return List.of();
            }

            @Override
            public Optional<Recepcionista> buscarCpf(String cpf) {
                return Optional.empty();
            }

            @Override
            public void atualizar(Recepcionista recepcionista) {

            }

            @Override
            public void excluir(Recepcionista recepcionista) {

            }
        };
        RecepcionistaService recepcionistaService = new RecepcionistaService(recepcionistaRepository);

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
