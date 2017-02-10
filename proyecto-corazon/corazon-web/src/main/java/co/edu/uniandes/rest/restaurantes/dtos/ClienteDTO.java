/*
 * ClienteDTO
 * Objeto de transferencia de datos de Clientes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import java.util.ArrayList;


/**
 * Objeto de transferencia de datos de Clientes.
 * @author Asistente
 */
public class ClienteDTO {
    
    /**
     * Atributos de la clase Cliente
     */
    private Long id;
    private String username;
    private String password;
    private int puntos;
    private String direccion;
    
    /**
     * 
     * @param entity 
     */
    public ClienteDTO(ClienteEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.direccion = entity.getDireccion();
            this.password = entity.getPassword();
            this.username = entity.getUsername();
            this.puntos = entity.getPuntos();
        }
    }
    
     /**
     * Convierte un objeto ClienteDTO a ClienteEntity.
     *
     * @return Nueva objeto ClienteEntity.
     * 
     */
    public ClienteEntity toEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(this.getId());
        entity.setUsername(this.getUsername());
        entity.setPassword(this.getPassword());
        entity.setDireccion(this.getDireccion());
        entity.setPuntos(this.getPuntos());
        return entity;
    }
    
    /**
     * Constructor por defecto
     */
    public ClienteDTO(){
        
    }
    
    /**
     * 
     * @param id
     * @param username
     * @param password
     * @param direccion
     * @param puntos
     */
    public ClienteDTO(long id, String username, 
            String password,
            String direccion,
            int puntos){
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.puntos = puntos;
        this.direccion = direccion;
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Retorna el objeto como una cadena de caracteres
     * @return 
     */
    public String toString()
    {
return "{ id : " + getId() + ", username : \"" + getUsername()+ "\" }" ;
    }

}
