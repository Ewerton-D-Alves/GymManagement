package gymproject.service;

import gymproject.exceptions.PessoaException;
import gymproject.models.*;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    //Para verificar se o CPF ta prenchido corretamente, pois ele é obrigatório.

    //Para verificar se a pessoa existe no Banco de dados, ideal utilizar o VerificarCpfPessoa antes.
    private void verificarPessoa(Pessoa pessoa) throws PessoaException {
        verificarCpfPessoa(pessoa);
        Optional<Pessoa> pessoaPresente = pessoaRepository.buscarCpf(pessoa.getCpf());
        if (pessoaPresente.isPresent()) {
            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
        }
    }
    public void cadastrarAluno(Aluno aluno) throws PessoaException {
        verificarPessoa(aluno);
        pessoaRepository.cadastrarAluno(aluno);
        System.out.println("Aluno cadastrado com sucesso.");
    }

    public void cadastrarGerente(Gerente gerente) throws PessoaException {
        verificarPessoa(gerente);
        pessoaRepository.cadastrarGerente(gerente);
        System.out.println("Gerente cadastrado com sucesso");
    }

    public void cadastrarProfessor(Professor professor) throws  PessoaException {
        verificarPessoa(professor);
        pessoaRepository.cadastrarProfessor(professor);
        System.out.println("Professor cadastrado com sucesso");
    }

    public void cadastrarRecepcionista(Recepcionista recepcionista) throws PessoaException {
        verificarPessoa(recepcionista);
        pessoaRepository.cadastrarRecepcionista(recepcionista);
        System.out.println("Recepcionista cadastrado com sucesso");
    }
    private void verificarCpfPessoa(Pessoa pessoa) throws PessoaException {
        if (pessoa.getCpf() == null || pessoa.getCpf().isBlank()) {
            throw new PessoaException("O CPF é obrigatório.");
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