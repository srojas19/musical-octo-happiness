/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.persistence;

import co.edu.uniandes.sisteam.corazon.entities.HistoriaClinicaEntity;
import co.edu.uniandes.sisteam.corazon.entities.MarcapasosRealEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author BarraganJeronimo
 */
@Stateless
public class MarcapasosPersistence {

    private static final Logger LOGGER = Logger.getLogger(MedicoPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamCorazonPU")
    protected EntityManager em;

    public MarcapasosRealEntity getMarcapasosPaciente(Long idPaciente) {
        TypedQuery q = em.createQuery("select u from MarcapasosRealEntity u where u.paciente.id = :idPaciente", HistoriaClinicaEntity.class);
        q = q.setParameter("idPaciente", idPaciente);
        List<MarcapasosRealEntity> similarName = q.getResultList();
        if (similarName.isEmpty()) {
            return null;
        } else {
            return similarName.get(0);
        }
    }

    public MarcapasosRealEntity create(MarcapasosRealEntity entity) {
        LOGGER.info("Creando un marcapasos nuevo " + entity.getNumeroSerie());
        em.persist(entity);
        return entity;
    }

    public MarcapasosRealEntity update(MarcapasosRealEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando marcapasos con id={0}", entity.getId());
        return em.merge(entity);
    }

}
