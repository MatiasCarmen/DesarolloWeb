package main.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.StoredProcedureQuery;
import main.modelo.Usuario;
import main.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class UsuarioDAO {

    public Usuario login(String username, String password) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Usuario.login");
            query.setParameter("p_username", username);
            query.setParameter("p_password", password);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean crearUsuario(String username, String password, String rol) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario u = new Usuario();
            u.setUsername(username);
            u.setPassword(password);
            u.setRol(rol);
            em.persist(u);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}
