/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "compraveiculoloja")
@NamedQueries({
    @NamedQuery(name = "Compraveiculoloja.findAll", query = "SELECT c FROM Compraveiculoloja c"),
    @NamedQuery(name = "Compraveiculoloja.findByIdCompra", query = "SELECT c FROM Compraveiculoloja c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compraveiculoloja.findByDocCompraVenda", query = "SELECT c FROM Compraveiculoloja c WHERE c.docCompraVenda = :docCompraVenda"),
    @NamedQuery(name = "Compraveiculoloja.findByDataCompra", query = "SELECT c FROM Compraveiculoloja c WHERE c.dataCompra = :dataCompra"),
    @NamedQuery(name = "Compraveiculoloja.findByDataEntrega", query = "SELECT c FROM Compraveiculoloja c WHERE c.dataEntrega = :dataEntrega")})
public class Compraveiculoloja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCompra")
    private Integer idCompra;
    @Column(name = "docCompraVenda")
    private String docCompraVenda;
    @Basic(optional = false)
    @Column(name = "dataCompra")
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    @Column(name = "dataEntrega")
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    @JoinColumn(name = "fkProprietario", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Cliente fkProprietario;
    @JoinColumn(name = "fkVeiculos", referencedColumnName = "IdVeiculo")
    @ManyToOne(optional = false)
    private Veiculos fkVeiculos;

    public Compraveiculoloja() {
    }

    public Compraveiculoloja(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Compraveiculoloja(Integer idCompra, Date dataCompra) {
        this.idCompra = idCompra;
        this.dataCompra = dataCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getDocCompraVenda() {
        return docCompraVenda;
    }

    public void setDocCompraVenda(String docCompraVenda) {
        this.docCompraVenda = docCompraVenda;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getFkProprietario() {
        return fkProprietario;
    }

    public void setFkProprietario(Cliente fkProprietario) {
        this.fkProprietario = fkProprietario;
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
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compraveiculoloja)) {
            return false;
        }
        Compraveiculoloja other = (Compraveiculoloja) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_motors.entities.Compraveiculoloja[ idCompra=" + idCompra + " ]";
    }
    
}
