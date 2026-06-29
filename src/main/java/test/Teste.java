package test;

import gymproject.models.Gerente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class Teste {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
        EntityManager em = emf.createEntityManager();

        try {
            Gerente gerente = new Gerente();

            gerente.setCpf("12345678901");
            gerente.setPrimeiroNome("gerente");
            gerente.setSobrenome("gerente");
            gerente.setTelefone("912345678");
            gerente.setDataNascimento(LocalDate.of(1998, 1, 8));
            gerente.setNomeEmerg("Maria Silva");
            gerente.setTelefoneEmerg("919876543");
            gerente.setLoginAcesso("admin");
            gerente.setSenhaAcesso("admin");

            em.getTransaction().begin();

            // Guardar o registo na base de dados
            em.persist(gerente);

            // Confirmar a transação
            em.getTransaction().commit();

            System.out.println("Gerente guardado com sucesso na base de dados!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Desfazer caso ocorra algum erro
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}