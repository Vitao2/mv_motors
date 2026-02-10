/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mv_motors.controllers.exceptions.NonexistentEntityException;
import mv_motors.controllers.exceptions.PreexistingEntityException;
import mv_motors.entities.Prestador;
import mv_motors.entities.Servicoprestado;
import mv_motors.entities.Tiposervico;
import mv_motors.entities.Veiculos;

/**
 *
 * @author vchri
 */
public class ServicoprestadoJpaController implements Serializable {

    public ServicoprestadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicoprestado servicoprestado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestador fkIdPrestador = servicoprestado.getFkIdPrestador();
            if (fkIdPrestador != null) {
                fkIdPrestador = em.getReference(fkIdPrestador.getClass(), fkIdPrestador.getIdPrestador());
                servicoprestado.setFkIdPrestador(fkIdPrestador);
            }
            Tiposervico fkIdTipoServico = servicoprestado.getFkIdTipoServico();
            if (fkIdTipoServico != null) {
                fkIdTipoServico = em.getReference(fkIdTipoServico.getClass(), fkIdTipoServico.getIdTipoServico());
                servicoprestado.setFkIdTipoServico(fkIdTipoServico);
            }
            Veiculos fkIdVeiculo = servicoprestado.getFkIdVeiculo();
            if (fkIdVeiculo != null) {
                fkIdVeiculo = em.getReference(fkIdVeiculo.getClass(), fkIdVeiculo.getIdVeiculo());
                servicoprestado.setFkIdVeiculo(fkIdVeiculo);
            }
            em.persist(servicoprestado);
            if (fkIdPrestador != null) {
                fkIdPrestador.getServicoprestadoList().add(servicoprestado);
                fkIdPrestador = em.merge(fkIdPrestador);
            }
            if (fkIdTipoServico != null) {
                fkIdTipoServico.getServicoprestadoList().add(servicoprestado);
                fkIdTipoServico = em.merge(fkIdTipoServico);
            }
            if (fkIdVeiculo != null) {
                fkIdVeiculo.getServicoprestadoList().add(servicoprestado);
                fkIdVeiculo = em.merge(fkIdVeiculo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicoprestado(servicoprestado.getIdServico()) != null) {
                throw new PreexistingEntityException("Servicoprestado " + servicoprestado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicoprestado servicoprestado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicoprestado persistentServicoprestado = em.find(Servicoprestado.class, servicoprestado.getIdServico());
            Prestador fkIdPrestadorOld = persistentServicoprestado.getFkIdPrestador();
            Prestador fkIdPrestadorNew = servicoprestado.getFkIdPrestador();
            Tiposervico fkIdTipoServicoOld = persistentServicoprestado.getFkIdTipoServico();
            Tiposervico fkIdTipoServicoNew = servicoprestado.getFkIdTipoServico();
            Veiculos fkIdVeiculoOld = persistentServicoprestado.getFkIdVeiculo();
            Veiculos fkIdVeiculoNew = servicoprestado.getFkIdVeiculo();
            if (fkIdPrestadorNew != null) {
                fkIdPrestadorNew = em.getReference(fkIdPrestadorNew.getClass(), fkIdPrestadorNew.getIdPrestador());
                servicoprestado.setFkIdPrestador(fkIdPrestadorNew);
            }
            if (fkIdTipoServicoNew != null) {
                fkIdTipoServicoNew = em.getReference(fkIdTipoServicoNew.getClass(), fkIdTipoServicoNew.getIdTipoServico());
                servicoprestado.setFkIdTipoServico(fkIdTipoServicoNew);
            }
            if (fkIdVeiculoNew != null) {
                fkIdVeiculoNew = em.getReference(fkIdVeiculoNew.getClass(), fkIdVeiculoNew.getIdVeiculo());
                servicoprestado.setFkIdVeiculo(fkIdVeiculoNew);
            }
            servicoprestado = em.merge(servicoprestado);
            if (fkIdPrestadorOld != null && !fkIdPrestadorOld.equals(fkIdPrestadorNew)) {
                fkIdPrestadorOld.getServicoprestadoList().remove(servicoprestado);
                fkIdPrestadorOld = em.merge(fkIdPrestadorOld);
            }
            if (fkIdPrestadorNew != null && !fkIdPrestadorNew.equals(fkIdPrestadorOld)) {
                fkIdPrestadorNew.getServicoprestadoList().add(servicoprestado);
                fkIdPrestadorNew = em.merge(fkIdPrestadorNew);
            }
            if (fkIdTipoServicoOld != null && !fkIdTipoServicoOld.equals(fkIdTipoServicoNew)) {
                fkIdTipoServicoOld.getServicoprestadoList().remove(servicoprestado);
                fkIdTipoServicoOld = em.merge(fkIdTipoServicoOld);
            }
            if (fkIdTipoServicoNew != null && !fkIdTipoServicoNew.equals(fkIdTipoServicoOld)) {
                fkIdTipoServicoNew.getServicoprestadoList().add(servicoprestado);
                fkIdTipoServicoNew = em.merge(fkIdTipoServicoNew);
            }
            if (fkIdVeiculoOld != null && !fkIdVeiculoOld.equals(fkIdVeiculoNew)) {
                fkIdVeiculoOld.getServicoprestadoList().remove(servicoprestado);
                fkIdVeiculoOld = em.merge(fkIdVeiculoOld);
            }
            if (fkIdVeiculoNew != null && !fkIdVeiculoNew.equals(fkIdVeiculoOld)) {
                fkIdVeiculoNew.getServicoprestadoList().add(servicoprestado);
                fkIdVeiculoNew = em.merge(fkIdVeiculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicoprestado.getIdServico();
                if (findServicoprestado(id) == null) {
                    throw new NonexistentEntityException("The servicoprestado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicoprestado servicoprestado;
            try {
                servicoprestado = em.getReference(Servicoprestado.class, id);
                servicoprestado.getIdServico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicoprestado with id " + id + " no longer exists.", enfe);
            }
            Prestador fkIdPrestador = servicoprestado.getFkIdPrestador();
            if (fkIdPrestador != null) {
                fkIdPrestador.getServicoprestadoList().remove(servicoprestado);
                fkIdPrestador = em.merge(fkIdPrestador);
            }
            Tiposervico fkIdTipoServico = servicoprestado.getFkIdTipoServico();
            if (fkIdTipoServico != null) {
                fkIdTipoServico.getServicoprestadoList().remove(servicoprestado);
                fkIdTipoServico = em.merge(fkIdTipoServico);
            }
            Veiculos fkIdVeiculo = servicoprestado.getFkIdVeiculo();
            if (fkIdVeiculo != null) {
                fkIdVeiculo.getServicoprestadoList().remove(servicoprestado);
                fkIdVeiculo = em.merge(fkIdVeiculo);
            }
            em.remove(servicoprestado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicoprestado> findServicoprestadoEntities() {
        return findServicoprestadoEntities(true, -1, -1);
    }

    public List<Servicoprestado> findServicoprestadoEntities(int maxResults, int firstResult) {
        return findServicoprestadoEntities(false, maxResults, firstResult);
    }

    private List<Servicoprestado> findServicoprestadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicoprestado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Servicoprestado findServicoprestado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicoprestado.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicoprestadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicoprestado> rt = cq.from(Servicoprestado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
