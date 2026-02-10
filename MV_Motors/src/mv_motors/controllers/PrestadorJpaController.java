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
import mv_motors.entities.Tiposervico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mv_motors.controllers.exceptions.IllegalOrphanException;
import mv_motors.controllers.exceptions.NonexistentEntityException;
import mv_motors.controllers.exceptions.PreexistingEntityException;
import mv_motors.entities.Prestador;
import mv_motors.entities.Servicoprestado;

/**
 *
 * @author vchri
 */
public class PrestadorJpaController implements Serializable {

    public PrestadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestador prestador) throws PreexistingEntityException, Exception {
        if (prestador.getTiposervicoList() == null) {
            prestador.setTiposervicoList(new ArrayList<Tiposervico>());
        }
        if (prestador.getServicoprestadoList() == null) {
            prestador.setServicoprestadoList(new ArrayList<Servicoprestado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tiposervico> attachedTiposervicoList = new ArrayList<Tiposervico>();
            for (Tiposervico tiposervicoListTiposervicoToAttach : prestador.getTiposervicoList()) {
                tiposervicoListTiposervicoToAttach = em.getReference(tiposervicoListTiposervicoToAttach.getClass(), tiposervicoListTiposervicoToAttach.getIdTipoServico());
                attachedTiposervicoList.add(tiposervicoListTiposervicoToAttach);
            }
            prestador.setTiposervicoList(attachedTiposervicoList);
            List<Servicoprestado> attachedServicoprestadoList = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListServicoprestadoToAttach : prestador.getServicoprestadoList()) {
                servicoprestadoListServicoprestadoToAttach = em.getReference(servicoprestadoListServicoprestadoToAttach.getClass(), servicoprestadoListServicoprestadoToAttach.getIdServico());
                attachedServicoprestadoList.add(servicoprestadoListServicoprestadoToAttach);
            }
            prestador.setServicoprestadoList(attachedServicoprestadoList);
            em.persist(prestador);
            for (Tiposervico tiposervicoListTiposervico : prestador.getTiposervicoList()) {
                tiposervicoListTiposervico.getPrestadorList().add(prestador);
                tiposervicoListTiposervico = em.merge(tiposervicoListTiposervico);
            }
            for (Servicoprestado servicoprestadoListServicoprestado : prestador.getServicoprestadoList()) {
                Prestador oldFkIdPrestadorOfServicoprestadoListServicoprestado = servicoprestadoListServicoprestado.getFkIdPrestador();
                servicoprestadoListServicoprestado.setFkIdPrestador(prestador);
                servicoprestadoListServicoprestado = em.merge(servicoprestadoListServicoprestado);
                if (oldFkIdPrestadorOfServicoprestadoListServicoprestado != null) {
                    oldFkIdPrestadorOfServicoprestadoListServicoprestado.getServicoprestadoList().remove(servicoprestadoListServicoprestado);
                    oldFkIdPrestadorOfServicoprestadoListServicoprestado = em.merge(oldFkIdPrestadorOfServicoprestadoListServicoprestado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrestador(prestador.getIdPrestador()) != null) {
                throw new PreexistingEntityException("Prestador " + prestador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prestador prestador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestador persistentPrestador = em.find(Prestador.class, prestador.getIdPrestador());
            List<Tiposervico> tiposervicoListOld = persistentPrestador.getTiposervicoList();
            List<Tiposervico> tiposervicoListNew = prestador.getTiposervicoList();
            List<Servicoprestado> servicoprestadoListOld = persistentPrestador.getServicoprestadoList();
            List<Servicoprestado> servicoprestadoListNew = prestador.getServicoprestadoList();
            List<String> illegalOrphanMessages = null;
            for (Servicoprestado servicoprestadoListOldServicoprestado : servicoprestadoListOld) {
                if (!servicoprestadoListNew.contains(servicoprestadoListOldServicoprestado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicoprestado " + servicoprestadoListOldServicoprestado + " since its fkIdPrestador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Tiposervico> attachedTiposervicoListNew = new ArrayList<Tiposervico>();
            for (Tiposervico tiposervicoListNewTiposervicoToAttach : tiposervicoListNew) {
                tiposervicoListNewTiposervicoToAttach = em.getReference(tiposervicoListNewTiposervicoToAttach.getClass(), tiposervicoListNewTiposervicoToAttach.getIdTipoServico());
                attachedTiposervicoListNew.add(tiposervicoListNewTiposervicoToAttach);
            }
            tiposervicoListNew = attachedTiposervicoListNew;
            prestador.setTiposervicoList(tiposervicoListNew);
            List<Servicoprestado> attachedServicoprestadoListNew = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListNewServicoprestadoToAttach : servicoprestadoListNew) {
                servicoprestadoListNewServicoprestadoToAttach = em.getReference(servicoprestadoListNewServicoprestadoToAttach.getClass(), servicoprestadoListNewServicoprestadoToAttach.getIdServico());
                attachedServicoprestadoListNew.add(servicoprestadoListNewServicoprestadoToAttach);
            }
            servicoprestadoListNew = attachedServicoprestadoListNew;
            prestador.setServicoprestadoList(servicoprestadoListNew);
            prestador = em.merge(prestador);
            for (Tiposervico tiposervicoListOldTiposervico : tiposervicoListOld) {
                if (!tiposervicoListNew.contains(tiposervicoListOldTiposervico)) {
                    tiposervicoListOldTiposervico.getPrestadorList().remove(prestador);
                    tiposervicoListOldTiposervico = em.merge(tiposervicoListOldTiposervico);
                }
            }
            for (Tiposervico tiposervicoListNewTiposervico : tiposervicoListNew) {
                if (!tiposervicoListOld.contains(tiposervicoListNewTiposervico)) {
                    tiposervicoListNewTiposervico.getPrestadorList().add(prestador);
                    tiposervicoListNewTiposervico = em.merge(tiposervicoListNewTiposervico);
                }
            }
            for (Servicoprestado servicoprestadoListNewServicoprestado : servicoprestadoListNew) {
                if (!servicoprestadoListOld.contains(servicoprestadoListNewServicoprestado)) {
                    Prestador oldFkIdPrestadorOfServicoprestadoListNewServicoprestado = servicoprestadoListNewServicoprestado.getFkIdPrestador();
                    servicoprestadoListNewServicoprestado.setFkIdPrestador(prestador);
                    servicoprestadoListNewServicoprestado = em.merge(servicoprestadoListNewServicoprestado);
                    if (oldFkIdPrestadorOfServicoprestadoListNewServicoprestado != null && !oldFkIdPrestadorOfServicoprestadoListNewServicoprestado.equals(prestador)) {
                        oldFkIdPrestadorOfServicoprestadoListNewServicoprestado.getServicoprestadoList().remove(servicoprestadoListNewServicoprestado);
                        oldFkIdPrestadorOfServicoprestadoListNewServicoprestado = em.merge(oldFkIdPrestadorOfServicoprestadoListNewServicoprestado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prestador.getIdPrestador();
                if (findPrestador(id) == null) {
                    throw new NonexistentEntityException("The prestador with id " + id + " no longer exists.");
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
            Prestador prestador;
            try {
                prestador = em.getReference(Prestador.class, id);
                prestador.getIdPrestador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Servicoprestado> servicoprestadoListOrphanCheck = prestador.getServicoprestadoList();
            for (Servicoprestado servicoprestadoListOrphanCheckServicoprestado : servicoprestadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prestador (" + prestador + ") cannot be destroyed since the Servicoprestado " + servicoprestadoListOrphanCheckServicoprestado + " in its servicoprestadoList field has a non-nullable fkIdPrestador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Tiposervico> tiposervicoList = prestador.getTiposervicoList();
            for (Tiposervico tiposervicoListTiposervico : tiposervicoList) {
                tiposervicoListTiposervico.getPrestadorList().remove(prestador);
                tiposervicoListTiposervico = em.merge(tiposervicoListTiposervico);
            }
            em.remove(prestador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prestador> findPrestadorEntities() {
        return findPrestadorEntities(true, -1, -1);
    }

    public List<Prestador> findPrestadorEntities(int maxResults, int firstResult) {
        return findPrestadorEntities(false, maxResults, firstResult);
    }

    private List<Prestador> findPrestadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestador.class));
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

    public Prestador findPrestador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prestador.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestador> rt = cq.from(Prestador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
