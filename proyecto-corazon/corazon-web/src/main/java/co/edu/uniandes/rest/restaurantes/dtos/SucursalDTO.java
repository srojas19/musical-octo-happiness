/*
 * SucursalDTO
 * Objeto de transferencia de datos de Sucursal.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Objeto de transferencia de datos de Sucursal.
 *
 * @author Asistente
 */
public class SucursalDTO {

    private Long id;
    private String name;

    private String ubicacion;
    private String direccion;

    /**
     * Constructor por defecto
     */
    public SucursalDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la sucursal
     * @param ubicacion nombre de la sucursal
     */
    public SucursalDTO(Long id, String name, String ubicacion, String direccion) {
        super();
        this.id = id;
        this.name = name;
        this.ubicacion = ubicacion;
        this.direccion = direccion;

    }

    /**
     * Crea un objeto SucursalDTO a partir de un objeto SucursalEntity.
     *
     * @param entity Entidad SucursalEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public SucursalDTO(SucursalEntity entity) {
        if (entity != null) {
            this.name = entity.getName();
            this.id = entity.getId();
            this.ubicacion = entity.getUbicacion();
            this.direccion = entity.getDireccion();
        }
    }

    /**
     * Convierte un objeto SucursalDTO a SucursalEntity.
     *
     * @return Nueva objeto SucursalEntity.
     *
     */
    public SucursalEntity toEntity() {
        SucursalEntity entity = new SucursalEntity();
        entity.setName(this.getName());
        entity.setId(this.getId());
        entity.setUbicacion(this.getUbicacion());
        entity.setDireccion(this.getDireccion());
        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the name to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the name
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param ubicacion the name to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId()
                + ", ubicacion : \"" + getUbicacion()
                + "\", direccion : \"" + getDireccion()
                + "\" }";
    }

}
