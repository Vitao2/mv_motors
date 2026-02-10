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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "prestador")
@NamedQueries({
    @NamedQuery(name = "Prestador.findAll", query = "SELECT p FROM Prestador p"),
    @NamedQuery(name = "Prestador.findByIdPrestador", query = "SELECT p FROM Prestador p WHERE p.idPrestador = :idPrestador"),
    @NamedQuery(name = "Prestador.findByNome", query = "SELECT p FROM Prestador p WHERE p.nome = :nome"),
    @NamedQuery(name = "Prestador.findByNumero", query = "SELECT p FROM Prestador p WHERE p.numero = :numero"),
    @NamedQuery(name = "Prestador.findByEstado", query = "SELECT p FROM Prestador p WHERE p.estado = :estado"),
    @NamedQuery(name = "Prestador.findByRua", query = "SELECT p FROM Prestador p WHERE p.rua = :rua"),
    @NamedQuery(name = "Prestador.findByBairro", query = "SELECT p FROM Prestador p WHERE p.bairro = :bairro"),
    @NamedQuery(name = "Prestador.findByCep", query = "SELECT p FROM Prestador p WHERE p.cep = :cep"),
    @NamedQuery(name = "Prestador.findByCidade", query = "SELECT p FROM Prestador p WHERE p.cidade = :cidade"),
    @NamedQuery(name = "Prestador.findByContato", query = "SELECT p FROM Prestador p WHERE p.contato = :contato"),
    @NamedQuery(name = "Prestador.findByTipoPrestador", query = "SELECT p FROM Prestador p WHERE p.tipoPrestador = :tipoPrestador")})
public class Prestador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPrestador")
    private Integer idPrestador;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "estado")
    private String estado;
    @Column(name = "rua")
    private String rua;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cep")
    private String cep;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "contato")
    private String contato;
    @Column(name = "tipoPrestador")
    private String tipoPrestador;
    @JoinTable(name = "presta", joinColumns = {
        @JoinColumn(name = "fkIdPrestador", referencedColumnName = "idPrestador")}, inverseJoinColumns = {
        @JoinColumn(name = "fkIdTipoServico", referencedColumnName = "idTipoServico")})
    @ManyToMany
    private List<Tiposervico> tiposervicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPrestador")
    private List<Servicoprestado> servicoprestadoList;

    public Prestador() {
    }

    public Prestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public Prestador(Integer idPrestador, String nome) {
        this.idPrestador = idPrestador;
        this.nome = nome;
    }

    public Integer getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTipoPrestador() {
        return tipoPrestador;
    }

    public void setTipoPrestador(String tipoPrestador) {
        this.tipoPrestador = tipoPrestador;
    }

    public List<Tiposervico> getTiposervicoList() {
        return tiposervicoList;
    }

    public void setTiposervicoList(List<Tiposervico> tiposervicoList) {
        this.tiposervicoList = tiposervicoList;
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
        hash += (idPrestador != null ? idPrestador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestador)) {
            return false;
        }
        Prestador other = (Prestador) object;
        if ((this.idPrestador == null && other.idPrestador != null) || (this.idPrestador != null && !this.idPrestador.equals(other.idPrestador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_motors.entities.Prestador[ idPrestador=" + idPrestador + " ]";
    }
    
}
