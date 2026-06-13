package gymproject.service;

import gymproject.exceptions.RecepcionistaNotFoundException;
import gymproject.models.Recepcionista;
import gymproject.repository.RecepcionistaRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class RecepcionistaService {
    private final RecepcionistaRepository recepcionistaRepository;

    public void cadastrarRecepcionista(Recepcionista recepcionistaNova) throws RecepcionistaNotFoundException {
        if (recepcionistaNova.getCpf() == null || recepcionistaNova.getCpf().isBlank()) {
            throw new RecepcionistaNotFoundException("O CPF é obrigatório.");
        }
        verificarRecepcionista(recepcionistaNova.getCpf());
        recepcionistaRepository.cadastrar(recepcionistaNova);
        System.out.println("Recepcionista cadastrado.");
    }
    //Metodo para verificar recepcionista
    private void verificarRecepcionista(String cpf) throws RecepcionistaNotFoundException {
        Optional<Recepcionista> recepCadastrado = recepcionistaRepository.buscarCpf(cpf);
        if (recepCadastrado.isPresent()) {
            throw new RecepcionistaNotFoundException("Já existe um recepcionista cadastrado.");
        }
        System.out.println("Recepcionista não encontrado.");
    }
}
