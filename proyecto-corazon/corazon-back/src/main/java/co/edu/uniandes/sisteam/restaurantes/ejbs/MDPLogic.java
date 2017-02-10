/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.sisteam.restaurantes.ejbs;


import co.edu.uniandes.sisteam.restaurantes.api.IClienteLogic;
import co.edu.uniandes.sisteam.restaurantes.api.IMDPLogic;
import co.edu.uniandes.sisteam.restaurantes.api.IMDPLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.MDPPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class MDPLogic implements IMDPLogic {

    @Inject
    private MDPPersistence persistence;

    @Inject
    private IClienteLogic clienteLogic;

    /**
     * Obtiene la lista de los registros de medios de pago que pertenecen a un
     * Cliente.
     *
     * @param clienteId id del Cliente la cual es padre de los medios de pago.
     * @return Colección de objetos de MDPEntity.
     *
     */
    @Override
    public List<MDPEntity> getMDPCliente(Long clienteId) {
        ClienteEntity cliente = clienteLogic.getCliente(clienteId);
        return cliente.getMDPS();
    }

    /**
     * Obtiene los datos de una instancia de MDP a partir de su ID.
     *
     * @param mdpId Identificador del medio de pago a consultar
     * @return Instancia de MDPEntity con los datos del medio de pago
     * consultado.
     *
     */
    @Override
    public MDPEntity getMDP(Long mdpId) {
        try {
            return persistence.find(mdpId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El medio de pago no existe");
        }
    }

    /**
     * Se encarga de crear una medio de pago en la base de datos.
     *
     * @param entity Objeto de MDPEntity con los datos nuevos
     * @param clienteid id del cliente la cual sera padre del nuevo medio de pago.
     * @return Objeto de MDPEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public MDPEntity createMDP(Long clienteid, MDPEntity entity) {
        ClienteEntity cliente = clienteLogic.getCliente(clienteid);
        entity.setCliente(cliente);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de medio de pago.
     *
     * @param entity Instancia de MDPEntity con los nuevos datos.
     * @param clienteid id del cliente la  cual sera padre del medio de pago
     * actualizada.
     * @return Instancia de MDPEntity con los datos actualizados.
     *
     */
    @Override
    public MDPEntity updateMDP(Long clienteid, MDPEntity entity) {
        ClienteEntity cliente = clienteLogic.getCliente(clienteid);
        entity.setCliente(cliente);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de medio de pago de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param clienteid id del cliente la cual es padre del medio de pago.
     *
     */
    @Override
    public void deleteMDP(Long id) {
        MDPEntity old = getMDP(id);
        persistence.delete(old.getId());
    }
}
