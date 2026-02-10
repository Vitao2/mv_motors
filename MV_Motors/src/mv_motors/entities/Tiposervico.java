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
@Table(name = "tiposervico")
@NamedQueries({
    @NamedQuery(name = "Tiposervico.findAll", query = "SELECT t FROM Tiposervico t"),
    @NamedQuery(name = "Tiposervico.findByIdTipoServico", query = "SELECT t FROM Tiposervico t WHERE t.idTipoServico = :idTipoServico"),
    @NamedQuery(name = "Tiposervico.findByNome", query = "SELECT t FROM Tiposervico t WHERE t.nome = :nome")})
public class Tiposervico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTipoServico")
    private Integer idTipoServico;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @ManyToMany(mappedBy = "tiposervicoList")
    private List<Prestador> prestadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTipoServico")
    private List<Servicoprestado> servicoprestadoList;

    public Tiposervico() {
    }

    public Tiposervico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public Tiposervico(Integer idTipoServico, String nome) {
        this.idTipoServico = idTipoServico;
        this.nome = nome;
    }

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Prestador> getPrestadorList() {
        return prestadorList;
    }

    public void setPrestadorList(List<Prestador> prestadorList) {
        this.prestadorList = prestadorList;
    }

    public List<Servicoprestado> getServicoprestadoList() {
        return servicoprestadoList;
    }

    public void setServicoprestadoList(List<Servicoprestado> servicoprestadoList) {
        this.servicoprestadoList = servicoprestadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoServico != null ? idTipoServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposervico)) {
            return false;
        }
        Tiposervico other = (Tiposervico) object;
        if ((this.idTipoServico == null && other.idTipoServico != null) || (this.idTipoServico != null && !this.idTipoServico.equals(other.idTipoServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_motors.entities.Tiposervico[ idTipoServico=" + idTipoServico + " ]";
    }
    
}
