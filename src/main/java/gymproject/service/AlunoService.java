package gymproject.service;

import gymproject.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class AlunoService {
    private final AlunoRepository repository;
}