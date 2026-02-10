/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "servicoprestado")
@NamedQueries({
    @NamedQuery(name = "Servicoprestado.findAll", query = "SELECT s FROM Servicoprestado s"),
    @NamedQuery(name = "Servicoprestado.findByIdServico", query = "SELECT s FROM Servicoprestado s WHERE s.idServico = :idServico"),
    @NamedQuery(name = "Servicoprestado.findByValor", query = "SELECT s FROM Servicoprestado s WHERE s.valor = :valor"),
    @NamedQuery(name = "Servicoprestado.findByDataInicio", query = "SELECT s FROM Servicoprestado s WHERE s.dataInicio = :dataInicio"),
    @NamedQuery(name = "Servicoprestado.findByDataFim", query = "SELECT s FROM Servicoprestado s WHERE s.dataFim = :dataFim")})
public class Servicoprestado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idServico")
    private Integer idServico;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @JoinColumn(name = "fkIdPrestador", referencedColumnName = "idPrestador")
    @ManyToOne(optional = false)
    private Prestador fkIdPrestador;
    @JoinColumn(name = "fkIdTipoServico", referencedColumnName = "idTipoServico")
    @ManyToOne(optional = false)
    private Tiposervico fkIdTipoServico;
    @JoinColumn(name = "fkIdVeiculo", referencedColumnName = "IdVeiculo")
    @ManyToOne(optional = false)
    private Veiculos fkIdVeiculo;

    public Servicoprestado() {
    }

    public Servicoprestado(Integer idServico) {
        this.idServico = idServico;
    }

    public Servicoprestado(Integer idServico, BigDecimal valor, Date dataInicio) {
        this.idServico = idServico;
        this.valor = valor;
        this.dataInicio = dataInicio;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Prestador getFkIdPrestador() {
        return fkIdPrestador;
    }

    public void setFkIdPrestador(Prestador fkIdPrestador) {
        this.fkIdPrestador = fkIdPrestador;
    }

    public Tiposervico getFkIdTipoServico() {
        return fkIdTipoServico;
    }

    public void setFkIdTipoServico(Tiposervico fkIdTipoServico) {
        this.fkIdTipoServico = fkIdTipoServico;
    }

    public Veiculos getFkIdVeiculo() {
        return fkIdVeiculo;
    }

    public void setFkIdVeiculo(Veiculos fkIdVeiculo) {
        this.fkIdVeiculo = fkIdVeiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServico != null ? idServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicoprestado)) {
            return false;
        }
        Servicoprestado other = (Servicoprestado) object;
        if ((this.idServico == null && other.idServico != null) || (this.idServico != null && !this.idServico.equals(other.idServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_motors.entities.Servicoprestado[ idServico=" + idServico + " ]";
    }
    
}
