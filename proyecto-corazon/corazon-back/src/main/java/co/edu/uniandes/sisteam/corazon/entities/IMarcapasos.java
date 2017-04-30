/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.util.Date;

/**
 *
 * @author sebastiansanchez
 */
public interface IMarcapasos 
{
    public Date getFechaImplante();

    public void setFechaImplante(Date fechaImplante);

    public Date getFinVidaUtil();

    public void setFinVidaUtil(Date finVidaUtil);

    public String getMarca();

    public void setMarca(String marca);

    public String getModelo();

    public void setModelo(String modelo);
            
    public String getNumeroSerie();

    public void setNumeroSerie(String numeroSerie);

    public Double getVoltaje();

    public void setVoltaje(Double voltaje);

    public Double getFrecuenciaElectrica();

    public void setFrecuenciaElectrica(Double frecuenciaElectrica);
    
    public PacienteEntity getPaciente(); 

    public void setPaciente(PacienteEntity paciente);
}
