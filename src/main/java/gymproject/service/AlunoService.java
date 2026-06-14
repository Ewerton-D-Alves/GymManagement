package gymproject.service;

import gymproject.exceptions.AlunoNotFoundException;
import gymproject.exceptions.RecepcionistaNotFoundException;
import gymproject.models.Aluno;
import gymproject.models.Pessoa;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class AlunoService {
    private final PessoaRepository pessoaRepository;

    public void cadastrarAluno(Aluno alunoNovo) throws AlunoNotFoundException {
        if (alunoNovo.getCpf() == null || alunoNovo.getCpf().isBlank()) {
            throw new RecepcionistaNotFoundException("O CPF é obrigatório.");
        }
        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(alunoNovo.getCpf());
        if (PessoaPresente.isPresent()) {
            throw new AlunoNotFoundException("Já existe uma pessoa cadastrada com esse CPF.");
        }
        pessoaRepository.cadastrarAluno(alunoNovo);
        System.out.println("Aluno cadastrado com sucesso.");
    }

}
