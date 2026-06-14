package gymproject.service;

import gymproject.exceptions.ProfessorNotFoundException;
import gymproject.models.Professor;
import gymproject.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    //Metodo para cadastrar professor
    public void cadastrarProfessor(Professor professorNovo) throws ProfessorNotFoundException {
        if (professorNovo.getCpf() == null || professorNovo.getCpf().isBlank()) {
            throw new ProfessorNotFoundException("O CPF é obrigatório.");
        }
        verificarProfessor(professorNovo.getCpf());
        professorRepository.cadastrar(professorNovo);
        System.out.println("Professor cadastrado.");
    }
    //Metodo para verificar cadastro
    private void verificarProfessor(String cpf) {
        Optional<Professor> professorCadastrado = professorRepository.buscarCpf(cpf);
        if (professorCadastrado.isPresent()) {
            throw new ProfessorNotFoundException("Já existe um professor cadastrado.");
        }
    }
}