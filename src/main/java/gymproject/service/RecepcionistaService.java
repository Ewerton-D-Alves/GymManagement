package gymproject.service;

import gymproject.exceptions.PessoaException;
import gymproject.models.Pessoa;
import gymproject.models.Recepcionista;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class RecepcionistaService {
    private final PessoaRepository pessoaRepository;

    public void cadastrarRecepcionista(Recepcionista recepcionistaNovo) throws PessoaException {
        if (recepcionistaNovo.getCpf() == null || recepcionistaNovo.getCpf().isBlank()) {
            throw new PessoaException("O CPF é obrigatório.");
        }
        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(recepcionistaNovo.getCpf());
        if (PessoaPresente.isPresent()) {
            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
        }
        pessoaRepository.cadastrarRecepcionista(recepcionistaNovo);
        System.out.println("Recepcionista cadastrado com sucesso.");
    }

}

