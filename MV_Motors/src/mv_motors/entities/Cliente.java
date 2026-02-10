/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vchri
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Cliente.findByContato", query = "SELECT c FROM Cliente c WHERE c.contato = :contato"),
    @NamedQuery(name = "Cliente.findByCidade", query = "SELECT c FROM Cliente c WHERE c.cidade = :cidade"),
    @NamedQuery(name = "Cliente.findByEstado", query = "SELECT c FROM Cliente c WHERE c.estado = :estado"),
    @NamedQuery(name = "Cliente.findByBairro", query = "SELECT c FROM Cliente c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Cliente.findByNumero", query = "SELECT c FROM Cliente c WHERE c.numero = :numero"),
    @NamedQuery(name = "Cliente.findByRua", query = "SELECT c FROM Cliente c WHERE c.rua = :rua"),
    @NamedQuery(name = "Cliente.findByCep", query = "SELECT c FROM Cliente c WHERE c.cep = :cep"),
    @NamedQuery(name = "Cliente.findByRg", query = "SELECT c FROM Cliente c WHERE c.rg = :rg")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "contato")
    private String contato;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "rua")
    private String rua;
    @Column(name = "cep")
    private String cep;
    @Column(name = "rg")
    private String rg;
    @ManyToMany(mappedBy = "clienteList")
    private List<Veiculos> veiculosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProprietario")
    private List<Compraveiculoloja> compraveiculolojaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkComprador")
    private List<Venda> vendaList;

    public Cliente() {
    }

    public Cliente(String cpf, String contato, String cidade, String estado, String bairro, Integer numero, String rua, String cep, String rg) {
        this.cpf = cpf;
        this.contato = contato;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.numero = numero;
        this.rua = rua;
        this.cep = cep;
        this.rg = rg;
    }     

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<Veiculos> getVeiculosList() {
        return veiculosList;
    }

    public void setVeiculosList(List<Veiculos> veiculosList) {
        this.veiculosList = veiculosList;
    }

    public List<Compraveiculoloja> getCompraveiculolojaList() {
        return compraveiculolojaList;
    }

    public void setCompraveiculolojaList(List<Compraveiculoloja> compraveiculolojaList) {
        this.compraveiculolojaList = compraveiculolojaList;
    }

    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCpf();
    }
    
}
