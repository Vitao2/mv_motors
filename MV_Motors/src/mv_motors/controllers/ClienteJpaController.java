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
import mv_motors.entities.Cliente;
import mv_motors.entities.Compraveiculoloja;
import mv_motors.entities.Venda;

/**
 *
 * @author vchri
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getVeiculosList() == null) {
            cliente.setVeiculosList(new ArrayList<Veiculos>());
        }
        if (cliente.getCompraveiculolojaList() == null) {
            cliente.setCompraveiculolojaList(new ArrayList<Compraveiculoloja>());
        }
        if (cliente.getVendaList() == null) {
            cliente.setVendaList(new ArrayList<Venda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Veiculos> attachedVeiculosList = new ArrayList<Veiculos>();
            for (Veiculos veiculosListVeiculosToAttach : cliente.getVeiculosList()) {
                veiculosListVeiculosToAttach = em.getReference(veiculosListVeiculosToAttach.getClass(), veiculosListVeiculosToAttach.getIdVeiculo());
                attachedVeiculosList.add(veiculosListVeiculosToAttach);
            }
            cliente.setVeiculosList(attachedVeiculosList);
            List<Compraveiculoloja> attachedCompraveiculolojaList = new ArrayList<Compraveiculoloja>();
            for (Compraveiculoloja compraveiculolojaListCompraveiculolojaToAttach : cliente.getCompraveiculolojaList()) {
                compraveiculolojaListCompraveiculolojaToAttach = em.getReference(compraveiculolojaListCompraveiculolojaToAttach.getClass(), compraveiculolojaListCompraveiculolojaToAttach.getIdCompra());
                attachedCompraveiculolojaList.add(compraveiculolojaListCompraveiculolojaToAttach);
            }
            cliente.setCompraveiculolojaList(attachedCompraveiculolojaList);
            List<Venda> attachedVendaList = new ArrayList<Venda>();
            for (Venda vendaListVendaToAttach : cliente.getVendaList()) {
                vendaListVendaToAttach = em.getReference(vendaListVendaToAttach.getClass(), vendaListVendaToAttach.getIdVenda());
                attachedVendaList.add(vendaListVendaToAttach);
            }
            cliente.setVendaList(attachedVendaList);
            em.persist(cliente);
            for (Veiculos veiculosListVeiculos : cliente.getVeiculosList()) {
                veiculosListVeiculos.getClienteList().add(cliente);
                veiculosListVeiculos = em.merge(veiculosListVeiculos);
            }
            for (Compraveiculoloja compraveiculolojaListCompraveiculoloja : cliente.getCompraveiculolojaList()) {
                Cliente oldFkProprietarioOfCompraveiculolojaListCompraveiculoloja = compraveiculolojaListCompraveiculoloja.getFkProprietario();
                compraveiculolojaListCompraveiculoloja.setFkProprietario(cliente);
                compraveiculolojaListCompraveiculoloja = em.merge(compraveiculolojaListCompraveiculoloja);
                if (oldFkProprietarioOfCompraveiculolojaListCompraveiculoloja != null) {
                    oldFkProprietarioOfCompraveiculolojaListCompraveiculoloja.getCompraveiculolojaList().remove(compraveiculolojaListCompraveiculoloja);
                    oldFkProprietarioOfCompraveiculolojaListCompraveiculoloja = em.merge(oldFkProprietarioOfCompraveiculolojaListCompraveiculoloja);
                }
            }
            for (Venda vendaListVenda : cliente.getVendaList()) {
                Cliente oldFkCompradorOfVendaListVenda = vendaListVenda.getFkComprador();
                vendaListVenda.setFkComprador(cliente);
                vendaListVenda = em.merge(vendaListVenda);
                if (oldFkCompradorOfVendaListVenda != null) {
                    oldFkCompradorOfVendaListVenda.getVendaList().remove(vendaListVenda);
                    oldFkCompradorOfVendaListVenda = em.merge(oldFkCompradorOfVendaListVenda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getCpf()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getCpf());
            List<Veiculos> veiculosListOld = persistentCliente.getVeiculosList();
            List<Veiculos> veiculosListNew = cliente.getVeiculosList();
            List<Compraveiculoloja> compraveiculolojaListOld = persistentCliente.getCompraveiculolojaList();
            List<Compraveiculoloja> compraveiculolojaListNew = cliente.getCompraveiculolojaList();
            List<Venda> vendaListOld = persistentCliente.getVendaList();
            List<Venda> vendaListNew = cliente.getVendaList();
            List<String> illegalOrphanMessages = null;
            for (Compraveiculoloja compraveiculolojaListOldCompraveiculoloja : compraveiculolojaListOld) {
                if (!compraveiculolojaListNew.contains(compraveiculolojaListOldCompraveiculoloja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compraveiculoloja " + compraveiculolojaListOldCompraveiculoloja + " since its fkProprietario field is not nullable.");
                }
            }
            for (Venda vendaListOldVenda : vendaListOld) {
                if (!vendaListNew.contains(vendaListOldVenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venda " + vendaListOldVenda + " since its fkComprador field is not nullable.");
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
            cliente.setVeiculosList(veiculosListNew);
            List<Compraveiculoloja> attachedCompraveiculolojaListNew = new ArrayList<Compraveiculoloja>();
            for (Compraveiculoloja compraveiculolojaListNewCompraveiculolojaToAttach : compraveiculolojaListNew) {
                compraveiculolojaListNewCompraveiculolojaToAttach = em.getReference(compraveiculolojaListNewCompraveiculolojaToAttach.getClass(), compraveiculolojaListNewCompraveiculolojaToAttach.getIdCompra());
                attachedCompraveiculolojaListNew.add(compraveiculolojaListNewCompraveiculolojaToAttach);
            }
            compraveiculolojaListNew = attachedCompraveiculolojaListNew;
            cliente.setCompraveiculolojaList(compraveiculolojaListNew);
            List<Venda> attachedVendaListNew = new ArrayList<Venda>();
            for (Venda vendaListNewVendaToAttach : vendaListNew) {
                vendaListNewVendaToAttach = em.getReference(vendaListNewVendaToAttach.getClass(), vendaListNewVendaToAttach.getIdVenda());
                attachedVendaListNew.add(vendaListNewVendaToAttach);
            }
            vendaListNew = attachedVendaListNew;
            cliente.setVendaList(vendaListNew);
            cliente = em.merge(cliente);
            for (Veiculos veiculosListOldVeiculos : veiculosListOld) {
                if (!veiculosListNew.contains(veiculosListOldVeiculos)) {
                    veiculosListOldVeiculos.getClienteList().remove(cliente);
                    veiculosListOldVeiculos = em.merge(veiculosListOldVeiculos);
                }
            }
            for (Veiculos veiculosListNewVeiculos : veiculosListNew) {
                if (!veiculosListOld.contains(veiculosListNewVeiculos)) {
                    veiculosListNewVeiculos.getClienteList().add(cliente);
                    veiculosListNewVeiculos = em.merge(veiculosListNewVeiculos);
                }
            }
            for (Compraveiculoloja compraveiculolojaListNewCompraveiculoloja : compraveiculolojaListNew) {
                if (!compraveiculolojaListOld.contains(compraveiculolojaListNewCompraveiculoloja)) {
                    Cliente oldFkProprietarioOfCompraveiculolojaListNewCompraveiculoloja = compraveiculolojaListNewCompraveiculoloja.getFkProprietario();
                    compraveiculolojaListNewCompraveiculoloja.setFkProprietario(cliente);
                    compraveiculolojaListNewCompraveiculoloja = em.merge(compraveiculolojaListNewCompraveiculoloja);
                    if (oldFkProprietarioOfCompraveiculolojaListNewCompraveiculoloja != null && !oldFkProprietarioOfCompraveiculolojaListNewCompraveiculoloja.equals(cliente)) {
                        oldFkProprietarioOfCompraveiculolojaListNewCompraveiculoloja.getCompraveiculolojaList().remove(compraveiculolojaListNewCompraveiculoloja);
                        oldFkProprietarioOfCompraveiculolojaListNewCompraveiculoloja = em.merge(oldFkProprietarioOfCompraveiculolojaListNewCompraveiculoloja);
                    }
                }
            }
            for (Venda vendaListNewVenda : vendaListNew) {
                if (!vendaListOld.contains(vendaListNewVenda)) {
                    Cliente oldFkCompradorOfVendaListNewVenda = vendaListNewVenda.getFkComprador();
                    vendaListNewVenda.setFkComprador(cliente);
                    vendaListNewVenda = em.merge(vendaListNewVenda);
                    if (oldFkCompradorOfVendaListNewVenda != null && !oldFkCompradorOfVendaListNewVenda.equals(cliente)) {
                        oldFkCompradorOfVendaListNewVenda.getVendaList().remove(vendaListNewVenda);
                        oldFkCompradorOfVendaListNewVenda = em.merge(oldFkCompradorOfVendaListNewVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getCpf();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getCpf();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Compraveiculoloja> compraveiculolojaListOrphanCheck = cliente.getCompraveiculolojaList();
            for (Compraveiculoloja compraveiculolojaListOrphanCheckCompraveiculoloja : compraveiculolojaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Compraveiculoloja " + compraveiculolojaListOrphanCheckCompraveiculoloja + " in its compraveiculolojaList field has a non-nullable fkProprietario field.");
            }
            List<Venda> vendaListOrphanCheck = cliente.getVendaList();
            for (Venda vendaListOrphanCheckVenda : vendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Venda " + vendaListOrphanCheckVenda + " in its vendaList field has a non-nullable fkComprador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Veiculos> veiculosList = cliente.getVeiculosList();
            for (Veiculos veiculosListVeiculos : veiculosList) {
                veiculosListVeiculos.getClienteList().remove(cliente);
                veiculosListVeiculos = em.merge(veiculosListVeiculos);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
