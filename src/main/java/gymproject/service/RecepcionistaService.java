package gymproject.service;

import gymproject.exceptions.ProfessorNotFoundException;
import gymproject.exceptions.RecepcionistaNotFoundException;
import gymproject.models.Pessoa;
import gymproject.models.Professor;
import gymproject.models.Recepcionista;
import gymproject.repository.PessoaRepository;
import gymproject.repository.RecepcionistaRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class RecepcionistaService {
    private final PessoaRepository pessoaRepository;

    public void cadastrarRecepcionista(Recepcionista recepcionistaNovo) throws RecepcionistaNotFoundException {
        if (recepcionistaNovo.getCpf() == null || recepcionistaNovo.getCpf().isBlank()) {
            throw new RecepcionistaNotFoundException("O CPF é obrigatório.");
        }
        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(recepcionistaNovo.getCpf());
        if (PessoaPresente.isPresent()) {
            throw new RecepcionistaNotFoundException("Já existe uma pessoa cadastrada com esse CPF.");
        }
        pessoaRepository.cadastrarRecepcionista(recepcionistaNovo);
        System.out.println("Recepcionista cadastrado com sucesso.");
    }

}

