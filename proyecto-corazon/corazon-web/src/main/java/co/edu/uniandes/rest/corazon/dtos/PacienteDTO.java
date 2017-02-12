/*
 * PacienteDTO
 * Objeto de transferencia de datos de Paciente.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
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
    private int cedula;
    private String nombres;
    private String apellidos;
    private int edad;
    private char sexo;

    /**
     * Constructor por defecto
     */
    public PacienteDTO() {
    }

    public PacienteDTO(Long id, int cedula, String nombres, String apellidos, int edad, char sexo) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
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
            this.cedula = entity.getCedula();
            this.nombres = entity.getNombres();
            this.apellidos = entity.getApellidos();
            this.edad = entity.getEdad();
            this.sexo = entity.getSexo();
           
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
        entity.setCedula(this.getCedula());
        entity.setNombres(this.getNombres());
        entity.setApellidos(this.getApellidos());
        entity.setEdad(this.getEdad());
        entity.setSexo(this.getSexo());
        
        return entity;
    }

    
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId()
                + ", names : \"" + getNombres()
                + "\", apellidos : \"" + getApellidos()
                + "\" }";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

}
