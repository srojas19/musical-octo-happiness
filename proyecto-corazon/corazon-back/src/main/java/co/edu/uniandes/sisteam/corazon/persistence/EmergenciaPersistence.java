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
package co.edu.uniandes.sisteam.corazon.persistence;

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class EmergenciaPersistence {

    private static final Logger LOGGER = Logger.getLogger(EmergenciaPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public EmergenciaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando emergencia con id={0}", id);
        return em.find(EmergenciaEntity.class, id);
    }
    
    

    public List<EmergenciaEntity> findAll() {
        LOGGER.info("Consultando todas las emergencias");
        Query q = em.createQuery("select u from EmergenciaEntity u");
        return q.getResultList();
    }

    public List<EmergenciaEntity> findAllInMedicion(Long medicionId) {
        LOGGER.log(Level.INFO, "Consultando todas las emergencias de la medicion id={0}", medicionId);
        TypedQuery q = em.createQuery("select d from EmergenciaEntity d  where d.medicion.id = :medicionId", EmergenciaEntity.class);
        q = q.setParameter("medicionId", medicionId);
        return q.getResultList();
    }

    public EmergenciaEntity create(EmergenciaEntity entity) {
        LOGGER.info("Creando una emergencia nueva");
        em.persist(entity);
        LOGGER.info("Emergencia creada");
        return entity;
    }

    public EmergenciaEntity update(EmergenciaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando emergencia con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en emergencias
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando emergencia con id={0}", id);
        EmergenciaEntity entity = em.find(EmergenciaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
