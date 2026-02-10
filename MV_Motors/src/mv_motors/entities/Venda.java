/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vchri
 */
@Entity
@Table(name = "venda")
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByIdVenda", query = "SELECT v FROM Venda v WHERE v.idVenda = :idVenda"),
    @NamedQuery(name = "Venda.findByDocCompraVenda", query = "SELECT v FROM Venda v WHERE v.docCompraVenda = :docCompraVenda"),
    @NamedQuery(name = "Venda.findByDataVenda", query = "SELECT v FROM Venda v WHERE v.dataVenda = :dataVenda"),
    @NamedQuery(name = "Venda.findByDataRetirada", query = "SELECT v FROM Venda v WHERE v.dataRetirada = :dataRetirada"),
    @NamedQuery(name = "Venda.findByFormaPagamento", query = "SELECT v FROM Venda v WHERE v.formaPagamento = :formaPagamento")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idVenda")
    private Integer idVenda;
    @Column(name = "docCompraVenda")
    private String docCompraVenda;
    @Basic(optional = false)
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @Column(name = "dataRetirada")
    @Temporal(TemporalType.DATE)
    private Date dataRetirada;
    @Column(name = "formaPagamento")
    private String formaPagamento;
    @JoinColumn(name = "fkComprador", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Cliente fkComprador;
    @JoinColumn(name = "fkFinanciamento", referencedColumnName = "idFinanciamento")
    @ManyToOne
    private Financiamento fkFinanciamento;
    @JoinColumn(name = "fkVeiculos", referencedColumnName = "IdVeiculo")
    @ManyToOne(optional = false)
    private Veiculos fkVeiculos;

    public Venda() {
    }     

    public Venda(String docCompraVenda, Date dataVenda, Date dataRetirada, String formaPagamento, Cliente fkComprador, Veiculos fkVeiculos) {
        //this.idVenda = idVenda;
        this.docCompraVenda = docCompraVenda;
        this.dataVenda = dataVenda;
        this.dataRetirada = dataRetirada;
        this.formaPagamento = formaPagamento;
        this.fkComprador = fkComprador;
        this.fkVeiculos = fkVeiculos;
    }
    
    

    public Venda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Venda(Integer idVenda, Date dataVenda) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public String getDocCompraVenda() {
        return docCompraVenda;
    }

    public void setDocCompraVenda(String docCompraVenda) {
        this.docCompraVenda = docCompraVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Cliente getFkComprador() {
        return fkComprador;
    }

    public void setFkComprador(Cliente fkComprador) {
        this.fkComprador = fkComprador;
    }

    public Financiamento getFkFinanciamento() {
        return fkFinanciamento;
    }

    public void setFkFinanciamento(Financiamento fkFinanciamento) {
        this.fkFinanciamento = fkFinanciamento;
    }

    public Veiculos getFkVeiculos() {
        return fkVeiculos;
    }

    public void setFkVeiculos(Veiculos fkVeiculos) {
        this.fkVeiculos = fkVeiculos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenda != null ? idVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.idVenda == null && other.idVenda != null) || (this.idVenda != null && !this.idVenda.equals(other.idVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_motors.entities.Venda[ idVenda=" + idVenda + " ]";
    }
    
}
