package gymproject.service;

import gymproject.exceptions.ProfessorNotFoundException;
import gymproject.models.Pessoa;
import gymproject.models.Professor;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;


import java.util.Optional;

@RequiredArgsConstructor
public class ProfessorService {
    private final PessoaRepository pessoaRepository;

    public void cadastrarProfessor(Professor professorNovo) throws ProfessorNotFoundException {
        if (professorNovo.getCpf() == null || professorNovo.getCpf().isBlank()) {
            throw new ProfessorNotFoundException("O CPF é obrigatório.");
        }
        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(professorNovo.getCpf());
        if (PessoaPresente.isPresent()) {
            throw new ProfessorNotFoundException("Já existe uma pessoa cadastrada com esse CPF.");
        }
            pessoaRepository.cadastrarProfessor(professorNovo);
            System.out.println("Professor cadastrado com sucesso.");
    }


}