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
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class PlatoEspPersistence {

    private static final Logger LOGGER = Logger.getLogger(PlatoEspPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamPU")
    protected EntityManager em;

    public PlatoEspEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando platoEsp con id={0}", id);
        return em.find(PlatoEspEntity.class, id);
    }

    public PlatoEspEntity findByName(Long sucursalId, String platoEspName) {
        TypedQuery q = em.createQuery("select d from PlatoEspEntity d  where d.sucursal.id = :sucursalId and d.name = :platoEspName", PlatoEspEntity.class);
        q = q.setParameter("sucursalId", sucursalId);
        q = q.setParameter("platoEspName", platoEspName);

        List<PlatoEspEntity> platosEspSimilarName = q.getResultList();
        if (platosEspSimilarName.isEmpty()) {
            return null;
        } else {
            return platosEspSimilarName.get(0);
        }

    }

    public List<PlatoEspEntity> findAll() {
        LOGGER.info("Consultando todas las platosEsp");
        Query q = em.createQuery("select u from PlatoEspEntity u");
        return q.getResultList();
    }

    public List<PlatoEspEntity> findAllInSucursal(Long sucursalId) {
        LOGGER.log(Level.INFO, "Consultando todas las platosEsp de la sucursal id={0}", sucursalId);
        TypedQuery q = em.createQuery("select d from PlatoEspEntity d  where d.sucursal.id = :sucursalId", PlatoEspEntity.class);
        q = q.setParameter("sucursalId", sucursalId);
        return q.getResultList();
    }

    public PlatoEspEntity create(PlatoEspEntity entity) {
        LOGGER.info("Creando una platoEsp nueva");
        em.persist(entity);
        LOGGER.info("PlatoEsp creada");
        return entity;
    }

    public PlatoEspEntity update(PlatoEspEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando platoEsp con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en platosEsp
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando platoEsp con id={0}", id);
        PlatoEspEntity entity = em.find(PlatoEspEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
