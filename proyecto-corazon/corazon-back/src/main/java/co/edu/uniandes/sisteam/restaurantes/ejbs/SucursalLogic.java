package co.edu.uniandes.sisteam.restaurantes.ejbs;

import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
import co.edu.uniandes.sisteam.restaurantes.persistence.SucursalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class SucursalLogic implements ISucursalLogic {

    @Inject
    private SucursalPersistence persistence;

    /**
     * Obtiene la lista de los registros de Sucursal.
     *
     * @return Colección de objetos de SucursalEntity.
     *
     */
    @Override
    public List<SucursalEntity> getSucursales() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Sucursal a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SucursalEntity con los datos del Sucursal
     * consultado.
     *
     */
    public SucursalEntity getSucursal(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Sucursal en la base de datos.
     *
     * @param entity Objeto de SucursalEntity con los datos nuevos
     * @return Objeto de SucursalEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException
     *
     */
    @Override
    public SucursalEntity createSucursal(SucursalEntity entity) throws BusinessLogicException 
    {
        SucursalEntity alreadyExist = getSucursalByName(entity.getName());
        if (alreadyExist != null) {
            throw new BusinessLogicException("Ya existe una sucursal con ese nombre");
        } else {
            persistence.create(entity);
        }
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Sucursal.
     *
     * @param entity Instancia de SucursalEntity con los nuevos datos.
     * @return Instancia de SucursalEntity con los datos actualizados.
     *
     */
    @Override
    public SucursalEntity updateSucursal(SucursalEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Sucursal de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    @Override
    public void deleteSucursal(Long id) {
        persistence.delete(id);
    }

    @Override
    public SucursalEntity getSucursalByName(String name) {
        return persistence.findByName(name);
    }

}
