
import gymproject.models.Aluno;
import gymproject.models.Professor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        boolean ativo = true;
        boolean autenticadoAdm = false;
        boolean autenticadoSect = false;
        boolean autenticadoProf = false;
        while (ativo) {
            int menuTipo = 0;
            int tipo = 0;
            while (!autenticadoAdm || !autenticadoProf || !autenticadoSect) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ====== \n" +
                        "Digite seu Login:");
                String login = s.nextLine();
                System.out.println("Digite sua Senha;");
                String senha = s.nextLine();
                // NESSE LOCAL PRECISO DE MÉTODOS QUE RECEBAM E COMPAREM SE EXISTE PERFIL E SENHA
                // COMPATIVEIS COM DADOS EXISTENTES NO BANCO DE DADOS
                // dados de teste são: Adm letra X, Secretária letra Y, e Professor letra Z.
                if (((login.equals("x") && senha.equals("x")))) {
                    menuTipo = 1;
                } else if (((login.equals("y") && senha.equals("y")))) {
                    menuTipo = 2;
                } else if (((login.equals("z") && senha.equals("z")))) {
                    menuTipo = 3;
                }
                switch (menuTipo) {
                    case 1:
                        autenticadoAdm = true;
                    case 2:
                        autenticadoSect = true;
                    case 3:
                        autenticadoProf = true;
                    default:
                        System.out.println("Login ou senha inválidos");
                }
            }
            // Admin reverterá para aqui até deslogar
            while (autenticadoAdm) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ====== \n" +
                        "1 - Cadastrar Aluno \n" + "2 - Consultar aluno\n" + "3 - Consultar Aulas \n" +
                        "4 - Criar Aula \n" + "5 - Cadastrar Professor \n" + "6 - Consultar Professor \n" +
                        "7 - Cadastrar Treino \n" + "8 - Consultar Treino \n" +
                        "9 - Cadastrar Secretário \n" + "10 - Consultar Secretário \n" +
                        ">|< Para maior lucidez, não seja um usuário de atacadão >|< \n" +
                        "Pressione 'x' para sair \n");
                String input = s.nextLine().trim();
                int tipoMenu = Integer.parseInt(input);
                if (input.equalsIgnoreCase("x")) {
                    autenticadoAdm = false;
                }
                switch (tipoMenu) {
                }
            }
        }
    }
}