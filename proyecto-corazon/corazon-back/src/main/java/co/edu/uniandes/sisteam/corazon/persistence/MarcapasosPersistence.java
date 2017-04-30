/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.persistence;

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

    public MarcapasosRealEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando marcapasos con id={0}", id);
        return em.find(MarcapasosRealEntity.class, id);
    }

    public MarcapasosRealEntity findByNumeroSerie(String numeroSerie) {
        LOGGER.log(Level.INFO, "Consultando marcapasos con numeroSerie= {0}", numeroSerie);
        TypedQuery<MarcapasosRealEntity> q
         = em.createQuery("select u from MarcapasosEntity u where u.numeroSerie= :numeroSerie", MarcapasosRealEntity.class);
        q = q.setParameter("numeroSerie", numeroSerie);

        List<MarcapasosRealEntity> marcapasoss = q.getResultList();
        if (marcapasoss.isEmpty()) {
            return null;
        } else {
           return marcapasoss.get(0);
        }
    }


    public List<MarcapasosRealEntity> findAll() {
        LOGGER.info("Consultando todos los marcapasoss");
        Query q = em.createQuery("select u from MarcapasosEntity u");
        return q.getResultList();
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

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando marcapasos con id={0}", id);
        MarcapasosRealEntity entity = em.find(MarcapasosRealEntity.class, id);
        em.remove(entity);
    }

    
    
}
