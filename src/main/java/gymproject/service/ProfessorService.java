package gymproject.service;

import gymproject.exceptions.ProfessorNotFoundException;
import gymproject.models.Professor;
import gymproject.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public void cadastrar(Professor professorNovo) throws ProfessorNotFoundException {

        if (professorNovo.getCpf() == null || professorNovo.getCpf().isBlank()) {
            throw new ProfessorNotFoundException("O CPF é obrigatório.");
        }
        Optional<Professor> profCadastrado = professorRepository.buscarCpf(professorNovo.getCpf());
        if (profCadastrado.isPresent()) {
            throw new ProfessorNotFoundException("Já existe um aluno cadastrado.");
        }
        professorRepository.cadastrar(professorNovo);
    }

}
