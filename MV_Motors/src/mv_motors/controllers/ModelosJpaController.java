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
import mv_motors.entities.Veiculos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mv_motors.controllers.exceptions.IllegalOrphanException;
import mv_motors.controllers.exceptions.NonexistentEntityException;
import mv_motors.controllers.exceptions.PreexistingEntityException;
import mv_motors.entities.Modelos;

/**
 *
 * @author vchri
 */
public class ModelosJpaController implements Serializable {

    public ModelosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modelos modelos) throws PreexistingEntityException, Exception {
        if (modelos.getVeiculosList() == null) {
            modelos.setVeiculosList(new ArrayList<Veiculos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Veiculos> attachedVeiculosList = new ArrayList<Veiculos>();
            for (Veiculos veiculosListVeiculosToAttach : modelos.getVeiculosList()) {
                veiculosListVeiculosToAttach = em.getReference(veiculosListVeiculosToAttach.getClass(), veiculosListVeiculosToAttach.getIdVeiculo());
                attachedVeiculosList.add(veiculosListVeiculosToAttach);
            }
            modelos.setVeiculosList(attachedVeiculosList);
            em.persist(modelos);
            for (Veiculos veiculosListVeiculos : modelos.getVeiculosList()) {
                Modelos oldIdModeloOfVeiculosListVeiculos = veiculosListVeiculos.getIdModelo();
                veiculosListVeiculos.setIdModelo(modelos);
                veiculosListVeiculos = em.merge(veiculosListVeiculos);
                if (oldIdModeloOfVeiculosListVeiculos != null) {
                    oldIdModeloOfVeiculosListVeiculos.getVeiculosList().remove(veiculosListVeiculos);
                    oldIdModeloOfVeiculosListVeiculos = em.merge(oldIdModeloOfVeiculosListVeiculos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findModelos(modelos.getIdModelo()) != null) {
                throw new PreexistingEntityException("Modelos " + modelos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modelos modelos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modelos persistentModelos = em.find(Modelos.class, modelos.getIdModelo());
            List<Veiculos> veiculosListOld = persistentModelos.getVeiculosList();
            List<Veiculos> veiculosListNew = modelos.getVeiculosList();
            List<String> illegalOrphanMessages = null;
            for (Veiculos veiculosListOldVeiculos : veiculosListOld) {
                if (!veiculosListNew.contains(veiculosListOldVeiculos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Veiculos " + veiculosListOldVeiculos + " since its idModelo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Veiculos> attachedVeiculosListNew = new ArrayList<Veiculos>();
            for (Veiculos veiculosListNewVeiculosToAttach : veiculosListNew) {
                veiculosListNewVeiculosToAttach = em.getReference(veiculosListNewVeiculosToAttach.getClass(), veiculosListNewVeiculosToAttach.getIdVeiculo());
                attachedVeiculosListNew.add(veiculosListNewVeiculosToAttach);
            }
            veiculosListNew = attachedVeiculosListNew;
            modelos.setVeiculosList(veiculosListNew);
            modelos = em.merge(modelos);
            for (Veiculos veiculosListNewVeiculos : veiculosListNew) {
                if (!veiculosListOld.contains(veiculosListNewVeiculos)) {
                    Modelos oldIdModeloOfVeiculosListNewVeiculos = veiculosListNewVeiculos.getIdModelo();
                    veiculosListNewVeiculos.setIdModelo(modelos);
                    veiculosListNewVeiculos = em.merge(veiculosListNewVeiculos);
                    if (oldIdModeloOfVeiculosListNewVeiculos != null && !oldIdModeloOfVeiculosListNewVeiculos.equals(modelos)) {
                        oldIdModeloOfVeiculosListNewVeiculos.getVeiculosList().remove(veiculosListNewVeiculos);
                        oldIdModeloOfVeiculosListNewVeiculos = em.merge(oldIdModeloOfVeiculosListNewVeiculos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = modelos.getIdModelo();
                if (findModelos(id) == null) {
                    throw new NonexistentEntityException("The modelos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modelos modelos;
            try {
                modelos = em.getReference(Modelos.class, id);
                modelos.getIdModelo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modelos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Veiculos> veiculosListOrphanCheck = modelos.getVeiculosList();
            for (Veiculos veiculosListOrphanCheckVeiculos : veiculosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Modelos (" + modelos + ") cannot be destroyed since the Veiculos " + veiculosListOrphanCheckVeiculos + " in its veiculosList field has a non-nullable idModelo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(modelos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modelos> findModelosEntities() {
        return findModelosEntities(true, -1, -1);
    }

    public List<Modelos> findModelosEntities(int maxResults, int firstResult) {
        return findModelosEntities(false, maxResults, firstResult);
    }

    private List<Modelos> findModelosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modelos.class));
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

    public Modelos findModelos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modelos.class, id);
        } finally {
            em.close();
        }
    }

    public int getModelosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modelos> rt = cq.from(Modelos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
