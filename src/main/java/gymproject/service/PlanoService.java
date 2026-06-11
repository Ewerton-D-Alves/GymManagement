package gymproject.service;

import gymproject.exceptions.PlanoNotFoundException;
import gymproject.models.Plano;
import gymproject.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;

    private void verificarPlano(Plano planoNovo) throws PlanoNotFoundException {
        Optional<Plano> planoCadastrado = planoRepository.buscarPlano(planoNovo.getCodPlano());
        if (planoCadastrado.isPresent()) {
            throw new PlanoNotFoundException("Já existe esse plano cadastrado.");
        }
        System.out.println("Plano não encontrado.");


    }
}
