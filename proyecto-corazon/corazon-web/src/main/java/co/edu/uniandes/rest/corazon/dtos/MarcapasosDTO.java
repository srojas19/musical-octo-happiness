/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import java.util.Date;

/**
 *
 * @author BarraganJeronimo
 */
public class MarcapasosDTO {
    
    
    private Date fechaImplante;

    private Date finVidaUtil;
    
    private String fechaImplanteS;

    private String finVidaUtilS;
    
    private String marca;
    
    private String modelo;
    
    private String numeroSerie;
    
    private Double voltaje;
    
    private Double frecuenciaElectrica;
    
    
    
    

    public Date getFechaImplante() {
        return fechaImplante;
    }

    public void setFechaImplante(Date fechaImplante) {
        this.fechaImplante = fechaImplante;
    }

    public Date getFinVidaUtil() {
        return finVidaUtil;
    }

    public void setFinVidaUtil(Date finVidaUtil) {
        this.finVidaUtil = finVidaUtil;
    }

    public String getFechaImplanteS() {
        return fechaImplanteS;
    }

    public void setFechaImplanteS(String fechaImplanteS) {
        this.fechaImplanteS = fechaImplanteS;
    }

    public String getFinVidaUtilS() {
        return finVidaUtilS;
    }

    public void setFinVidaUtilS(String finVidaUtilS) {
        this.finVidaUtilS = finVidaUtilS;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Double getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(Double voltaje) {
        this.voltaje = voltaje;
    }

    public Double getFrecuenciaElectrica() {
        return frecuenciaElectrica;
    }

    public void setFrecuenciaElectrica(Double frecuenciaElectrica) {
        this.frecuenciaElectrica = frecuenciaElectrica;
    }
    
    
    
    
    
}
