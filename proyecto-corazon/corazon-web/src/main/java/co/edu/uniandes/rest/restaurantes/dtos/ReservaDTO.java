/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;


import java.util.Date;

/**
 *
 * @author BarraganJeronimo
 */
public class ReservaDTO {

    private int numPersonas;
    private int numPiso;
    private long id;

    private Date fecha;
    private String name;
    private String fechaS;

    /**
     * Constructor por defecto
     */
    public ReservaDTO() {
        
    }

    /**
     * Constructor con parámetros.
     *
     * @param nId
     * @param nFecha
     * @param nNumPersonas
     * @param nPiso
     * @param nHora
     */
    public ReservaDTO(long nId, Date nFecha, int nNumPersonas, int nPiso, String nHora) {
        super();
        this.numPersonas = nNumPersonas;
        this.numPiso = nPiso;
        this.id = nId;
        this.fecha = nFecha;
        this.name = nHora;

    }

    /**
     * Crea un objeto ReservaDTO a partir de un objeto ReservaEntity.
     *
     * @param entity Entidad ReservaEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public ReservaDTO(ReservaEntity entity) {
        if (entity != null) {
            this.numPersonas = entity.getNumPersonas();
            this.numPiso = entity.getNumPiso();
            this.id = entity.getId();
            this.fecha = entity.getFecha();
            this.name = entity.getName();
        }
    }

    /**
     * Convierte un objeto ReservaDTO a ReservaEntity.
     *
     * @return Nueva objeto ReservaEntity.
     *
     */
    public ReservaEntity toEntity() {

        ReservaEntity entity = new ReservaEntity();
        entity.setName(this.name);
        entity.setFecha(fecha);
        entity.setNumPersonas(numPersonas);
        entity.setNumPiso(numPiso);
        return entity;

    }

    public Date getFecha() {
        return this.fecha;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public int getNumPiso() {
        return numPiso;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public void setNumPiso(int numPiso) {
        this.numPiso = numPiso;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String nHora) {
        this.name = nHora;
    }

    public String getFechaS() {
        return fechaS;
    }

    public void setFechaS(String fechaS) {
        this.fechaS = fechaS;
    }

}
