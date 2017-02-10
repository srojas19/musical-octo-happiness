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


import co.edu.uniandes.sisteam.restaurantes.api.IMesaLogic;
import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.MesaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class MesaLogic implements IMesaLogic {

    @Inject
    private MesaPersistence persistence;

    @Inject
    private ISucursalLogic sucursalLogic;

    /**
     * Obtiene la lista de los registros de Mesa que pertenecen a una
     * Sucursal.
     *
     * @param sucursalid id de la Sucursal la cual es padre de las Mesas.
     * @return Colección de objetos de MesaEntity.
     *
     */
    @Override
    public List<MesaEntity> getMesas(Long sucursalid) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(sucursalid);
        return sucursal.getMesas();
    }

    /**
     * Obtiene los datos de una instancia de Mesa a partir de su ID.
     *
     * @param mesaId Identificador de la Mesa a consultar
     * @return Instancia de MesaEntity con los datos de la Mesa
     * consultada.
     *
     */
    @Override
    public MesaEntity getMesa(Long mesaId) {
        try {
            return persistence.find(mesaId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La Mesa no existe");
        }
    }

    /**
     * Se encarga de crear una Mesa en la base de datos.
     *
     * @param entity Objeto de MesaEntity con los datos nuevos
     * @param sucursalid id de la Sucursal la cual sera padre de la nueva Mesa.
     * @return Objeto de MesaEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public MesaEntity createMesa(Long sucursalid, MesaEntity entity) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(sucursalid);
        //sucursal.add(entity);
        entity.setSucursal(sucursal);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Mesa.
     *
     * @param entity Instancia de MesaEntity con los nuevos datos.
     * @param sucursalid id de la Sucursal la  cual sera padre de la Mesa
     * actualizada.
     * @return Instancia de MesaEntity con los datos actualizados.
     *
     */
    @Override
    public MesaEntity updateMesa(Long sucursalid, MesaEntity entity) {
        SucursalEntity sucursal = sucursalLogic.getSucursal(sucursalid);
        entity.setSucursal(sucursal);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Mesa de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param sucursalid id de la Sucursal la cual es padre de la Mesa.
     *
     */
    @Override
    public void deleteMesa(Long id) {
        MesaEntity old = getMesa(id);
        persistence.delete(old.getId());
    }
}
