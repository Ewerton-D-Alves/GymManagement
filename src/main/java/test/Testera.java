package test;

import gymproject.models.Gerente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testera {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Gerente> gerente = new ArrayList<>();

        System.out.println("Digite seu nome");
        String nome = sc.nextLine();
        System.out.println("Digite seu sobrenome");
        String sobrenome = sc.nextLine();
        System.out.println("Digite seu CPF");
        String cpf = sc.nextLine();
        System.out.println("Digite seu telefone");
        String telefone = sc.nextLine();
        System.out.println("Digite sua data de nascimento");
        String dataNascimento = sc.nextLine();
        LocalDate dataHora = LocalDate.parse(dataNascimento);
        System.out.println("Digite seu telefone de emergencia");
        String telEmerg = sc.nextLine();
        System.out.println("Digite o nome do seu contáto de emergÊncia");
        String contatoEmerg = sc.nextLine();
        String login = null;
        String senha = null;
        String funcao = null; //primeiroNome, sobrenome, cpf, telefone, dataNascimento, telefoneEmerg, nomeEmerg, loginAcesso, senhaAcesso, funcao
        Gerente gerente1 = new Gerente (nome, sobrenome, cpf, telefone, dataHora, telEmerg, contatoEmerg, login, senha, funcao);

        //verificar essa parte de não poder decidir o que quer cadastrar ou não.
        gerente.add(gerente1);



    }
}
