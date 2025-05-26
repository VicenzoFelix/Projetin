import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Usuario;

public class GerenciadorProjetoApp {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

        Usuario usuario1 = new Usuario(
                1, "Dick Vigarista",
                "dickvigarista@gmail.com", "12345"
        );
        Usuario usuario2 = new Usuario(
                2, "Penélope Charmosa",
                "penelope@gmail.com", "54321"
        );

        // Persistir no BD
        em.getTransaction().begin();
        em.persist(usuario1);
        em.persist(usuario2);
        em.getTransaction().commit();

    }
}
