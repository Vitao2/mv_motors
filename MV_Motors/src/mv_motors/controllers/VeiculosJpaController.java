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
import mv_motors.entities.Modelos;
import mv_motors.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mv_motors.controllers.exceptions.IllegalOrphanException;
import mv_motors.controllers.exceptions.NonexistentEntityException;
import mv_motors.controllers.exceptions.PreexistingEntityException;
import mv_motors.entities.Compraveiculoloja;
import mv_motors.entities.Venda;
import mv_motors.entities.Servicoprestado;
import mv_motors.entities.Veiculos;

/**
 *
 * @author vchri
 */
public class VeiculosJpaController implements Serializable {

    public VeiculosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Veiculos veiculos) throws PreexistingEntityException, Exception {
        if (veiculos.getClienteList() == null) {
            veiculos.setClienteList(new ArrayList<Cliente>());
        }
        if (veiculos.getCompraveiculolojaList() == null) {
            veiculos.setCompraveiculolojaList(new ArrayList<Compraveiculoloja>());
        }
        if (veiculos.getVendaList() == null) {
            veiculos.setVendaList(new ArrayList<Venda>());
        }
        if (veiculos.getServicoprestadoList() == null) {
            veiculos.setServicoprestadoList(new ArrayList<Servicoprestado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modelos idModelo = veiculos.getIdModelo();
            if (idModelo != null) {
                idModelo = em.getReference(idModelo.getClass(), idModelo.getIdModelo());
                veiculos.setIdModelo(idModelo);
            }
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : veiculos.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getCpf());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            veiculos.setClienteList(attachedClienteList);
            List<Compraveiculoloja> attachedCompraveiculolojaList = new ArrayList<Compraveiculoloja>();
            for (Compraveiculoloja compraveiculolojaListCompraveiculolojaToAttach : veiculos.getCompraveiculolojaList()) {
                compraveiculolojaListCompraveiculolojaToAttach = em.getReference(compraveiculolojaListCompraveiculolojaToAttach.getClass(), compraveiculolojaListCompraveiculolojaToAttach.getIdCompra());
                attachedCompraveiculolojaList.add(compraveiculolojaListCompraveiculolojaToAttach);
            }
            veiculos.setCompraveiculolojaList(attachedCompraveiculolojaList);
            List<Venda> attachedVendaList = new ArrayList<Venda>();
            for (Venda vendaListVendaToAttach : veiculos.getVendaList()) {
                vendaListVendaToAttach = em.getReference(vendaListVendaToAttach.getClass(), vendaListVendaToAttach.getIdVenda());
                attachedVendaList.add(vendaListVendaToAttach);
            }
            veiculos.setVendaList(attachedVendaList);
            List<Servicoprestado> attachedServicoprestadoList = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListServicoprestadoToAttach : veiculos.getServicoprestadoList()) {
                servicoprestadoListServicoprestadoToAttach = em.getReference(servicoprestadoListServicoprestadoToAttach.getClass(), servicoprestadoListServicoprestadoToAttach.getIdServico());
                attachedServicoprestadoList.add(servicoprestadoListServicoprestadoToAttach);
            }
            veiculos.setServicoprestadoList(attachedServicoprestadoList);
            em.persist(veiculos);
            if (idModelo != null) {
                idModelo.getVeiculosList().add(veiculos);
                idModelo = em.merge(idModelo);
            }
            for (Cliente clienteListCliente : veiculos.getClienteList()) {
                clienteListCliente.getVeiculosList().add(veiculos);
                clienteListCliente = em.merge(clienteListCliente);
            }
            for (Compraveiculoloja compraveiculolojaListCompraveiculoloja : veiculos.getCompraveiculolojaList()) {
                Veiculos oldFkVeiculosOfCompraveiculolojaListCompraveiculoloja = compraveiculolojaListCompraveiculoloja.getFkVeiculos();
                compraveiculolojaListCompraveiculoloja.setFkVeiculos(veiculos);
                compraveiculolojaListCompraveiculoloja = em.merge(compraveiculolojaListCompraveiculoloja);
                if (oldFkVeiculosOfCompraveiculolojaListCompraveiculoloja != null) {
                    oldFkVeiculosOfCompraveiculolojaListCompraveiculoloja.getCompraveiculolojaList().remove(compraveiculolojaListCompraveiculoloja);
                    oldFkVeiculosOfCompraveiculolojaListCompraveiculoloja = em.merge(oldFkVeiculosOfCompraveiculolojaListCompraveiculoloja);
                }
            }
            for (Venda vendaListVenda : veiculos.getVendaList()) {
                Veiculos oldFkVeiculosOfVendaListVenda = vendaListVenda.getFkVeiculos();
                vendaListVenda.setFkVeiculos(veiculos);
                vendaListVenda = em.merge(vendaListVenda);
                if (oldFkVeiculosOfVendaListVenda != null) {
                    oldFkVeiculosOfVendaListVenda.getVendaList().remove(vendaListVenda);
                    oldFkVeiculosOfVendaListVenda = em.merge(oldFkVeiculosOfVendaListVenda);
                }
            }
            for (Servicoprestado servicoprestadoListServicoprestado : veiculos.getServicoprestadoList()) {
                Veiculos oldFkIdVeiculoOfServicoprestadoListServicoprestado = servicoprestadoListServicoprestado.getFkIdVeiculo();
                servicoprestadoListServicoprestado.setFkIdVeiculo(veiculos);
                servicoprestadoListServicoprestado = em.merge(servicoprestadoListServicoprestado);
                if (oldFkIdVeiculoOfServicoprestadoListServicoprestado != null) {
                    oldFkIdVeiculoOfServicoprestadoListServicoprestado.getServicoprestadoList().remove(servicoprestadoListServicoprestado);
                    oldFkIdVeiculoOfServicoprestadoListServicoprestado = em.merge(oldFkIdVeiculoOfServicoprestadoListServicoprestado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVeiculos(veiculos.getIdVeiculo()) != null) {
                throw new PreexistingEntityException("Veiculos " + veiculos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Veiculos veiculos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Veiculos persistentVeiculos = em.find(Veiculos.class, veiculos.getIdVeiculo());
            Modelos idModeloOld = persistentVeiculos.getIdModelo();
            Modelos idModeloNew = veiculos.getIdModelo();
            List<Cliente> clienteListOld = persistentVeiculos.getClienteList();
            List<Cliente> clienteListNew = veiculos.getClienteList();
            List<Compraveiculoloja> compraveiculolojaListOld = persistentVeiculos.getCompraveiculolojaList();
            List<Compraveiculoloja> compraveiculolojaListNew = veiculos.getCompraveiculolojaList();
            List<Venda> vendaListOld = persistentVeiculos.getVendaList();
            List<Venda> vendaListNew = veiculos.getVendaList();
            List<Servicoprestado> servicoprestadoListOld = persistentVeiculos.getServicoprestadoList();
            List<Servicoprestado> servicoprestadoListNew = veiculos.getServicoprestadoList();
            List<String> illegalOrphanMessages = null;
            for (Compraveiculoloja compraveiculolojaListOldCompraveiculoloja : compraveiculolojaListOld) {
                if (!compraveiculolojaListNew.contains(compraveiculolojaListOldCompraveiculoloja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compraveiculoloja " + compraveiculolojaListOldCompraveiculoloja + " since its fkVeiculos field is not nullable.");
                }
            }
            for (Venda vendaListOldVenda : vendaListOld) {
                if (!vendaListNew.contains(vendaListOldVenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venda " + vendaListOldVenda + " since its fkVeiculos field is not nullable.");
                }
            }
            for (Servicoprestado servicoprestadoListOldServicoprestado : servicoprestadoListOld) {
                if (!servicoprestadoListNew.contains(servicoprestadoListOldServicoprestado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicoprestado " + servicoprestadoListOldServicoprestado + " since its fkIdVeiculo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idModeloNew != null) {
                idModeloNew = em.getReference(idModeloNew.getClass(), idModeloNew.getIdModelo());
                veiculos.setIdModelo(idModeloNew);
            }
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getCpf());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            veiculos.setClienteList(clienteListNew);
            List<Compraveiculoloja> attachedCompraveiculolojaListNew = new ArrayList<Compraveiculoloja>();
            for (Compraveiculoloja compraveiculolojaListNewCompraveiculolojaToAttach : compraveiculolojaListNew) {
                compraveiculolojaListNewCompraveiculolojaToAttach = em.getReference(compraveiculolojaListNewCompraveiculolojaToAttach.getClass(), compraveiculolojaListNewCompraveiculolojaToAttach.getIdCompra());
                attachedCompraveiculolojaListNew.add(compraveiculolojaListNewCompraveiculolojaToAttach);
            }
            compraveiculolojaListNew = attachedCompraveiculolojaListNew;
            veiculos.setCompraveiculolojaList(compraveiculolojaListNew);
            List<Venda> attachedVendaListNew = new ArrayList<Venda>();
            for (Venda vendaListNewVendaToAttach : vendaListNew) {
                vendaListNewVendaToAttach = em.getReference(vendaListNewVendaToAttach.getClass(), vendaListNewVendaToAttach.getIdVenda());
                attachedVendaListNew.add(vendaListNewVendaToAttach);
            }
            vendaListNew = attachedVendaListNew;
            veiculos.setVendaList(vendaListNew);
            List<Servicoprestado> attachedServicoprestadoListNew = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListNewServicoprestadoToAttach : servicoprestadoListNew) {
                servicoprestadoListNewServicoprestadoToAttach = em.getReference(servicoprestadoListNewServicoprestadoToAttach.getClass(), servicoprestadoListNewServicoprestadoToAttach.getIdServico());
                attachedServicoprestadoListNew.add(servicoprestadoListNewServicoprestadoToAttach);
            }
            servicoprestadoListNew = attachedServicoprestadoListNew;
            veiculos.setServicoprestadoList(servicoprestadoListNew);
            veiculos = em.merge(veiculos);
            if (idModeloOld != null && !idModeloOld.equals(idModeloNew)) {
                idModeloOld.getVeiculosList().remove(veiculos);
                idModeloOld = em.merge(idModeloOld);
            }
            if (idModeloNew != null && !idModeloNew.equals(idModeloOld)) {
                idModeloNew.getVeiculosList().add(veiculos);
                idModeloNew = em.merge(idModeloNew);
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.getVeiculosList().remove(veiculos);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    clienteListNewCliente.getVeiculosList().add(veiculos);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                }
            }
            for (Compraveiculoloja compraveiculolojaListNewCompraveiculoloja : compraveiculolojaListNew) {
                if (!compraveiculolojaListOld.contains(compraveiculolojaListNewCompraveiculoloja)) {
                    Veiculos oldFkVeiculosOfCompraveiculolojaListNewCompraveiculoloja = compraveiculolojaListNewCompraveiculoloja.getFkVeiculos();
                    compraveiculolojaListNewCompraveiculoloja.setFkVeiculos(veiculos);
                    compraveiculolojaListNewCompraveiculoloja = em.merge(compraveiculolojaListNewCompraveiculoloja);
                    if (oldFkVeiculosOfCompraveiculolojaListNewCompraveiculoloja != null && !oldFkVeiculosOfCompraveiculolojaListNewCompraveiculoloja.equals(veiculos)) {
                        oldFkVeiculosOfCompraveiculolojaListNewCompraveiculoloja.getCompraveiculolojaList().remove(compraveiculolojaListNewCompraveiculoloja);
                        oldFkVeiculosOfCompraveiculolojaListNewCompraveiculoloja = em.merge(oldFkVeiculosOfCompraveiculolojaListNewCompraveiculoloja);
                    }
                }
            }
            for (Venda vendaListNewVenda : vendaListNew) {
                if (!vendaListOld.contains(vendaListNewVenda)) {
                    Veiculos oldFkVeiculosOfVendaListNewVenda = vendaListNewVenda.getFkVeiculos();
                    vendaListNewVenda.setFkVeiculos(veiculos);
                    vendaListNewVenda = em.merge(vendaListNewVenda);
                    if (oldFkVeiculosOfVendaListNewVenda != null && !oldFkVeiculosOfVendaListNewVenda.equals(veiculos)) {
                        oldFkVeiculosOfVendaListNewVenda.getVendaList().remove(vendaListNewVenda);
                        oldFkVeiculosOfVendaListNewVenda = em.merge(oldFkVeiculosOfVendaListNewVenda);
                    }
                }
            }
            for (Servicoprestado servicoprestadoListNewServicoprestado : servicoprestadoListNew) {
                if (!servicoprestadoListOld.contains(servicoprestadoListNewServicoprestado)) {
                    Veiculos oldFkIdVeiculoOfServicoprestadoListNewServicoprestado = servicoprestadoListNewServicoprestado.getFkIdVeiculo();
                    servicoprestadoListNewServicoprestado.setFkIdVeiculo(veiculos);
                    servicoprestadoListNewServicoprestado = em.merge(servicoprestadoListNewServicoprestado);
                    if (oldFkIdVeiculoOfServicoprestadoListNewServicoprestado != null && !oldFkIdVeiculoOfServicoprestadoListNewServicoprestado.equals(veiculos)) {
                        oldFkIdVeiculoOfServicoprestadoListNewServicoprestado.getServicoprestadoList().remove(servicoprestadoListNewServicoprestado);
                        oldFkIdVeiculoOfServicoprestadoListNewServicoprestado = em.merge(oldFkIdVeiculoOfServicoprestadoListNewServicoprestado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = veiculos.getIdVeiculo();
                if (findVeiculos(id) == null) {
                    throw new NonexistentEntityException("The veiculos with id " + id + " no longer exists.");
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
            Veiculos veiculos;
            try {
                veiculos = em.getReference(Veiculos.class, id);
                veiculos.getIdVeiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veiculos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Compraveiculoloja> compraveiculolojaListOrphanCheck = veiculos.getCompraveiculolojaList();
            for (Compraveiculoloja compraveiculolojaListOrphanCheckCompraveiculoloja : compraveiculolojaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veiculos (" + veiculos + ") cannot be destroyed since the Compraveiculoloja " + compraveiculolojaListOrphanCheckCompraveiculoloja + " in its compraveiculolojaList field has a non-nullable fkVeiculos field.");
            }
            List<Venda> vendaListOrphanCheck = veiculos.getVendaList();
            for (Venda vendaListOrphanCheckVenda : vendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veiculos (" + veiculos + ") cannot be destroyed since the Venda " + vendaListOrphanCheckVenda + " in its vendaList field has a non-nullable fkVeiculos field.");
            }
            List<Servicoprestado> servicoprestadoListOrphanCheck = veiculos.getServicoprestadoList();
            for (Servicoprestado servicoprestadoListOrphanCheckServicoprestado : servicoprestadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Veiculos (" + veiculos + ") cannot be destroyed since the Servicoprestado " + servicoprestadoListOrphanCheckServicoprestado + " in its servicoprestadoList field has a non-nullable fkIdVeiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Modelos idModelo = veiculos.getIdModelo();
            if (idModelo != null) {
                idModelo.getVeiculosList().remove(veiculos);
                idModelo = em.merge(idModelo);
            }
            List<Cliente> clienteList = veiculos.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.getVeiculosList().remove(veiculos);
                clienteListCliente = em.merge(clienteListCliente);
            }
            em.remove(veiculos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Veiculos> findVeiculosEntities() {
        return findVeiculosEntities(true, -1, -1);
    }

    public List<Veiculos> findVeiculosEntities(int maxResults, int firstResult) {
        return findVeiculosEntities(false, maxResults, firstResult);
    }

    private List<Veiculos> findVeiculosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Veiculos.class));
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

    public Veiculos findVeiculos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Veiculos.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeiculosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Veiculos> rt = cq.from(Veiculos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
