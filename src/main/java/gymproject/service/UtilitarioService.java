package gymproject.service;

public class UtilitarioService {

    public int cpfValido(String cpf) {
        int resultado = 0;
        if (cpf == null || cpf.isEmpty()) {
            resultado = 1;
        }
        else if (!cpf.chars().allMatch(Character::isDigit)) {
             resultado = 4;
        }else if (cpf.chars().allMatch(Character::isDigit)) {
             if (cpf.length()==11){
                 resultado = 3;
            }else {
                 resultado = 2;
             }
        }

        return resultado;
    }
}
