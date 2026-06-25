
import gymproject.service.UtilitarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UtilitarioService ut = new UtilitarioService();
        Scanner s = new Scanner(System.in);
        boolean ativo = true;
        // variaveis de autorização
        // Define quais menus são acessáveis
        boolean autenticadoAdm = false;
        boolean autenticadoSect = false;
        boolean autenticadoProf = false;
        // variaveis de menus
        boolean menuCentral = false;
        boolean cadAluno = false;
        boolean verAluno = false;
        boolean cadAula = false;
        boolean verAula = false;
        boolean cadProf = false;
        boolean verProf = false;
        boolean cadTreino = false;
        boolean verTreino = false;
        boolean cadSect = false;
        boolean verSect = false;
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
                        menuCentral = true;
                    case 2:
                        autenticadoSect = true;
                        menuCentral = true;
                    case 3:
                        autenticadoProf = true;
                        menuCentral = true;
                    default:
                        System.out.println("Login ou senha inválidos");
                }
            }
            // Admin reverterá para aqui até deslogar
            while (autenticadoAdm) {

                while (menuCentral) {

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
                        //Aluno cadastro
                        case 1:
                            cadAluno = true;
                            while (cadAluno) {
                                String cpf = null;
                                String pNome = null;
                                String mNome = null;
                                String sNome = null;
                                String telefone = null;
                                LocalDate dataNascimento = null;
                                String nomeEmerg = null;
                                String telefoneEmerg = null;
                                System.out.println("========== Cadastrar Aluno ===========\n");
                                int etapa = 1;
                                while (etapa == 1) {
                                    System.out.println("Digite o CPF do Aluno");
                                    input = s.nextLine().trim();
                                    // Nesse momento, o metodo deve receber esse dado e retornar se passou ou não
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("======== Cancelado ========");
                                        cadAluno = false;
                                        etapa = 0;
                                    } else {
                                        switch (ut.cpfValido(input)) {
                                            case 1 -> System.out.println("Campo Vazio, tente novamente");
                                            case 2 -> System.out.println("Escreva somente até 11 caracteres");
                                            case 3 -> {
                                                cpf = input;
                                                System.out.println("====== Ok =======" + cpf);
                                                etapa = 2;
                                            }
                                            case 4 -> System.out.println("Digite somente números");
                                            default -> System.out.println("não");

                                        }
                                    }
                                }
                                while (etapa == 2) {
                                    System.out.println("Digite o Nome do Aluno");
                                    input = s.nextLine().trim();
                                    // Nesse momento, o metodo deve receber esse dado e retornar se passou ou não
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("======== Cancelado ========");
                                        cadAluno = false;
                                        etapa = 0;

                                    } else {
                                        if (input.chars().allMatch(Character::isLetter)) {
                                            pNome = input;
                                            System.out.println("====== Ok =======" + pNome);
                                            etapa = 3;
                                        }
                                    }
                                }
                                while (etapa == 3) {
                                    System.out.println("Digite o Telefone do Aluno");
                                    input = s.nextLine().trim();
                                    // Nesse momento, o metodo deve receber esse dado e retornar se passou ou não
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("======== Cancelado ========");
                                        cadAluno = false;
                                        etapa = 0;
                                    } else {
                                        if (input.chars().allMatch(Character::isDigit)) {
                                            telefone = input;
                                            System.out.println("====== Ok =======" + telefone);
                                            etapa = 4;
                                        }
                                    }
                                }
                                while (etapa == 4) {
                                    System.out.println("Digite a Data de Nascimento do Aluno - Espaços entre dia mes ano");
                                    input = s.nextLine();
                                    // Nesse momento, o metodo deve receber esse dado e retornar se passou ou não
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("======== Cancelado ========");
                                        cadAluno = false;
                                        etapa = 0;
                                    } else {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
                                        LocalDate dNasc = LocalDate.parse(input, formatter);
                                        dataNascimento = dNasc;
                                        System.out.println("====== Ok =======" + dNasc);
                                        etapa = 5;
                                    }
                                }
                                while (etapa == 5) {
                                    System.out.println("Digite o Nome do Contato de emergência do Aluno");
                                    input = s.nextLine();
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("======== Cancelado ========");
                                        cadAluno = false;
                                        etapa = 0;

                                    } else {
                                        if (input.chars().allMatch(Character::isLetter)) {
                                            nomeEmerg = input;
                                            System.out.println("====== Ok =======" + nomeEmerg);
                                            etapa = 6;
                                        }
                                    }
                                }
                                while (etapa == 6) {
                                    System.out.println("Digite o Telefone do Aluno");
                                    input = s.nextLine().trim();
                                    // Nesse momento, o metodo deve receber esse dado e retornar se passou ou não
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("======== Cancelado ========");
                                        cadAluno = false;
                                        etapa = 0;
                                    } else {
                                        if (input.chars().allMatch(Character::isDigit)) {
                                            telefoneEmerg = input;
                                            System.out.println("====== Ok =======" + telefoneEmerg);
                                            etapa = 7;
                                        }
                                    }
                                }
                                // Teste besta
//                                while(etapa == 7){
//                                    UUID uuid = UUID.randomUUID();
//                                    Aluno aluno = new Aluno(pNome, mNome, sNome, cpf, telefone,
//                                            dataNascimento, nomeEmerg, telefoneEmerg, uuid);
//                                    System.out.println(aluno);
//                                    System.out.println("Cadastrado com sucesso");
//                                }
//
//                            }
                            }
                        case 2:
                            verAluno = true;
                            menuCentral = false;
                        case 3:
                            verAula = true;
                            menuCentral = false;
                        case 4:
                            cadAula = true;
                            menuCentral = false;
                        case 5:
                            cadProf = true;
                            menuCentral = false;
                        case 6:
                            verProf = true;
                            menuCentral = false;
                        case 7:
                            cadTreino = true;
                            menuCentral = false;
                        case 8:
                            verTreino = true;
                            menuCentral = false;
                        case 9:
                            cadSect = true;
                            menuCentral = false;
                        case 10:
                            verSect = true;
                            menuCentral = false;
                        default:
                            menuCentral = true;
                    }

                }

            }
        }
    }
}