/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lnv
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DomicilioEntity> domicilios = new ArrayList<>();
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<>();
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MDPEntity> mdps = new ArrayList<>();
    
    private String username;
    private String password;
    private int puntos;
    private String direccion;
    
    public List<DomicilioEntity> getDomicilios(){
        return domicilios;
    }
    
    public void setDomicilios(List<DomicilioEntity> domicilios){
        this.domicilios = domicilios;
    }
    
    public List<ReservaEntity> getReservas(){
        return reservas;
    }
    
    public void setReservas(List<ReservaEntity> reservas){
        this.reservas = reservas;
    }
    
    public List<MDPEntity> getMDPS(){
        return mdps;
    }
    
    public void setMPDS(List<MDPEntity> mdps){
        this.mdps = mdps;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
}
