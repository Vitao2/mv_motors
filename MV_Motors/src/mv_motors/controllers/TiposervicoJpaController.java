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
import mv_motors.entities.Prestador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mv_motors.controllers.exceptions.IllegalOrphanException;
import mv_motors.controllers.exceptions.NonexistentEntityException;
import mv_motors.controllers.exceptions.PreexistingEntityException;
import mv_motors.entities.Servicoprestado;
import mv_motors.entities.Tiposervico;

/**
 *
 * @author vchri
 */
public class TiposervicoJpaController implements Serializable {

    public TiposervicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiposervico tiposervico) throws PreexistingEntityException, Exception {
        if (tiposervico.getPrestadorList() == null) {
            tiposervico.setPrestadorList(new ArrayList<Prestador>());
        }
        if (tiposervico.getServicoprestadoList() == null) {
            tiposervico.setServicoprestadoList(new ArrayList<Servicoprestado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Prestador> attachedPrestadorList = new ArrayList<Prestador>();
            for (Prestador prestadorListPrestadorToAttach : tiposervico.getPrestadorList()) {
                prestadorListPrestadorToAttach = em.getReference(prestadorListPrestadorToAttach.getClass(), prestadorListPrestadorToAttach.getIdPrestador());
                attachedPrestadorList.add(prestadorListPrestadorToAttach);
            }
            tiposervico.setPrestadorList(attachedPrestadorList);
            List<Servicoprestado> attachedServicoprestadoList = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListServicoprestadoToAttach : tiposervico.getServicoprestadoList()) {
                servicoprestadoListServicoprestadoToAttach = em.getReference(servicoprestadoListServicoprestadoToAttach.getClass(), servicoprestadoListServicoprestadoToAttach.getIdServico());
                attachedServicoprestadoList.add(servicoprestadoListServicoprestadoToAttach);
            }
            tiposervico.setServicoprestadoList(attachedServicoprestadoList);
            em.persist(tiposervico);
            for (Prestador prestadorListPrestador : tiposervico.getPrestadorList()) {
                prestadorListPrestador.getTiposervicoList().add(tiposervico);
                prestadorListPrestador = em.merge(prestadorListPrestador);
            }
            for (Servicoprestado servicoprestadoListServicoprestado : tiposervico.getServicoprestadoList()) {
                Tiposervico oldFkIdTipoServicoOfServicoprestadoListServicoprestado = servicoprestadoListServicoprestado.getFkIdTipoServico();
                servicoprestadoListServicoprestado.setFkIdTipoServico(tiposervico);
                servicoprestadoListServicoprestado = em.merge(servicoprestadoListServicoprestado);
                if (oldFkIdTipoServicoOfServicoprestadoListServicoprestado != null) {
                    oldFkIdTipoServicoOfServicoprestadoListServicoprestado.getServicoprestadoList().remove(servicoprestadoListServicoprestado);
                    oldFkIdTipoServicoOfServicoprestadoListServicoprestado = em.merge(oldFkIdTipoServicoOfServicoprestadoListServicoprestado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposervico(tiposervico.getIdTipoServico()) != null) {
                throw new PreexistingEntityException("Tiposervico " + tiposervico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiposervico tiposervico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiposervico persistentTiposervico = em.find(Tiposervico.class, tiposervico.getIdTipoServico());
            List<Prestador> prestadorListOld = persistentTiposervico.getPrestadorList();
            List<Prestador> prestadorListNew = tiposervico.getPrestadorList();
            List<Servicoprestado> servicoprestadoListOld = persistentTiposervico.getServicoprestadoList();
            List<Servicoprestado> servicoprestadoListNew = tiposervico.getServicoprestadoList();
            List<String> illegalOrphanMessages = null;
            for (Servicoprestado servicoprestadoListOldServicoprestado : servicoprestadoListOld) {
                if (!servicoprestadoListNew.contains(servicoprestadoListOldServicoprestado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicoprestado " + servicoprestadoListOldServicoprestado + " since its fkIdTipoServico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Prestador> attachedPrestadorListNew = new ArrayList<Prestador>();
            for (Prestador prestadorListNewPrestadorToAttach : prestadorListNew) {
                prestadorListNewPrestadorToAttach = em.getReference(prestadorListNewPrestadorToAttach.getClass(), prestadorListNewPrestadorToAttach.getIdPrestador());
                attachedPrestadorListNew.add(prestadorListNewPrestadorToAttach);
            }
            prestadorListNew = attachedPrestadorListNew;
            tiposervico.setPrestadorList(prestadorListNew);
            List<Servicoprestado> attachedServicoprestadoListNew = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListNewServicoprestadoToAttach : servicoprestadoListNew) {
                servicoprestadoListNewServicoprestadoToAttach = em.getReference(servicoprestadoListNewServicoprestadoToAttach.getClass(), servicoprestadoListNewServicoprestadoToAttach.getIdServico());
                attachedServicoprestadoListNew.add(servicoprestadoListNewServicoprestadoToAttach);
            }
            servicoprestadoListNew = attachedServicoprestadoListNew;
            tiposervico.setServicoprestadoList(servicoprestadoListNew);
            tiposervico = em.merge(tiposervico);
            for (Prestador prestadorListOldPrestador : prestadorListOld) {
                if (!prestadorListNew.contains(prestadorListOldPrestador)) {
                    prestadorListOldPrestador.getTiposervicoList().remove(tiposervico);
                    prestadorListOldPrestador = em.merge(prestadorListOldPrestador);
                }
            }
            for (Prestador prestadorListNewPrestador : prestadorListNew) {
                if (!prestadorListOld.contains(prestadorListNewPrestador)) {
                    prestadorListNewPrestador.getTiposervicoList().add(tiposervico);
                    prestadorListNewPrestador = em.merge(prestadorListNewPrestador);
                }
            }
            for (Servicoprestado servicoprestadoListNewServicoprestado : servicoprestadoListNew) {
                if (!servicoprestadoListOld.contains(servicoprestadoListNewServicoprestado)) {
                    Tiposervico oldFkIdTipoServicoOfServicoprestadoListNewServicoprestado = servicoprestadoListNewServicoprestado.getFkIdTipoServico();
                    servicoprestadoListNewServicoprestado.setFkIdTipoServico(tiposervico);
                    servicoprestadoListNewServicoprestado = em.merge(servicoprestadoListNewServicoprestado);
                    if (oldFkIdTipoServicoOfServicoprestadoListNewServicoprestado != null && !oldFkIdTipoServicoOfServicoprestadoListNewServicoprestado.equals(tiposervico)) {
                        oldFkIdTipoServicoOfServicoprestadoListNewServicoprestado.getServicoprestadoList().remove(servicoprestadoListNewServicoprestado);
                        oldFkIdTipoServicoOfServicoprestadoListNewServicoprestado = em.merge(oldFkIdTipoServicoOfServicoprestadoListNewServicoprestado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiposervico.getIdTipoServico();
                if (findTiposervico(id) == null) {
                    throw new NonexistentEntityException("The tiposervico with id " + id + " no longer exists.");
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
            Tiposervico tiposervico;
            try {
                tiposervico = em.getReference(Tiposervico.class, id);
                tiposervico.getIdTipoServico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposervico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Servicoprestado> servicoprestadoListOrphanCheck = tiposervico.getServicoprestadoList();
            for (Servicoprestado servicoprestadoListOrphanCheckServicoprestado : servicoprestadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tiposervico (" + tiposervico + ") cannot be destroyed since the Servicoprestado " + servicoprestadoListOrphanCheckServicoprestado + " in its servicoprestadoList field has a non-nullable fkIdTipoServico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Prestador> prestadorList = tiposervico.getPrestadorList();
            for (Prestador prestadorListPrestador : prestadorList) {
                prestadorListPrestador.getTiposervicoList().remove(tiposervico);
                prestadorListPrestador = em.merge(prestadorListPrestador);
            }
            em.remove(tiposervico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiposervico> findTiposervicoEntities() {
        return findTiposervicoEntities(true, -1, -1);
    }

    public List<Tiposervico> findTiposervicoEntities(int maxResults, int firstResult) {
        return findTiposervicoEntities(false, maxResults, firstResult);
    }

    private List<Tiposervico> findTiposervicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiposervico.class));
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

    public Tiposervico findTiposervico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiposervico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposervicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiposervico> rt = cq.from(Tiposervico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
