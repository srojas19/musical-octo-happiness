package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import java.sql.Date;

/*
 * MedicionDTO
 * Objeto de transferencia de datos de Mediciones.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class EmergenciaDTO {
    
    private Long id;
    private String gps;
    
    
    
    /**
     * Constructor por defecto
     */
    public EmergenciaDTO() {
	}

    public EmergenciaDTO(Long id, String dictamen){
        this.id = id;
        this.gps = gps;
        
    }
    
    public EmergenciaDTO(EmergenciaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.gps = entity.getGps();
           
        }
    }
    
     /**
     * Convierte un objeto MedicionDTO a MedicionEntity.
     *
     * @return Nueva objeto MedicionEntity.
     * 
     */
    public EmergenciaEntity toEntity() {
        EmergenciaEntity entity = new EmergenciaEntity();
        entity.setId(this.getId());
        entity.setGps(this.getGps());
        
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    

}
