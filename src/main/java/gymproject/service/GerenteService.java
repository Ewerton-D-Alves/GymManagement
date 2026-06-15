package gymproject.service;

import gymproject.models.Gerente;
import gymproject.models.Pessoa;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class GerenteService {
    private final PessoaRepository pessoaRepository;

    public void cadastrarGerente(Gerente gerenteNovo) throws GerenteNotFoundException {
        if (gerenteNovo.getCpf() == null || gerenteNovo.getCpf().isBlank()) {
            throw new GerenteNotFoundException("O CPF é obrigatório.");
        }
        Optional<Pessoa> PessoaPresente = pessoaRepository.buscarCpf(gerenteNovo.getCpf());
        if (PessoaPresente.isPresent()) {
            throw new GerenteNotFoundException("Já existe uma pessoa cadastrada com esse CPF.");
        }
        pessoaRepository.cadastrarGerente(gerenteNovo);
        System.out.println("Gerente cadastrado com sucesso.");
    }

}

