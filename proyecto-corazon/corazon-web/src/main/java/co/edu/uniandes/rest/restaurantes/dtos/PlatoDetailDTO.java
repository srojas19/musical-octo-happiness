/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;

/**
 *
 * @author n.sanabria10
 */
public class PlatoDetailDTO extends PlatoDTO{
    
    private DomicilioDTO domicilio;
    
    public PlatoDetailDTO() 
    {
        super();
    }
            
   
    public PlatoDetailDTO(PlatoEntity entity) {
        super(entity);
        if (entity != null) {
            if(entity.getDomicilio()!=null){
                this.domicilio = new DomicilioDTO(entity.getDomicilio());
            }
        }

    }

    public PlatoEntity toEntity() {

        PlatoEntity entity = super.toEntity();
        if (domicilio != null) {
          entity.setDomicilio(domicilio.toEntity());
        }
        
        return entity;
    }

    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }
           
}
