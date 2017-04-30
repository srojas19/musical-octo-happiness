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
 * @author 
 */
public class MarcapasosProxy implements IMarcapasos {

    private MarcapasosRealEntity real;

    public MarcapasosProxy()
    {
        real = new MarcapasosRealEntity();
    }

    @Override
    public Date getFechaImplante() {
        return real.getFechaImplante();
    }

    @Override
    public void setFechaImplante(Date fechaImplante) {
        real.setFechaImplante(fechaImplante);
    }

    @Override
    public Date getFinVidaUtil() {
        return real.getFinVidaUtil();
    }

    @Override
    public void setFinVidaUtil(Date finVidaUtil) {
        real.setFinVidaUtil(finVidaUtil);
    }

    @Override
    public String getMarca() {
        return real.getMarca();
    }

    @Override
    public void setMarca(String marca) {
        real.setMarca(marca);
    }

    @Override
    public String getModelo() {
        return real.getModelo();
    }

    @Override
    public void setModelo(String modelo) {
        real.setModelo(modelo);
    }

    @Override
    public String getNumeroSerie() {
        return real.getNumeroSerie();
    }

    @Override
    public void setNumeroSerie(String numeroSerie) {
        real.setNumeroSerie(numeroSerie);
    }

    @Override
    public Double getVoltaje() {
        return real.getVoltaje();
    }

    @Override
    public void setVoltaje(Double voltaje) {
        real.setVoltaje(voltaje);
    }

    @Override
    public Double getFrecuenciaElectrica() {
        return real.getFrecuenciaElectrica();
    }

    @Override
    public void setFrecuenciaElectrica(Double frecuenciaElectrica) {
        real.setFrecuenciaElectrica(frecuenciaElectrica);
    }

    @Override
    public PacienteEntity getPaciente() {
        return real.getPaciente();
    }

    @Override
    public void setPaciente(PacienteEntity paciente) {
        real.setPaciente(paciente);
    }

}
