/*
 *PlatoDTO
 * Objeto de transferencia de datos de Platos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;


/**
 * Objeto de transferencia de datos de Platos.
 * @author n.sanabria10
 */
public class PlatoDTO {
    
    /**
     * Atributos de la clase plato
     */
    private Long id;
    private String nombre;
    private int precio;
    private String Descripcion;
   // private boolean exclusivo;
    
     /**
     * Constructor por defecto
     */
    public PlatoDTO() {
	}
    
    /**
     * Constructor de la clase Plato
     */
    public PlatoDTO(Long id, String nombre, int precio, String descripcion)
    {
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.Descripcion=descripcion;
    }
    
    public PlatoDTO( PlatoEntity entity) {
	if (entity!=null){
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.precio = entity.getPrecio();
            this.Descripcion = entity.getDescripcion();
        }
    }
    
    public PlatoEntity toEntity(){
        
        PlatoEntity entity = new PlatoEntity();     
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setPrecio(this.precio);
        entity.setDescripcion(this.Descripcion);
        return entity;
    }
    
    /**
     * Obtiene el id del plato
     * @return id del plato
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id del plato
     * @param id el id deseado
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del plato
     * @return El nombre del plato
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del plato
     * @param nombre el nombre deseado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del plato
     * @return El precio del plato
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio del plato
     * @param precio el precio deseado
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la descripción del plato
     * @return La descripción del plato
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * Modifica la descripción del plato
     * @param Descripcion La descripción deseada
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

//    /**
//     * Informa si el plato es exclusivo o no
//     * @return Es exclusivo o no el plato
//     */
//    public boolean isExclusivo() {
//        return exclusivo;
//    }
//
//    /**
//     * Modifica el valor para la exclusividad del plato
//     * @param exclusivo es exclusivo o no
//     */
//    public void setExclusivo(boolean exclusivo) {
//        this.exclusivo = exclusivo;
//    }
    
    /**
     * Devuelve el objeto como una cadena de caracteres
     * @return El objeto como una cadena de caracteres
     */
    @Override
    public String toString()
    {
        return "{ id : " + getId()+ ", nombre : " + getNombre()+", precio : " + getPrecio()+", descripcion : " + getDescripcion()+" }" ; 
    }
    
    
    
}
