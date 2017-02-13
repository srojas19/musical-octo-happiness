/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicoEspecialistaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author BarraganJeronimo
 */
@Stateless
public class MedicoEspecialistaPersistence {

    private static final Logger LOGGER = Logger.getLogger(MedicoPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public MedicoEspecialistaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medico con id={0}", id);
        return em.find(MedicoEspecialistaEntity.class, id);
    }

    public MedicoEspecialistaEntity findByCedula(int cedula) {
        LOGGER.log(Level.INFO, "Consultando medico con cedula = {0}", cedula);
        TypedQuery<MedicoEspecialistaEntity> q
         = em.createQuery("select u from MedicoEspecialistaEntity u where u.cedula = :cedula", MedicoEspecialistaEntity.class);
        q = q.setParameter("cedula", cedula);

        List<MedicoEspecialistaEntity> medicos = q.getResultList();
        if (medicos.isEmpty()) {
            return null;
        } else {
            return medicos.get(0);
        }
    }

    public MedicoEspecialistaEntity findByTarjetaProfesional(String nTarjetaProfesional) {
        LOGGER.log(Level.INFO, "Consultando medico con número de tarjeta profecional = {0}", nTarjetaProfesional);
        TypedQuery<MedicoEspecialistaEntity> q
         = em.createQuery("select u from MedicoEspecialistaEntity u where u.tarjetaProfesional = :nTarjetaProfesional", MedicoEspecialistaEntity.class);
        q = q.setParameter("nTarjetaProfesional", nTarjetaProfesional);

        List<MedicoEspecialistaEntity> medicos = q.getResultList();
        if (medicos.isEmpty()) {
            return null;
        } else {
            return medicos.get(0);
        }
    }

    public List<MedicoEspecialistaEntity> findAll() {
        LOGGER.info("Consultando todos los medicos");
        Query q = em.createQuery("select u from MedicoEspecialistaEntity u");
        return q.getResultList();
    }

    public MedicoEspecialistaEntity create(MedicoEspecialistaEntity entity) {
        LOGGER.info("Creando un medico nuevo " + entity.getNombres());
        em.persist(entity);

        return entity;
    }

    public MedicoEspecialistaEntity update(MedicoEspecialistaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando medico con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando medico con id={0}", id);
        MedicoEspecialistaEntity entity = em.find(MedicoEspecialistaEntity.class, id);
        em.remove(entity);
    }

}
