/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lnv
 */
@Entity
public class MDPEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    private String tipo;
    private Long num;
    
    public ClienteEntity getCliente(){
        return cliente;
    }
    
    public void setCliente(ClienteEntity cliente){
        this.cliente = cliente;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public Long getNum(){
        return num;
    }
    
    public void setNum(Long num){
        this.num = num;
    }
    
}
