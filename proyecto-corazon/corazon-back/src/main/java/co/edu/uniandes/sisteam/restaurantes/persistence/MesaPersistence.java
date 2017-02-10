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
package co.edu.uniandes.sisteam.restaurantes.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class MesaPersistence {

    private static final Logger LOGGER = Logger.getLogger(MesaPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamPU")
    protected EntityManager em;

    public MesaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando mesa con id={0}", id);
        return em.find(MesaEntity.class, id);
    }
    
    public MesaEntity findByName(Long sucursalId, String mesaName) {
        TypedQuery q = em.createQuery("select d from MesaEntity d  where d.sucursal.id = :sucursalId and d.name = :mesaName", MesaEntity.class);
        q = q.setParameter("sucursalId", sucursalId);
        q = q.setParameter("mesaName", mesaName);

        List<MesaEntity> mesasSimilarName = q.getResultList();
        if (mesasSimilarName.isEmpty()) {
            return null;
        } else {
            return mesasSimilarName.get(0);
        }

    }

    public List<MesaEntity> findAll() {
        LOGGER.info("Consultando todas las mesas");
        Query q = em.createQuery("select u from MesaEntity u");
        return q.getResultList();
    }

    public List<MesaEntity> findAllInSucursal(Long sucursalId) {
        LOGGER.log(Level.INFO, "Consultando todas las mesas de la sucursal id={0}", sucursalId);
        TypedQuery q = em.createQuery("select d from MesaEntity d  where d.sucursal.id = :sucursalId", MesaEntity.class);
        q = q.setParameter("sucursalId", sucursalId);
        return q.getResultList();
    }

    public MesaEntity create(MesaEntity entity) {
        LOGGER.info("Creando una mesa nueva");
        em.persist(entity);
        LOGGER.info("Mesa creada");
        return entity;
    }

    public MesaEntity update(MesaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando mesa con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en mesas
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando mesa con id={0}", id);
        MesaEntity entity = em.find(MesaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
