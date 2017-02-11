/*
 * PacienteDTO
 * Objeto de transferencia de datos de Paciente.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.PacienteEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Objeto de transferencia de datos de Paciente.
 *
 * @author Asistente
 */
public class PacienteDTO {

    private Long id;   
    private Long idSmartphone;
    private String name;
    private String apellido;
    private String diagnostico;

    /**
     * Constructor por defecto
     */
    public PacienteDTO() {
    }

    public PacienteDTO(Long id, Long idSmartphone, String name, String apellido, String diagnostico) {
        this.id = id;
        this.idSmartphone = idSmartphone;
        this.name = name;
        this.apellido = apellido;
        this.diagnostico = diagnostico;
    }
    
    

    /**
     * Crea un objeto PacienteDTO a partir de un objeto PacienteEntity.
     *
     * @param entity Entidad PacienteEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public PacienteDTO(PacienteEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.idSmartphone = entity.getIdSmartphone();
            this.name = entity.getName();
            this.apellido = entity.getApellido();
            this.diagnostico = entity.getDiagnostico();
        }
    }

    /**
     * Convierte un objeto PacienteDTO a PacienteEntity.
     *
     * @return Nueva objeto PacienteEntity.
     *
     */
    public PacienteEntity toEntity() {
        PacienteEntity entity = new PacienteEntity();
        entity.setId(this.getId());
        entity.setIdSmartphone(this.getIdSmartphone());
        entity.setName(this.getName());
        entity.setApellido(this.getApellido());
        entity.setDiagnostico(this.getDiagnostico());
        
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSmartphone() {
        return idSmartphone;
    }

    public void setIdSmartphone(Long idSmartphone) {
        this.idSmartphone = idSmartphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId()
                + ", name : \"" + getName()
                + "\", apellido : \"" + getApellido()
                + "\" }";
    }

}
