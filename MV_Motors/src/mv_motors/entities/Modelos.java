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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vchri
 */
@Entity
@Table(name = "modelos")
@NamedQueries({
    @NamedQuery(name = "Modelos.findAll", query = "SELECT m FROM Modelos m"),
    @NamedQuery(name = "Modelos.findByIdModelo", query = "SELECT m FROM Modelos m WHERE m.idModelo = :idModelo"),
    @NamedQuery(name = "Modelos.findByMarca", query = "SELECT m FROM Modelos m WHERE m.marca = :marca"),
    @NamedQuery(name = "Modelos.findByMotor", query = "SELECT m FROM Modelos m WHERE m.motor = :motor"),
    @NamedQuery(name = "Modelos.findByNome", query = "SELECT m FROM Modelos m WHERE m.nome = :nome"),
    @NamedQuery(name = "Modelos.findByCilindradas", query = "SELECT m FROM Modelos m WHERE m.cilindradas = :cilindradas"),
    @NamedQuery(name = "Modelos.findByTransmissao", query = "SELECT m FROM Modelos m WHERE m.transmissao = :transmissao"),
    @NamedQuery(name = "Modelos.findByCapacidadeDoTanque", query = "SELECT m FROM Modelos m WHERE m.capacidadeDoTanque = :capacidadeDoTanque"),
    @NamedQuery(name = "Modelos.findByConsumoMedio", query = "SELECT m FROM Modelos m WHERE m.consumoMedio = :consumoMedio"),
    @NamedQuery(name = "Modelos.findByPotenciaMotor", query = "SELECT m FROM Modelos m WHERE m.potenciaMotor = :potenciaMotor"),
    @NamedQuery(name = "Modelos.findByArCondicionado", query = "SELECT m FROM Modelos m WHERE m.arCondicionado = :arCondicionado"),
    @NamedQuery(name = "Modelos.findByAirbagDianteiro", query = "SELECT m FROM Modelos m WHERE m.airbagDianteiro = :airbagDianteiro"),
    @NamedQuery(name = "Modelos.findByAirbagTraseiro", query = "SELECT m FROM Modelos m WHERE m.airbagTraseiro = :airbagTraseiro"),
    @NamedQuery(name = "Modelos.findByDirecaoHidraulica", query = "SELECT m FROM Modelos m WHERE m.direcaoHidraulica = :direcaoHidraulica"),
    @NamedQuery(name = "Modelos.findByVidroEletrico", query = "SELECT m FROM Modelos m WHERE m.vidroEletrico = :vidroEletrico"),
    @NamedQuery(name = "Modelos.findByCamerasLaterais", query = "SELECT m FROM Modelos m WHERE m.camerasLaterais = :camerasLaterais"),
    @NamedQuery(name = "Modelos.findByCombustivel", query = "SELECT m FROM Modelos m WHERE m.combustivel = :combustivel"),
    @NamedQuery(name = "Modelos.findByCameraTraseira", query = "SELECT m FROM Modelos m WHERE m.cameraTraseira = :cameraTraseira")})
public class Modelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdModelo")
    private Integer idModelo;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "motor")
    private String motor;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "cilindradas")
    private int cilindradas;
    @Basic(optional = false)
    @Column(name = "transmissao")
    private String transmissao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "capacidadeDoTanque")
    private BigDecimal capacidadeDoTanque;
    @Basic(optional = false)
    @Column(name = "consumoMedio")
    private BigDecimal consumoMedio;
    @Basic(optional = false)
    @Column(name = "potenciaMotor")
    private BigDecimal potenciaMotor;
    @Basic(optional = false)
    @Column(name = "arCondicionado")
    private boolean arCondicionado;
    @Basic(optional = false)
    @Column(name = "airbagDianteiro")
    private boolean airbagDianteiro;
    @Basic(optional = false)
    @Column(name = "airbagTraseiro")
    private boolean airbagTraseiro;
    @Basic(optional = false)
    @Column(name = "direcaoHidraulica")
    private boolean direcaoHidraulica;
    @Basic(optional = false)
    @Column(name = "vidroEletrico")
    private boolean vidroEletrico;
    @Basic(optional = false)
    @Column(name = "camerasLaterais")
    private boolean camerasLaterais;
    @Basic(optional = false)
    @Column(name = "combustivel")
    private String combustivel;
    @Basic(optional = false)
    @Column(name = "cameraTraseira")
    private boolean cameraTraseira;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModelo")
    private List<Veiculos> veiculosList;

    public Modelos() {
    }

    public Modelos(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Modelos(String marca, String motor, String nome, int cilindradas, String transmissao, BigDecimal capacidadeDoTanque, BigDecimal consumoMedio, BigDecimal potenciaMotor, boolean arCondicionado, boolean airbagDianteiro, boolean airbagTraseiro, boolean direcaoHidraulica, boolean vidroEletrico, boolean camerasLaterais, String combustivel, boolean cameraTraseira) {
        //this.idModelo = idModelo;
        this.marca = marca;
        this.motor = motor;
        this.nome = nome;
        this.cilindradas = cilindradas;
        this.transmissao = transmissao;
        this.capacidadeDoTanque = capacidadeDoTanque;
        this.consumoMedio = consumoMedio;
        this.potenciaMotor = potenciaMotor;
        this.arCondicionado = arCondicionado;
        this.airbagDianteiro = airbagDianteiro;
        this.airbagTraseiro = airbagTraseiro;
        this.direcaoHidraulica = direcaoHidraulica;
        this.vidroEletrico = vidroEletrico;
        this.camerasLaterais = camerasLaterais;
        this.combustivel = combustivel;
        this.cameraTraseira = cameraTraseira;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public BigDecimal getCapacidadeDoTanque() {
        return capacidadeDoTanque;
    }

    public void setCapacidadeDoTanque(BigDecimal capacidadeDoTanque) {
        this.capacidadeDoTanque = capacidadeDoTanque;
    }

    public BigDecimal getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(BigDecimal consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public BigDecimal getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(BigDecimal potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public boolean getArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean getAirbagDianteiro() {
        return airbagDianteiro;
    }

    public void setAirbagDianteiro(boolean airbagDianteiro) {
        this.airbagDianteiro = airbagDianteiro;
    }

    public boolean getAirbagTraseiro() {
        return airbagTraseiro;
    }

    public void setAirbagTraseiro(boolean airbagTraseiro) {
        this.airbagTraseiro = airbagTraseiro;
    }

    public boolean getDirecaoHidraulica() {
        return direcaoHidraulica;
    }

    public void setDirecaoHidraulica(boolean direcaoHidraulica) {
        this.direcaoHidraulica = direcaoHidraulica;
    }

    public boolean getVidroEletrico() {
        return vidroEletrico;
    }

    public void setVidroEletrico(boolean vidroEletrico) {
        this.vidroEletrico = vidroEletrico;
    }

    public boolean getCamerasLaterais() {
        return camerasLaterais;
    }

    public void setCamerasLaterais(boolean camerasLaterais) {
        this.camerasLaterais = camerasLaterais;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public boolean getCameraTraseira() {
        return cameraTraseira;
    }

    public void setCameraTraseira(boolean cameraTraseira) {
        this.cameraTraseira = cameraTraseira;
    }

    public List<Veiculos> getVeiculosList() {
        return veiculosList;
    }

    public void setVeiculosList(List<Veiculos> veiculosList) {
        this.veiculosList = veiculosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModelo != null ? idModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelos)) {
            return false;
        }
        Modelos other = (Modelos) object;
        if ((this.idModelo == null && other.idModelo != null) || (this.idModelo != null && !this.idModelo.equals(other.idModelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
    
}
