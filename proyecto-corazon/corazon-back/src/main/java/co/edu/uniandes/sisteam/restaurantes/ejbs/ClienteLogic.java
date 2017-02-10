
package co.edu.uniandes.sisteam.restaurantes.ejbs;

import co.edu.uniandes.sisteam.restaurantes.api.IClienteLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.ClientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;


@Stateless
public class ClienteLogic implements IClienteLogic {

    @Inject private ClientePersistence persistence;


    /**
     * Obtiene la lista de los registros de Cliente.
     *
     * @return Colección de objetos de ClienteEntity.
     * 
     */
    @Override
    public List<ClienteEntity> getClientes() {
        return persistence.findAll();
    }


    /**
     * Obtiene los datos de una instancia de Cliente a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ClienteEntity con los datos del Sucursal consultado.
     * 
     */
    public ClienteEntity getCliente(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Cliente en la base de datos.
     *
     * @param entity Objeto de ClienteEntity con los datos nuevos
     * @return Objeto de ClienteEntity con los datos nuevos y su ID.
     * 
     */
    @Override
    public ClienteEntity createCliente(ClienteEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Cliente.
     *
     * @param entity Instancia de ClienteEntity con los nuevos datos.
     * @return Instancia de ClienteEntity con los datos actualizados.
     * 
     */
    @Override
    public ClienteEntity updateCliente(ClienteEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Cliente de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * 
     */
    @Override
    public void deleteCliente(Long id) {
        persistence.delete(id);
    }

    @Override
    public ClienteEntity getClienteByUsername(String name) {
        return persistence.findByName(name);
    }
  
}
