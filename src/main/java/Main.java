
import gymproject.models.Aluno;
import gymproject.models.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Aluno> aluno1 = new ArrayList<>();
        List<Professor> professor1 = new ArrayList<>();

        System.out.println("======= Bem-vindo a academia entra forte e sai frango ======");
        System.out.println(" ");
        System.out.println(">|< Para maior lucidez, não seja um usuário de atacadão >|<");


        System.out.println("Digite X");
        var valorEntrada = sc.next();
        while(!valorEntrada.equalsIgnoreCase("x")) {

            if (valorEntrada.equalsIgnoreCase("x")) {
            }
            valorEntrada = sc.next();

        }
        System.out.println("É x porra");
}
}
