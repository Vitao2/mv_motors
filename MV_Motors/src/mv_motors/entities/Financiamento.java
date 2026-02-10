/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vchri
 */
@Entity
@Table(name = "financiamento")
@NamedQueries({
    @NamedQuery(name = "Financiamento.findAll", query = "SELECT f FROM Financiamento f"),
    @NamedQuery(name = "Financiamento.findByIdFinanciamento", query = "SELECT f FROM Financiamento f WHERE f.idFinanciamento = :idFinanciamento"),
    @NamedQuery(name = "Financiamento.findByValor", query = "SELECT f FROM Financiamento f WHERE f.valor = :valor"),
    @NamedQuery(name = "Financiamento.findByParcelas", query = "SELECT f FROM Financiamento f WHERE f.parcelas = :parcelas"),
    @NamedQuery(name = "Financiamento.findByNomeBanco", query = "SELECT f FROM Financiamento f WHERE f.nomeBanco = :nomeBanco")})
public class Financiamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFinanciamento")
    private Integer idFinanciamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "parcelas")
    private int parcelas;
    @Basic(optional = false)
    @Column(name = "nomeBanco")
    private String nomeBanco;
    @OneToMany(mappedBy = "fkFinanciamento")
    private List<Venda> vendaList;

    public Financiamento() {
    }

    public Financiamento(Integer idFinanciamento) {
        this.idFinanciamento = idFinanciamento;
    }

    public Financiamento(Integer idFinanciamento, BigDecimal valor, int parcelas, String nomeBanco) {
        this.idFinanciamento = idFinanciamento;
        this.valor = valor;
        this.parcelas = parcelas;
        this.nomeBanco = nomeBanco;
    }

    public Integer getIdFinanciamento() {
        return idFinanciamento;
    }

    public void setIdFinanciamento(Integer idFinanciamento) {
        this.idFinanciamento = idFinanciamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
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
        hash += (idFinanciamento != null ? idFinanciamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Financiamento)) {
            return false;
        }
        Financiamento other = (Financiamento) object;
        if ((this.idFinanciamento == null && other.idFinanciamento != null) || (this.idFinanciamento != null && !this.idFinanciamento.equals(other.idFinanciamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_motors.entities.Financiamento[ idFinanciamento=" + idFinanciamento + " ]";
    }
    
}
