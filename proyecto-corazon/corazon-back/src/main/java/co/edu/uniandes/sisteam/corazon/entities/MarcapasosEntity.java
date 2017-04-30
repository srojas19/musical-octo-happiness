/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author BarraganJeronimo
 */
@Entity
public class MarcapasosEntity extends BaseEntity implements Serializable,IMarcapasos {

    @PodamExclude
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaImplante;

    @PodamExclude
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVidaUtil;
    
    private String marca;
    
    private String modelo;
    
    private String numeroSerie;
    
    private Double voltaje;
    
    private Double frecuenciaElectrica;
    
    @OneToOne(fetch = FetchType.LAZY)
    private PacienteEntity paciente;

    

    
    @Override
    public Date getFechaImplante() {
        return fechaImplante;
    }

    @Override
    public void setFechaImplante(Date fechaImplante) {
        this.fechaImplante = fechaImplante;
    }

    @Override
    public Date getFinVidaUtil() {
        return finVidaUtil;
    }

    @Override
    public void setFinVidaUtil(Date finVidaUtil) {
        this.finVidaUtil = finVidaUtil;
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String getNumeroSerie() {
        return numeroSerie;
    }

    @Override
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    @Override
    public Double getVoltaje() {
        return voltaje;
    }

    @Override
    public void setVoltaje(Double voltaje) {
        this.voltaje = voltaje;
    }

    @Override
    public Double getFrecuenciaElectrica() {
        return frecuenciaElectrica;
    }

    @Override
    public void setFrecuenciaElectrica(Double frecuenciaElectrica) {
        this.frecuenciaElectrica = frecuenciaElectrica;
    }
    
    @Override
    public PacienteEntity getPaciente() {
        return paciente;
    }

    @Override
    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

}
