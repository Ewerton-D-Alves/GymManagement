package gymproject.repository;

import gymproject.models.*;
import jakarta.data.repository.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository {


    //Para buscar todas as pessoas \/

    @Find
    Optional<Pessoa> buscarCpf(String cpf);

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Aluno @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Inicio das opções de aluno;
    @Insert
    void cadastrarAluno(Aluno aluno);

    @Find
    List<Aluno> listarAlunos();

    @Find
    Optional<Aluno> buscarCpfAluno(String cpf);

    @Update
    void atualizarAluno(Aluno aluno);

    @Delete
    void excluirAluno(Aluno aluno);
    //Fim das opções de aluno
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Gerente @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Inicio das opções de gerente
    @Insert
    void cadastrarGerente(Gerente gerente);

    @Find
    List<Gerente> listarGerente();

//    @Find
//    Optional<Gerente> buscarCpfGerente(String cpf);

    @Update
    void atualizarGerente(Gerente gerente);

    @Delete
    void excluirGerente(Gerente gerente);
    //Fim das opções de gerente
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Professor @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Inicio das opções de professor
    @Insert
    void cadastrarProfessor(Professor professor);

    @Find
    List<Professor> listarProfessor();

    @Find
    Optional<Professor> buscarCpfprofesor(String cpf);

    @Update
    void atualizarProfessor(Professor professor);

    @Delete
    void excluirProfessor(Professor professor);
    //Fim das opções de professor
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Recepcionista @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Inicio das opções de recepcionista
    @Insert
    void cadastrarRecepcionista(Recepcionista recepcionista);

    @Find
    List<Recepcionista> listarRecepcionista();

//    @Find
//    Optional<Recepcionista> buscarCpfRecepcionista(String cpf);

    @Update
    void atualizarRecepcionista(Recepcionista recepcionista);

    @Delete
    void excluirRecepcionista(Recepcionista recepcionista);
    //Fim das opções de recepcionista
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Staff @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Inicio das opções de staff
    @Insert
    void cadastrarUsuario(Staff staff);

    @Update
    void atualizarUsuario(Staff staff);

    @Find
    Optional<Staff> buscarLogin(String loginAcesso);

    @Find
    Optional<Staff> buscarSenha(String senhaAcesso);

    @Find
    Optional<Staff> buscarCpfStaff(String cpf);

    @Update
    void alterarLogin(String loginAcesso);

    @Update
    void alterarSenha(String senhaAcesso);

    @Delete
    void removerUsuario(String loginAcesso,String senhaAcesso);
    //Fim das opções de staff
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
