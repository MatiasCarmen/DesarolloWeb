package main.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import main.modelo.Reserva;
import main.util.JPAUtil;

import java.util.List;

public class ReservaDao {

    public boolean crearReserva(Reserva reserva) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Reserva.insert");
            query.setParameter("p_nombre_cliente", reserva.getNombreCliente());
            query.setParameter("p_fecha", reserva.getFecha());
            query.setParameter("p_hora", reserva.getHora());
            query.setParameter("p_numero_mesa", reserva.getNumeroMesa());
            query.setParameter("p_estado", reserva.getEstado());
            query.execute();
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

    public List<Reserva> obtenerReservas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Reserva.list");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
