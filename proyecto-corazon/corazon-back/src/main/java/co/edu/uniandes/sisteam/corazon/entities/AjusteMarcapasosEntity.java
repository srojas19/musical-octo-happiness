/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author BarraganJeronimo
 */
class AjusteMarcapasosEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAjuste;

    private Double nuevaFrecuenciaElectrica;
    private Double nuevoVoltaje;

    @PodamExclude
    @ManyToOne
    private Marcapasos marcapasos;

    @PodamExclude
    @ManyToOne
    private MedicoEspecialistaEntity medicoResponsable;

    public Date getFechaAjuste() {
        return fechaAjuste;
    }

    public void setFechaAjuste(Date fechaAjuste) {
        this.fechaAjuste = fechaAjuste;
    }

    public Double getNuevaFrecuenciaElectrica() {
        return nuevaFrecuenciaElectrica;
    }

    public void setNuevaFrecuenciaElectrica(Double nuevaFrecuenciaElectrica) {
        this.nuevaFrecuenciaElectrica = nuevaFrecuenciaElectrica;
    }

    public Double getNuevoVoltaje() {
        return nuevoVoltaje;
    }

    public void setNuevoVoltaje(Double nuevoVoltaje) {
        this.nuevoVoltaje = nuevoVoltaje;
    }

    public Marcapasos getMarcapasos() {
        return marcapasos;
    }

    public void setMarcapasos(Marcapasos marcapasos) {
        this.marcapasos = marcapasos;
    }

    public MedicoEspecialistaEntity getMedicoResponsable() {
        return medicoResponsable;
    }

    public void setMedicoResponsable(MedicoEspecialistaEntity medicoResponsable) {
        this.medicoResponsable = medicoResponsable;
    }

}
