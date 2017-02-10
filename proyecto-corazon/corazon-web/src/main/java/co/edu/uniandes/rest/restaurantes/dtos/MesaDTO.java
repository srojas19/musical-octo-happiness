/*
 * MesaDTO
 * Objeto de transferencia de datos de Mesas.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class MesaDTO {
    
    private Long id;
    private String name;
    private Long capacidad;
    private Long numPiso;
    
    
    /**
     * Constructor por defecto
     */
    public MesaDTO() {
	}
    
        /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param cap
     * @param piso
     */
    public MesaDTO(Long id, String name, Long cap, Long piso) {
		super();
		this.id = id;
                this.name = name;
		this.capacidad = cap;
                this.numPiso = piso;
	}


    public MesaDTO(MesaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.capacidad = entity.getCapacidad();
            this.numPiso = entity.getNumPiso();
            
        }
    }
    
     /**
     * Convierte un objeto MesaDTO a MesaEntity.
     *
     * @return Nueva objeto MesaEntity.
     * 
     */
    public MesaEntity toEntity() {
        MesaEntity entity = new MesaEntity();
        entity.setName(this.getName());
        entity.setId(this.getId());
        entity.setCapacidad(this.getCapacidad());
        entity.setNumPiso(this.getNumPiso());
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
     * @return the id
     */
    public Long getCapacidad() {
        return capacidad;
    }

    /**
     * @param cap the id to set
     */
    public void setCapacidad(Long cap) {
        this.capacidad = cap;
    }
    
    /**
     * @return the id
     */
    public Long getNumPiso() {
        return numPiso;
    }

    /**
     * @param cap the id to set
     */
    public void setNumPiso(Long cap) {
        this.numPiso = cap;
    }

    
    
}
