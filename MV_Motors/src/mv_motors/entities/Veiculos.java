/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mv_motors.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vchri
 */
@Entity
@Table(name = "veiculos")
@NamedQueries({
    @NamedQuery(name = "Veiculos.findAll", query = "SELECT v FROM Veiculos v"),
    @NamedQuery(name = "Veiculos.findByIdVeiculo", query = "SELECT v FROM Veiculos v WHERE v.idVeiculo = :idVeiculo"),
    @NamedQuery(name = "Veiculos.findByComplementos", query = "SELECT v FROM Veiculos v WHERE v.complementos = :complementos"),
    @NamedQuery(name = "Veiculos.findByQuilometragem", query = "SELECT v FROM Veiculos v WHERE v.quilometragem = :quilometragem"),
    @NamedQuery(name = "Veiculos.findByPreco", query = "SELECT v FROM Veiculos v WHERE v.preco = :preco"),
    @NamedQuery(name = "Veiculos.findByAno", query = "SELECT v FROM Veiculos v WHERE v.ano = :ano"),
    @NamedQuery(name = "Veiculos.findByDocumento", query = "SELECT v FROM Veiculos v WHERE v.documento = :documento"),
    @NamedQuery(name = "Veiculos.findByPlaca", query = "SELECT v FROM Veiculos v WHERE v.placa = :placa")})
public class Veiculos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdVeiculo")
    private Integer idVeiculo;
    @Column(name = "complementos")
    private String complementos;
    @Basic(optional = false)
    @Column(name = "quilometragem")
    private int quilometragem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "preco")
    private BigDecimal preco;
    @Basic(optional = false)
    @Column(name = "ano")
    private int ano;
    @Basic(optional = false)
    @Column(name = "documento")
    private String documento;
    @Column(name = "placa")
    private String placa;
    @JoinTable(name = "veiculoconcessionado", joinColumns = {
        @JoinColumn(name = "fkVeiculos", referencedColumnName = "IdVeiculo")}, inverseJoinColumns = {
        @JoinColumn(name = "fkProprietario", referencedColumnName = "cpf")})
    @ManyToMany
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVeiculos")
    private List<Compraveiculoloja> compraveiculolojaList;
    @JoinColumn(name = "IdModelo", referencedColumnName = "IdModelo")
    @ManyToOne(optional = false)
    private Modelos idModelo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkVeiculos")
    private List<Venda> vendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdVeiculo")
    private List<Servicoprestado> servicoprestadoList;

    public Veiculos() {
    }

    public Veiculos(Integer idVeiculo, String complementos, int quilometragem, BigDecimal preco, int ano, String documento, String placa, Modelos idModelo) {
        this.idVeiculo = idVeiculo;
        this.complementos = complementos;
        this.quilometragem = quilometragem;
        this.preco = preco;
        this.ano = ano;
        this.documento = documento;
        this.placa = placa;
        this.idModelo = idModelo;
    }    

    public Veiculos(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Veiculos(Integer idVeiculo, int quilometragem, BigDecimal preco, int ano, String documento) {
        this.idVeiculo = idVeiculo;
        this.quilometragem = quilometragem;
        this.preco = preco;
        this.ano = ano;
        this.documento = documento;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getComplementos() {
        return complementos;
    }

    public void setComplementos(String complementos) {
        this.complementos = complementos;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public List<Compraveiculoloja> getCompraveiculolojaList() {
        return compraveiculolojaList;
    }

    public void setCompraveiculolojaList(List<Compraveiculoloja> compraveiculolojaList) {
        this.compraveiculolojaList = compraveiculolojaList;
    }

    public Modelos getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelos idModelo) {
        this.idModelo = idModelo;
    }

    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
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
        hash += (idVeiculo != null ? idVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculos)) {
            return false;
        }
        Veiculos other = (Veiculos) object;
        if ((this.idVeiculo == null && other.idVeiculo != null) || (this.idVeiculo != null && !this.idVeiculo.equals(other.idVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {      
        return String.valueOf(this.getIdVeiculo()).concat(" | " + String.valueOf(this.getIdModelo()));     
    }
    
}
