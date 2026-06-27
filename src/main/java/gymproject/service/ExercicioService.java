package gymproject.service;

import gymproject.exceptions.ExercicioException;
import gymproject.models.*;
import gymproject.repository.ExercicioRepository;
import gymproject.repository.PessoaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;

    Scanner sc = new Scanner(System.in);
    PessoaRepository pessoaRepository = new PessoaRepository(){
        @Override
        public Optional<Pessoa> buscarCpf(String cpf) {
            return Optional.empty();
        }

        @Override
        public void deletarPessoa(String cpf) {

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
        public void alterarLoginESenha(String login, String senha, String cpf) {

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
    PessoaService pessoaService = new PessoaService(pessoaRepository);
    //é o que vai mandar os dados para o banco
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
    EntityManager em = emf.createEntityManager();

    DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void verificarAula(String tipo) throws ExercicioException {

        Optional<Aula> pegarId = exercicioRepository.buscarAula(tipo);
        try {
            if (pegarId.isPresent()){
                System.out.println("Aqui está a aula cadastrada: " + tipo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel encontrar essa aula.");
        }
    }

    public void cadastrarAula() throws ExercicioException {

        System.out.print("Qual o tipo de aula que deseja cadastrar?");
        String aulaTipo = sc.nextLine();
        System.out.print("Qual dia da aula que deseja cadastrar? (dd/mm/aaaa)");
        String dataTreino = sc.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataTreino, formatar);
        System.out.print("Digite o cpf do responsável.");
        String cpf = sc.nextLine();
        Professor professorResponsavel = pessoaRepository.buscarCpfprofesor(cpf)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        var aula1 = new Aula(aulaTipo, dataHora, professorResponsavel);
        try {
            exercicioRepository.cadastrarAula(aula1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizarAula(Aula aula) throws ExercicioException {

        Optional<Aula> aulaExiste = exercicioRepository.buscarAula(aula.getTipo());
        if (aulaExiste.isEmpty()){
            throw new ExercicioException("Aula não encontrada.");
        }
        try {
            exercicioRepository.atualizarAula(aula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deletarAula(String id) throws ExercicioException {

        Optional<Aula> aulaExiste = exercicioRepository.buscarAula(id);
        if (aulaExiste.isEmpty()) {
            throw new ExercicioException("Aula não encontrada.");
        }
        try {
            exercicioRepository.deletarAula(aulaExiste.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public void verificarTreino(String tipo) throws ExercicioException {

        Optional<Treino> pegarId = exercicioRepository.buscarTreino(tipo);
        try {
            if (pegarId.isPresent()){
                System.out.println("Aqui está o treino cadastrado: " + tipo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel encontrar esse treino.");
        }
    }

    public void cadastrarTreino() throws ExercicioException {

        System.out.print("Qual o tipo de treino que deseja cadastrar? ");
        String aulaTipo = sc.nextLine();
        System.out.print("Quantas séries para esse exercicio? ");
        String exercicio = sc.nextLine();
        System.out.print("Digite o cpf do responsável.");
        String cpf = sc.nextLine();
        Professor professorResponsavel = pessoaRepository.buscarCpfprofesor(cpf)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        var treino1 = new Treino(aulaTipo, exercicio, professorResponsavel);
        try {
            exercicioRepository.cadastrarTreino(treino1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizarTreino(Treino treino) throws ExercicioException {

        Optional<Treino> aulaExiste = exercicioRepository.buscarTreino(treino.getTipo());
        if (aulaExiste.isEmpty()){
            throw new ExercicioException("Treino não encontrado.");
        }
        try {
            exercicioRepository.atualizarTreino(treino);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarTreino(String id) throws ExercicioException {

        Optional<Treino> aulaExiste = exercicioRepository.buscarTreino(id);
        if (aulaExiste.isEmpty()){
            throw new ExercicioException("Treino não encontrado.");
        }
        try {
            exercicioRepository.deletarTreino(aulaExiste.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
