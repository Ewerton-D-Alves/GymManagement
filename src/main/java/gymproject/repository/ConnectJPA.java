package gymproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConnectJPA {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
    EntityManager em = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
