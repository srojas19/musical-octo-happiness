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
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class SucursalPersistence {

    private static final Logger LOGGER = Logger.getLogger(SucursalPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamPU")
    protected EntityManager em;

    public SucursalEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando sucursal con id={0}", id);
        return em.find(SucursalEntity.class, id);
    }

    public SucursalEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando sucursal con name = {0}", name);
        TypedQuery<SucursalEntity> q
                = em.createQuery("select u from SucursalEntity u where u.name = :name", SucursalEntity.class);
        q = q.setParameter("name", name);

        List<SucursalEntity> sucursalesSimilarName = q.getResultList();
        if (sucursalesSimilarName.isEmpty()) {
            return null;
        } else {
            return sucursalesSimilarName.get(0);
        }
    }

    public List<SucursalEntity> findAll() {
        LOGGER.info("Consultando todos las sucursales");
        Query q = em.createQuery("select u from SucursalEntity u");
        return q.getResultList();
    }

    public SucursalEntity create(SucursalEntity entity) {
        LOGGER.info("Creando una sucursal nueva " + entity.getName());
        em.persist(entity);

        return entity;
    }

    public SucursalEntity update(SucursalEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando sucursal con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando sucursal con id={0}", id);
        SucursalEntity entity = em.find(SucursalEntity.class, id);
        em.remove(entity);
    }
}
