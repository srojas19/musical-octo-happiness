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


import co.edu.uniandes.sisteam.restaurantes.api.IPlatoEspLogic;
import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.PlatoEspPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class PlatoEspLogic implements IPlatoEspLogic {

    @Inject
    private PlatoEspPersistence persistence;

    @Inject
    private ISucursalLogic sucursalLogic;

    /**
     * Obtiene la lista de los registros de PlatoEsp que pertenecen a una
     * Sucursal.
     *
     * @param sucursalid id de la Sucursal la cual es padre de las PlatosEsp.
     * @return Colección de objetos de PlatoEspEntity.
     *
     */
    @Override
    public List<PlatoEspEntity> getPlatosEsp(Long sucursalid) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(sucursalid);
        return sucursal.getPlatosEsp();
    }

    /**
     * Obtiene los datos de una instancia de PlatoEsp a partir de su ID.
     *
     * @param platoEsp Identificador de la PlatoEsp a consultar
     * @return Instancia de PlatoEspEntity con los datos de la PlatoEsp
     * consultada.
     *
     */
    @Override
    public PlatoEspEntity getPlatoEsp(Long platoEspId) {
        try {
            return persistence.find(platoEspId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La PlatoEsp no existe");
        }
    }

    /**
     * Se encarga de crear una PlatoEsp en la base de datos.
     *
     * @param entity Objeto de PlatoEspEntity con los datos nuevos
     * @param sucursalid id de la Sucursal la cual sera padre de la nueva PlatoEsp.
     * @return Objeto de PlatoEspEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public PlatoEspEntity createPlatoEsp(Long sucursalid, PlatoEspEntity entity) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(sucursalid);
        entity.setSucursal(sucursal);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de PlatoEsp.
     *
     * @param entity Instancia de PlatoEspEntity con los nuevos datos.
     * @param sucursalid id de la Sucursal la  cual sera padre de la PlatoEsp
     * actualizada.
     * @return Instancia de PlatoEspEntity con los datos actualizados.
     *
     */
    @Override
    public PlatoEspEntity updatePlatoEsp(Long sucursalid, PlatoEspEntity entity) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(sucursalid);
        entity.setSucursal(sucursal);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de PlatoEsp de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param sucursalid id de la Sucursal la cual es padre de la PlatoEsp.
     *
     */
    @Override
    public void deletePlatoEsp(Long id) {
        PlatoEspEntity old = getPlatoEsp(id);
        persistence.delete(old.getId());
    }
}
