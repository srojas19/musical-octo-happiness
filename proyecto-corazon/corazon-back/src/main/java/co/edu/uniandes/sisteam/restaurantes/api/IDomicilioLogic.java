/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.api;


import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import java.util.List;

public interface IDomicilioLogic 
{
    public List<DomicilioEntity> getDomicilios();
    public List<DomicilioEntity> getDomiciliosSucursal(Long idSucursal);
    public List<DomicilioEntity> getDomiciliosCliente(Long idCliente);
    public DomicilioEntity getDomicilio(Long idDomicilio);
    public DomicilioEntity createDomicilio(Long idCliente, Long idSucursal, DomicilioEntity entity);
    public DomicilioEntity updateDomicilio(Long idSucursal, DomicilioEntity entity);
    public void addPlato(Long id, PlatoEntity plato);
    public void deletePlato(Long id, Long idP);
    public void deleteDomicilio(Long id);
}
