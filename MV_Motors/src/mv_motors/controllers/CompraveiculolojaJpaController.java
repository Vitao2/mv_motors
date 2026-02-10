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
import mv_motors.entities.Compraveiculoloja;
import mv_motors.entities.Veiculos;

/**
 *
 * @author vchri
 */
public class CompraveiculolojaJpaController implements Serializable {

    public CompraveiculolojaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compraveiculoloja compraveiculoloja) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente fkProprietario = compraveiculoloja.getFkProprietario();
            if (fkProprietario != null) {
                fkProprietario = em.getReference(fkProprietario.getClass(), fkProprietario.getCpf());
                compraveiculoloja.setFkProprietario(fkProprietario);
            }
            Veiculos fkVeiculos = compraveiculoloja.getFkVeiculos();
            if (fkVeiculos != null) {
                fkVeiculos = em.getReference(fkVeiculos.getClass(), fkVeiculos.getIdVeiculo());
                compraveiculoloja.setFkVeiculos(fkVeiculos);
            }
            em.persist(compraveiculoloja);
            if (fkProprietario != null) {
                fkProprietario.getCompraveiculolojaList().add(compraveiculoloja);
                fkProprietario = em.merge(fkProprietario);
            }
            if (fkVeiculos != null) {
                fkVeiculos.getCompraveiculolojaList().add(compraveiculoloja);
                fkVeiculos = em.merge(fkVeiculos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompraveiculoloja(compraveiculoloja.getIdCompra()) != null) {
                throw new PreexistingEntityException("Compraveiculoloja " + compraveiculoloja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compraveiculoloja compraveiculoloja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compraveiculoloja persistentCompraveiculoloja = em.find(Compraveiculoloja.class, compraveiculoloja.getIdCompra());
            Cliente fkProprietarioOld = persistentCompraveiculoloja.getFkProprietario();
            Cliente fkProprietarioNew = compraveiculoloja.getFkProprietario();
            Veiculos fkVeiculosOld = persistentCompraveiculoloja.getFkVeiculos();
            Veiculos fkVeiculosNew = compraveiculoloja.getFkVeiculos();
            if (fkProprietarioNew != null) {
                fkProprietarioNew = em.getReference(fkProprietarioNew.getClass(), fkProprietarioNew.getCpf());
                compraveiculoloja.setFkProprietario(fkProprietarioNew);
            }
            if (fkVeiculosNew != null) {
                fkVeiculosNew = em.getReference(fkVeiculosNew.getClass(), fkVeiculosNew.getIdVeiculo());
                compraveiculoloja.setFkVeiculos(fkVeiculosNew);
            }
            compraveiculoloja = em.merge(compraveiculoloja);
            if (fkProprietarioOld != null && !fkProprietarioOld.equals(fkProprietarioNew)) {
                fkProprietarioOld.getCompraveiculolojaList().remove(compraveiculoloja);
                fkProprietarioOld = em.merge(fkProprietarioOld);
            }
            if (fkProprietarioNew != null && !fkProprietarioNew.equals(fkProprietarioOld)) {
                fkProprietarioNew.getCompraveiculolojaList().add(compraveiculoloja);
                fkProprietarioNew = em.merge(fkProprietarioNew);
            }
            if (fkVeiculosOld != null && !fkVeiculosOld.equals(fkVeiculosNew)) {
                fkVeiculosOld.getCompraveiculolojaList().remove(compraveiculoloja);
                fkVeiculosOld = em.merge(fkVeiculosOld);
            }
            if (fkVeiculosNew != null && !fkVeiculosNew.equals(fkVeiculosOld)) {
                fkVeiculosNew.getCompraveiculolojaList().add(compraveiculoloja);
                fkVeiculosNew = em.merge(fkVeiculosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compraveiculoloja.getIdCompra();
                if (findCompraveiculoloja(id) == null) {
                    throw new NonexistentEntityException("The compraveiculoloja with id " + id + " no longer exists.");
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
            Compraveiculoloja compraveiculoloja;
            try {
                compraveiculoloja = em.getReference(Compraveiculoloja.class, id);
                compraveiculoloja.getIdCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compraveiculoloja with id " + id + " no longer exists.", enfe);
            }
            Cliente fkProprietario = compraveiculoloja.getFkProprietario();
            if (fkProprietario != null) {
                fkProprietario.getCompraveiculolojaList().remove(compraveiculoloja);
                fkProprietario = em.merge(fkProprietario);
            }
            Veiculos fkVeiculos = compraveiculoloja.getFkVeiculos();
            if (fkVeiculos != null) {
                fkVeiculos.getCompraveiculolojaList().remove(compraveiculoloja);
                fkVeiculos = em.merge(fkVeiculos);
            }
            em.remove(compraveiculoloja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compraveiculoloja> findCompraveiculolojaEntities() {
        return findCompraveiculolojaEntities(true, -1, -1);
    }

    public List<Compraveiculoloja> findCompraveiculolojaEntities(int maxResults, int firstResult) {
        return findCompraveiculolojaEntities(false, maxResults, firstResult);
    }

    private List<Compraveiculoloja> findCompraveiculolojaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compraveiculoloja.class));
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

    public Compraveiculoloja findCompraveiculoloja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compraveiculoloja.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraveiculolojaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compraveiculoloja> rt = cq.from(Compraveiculoloja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
