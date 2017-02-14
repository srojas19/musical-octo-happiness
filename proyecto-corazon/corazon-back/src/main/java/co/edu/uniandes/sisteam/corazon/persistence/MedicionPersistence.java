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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class MedicionPersistence {

    private static final Logger LOGGER = Logger.getLogger(MedicionPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamPU")
    protected EntityManager em;

    public MedicionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medicion con id={0}", id);
        return em.find(MedicionEntity.class, id);
    }
    
    public MedicionEntity findByFecha(Long pacienteId, Date medicionFecha) {
        TypedQuery q = em.createQuery("select d from MedicionEntity d  where d.paciente.id = :pacienteId and d.fecha = :medicionFecha", MedicionEntity.class);
        q = q.setParameter("pacienteId", pacienteId);
        q = q.setParameter("medicionFecha", medicionFecha);

        List<MedicionEntity> medicionesSimilarName = q.getResultList();
        if (medicionesSimilarName.isEmpty()) {
            return null;
        } else {
            return medicionesSimilarName.get(0);
        }

    }

    public List<MedicionEntity> findAll() {
        LOGGER.info("Consultando todas las mediciones");
        Query q = em.createQuery("select u from MedicionEntity u");
        return q.getResultList();
    }

    public List<MedicionEntity> findAllInPaciente(Long pacienteId) {
        LOGGER.log(Level.INFO, "Consultando todas las mediciones de la paciente id={0}", pacienteId);
        TypedQuery q = em.createQuery("select d from MedicionEntity d  where d.paciente.id = :pacienteId", MedicionEntity.class);
        q = q.setParameter("pacienteId", pacienteId);
        return q.getResultList();
    }

    public MedicionEntity create(MedicionEntity entity) {
        LOGGER.info("Creando una medicion nueva");
        em.persist(entity);
        LOGGER.info("Medicion creada");
        return entity;
    }

    public MedicionEntity update(MedicionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando medicion con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en mediciones
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando medicion con id={0}", id);
        MedicionEntity entity = em.find(MedicionEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
