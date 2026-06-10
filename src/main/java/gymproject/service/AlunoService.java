package gymproject.service;

import gymproject.exceptions.AlunoNotFoundException;
import gymproject.models.Aluno;
import gymproject.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    //Metodo para cadastrar aluno
    public void cadastrarAluno(Aluno alunoNovo) throws AlunoNotFoundException {
        if (alunoNovo.getCpf() == null || alunoNovo.getCpf().isBlank()) {
            throw new AlunoNotFoundException("O CPF é obrigatório.");
        }
        verificarAluno(alunoNovo.getCpf());
        alunoRepository.cadastrar(alunoNovo);
        System.out.println("aluno cadastrado.");
    }
    //Metodo para verificar aluno
    private void verificarAluno(String cpf) throws AlunoNotFoundException {
        Optional<Aluno> alunoCadastrado = alunoRepository.buscarCpf(cpf);
        if (alunoCadastrado.isPresent()) {
            throw new AlunoNotFoundException("Já existe um aluno cadastrado.");
        }
    }


}