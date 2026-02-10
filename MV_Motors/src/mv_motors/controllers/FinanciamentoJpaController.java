/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mv_motors.entities.Venda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mv_motors.controllers.exceptions.NonexistentEntityException;
import mv_motors.controllers.exceptions.PreexistingEntityException;
import mv_motors.entities.Financiamento;

/**
 *
 * @author vchri
 */
public class FinanciamentoJpaController implements Serializable {

    public FinanciamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Financiamento financiamento) throws PreexistingEntityException, Exception {
        if (financiamento.getVendaList() == null) {
            financiamento.setVendaList(new ArrayList<Venda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venda> attachedVendaList = new ArrayList<Venda>();
            for (Venda vendaListVendaToAttach : financiamento.getVendaList()) {
                vendaListVendaToAttach = em.getReference(vendaListVendaToAttach.getClass(), vendaListVendaToAttach.getIdVenda());
                attachedVendaList.add(vendaListVendaToAttach);
            }
            financiamento.setVendaList(attachedVendaList);
            em.persist(financiamento);
            for (Venda vendaListVenda : financiamento.getVendaList()) {
                Financiamento oldFkFinanciamentoOfVendaListVenda = vendaListVenda.getFkFinanciamento();
                vendaListVenda.setFkFinanciamento(financiamento);
                vendaListVenda = em.merge(vendaListVenda);
                if (oldFkFinanciamentoOfVendaListVenda != null) {
                    oldFkFinanciamentoOfVendaListVenda.getVendaList().remove(vendaListVenda);
                    oldFkFinanciamentoOfVendaListVenda = em.merge(oldFkFinanciamentoOfVendaListVenda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFinanciamento(financiamento.getIdFinanciamento()) != null) {
                throw new PreexistingEntityException("Financiamento " + financiamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Financiamento financiamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Financiamento persistentFinanciamento = em.find(Financiamento.class, financiamento.getIdFinanciamento());
            List<Venda> vendaListOld = persistentFinanciamento.getVendaList();
            List<Venda> vendaListNew = financiamento.getVendaList();
            List<Venda> attachedVendaListNew = new ArrayList<Venda>();
            for (Venda vendaListNewVendaToAttach : vendaListNew) {
                vendaListNewVendaToAttach = em.getReference(vendaListNewVendaToAttach.getClass(), vendaListNewVendaToAttach.getIdVenda());
                attachedVendaListNew.add(vendaListNewVendaToAttach);
            }
            vendaListNew = attachedVendaListNew;
            financiamento.setVendaList(vendaListNew);
            financiamento = em.merge(financiamento);
            for (Venda vendaListOldVenda : vendaListOld) {
                if (!vendaListNew.contains(vendaListOldVenda)) {
                    vendaListOldVenda.setFkFinanciamento(null);
                    vendaListOldVenda = em.merge(vendaListOldVenda);
                }
            }
            for (Venda vendaListNewVenda : vendaListNew) {
                if (!vendaListOld.contains(vendaListNewVenda)) {
                    Financiamento oldFkFinanciamentoOfVendaListNewVenda = vendaListNewVenda.getFkFinanciamento();
                    vendaListNewVenda.setFkFinanciamento(financiamento);
                    vendaListNewVenda = em.merge(vendaListNewVenda);
                    if (oldFkFinanciamentoOfVendaListNewVenda != null && !oldFkFinanciamentoOfVendaListNewVenda.equals(financiamento)) {
                        oldFkFinanciamentoOfVendaListNewVenda.getVendaList().remove(vendaListNewVenda);
                        oldFkFinanciamentoOfVendaListNewVenda = em.merge(oldFkFinanciamentoOfVendaListNewVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = financiamento.getIdFinanciamento();
                if (findFinanciamento(id) == null) {
                    throw new NonexistentEntityException("The financiamento with id " + id + " no longer exists.");
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
            Financiamento financiamento;
            try {
                financiamento = em.getReference(Financiamento.class, id);
                financiamento.getIdFinanciamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financiamento with id " + id + " no longer exists.", enfe);
            }
            List<Venda> vendaList = financiamento.getVendaList();
            for (Venda vendaListVenda : vendaList) {
                vendaListVenda.setFkFinanciamento(null);
                vendaListVenda = em.merge(vendaListVenda);
            }
            em.remove(financiamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Financiamento> findFinanciamentoEntities() {
        return findFinanciamentoEntities(true, -1, -1);
    }

    public List<Financiamento> findFinanciamentoEntities(int maxResults, int firstResult) {
        return findFinanciamentoEntities(false, maxResults, firstResult);
    }

    private List<Financiamento> findFinanciamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Financiamento.class));
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

    public Financiamento findFinanciamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Financiamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinanciamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Financiamento> rt = cq.from(Financiamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
