package gymproject.service;

import gymproject.exceptions.PessoaException;
import gymproject.models.Gerente;
import gymproject.models.Pessoa;
import gymproject.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class GerenteService {
    private final PessoaRepository pessoaRepository;

}

