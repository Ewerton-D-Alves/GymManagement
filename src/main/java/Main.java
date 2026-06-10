
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
        var menu = 0;
        while(!valorEntrada.equalsIgnoreCase("x")) {

            if (valorEntrada.equalsIgnoreCase("x")) {

                var option = -1;
                switch (menu){

                    case 1:
                        if (option < 0) {
                            System.out.println("Nome do aluno: ");
                            System.out.println("CPF do aluno: ");
                            System.out.println("Data de nascimento do aluno: ");

                        }

                }

            }
            valorEntrada = sc.next();


        }
        System.out.println("É x porra");
}
}
