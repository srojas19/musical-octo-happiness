/*
 *PlatoDTO
 * Objeto de transferencia de datos de Platos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;

/**
 * Objeto de transferencia de datos de Platos.
 *
 * @author n.sanabria10
 */
public class PlatoEspDTO {

    /**
     * Atributos de la clase plato
     */
    private Long id;
    private String name;
    private int precio;
    private String descripcion;

    /**
     * Constructor por defecto
     */
    public PlatoEspDTO() {
    }

    /**
     * Constructor de la clase Plato
     */
    public PlatoEspDTO(Long id, String name, int precio, String descripcion) {
        this.id = id;
        this.name = name;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public PlatoEspDTO(PlatoEspEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.precio = entity.getPrecio();
            this.descripcion = entity.getDescripcion();
        }
    }

    /**
     * Convierte un objeto PlatoEspDTO a PlatoEspEntity.
     *
     * @return Nueva objeto PlatoEspEntity.
     *
     */
    public PlatoEspEntity toEntity() {
        PlatoEspEntity entity = new PlatoEspEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setPrecio(this.getPrecio());
        entity.setDescripcion(this.getDescripcion());
        return entity;
    }

    /**
     * Obtiene el id del plato
     *
     * @return id del plato
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id del plato
     *
     * @param id el id deseado
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el name del plato
     *
     * @return El name del plato
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el name del plato
     *
     * @param name el name deseado
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del plato
     *
     * @return El precio del plato
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio del plato
     *
     * @param precio el precio deseado
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la descripción del plato
     *
     * @return La descripción del plato
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripción del plato
     *
     * @param Descripcion La descripción deseada
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    /**
//     * Devuelve el objeto como una cadena de caracteres
//     * @return El objeto como una cadena de caracteres
//     */
//    @Override
//    public String toString()
//    {
//        return "{ id : " + getId()+ ", name : " + getNombre()+", precio : " + getPrecio()+", descripcion : " + getDescripcion()+", exclusivo : \"" + isExclusivo()+ "\" }" ; 
//    }
}
