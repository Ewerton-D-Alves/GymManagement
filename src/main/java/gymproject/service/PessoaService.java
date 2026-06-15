package gymproject.service;

import gymproject.exceptions.PessoaException;
import gymproject.models.*;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    private void verificarPessoa(Pessoa pessoa) throws PessoaException {
        if (pessoa.getCpf() == null || pessoa.getCpf().isBlank()) {
            throw new PessoaException("O CPF é obrigatório.");
        }
            Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(pessoa.getCpf());
            if (PessoaPresente.isPresent()) {
                throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
            }
        }

    public void cadastrarAluno(Aluno alunoNovo) throws PessoaException {
        verificarPessoa(alunoNovo);
        pessoaRepository.cadastrarAluno(alunoNovo);
        System.out.println("Aluno cadastrado com sucesso.");
    }

    public void cadastrarGerente(Gerente gerenteNovo) throws PessoaException {
        verificarPessoa(gerenteNovo);
        pessoaRepository.cadastrarGerente(gerenteNovo);
        System.out.println("Gerente cadastrado com sucesso");
    }

    public void cadastrarProfessor(Professor professorNovo) throws  PessoaException {
        verificarPessoa(professorNovo);
        pessoaRepository.cadastrarProfessor(professorNovo);
        System.out.println("Professor cadastrado com sucesso");
    }

    public void cadastrarRecepcionista(Recepcionista recepcionistaNovo) throws PessoaException {
        verificarPessoa(recepcionistaNovo);
        pessoaRepository.cadastrarRecepcionista(recepcionistaNovo);
        System.out.println("Recepcionista cadastrado com sucesso");
    }
//    public void cadastrarGerente(Gerente gerenteNovo) throws PessoaException {
//        if (gerenteNovo.getCpf() == null || gerenteNovo.getCpf().isBlank()) {
//            throw new PessoaException("O CPF é obrigatório.");
//        }
//        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(gerenteNovo.getCpf());
//        if (PessoaPresente.isPresent()) {
//            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
//        }
//        pessoaRepository.cadastrarGerente(gerenteNovo);
//        System.out.println("Gerente cadastrado com sucesso.");
//    }
//
//    public void cadastrarProfessor(Professor professorNovo) throws PessoaException {
//        if (professorNovo.getCpf() == null || professorNovo.getCpf().isBlank()) {
//            throw new PessoaException("O CPF é obrigatório.");
//        }
//        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(professorNovo.getCpf());
//        if (PessoaPresente.isPresent()) {
//            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
//        }
//        pessoaRepository.cadastrarProfessor(professorNovo);
//        System.out.println("Professor cadastrado com sucesso.");
//    }
//
//    public void cadastrarRecepcionista(Recepcionista recepcionistaNovo) throws PessoaException {
//        if (recepcionistaNovo.getCpf() == null || recepcionistaNovo.getCpf().isBlank()) {
//            throw new PessoaException("O CPF é obrigatório.");
//        }
//        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(recepcionistaNovo.getCpf());
//        if (PessoaPresente.isPresent()) {
//            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
//        }
//        pessoaRepository.cadastrarRecepcionista(recepcionistaNovo);
//        System.out.println("Recepcionista cadastrado com sucesso.");
//    }

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