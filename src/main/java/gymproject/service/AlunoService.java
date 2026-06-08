package gymproject.service;

import gymproject.models.Aluno;
import gymproject.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor

public class AlunoService {
    private final AlunoRepository repository;

    public void cadastrar(Aluno alunoNovo) {

        if (alunoNovo.getCpf() == null || alunoNovo.getCpf().isBlank()) {
            throw new RuntimeException("O CPF é obrigatório.");
        }

        Optional<Aluno> alunoCadastrado = repository.buscarCpf(alunoNovo.getCpf());
        if (alunoCadastrado.isPresent()) {
        throw new RuntimeException("Já existe um aluno cadastrado.");
        }

        alunoNovo.setMatricula(UUID.randomUUID());
        repository.cadastrar(alunoNovo);
    }

}