/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;

/**
 *
 * @author lnv
 */
public class MDPDTO {
    
    // Atributos de la clase
    private Long id;
    private String tipo;
    private Long num;
    
    public MDPDTO(){
        
    }
    
    /**
     * 
     * @param id
     * @param tipo
     * @param num 
     */
    public MDPDTO(Long id, String tipo, Long num){
        this.id=id;
        this.tipo=tipo;
        this.num=num;
    }
    
    public MDPDTO(MDPEntity entity){
        if(entity != null){
            this.num = entity.getNum();
            this.tipo = entity.getTipo();
            this.id=entity.getId();
        }
    }
    
     /**
     * Convierte un objeto MDPDTO a MDPEntity.
     *
     * @return Nueva objeto MDPEntity.
     * 
     */
    public MDPEntity toEntity() {
        MDPEntity entity = new MDPEntity();
        entity.setId(this.getId());
        entity.setNum(this.getNum());
        entity.setTipo(this.getTipo());
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

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the num
     */
    public Long getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(Long num) {
        this.num = num;
    }
    
    
}
