package gymproject.exceptions;

public class TreinoNotFoundException extends RuntimeException {
    public TreinoNotFoundException() {
        super("O treino ainda não foi criado!");
    }
}
