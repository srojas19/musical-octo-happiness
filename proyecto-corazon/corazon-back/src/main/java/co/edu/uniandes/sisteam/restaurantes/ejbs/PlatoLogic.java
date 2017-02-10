/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.ejbs;

import co.edu.uniandes.sisteam.restaurantes.api.IDomicilioLogic;
import co.edu.uniandes.sisteam.restaurantes.api.IPlatoLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.PlatoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author n.sanabria10
 */
@Stateless
public class PlatoLogic implements IPlatoLogic {
    
    @Inject
    private PlatoPersistence persistence;
    
    @Inject
    private IDomicilioLogic domicilioLogic;
    
    @Override
    public List<PlatoEntity> getPlatosDomicilio(Long id)
    {
        DomicilioEntity domicilio = domicilioLogic.getDomicilio(id);
        
        return domicilio.getPlatos();
    }

    @Override
    public PlatoEntity getPlato(long id) {
        try{
        return persistence.find(id);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El plato no existe");
        }
    }

    @Override
    public List<PlatoEntity> getPlatos() {
        
            return persistence.findAll();  
    }

    @Override
    public PlatoEntity createPlato(Long idD, PlatoEntity pe) {
        
        DomicilioEntity domicilio = domicilioLogic.getDomicilio(idD);
        List<PlatoEntity> platos = domicilio.getPlatos();
        pe.setDomicilio(domicilio);
        persistence.create(pe);
        
        return pe;
    }

    @Override
    public PlatoEntity updatePlato(Long idD, PlatoEntity pe) {
        DomicilioEntity domicilio = domicilioLogic.getDomicilio(idD);
        List<PlatoEntity> platos = domicilio.getPlatos();
        pe.setDomicilio(domicilio);
        return persistence.update(pe);
    }

    @Override
    public void deletePlato(long id) {
        
        
        persistence.delete(id);
    }
}
