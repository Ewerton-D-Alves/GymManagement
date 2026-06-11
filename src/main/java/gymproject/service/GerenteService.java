package gymproject.service;

import gymproject.exceptions.GerenteNotFoundException;

import gymproject.models.Gerente;
import gymproject.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class GerenteService {
    private final GerenteRepository gerenteRepository;

    public void cadastrarGerente(Gerente gerenteNovo) throws GerenteNotFoundException {
        if (gerenteNovo.getCpf() == null || gerenteNovo.getCpf().isBlank()) {
            throw new GerenteNotFoundException("O CPF é obrigatório.");
        }
        verificarGerente(gerenteNovo.getCpf());
        gerenteRepository.cadastrar(gerenteNovo);
        System.out.println("Gerente cadastrado.");
    }
    //Metodo para verificar recepcionista
    private void verificarGerente(String cpf) throws GerenteNotFoundException {
        Optional<Gerente> gerenteCadastrado = gerenteRepository.buscarCpf(cpf);
        if (gerenteCadastrado.isPresent()) {
            throw new GerenteNotFoundException("Já existe um gerente cadastrado.");
        }
        System.out.println("Gerente não encontrado.");
    }
}
