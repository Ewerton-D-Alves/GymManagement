package gymproject.service;

import gymproject.exceptions.TreinoNotFoundException;
import gymproject.models.Treino;

public class TreinoService {


    public void cadastrarTreino(Treino treino) throws TreinoNotFoundException {
        if (treino == null) {
            throw new TreinoNotFoundException();
        }

    }

}
