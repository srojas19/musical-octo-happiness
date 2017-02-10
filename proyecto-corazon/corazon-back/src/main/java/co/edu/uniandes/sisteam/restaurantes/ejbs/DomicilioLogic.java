/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.ejbs;

import co.edu.uniandes.sisteam.restaurantes.api.IDomicilioLogic;
import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.api.IClienteLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.DomicilioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class DomicilioLogic implements IDomicilioLogic
{

    @Inject
    private DomicilioPersistence persistence;
    
    @Inject
    private ISucursalLogic sucursalLogic;
    
    @Inject
    private IClienteLogic clienteLogic;
    
    /**
     * Obtiene la lista de los registros todos los domicilios
     * 
     * @return Colección de objetos de DomicilioEntity.
     *
     */
    @Override
    public List<DomicilioEntity> getDomicilios() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de domicilios que pertenecen a una
     * Sucursal.
     *
     * @param idSucursal id de la Sucursal la cual es padre de los domicilios.
     * @return Colección de objetos de DomicilioEntity.
     *
     */
    @Override
    public List<DomicilioEntity> getDomiciliosSucursal(Long idSucursal) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(idSucursal);
        return sucursal.getDomicilios();
    }
    
    
    /**
     * Obtiene la lista de los registros de domicilios que pertenecen a un
     * Cliente.
     *
     * @param idCliente id de la Sucursal la cual es padre de los domicilios.
     * @return Colección de objetos de DomicilioEntity.
     *
     */
    @Override
    public List<DomicilioEntity> getDomiciliosCliente(Long idCliente) {
       ClienteEntity cliente = clienteLogic.getCliente(idCliente);
       return cliente.getDomicilios();
    }

    /**
     * Obtiene los datos de una instancia de domicilio a partir de su ID.
     *
     * @param idDomcilio Identificador del domicilio a consultar
     * @return Instancia de DomicilioEntity con los datos del Domicilio
     * consultada.
     *
     */
    @Override
    public DomicilioEntity getDomicilio(Long idDomicilio) {
        try {
            return persistence.find(idDomicilio);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El domicilio no existe");
        }
    }

    /**
     * Se encarga de crear un domicilio en la base de datos.
     *
     * @param entity Objeto de DomicilioEntity con los datos nuevos
     * @param idSucursal id de la Sucursal la cual sera padre del nuevo domicilio.
     * @param idCliente id de la Sucursal la cual sera padre del nuevo domicilio.
     * @return Objeto de DomicilioEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public DomicilioEntity createDomicilio(Long idCliente, Long idSucursal, DomicilioEntity entity) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(idSucursal);
        ClienteEntity cliente = clienteLogic.getCliente(idCliente);
        entity.setSucursal(sucursal);
        entity.setCliente(cliente);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de domicilio.
     *
     * @param entity Instancia de DomicilioEntity con los nuevos datos.
     * @param idSucursal id de la Sucursal la  cual sera padre del domicilio
     * actualizada.
     * @return Instancia de DomicilioEntity con los datos actualizados.
     *
     */
    @Override
    public DomicilioEntity updateDomicilio(Long idSucursal, DomicilioEntity entity) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(idSucursal);
        entity.setSucursal(sucursal);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de domicilio de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param id id del Domicilio el cual es padre del domicilio.
     *
     */
    @Override
    public void deleteDomicilio(Long id) {
        DomicilioEntity old = getDomicilio(id);
        persistence.delete(old.getId());
    }
    
    @Override
    public void addPlato(Long id, PlatoEntity plato)
    {
        DomicilioEntity entity = getDomicilio(id);
        entity.addPlato(plato);
    }
    
    @Override
    public void deletePlato(Long id, Long idP)
    {
        DomicilioEntity entity = getDomicilio(id);
        entity.deletePlato(idP);
    }
}
