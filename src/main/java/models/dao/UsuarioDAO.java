package models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Usuario;

import java.util.List;

public class UsuarioDAO {
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("PU");

    public List<Usuario> findAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Usuario",
                Usuario.class).getResultList();
    }
}