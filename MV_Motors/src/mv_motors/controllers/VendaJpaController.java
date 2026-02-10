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
import mv_motors.entities.Cliente;
import mv_motors.entities.Financiamento;
import mv_motors.entities.Veiculos;
import mv_motors.entities.Venda;

/**
 *
 * @author vchri
 */
public class VendaJpaController implements Serializable {

    public VendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venda venda) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente fkComprador = venda.getFkComprador();
            if (fkComprador != null) {
                fkComprador = em.getReference(fkComprador.getClass(), fkComprador.getCpf());
                venda.setFkComprador(fkComprador);
            }
            Financiamento fkFinanciamento = venda.getFkFinanciamento();
            if (fkFinanciamento != null) {
                fkFinanciamento = em.getReference(fkFinanciamento.getClass(), fkFinanciamento.getIdFinanciamento());
                venda.setFkFinanciamento(fkFinanciamento);
            }
            Veiculos fkVeiculos = venda.getFkVeiculos();
            if (fkVeiculos != null) {
                fkVeiculos = em.getReference(fkVeiculos.getClass(), fkVeiculos.getIdVeiculo());
                venda.setFkVeiculos(fkVeiculos);
            }
            em.persist(venda);
            if (fkComprador != null) {
                fkComprador.getVendaList().add(venda);
                fkComprador = em.merge(fkComprador);
            }
            if (fkFinanciamento != null) {
                fkFinanciamento.getVendaList().add(venda);
                fkFinanciamento = em.merge(fkFinanciamento);
            }
            if (fkVeiculos != null) {
                fkVeiculos.getVendaList().add(venda);
                fkVeiculos = em.merge(fkVeiculos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVenda(venda.getIdVenda()) != null) {
                throw new PreexistingEntityException("Venda " + venda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venda venda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda persistentVenda = em.find(Venda.class, venda.getIdVenda());
            Cliente fkCompradorOld = persistentVenda.getFkComprador();
            Cliente fkCompradorNew = venda.getFkComprador();
            Financiamento fkFinanciamentoOld = persistentVenda.getFkFinanciamento();
            Financiamento fkFinanciamentoNew = venda.getFkFinanciamento();
            Veiculos fkVeiculosOld = persistentVenda.getFkVeiculos();
            Veiculos fkVeiculosNew = venda.getFkVeiculos();
            if (fkCompradorNew != null) {
                fkCompradorNew = em.getReference(fkCompradorNew.getClass(), fkCompradorNew.getCpf());
                venda.setFkComprador(fkCompradorNew);
            }
            if (fkFinanciamentoNew != null) {
                fkFinanciamentoNew = em.getReference(fkFinanciamentoNew.getClass(), fkFinanciamentoNew.getIdFinanciamento());
                venda.setFkFinanciamento(fkFinanciamentoNew);
            }
            if (fkVeiculosNew != null) {
                fkVeiculosNew = em.getReference(fkVeiculosNew.getClass(), fkVeiculosNew.getIdVeiculo());
                venda.setFkVeiculos(fkVeiculosNew);
            }
            venda = em.merge(venda);
            if (fkCompradorOld != null && !fkCompradorOld.equals(fkCompradorNew)) {
                fkCompradorOld.getVendaList().remove(venda);
                fkCompradorOld = em.merge(fkCompradorOld);
            }
            if (fkCompradorNew != null && !fkCompradorNew.equals(fkCompradorOld)) {
                fkCompradorNew.getVendaList().add(venda);
                fkCompradorNew = em.merge(fkCompradorNew);
            }
            if (fkFinanciamentoOld != null && !fkFinanciamentoOld.equals(fkFinanciamentoNew)) {
                fkFinanciamentoOld.getVendaList().remove(venda);
                fkFinanciamentoOld = em.merge(fkFinanciamentoOld);
            }
            if (fkFinanciamentoNew != null && !fkFinanciamentoNew.equals(fkFinanciamentoOld)) {
                fkFinanciamentoNew.getVendaList().add(venda);
                fkFinanciamentoNew = em.merge(fkFinanciamentoNew);
            }
            if (fkVeiculosOld != null && !fkVeiculosOld.equals(fkVeiculosNew)) {
                fkVeiculosOld.getVendaList().remove(venda);
                fkVeiculosOld = em.merge(fkVeiculosOld);
            }
            if (fkVeiculosNew != null && !fkVeiculosNew.equals(fkVeiculosOld)) {
                fkVeiculosNew.getVendaList().add(venda);
                fkVeiculosNew = em.merge(fkVeiculosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venda.getIdVenda();
                if (findVenda(id) == null) {
                    throw new NonexistentEntityException("The venda with id " + id + " no longer exists.");
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
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getIdVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venda with id " + id + " no longer exists.", enfe);
            }
            Cliente fkComprador = venda.getFkComprador();
            if (fkComprador != null) {
                fkComprador.getVendaList().remove(venda);
                fkComprador = em.merge(fkComprador);
            }
            Financiamento fkFinanciamento = venda.getFkFinanciamento();
            if (fkFinanciamento != null) {
                fkFinanciamento.getVendaList().remove(venda);
                fkFinanciamento = em.merge(fkFinanciamento);
            }
            Veiculos fkVeiculos = venda.getFkVeiculos();
            if (fkVeiculos != null) {
                fkVeiculos.getVendaList().remove(venda);
                fkVeiculos = em.merge(fkVeiculos);
            }
            em.remove(venda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
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

    public Venda findVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venda> rt = cq.from(Venda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
