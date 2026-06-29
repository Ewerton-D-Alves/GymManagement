
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

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
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
        boolean cadPessoa, verPessoa, cadAula, verAula, cadTreino, verTreino;
        boolean delAula, delTreino, delPessoa, cadAcesso;
        int tipoMenu;
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
            int acesso = 0;
            while (prelogin) {
                System.out.println("Seja bem-vindo, o que quer para hoje?");
                System.out.println("1. Acessar o login.\n2. Sair");
                String oQueQuer = sc.nextLine();
                if (oQueQuer.equalsIgnoreCase("Acessar o login") || oQueQuer.equalsIgnoreCase("1")) {
                    prelogin = false;
                } else if (oQueQuer.equalsIgnoreCase("Sair") || oQueQuer.equalsIgnoreCase("2")) {
                    prelogin = false;
                    ativo = false;
                }
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
                    //acesso = 1;
                    autenticadoAdm = true;
                    break;
                }
                if (usuarioExiste instanceof Professor) {
                    //acesso = 2;
                    autenticadoProf = true;
                    break;
                }
                if (usuarioExiste instanceof Recepcionista) {
                    //acesso = 3;
                    autenticadoSect = true;
                    break;
                }

//                if (acesso == 1) {
//                    autenticadoAdm = true;
//                } else if (acesso == 2) {
//                    autenticadoProf = true;
//                } else if (acesso == 3) {
//                    autenticadoSect = true;
//                }
//                switch (acesso) {
//                    case 1:
//                        autenticadoAdm = true;
//                    case 2:
//                        autenticadoProf = true;
//                    case 3:
//                        autenticadoSect = true;
//                }
            }
            // Admin reverterá para aqui até desconectar
            while (autenticadoAdm) {
                System.out.println("""
                        ======= Bem-vindo a academia entra forte e sai frango ======\s
                        1 - Cadastrar Integrante (Aluno ou Staff).\s
                        2 - Consultar Integrante\s
                        3 - Criar Aulas\s
                        4 - Consultar Aula\s
                        5 - Cadastrar Treino\s
                        6 - Consultar treino\s
                        7 - Cancelar Aula\s
                        8 - Cancelar Treino\s
                        9 - Inativar Integrante\s
                        10 - Cadastrar Acesso\s
                        >|< Para maior lucidez, não seja um usuário de atacadão >|<\s
                        Pressione 'x' para sair.""");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("x")) {
                    autenticadoAdm = false;
                }
                if (input.equalsIgnoreCase("") || !input.matches("\\d+") || input == null){
                    tipoMenu = 99;
                }else{
                    tipoMenu = Integer.parseInt(input);}
                switch (tipoMenu) {
                    case 1:
                        cadPessoa = true;
                        while (cadPessoa) {
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
                                cadPessoa= false;
                            }
                        }
                        break;
                    case 2:
                        //Consultar integrante
                        verPessoa = true;
                        while (verPessoa){
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
                                verPessoa = false;
                            }
                        }
                        break;

                    case 3:
                        //cadastrar aulas
                        cadAula = true;
                        while (cadAula){
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
                                cadAula = false;
                            }
                        }
                        break;
                    case 4:
                        //Consultar aulas
                        verAula = true;
                        while (verAula){
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
                                verAula = false;
                            }
                        }
                        break;

                    case 5:
                        //Cadastrar treinos
                        cadTreino = true;
                        while (cadTreino) {
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
                                cadTreino = false;
                            }
                        }
                        break;
                    case 6:
                        //Consultar treinos
                        verTreino = true;
                        while (verTreino){
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
                                verTreino = false;
                            }
                        }
                        break;
                    case 7:
                        //Cancelar aula
                        delAula = true;
                        while (delAula) {
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
                                delAula = false;
                            }
                        }
                        break;

                    case 8:
                        //Cancelar treino
                        delTreino = true;
                        while (delTreino) {
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
                                delTreino = false;
                            }
                        }
                        break;

                    case 9:
                        //Inativar integrante
                        delPessoa = true;
                        while (delPessoa) {
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
                                delPessoa = false;
                            }
                        }
                        break;

                    case 10:
                        //Cadastrar acesso
                        cadAcesso = true;
                        while (cadAcesso) {

                            //cadastra o usuário da Staff
                            pessoaService.cadastrarStaff();
                            System.out.println("Deseja cadastrar outro usuário?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")){
                                cadAcesso = false;
                            }
                        }break;
                    default:
                        break;
                }
            }
            while (autenticadoProf) {
                System.out.println("""
                        ======= Bem-vindo a academia entra forte e sai frango ======\s
                        1 - Consultar Integrante\s
                        2 - Criar Aulas\s
                        3 - Consultar Aula\s
                        4 - Cadastrar Treino\s
                        5 - Consultar treino\s
                        6 - Cancelar Aula\s
                        7 - Cancelar Treino\s
                        >|< Para maior lucidez, não seja um usuário de atacadão >|<\s
                        Pressione 'x' para sair.""");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("x")) {
                    autenticadoProf = false;
                }
                if (input.equalsIgnoreCase(" ") || input.matches("\\d+") || input == null){
                    tipoMenu = 99;
                }else{
                    tipoMenu = Integer.parseInt(input);}
                switch (tipoMenu) {

                    case 1:
                        //Consultar integrante
                        verPessoa = true;
                        while (verPessoa){
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
                                verPessoa = false;
                            }
                        }
                        break;

                    case 2:
                        //cadastrar aulas
                        cadAula = true;
                        while (cadAula){
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
                                cadAula = false;
                            }
                        }
                        break;
                    case 3:
                        //Consultar aulas
                        verAula = true;
                        while (verAula){
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
                                verAula = false;
                            }
                        }
                        break;

                    case 4:
                        //Cadastrar treinos
                        cadTreino = true;
                        while (cadTreino) {
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
                                cadTreino = false;
                            }
                        }
                        break;
                    case 5:
                        //Consultar treinos
                        verTreino = true;
                        while (verTreino){
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
                                verTreino = false;
                            }
                        }
                        break;
                    case 6:
                        //Cancelar aula
                        delAula = true;
                        while (delAula) {
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
                                delAula = false;
                            }
                        }
                        break;

                    case 7:
                        //Cancelar treino
                        delTreino = true;
                        while (delTreino) {
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
                                delTreino = false;
                            }
                        }
                        break;
                    default:
                        break;
                }

            }
            while (autenticadoSect) {
                System.out.println("""
                        ======= Bem-vindo a academia entra forte e sai frango ======\s
                        1 - Cadastrar Integrante (Aluno ou Staff).\s
                        2 - Consultar Integrante\s
                        3 - Criar Aulas\s
                        4 - Consultar Aula\s
                        5 - Consultar Treino\s
                        6 - Cancelar Aula\s
                        7 - Cancelar Treino\s
                        8 - Inativar Integrante\s
                        9 - Cadastrar Acesso\s
                        >|< Para maior lucidez, não seja um usuário de atacadão >|<\s
                        Pressione 'x' para sair.""");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("x")) {
                    autenticadoSect = false;
                }
                if (input.equalsIgnoreCase(" ") || input.matches("\\d+") || input == null){
                    tipoMenu = 99;
                }else{
                    tipoMenu = Integer.parseInt(input);}
                switch (tipoMenu) {
                    case 1:
                        cadPessoa = true;
                        while (cadPessoa) {
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
                                cadPessoa= false;
                            }
                        }
                        break;
                    case 2:
                        //Consultar integrante
                        verPessoa = true;
                        while (verPessoa){
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
                                verPessoa = false;
                            }
                        }
                        break;

                    case 3:
                        //cadastrar aulas
                        cadAula = true;
                        while (cadAula){
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
                                cadAula = false;
                            }
                        }
                        break;
                    case 4:
                        //Consultar aulas
                        verAula = true;
                        while (verAula){
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
                                verAula = false;
                            }
                        }
                        break;

                    case 5:
                        //Consultar treinos
                        verTreino = true;
                        while (verTreino){
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
                                verTreino = false;
                            }
                        }
                        break;
                    case 6:
                        //Cancelar aula
                        delAula = true;
                        while (delAula) {
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
                                delAula = false;
                            }
                        }
                        break;

                    case 7:
                        //Cancelar treino
                        delTreino = true;
                        while (delTreino) {
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
                                delTreino = false;
                            }
                        }
                        break;

                    case 8:
                        //Inativar integrante
                        delPessoa = true;
                        while (delPessoa) {
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
                                delPessoa = false;
                            }
                        }
                        break;

                    case 9:
                        //Cadastrar acesso
                        cadAcesso = true;
                        while (cadAcesso) {

                            //cadastra o usuário da Staff
                            pessoaService.cadastrarStaff();
                            System.out.println("Deseja cadastrar outro usuário?");
                            System.out.println("S - Sim\nX - Não");
                            String finalizar = sc.nextLine().trim();
                            if (finalizar.equalsIgnoreCase("X") || finalizar.equalsIgnoreCase("Não")){
                                cadAcesso = false;
                            }
                        }break;
                    default:
                        break;
                }

            }

        }
    }
}
