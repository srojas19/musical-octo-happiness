/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.api;

import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import java.util.List;

/**
 *
 * @author n.sanabria10
 */
public interface IPlatoLogic {
    
    public PlatoEntity getPlato(long id);
    public List<PlatoEntity> getPlatos();
    public List<PlatoEntity> getPlatosDomicilio(Long idDomicilio);
    public PlatoEntity createPlato(Long idD, PlatoEntity pe);
    public PlatoEntity updatePlato(Long id, PlatoEntity pe);
    public void deletePlato(long id);
}
