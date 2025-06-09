package models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
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

    public void save(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (em.find(Usuario.class, usuario.getId()) == null) {
                em.persist(usuario);
            } else {
                em.merge(usuario);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario u = em.find(Usuario.class, usuario.getId());
            if (u != null) {
                em.remove(u);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}