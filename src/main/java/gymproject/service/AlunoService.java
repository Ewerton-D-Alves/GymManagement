package gymproject.service;

import gymproject.exceptions.AlunoNotFoundException;
import gymproject.models.Aluno;
import gymproject.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor

public class AlunoService {
    private final AlunoRepository alunoRepository;

    public void cadastrar(Aluno alunoNovo) throws AlunoNotFoundException {

        if (alunoNovo.getCpf() == null || alunoNovo.getCpf().isBlank()) {
            throw new AlunoNotFoundException("O CPF é obrigatório.");
        }
        Optional<Aluno> alunoCadastrado = alunoRepository.buscarCpf(alunoNovo.getCpf());
        if (alunoCadastrado.isPresent()) {
        throw new AlunoNotFoundException("Já existe um aluno cadastrado.");
        }
        alunoNovo.setMatricula(UUID.randomUUID());
        alunoRepository.cadastrar(alunoNovo);
    }

}