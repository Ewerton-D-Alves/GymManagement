package test;

import gymproject.exceptions.ExercicioException;
import gymproject.exceptions.PessoaException;
import gymproject.models.*;
import gymproject.repository.*;
import gymproject.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Testera {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
        EntityManager em = ConnectJPA.getEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        StatelessSession session = sessionFactory.openStatelessSession();

        boolean ativo = true;
        boolean autenticadoAdm = false;
        boolean autenticadoSect = false;
        boolean autenticadoProf = false;
        boolean loop1 = true, loop2 = true, loop3 = true, loop4 = true, loop5 = true, loop6 = true;
        boolean loop7 = true, loop8 = true, loop9 = true, loop10 = true;
        boolean prof1 = true, prof2 = true, prof3 = true, prof4 = true, prof5 = true, prof6 = true;
        boolean recep1 = true, recep2 = true, recep3 = true, recep4 = true, recep5 = true, recep6 = true;
        boolean recep7 = true, recep8 = true, recep9 = true;
        boolean prelogin = true;

        //Repositórios

        PessoaRepository pessoaRepository = new PessoaRepository_(session);
        LoginRepository loginRepository = new LoginRepository_(session);
        ExercicioRepository exercicioRepository = new ExercicioRepository_(session);

        //Serviços
        PessoaService pessoaService = new PessoaService(pessoaRepository);
        StaffService staffService = new StaffService(loginRepository);
        ExercicioService exercicioService = new ExercicioService(exercicioRepository);

        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");



        while (ativo) {
            Staff usuarioExiste = null;
            int menuTipo = 0;
            int tipo = 0;
            while (prelogin) {
                System.out.println("Seja bem-vindo, o que quer para hoje?");
                System.out.println("1. Acessar o login.\n2. Sair");
                String oQueQuer = sc.nextLine();
                if (oQueQuer.equalsIgnoreCase("Acessar o login") || oQueQuer.equalsIgnoreCase("2")) {
                    prelogin = false;
                }
                break;
            }
            while (!autenticadoAdm || !autenticadoProf || !autenticadoSect) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ======");
                System.out.print("Digite seu Login: ");
                String login = sc.nextLine();
                System.out.print("Digite sua Senha: ");
                String senha = sc.nextLine();
                try {
                    usuarioExiste = staffService.verificarAcesso(login, senha);
                } catch (PessoaException erro) {
                    System.out.println(erro.getMessage());
                    System.out.println("Deseja tentar novamente?");
                    System.out.println("S - Sim\nX - Não");
                    String finalizar = sc.nextLine().trim();
                    if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                        break;
                    }
                }

                if (usuarioExiste instanceof Gerente) {
                    menuTipo = 1;
                }
                if (usuarioExiste instanceof Professor) {
                    menuTipo = 2;
                }
                if (usuarioExiste instanceof Recepcionista) {
                    menuTipo = 3;
                }
                switch (menuTipo) {
                    case 1:
                        autenticadoAdm = true;
                    case 2:
                        autenticadoProf = true;
                    case 3:
                        autenticadoSect = true;
                }
            }
            // Admin reverterá para aqui até deslogar
            while (autenticadoAdm) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ====== \n" +
                        "1 - Cadastrar Integrante (Aluno ou Staff). \n" + "2 - Consultar Integrante \n" + "3 - Consultar Aulas \n" +
                        "4 - Criar Aula \n" + "5 - Cadastrar Treino \n" + "6 - Consultar treino \n" +
                        "7 - Cancelar Aula \n" + "8 - Cancelar Treino \n" + "9 - Inativar Integrante \n" +
                        "10 - Cadastrar Acesso \n" +  ">|< Para maior lucidez, não seja um usuário de atacadão >|< \n" +
                        "Pressione 'x' para sair.");
                String input = sc.nextLine().trim();

                switch (input) {
                    case "1":
                        while (loop1) {
                            //cadastra pessoa, pode ser puxado para quaisquer menus.
                            try {
                                pessoaService.cadastrarPessoa();
                            } catch (PessoaException e) {
                                System.out.println("Já existe pessoa cadastrada com esse CPF, tente novamente.");
                                continue;
                            }
                            System.out.println("Deseja cadastrar outra pessoa?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")){
                                loop1 = false;
                            }
                        }
                        break;
                    case "2":
                        //Consultar integrante
                        while (loop2){
                            System.out.print("Digite o CPF: ");
                            String cpfExiste = sc.nextLine();
                            try {
                                pessoaService.procurarPessoa(cpfExiste);
                            } catch (PessoaException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja tentar novamente?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop2 = false;
                            }
                            break;
                        }

                    case "3":
                        //cadastrar aulas
                        while (loop3){
                            try {
                                exercicioService.cadastrarAula();
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cadastrar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop3 = false;
                            }
                        }
                        break;
                    case "4":
                        //Consultar aulas
                        while (loop4){
                            System.out.print("Diga o tipo de aula: ");
                            String tipoAula = sc.nextLine();
                            try {
                                exercicioService.verificarAula(tipoAula);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja consultar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop4 = false;
                            }
                            break;
                        }

                    case "5":
                        //Cadastrar treinos
                        while (loop5) {
                            try {
                                exercicioService.cadastrarTreino();
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cadastrar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop5 = false;
                            }
                            break;
                        }
                    case "6":
                        //Consultar treinos
                        while (loop6){
                            System.out.print("Diga o tipo de treino: ");
                            String tipoTreino = sc.nextLine();
                            try {
                                exercicioService.verificarTreino(tipoTreino);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja consultar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop6 = false;
                            }
                            break;
                        }
                    case "7":
                        //Cancelar aula
                        while (loop7) {
                            System.out.print("Adicione o identificador da aula");
                            String id = sc.nextLine();
                            try {
                                exercicioService.deletarAula(id);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cancelar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop7 = false;
                            }
                            break;
                        }

                    case "8":
                        //Cancelar treino
                        while (loop8) {
                            System.out.print("Adicione o identificador do treino");
                            String id = sc.nextLine();
                            try {
                                exercicioService.deletarTreino(id);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cancelar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop8 = false;
                            }
                            break;
                        }

                    case "9":
                        //Inativar integrante
                        while (loop9) {
                            System.out.print("Digite o CPF: ");
                            String cpfExiste = sc.nextLine();
                            try {
                                pessoaService.deletarPessoa(cpfExiste);
                            } catch (PessoaException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja tentar novamente?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                loop9 = false;
                            }
                            break;
                        }

                    case "10":
                        //Cadastrar acesso
                        while (loop9) {

                            //cadastra o usuário da Staff
                            pessoaService.cadastrarStaff();
                            System.out.println("Deseja cadastrar outro usuário?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")){
                                loop9 = false;
                            }
                        }
                        break;
                }
                int tipoMenu = Integer.parseInt(input);
                if (input.equalsIgnoreCase("x")) {
                    autenticadoAdm = false;
                }
                switch (tipoMenu) {
                }
            }
            while (autenticadoProf) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ====== \n" +
                        "1 - Criar Aula \n" + "2 - Consultar Aulas \n" + "3 - Cadastrar Treino \n" + "4 - Consultar treino \n" +
                        "5 - Cancelar Aula \n" + "6 - Cancelar Treino \n" +  ">|< Para maior lucidez, não seja um usuário de atacadão >|< \n" +
                        "Pressione 'x' para sair.");
                String input = sc.nextLine().trim();
                switch (input) {
                    case "1":
                        //cadastrar aulas
                        while (prof1){
                            try {
                                exercicioService.cadastrarAula();
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cadastrar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                prof1 = false;
                            }
                        }
                        break;
                    case "2":
                        //Consultar aulas
                        while (prof2){
                            System.out.print("Diga o tipo de aula: ");
                            String tipoAula = sc.nextLine();
                            try {
                                exercicioService.verificarAula(tipoAula);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja consultar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                prof2 = false;
                            }
                            break;
                        }

                    case "3":
                        //Cadastrar treinos
                        while (prof3) {
                            try {
                                exercicioService.cadastrarTreino();
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cadastrar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                prof3 = false;
                            }
                            break;
                        }
                    case "4":
                        //Consultar treinos
                        while (prof4){
                            System.out.print("Diga o tipo de treino: ");
                            String tipoTreino = sc.nextLine();
                            try {
                                exercicioService.verificarTreino(tipoTreino);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja consultar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                prof4 = false;
                            }
                            break;
                        }
                    case "5":
                        //Cancelar aula
                        while (prof5) {
                            System.out.print("Adicione o identificador da aula");
                            String id = sc.nextLine();
                            try {
                                exercicioService.deletarAula(id);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cancelar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                prof5 = false;
                            }
                            break;
                        }

                    case "6":
                        //Cancelar treino
                        while (prof6) {
                            System.out.print("Adicione o identificador do treino");
                            String id = sc.nextLine();
                            try {
                                exercicioService.deletarTreino(id);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cancelar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                prof6 = false;
                            }
                            break;
                        }
                }
                int tipoMenu = Integer.parseInt(input);
                if (input.equalsIgnoreCase("x")) {
                    autenticadoAdm = false;
                }
                switch (tipoMenu) {

                }
            }
            while (autenticadoSect) {
                System.out.println("======= Bem-vindo a academia entra forte e sai frango ====== \n" +
                        "1 - Cadastrar Integrante (Aluno ou Staff). \n" + "2 - Consultar Integrante \n" + "3 - Criar Aulas \n" +
                        "4 - Consultar Aula \n" + "5 - Consultar treino \n" +
                        "6 - Cancelar Aula \n" + "7 - Cancelar Treino \n" + "8 - Inativar Integrante \n" +
                        "9 - Cadastrar Acesso \n" +  ">|< Para maior lucidez, não seja um usuário de atacadão >|< \n" +
                        "Pressione 'x' para sair.");
                String input = sc.nextLine().trim();

                switch (input) {
                    case "1":
                        while (recep1) {
                            //cadastra pessoa, pode ser puxado para quaisquer menus.
                            try {
                                pessoaService.cadastrarPessoa();
                            } catch (PessoaException e) {
                                System.out.println("Já existe pessoa cadastrada com esse CPF, tente novamente.");
                                continue;
                            }
                            System.out.println("Deseja cadastrar outra pessoa?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")){
                                recep1 = false;
                            }
                        }
                        break;
                    case "2":
                        //Consultar integrante
                        while (recep2){
                            System.out.print("Digite o CPF: ");
                            String cpfExiste = sc.nextLine();
                            try {
                                pessoaService.procurarPessoa(cpfExiste);
                            } catch (PessoaException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja tentar novamente?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                recep2 = false;
                            }
                            break;
                        }

                    case "3":
                        //cadastrar aulas
                        while (recep3){
                            try {
                                exercicioService.cadastrarAula();
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cadastrar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                recep3 = false;
                            }
                        }
                        break;
                    case "4":
                        //Consultar aulas
                        while (recep4){
                            System.out.print("Diga o tipo de aula: ");
                            String tipoAula = sc.nextLine();
                            try {
                                exercicioService.verificarAula(tipoAula);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja consultar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                recep4 = false;
                            }
                            break;
                        }

                    case "5":
                        //Consultar treinos
                        while (recep5){
                            System.out.print("Diga o tipo de treino: ");
                            String tipoTreino = sc.nextLine();
                            try {
                                exercicioService.verificarTreino(tipoTreino);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja consultar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                recep5 = false;
                            }
                            break;
                        }
                    case "6":
                        //Cancelar aula
                        while (recep6) {
                            System.out.print("Adicione o identificador da aula");
                            String id = sc.nextLine();
                            try {
                                exercicioService.deletarAula(id);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cancelar outra aula?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                recep6 = false;
                            }
                            break;
                        }

                    case "7":
                        //Cancelar treino
                        while (recep7) {
                            System.out.print("Adicione o identificador do treino");
                            String id = sc.nextLine();
                            try {
                                exercicioService.deletarTreino(id);
                            } catch (ExercicioException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja cancelar outro treino?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                recep7 = false;
                            }
                            break;
                        }

                    case "8":
                        //Inativar integrante
                        while (recep8) {
                            System.out.print("Digite o CPF: ");
                            String cpfExiste = sc.nextLine();
                            try {
                                pessoaService.deletarPessoa(cpfExiste);
                            } catch (PessoaException e) {
                                System.out.println("Não foi ponsível continuar com essa ação.");
                                continue;
                            }
                            System.out.println("Deseja tentar novamente?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")) {
                                recep8 = false;
                            }
                            break;
                        }

                    case "9":
                        //Cadastrar acesso
                        while (recep9) {

                            //cadastra o usuário da Staff
                            pessoaService.cadastrarStaff();
                            System.out.println("Deseja cadastrar outro usuário?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")){
                                recep9 = false;
                            }
                        }
                        break;
                }
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
