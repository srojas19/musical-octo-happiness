/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.persistence;

import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
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
 * @author n.sanabria10
 */
@Stateless
public class PlatoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PlatoPersistence.class.getName());
    
    @PersistenceContext(unitName = "SisteamPU")
    private EntityManager em;
    
    public PlatoEntity update(PlatoEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando plato con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public PlatoEntity create(PlatoEntity entity) {
        
        LOGGER.info("Creando un plato nuevo");
        em.persist(entity);
        LOGGER.info("Plato creado");
        return entity;
        
    }
    
    public PlatoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando plato con id={0}", id);
        return em.find(PlatoEntity.class, id);
    }
    
      public PlatoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando plato con nombre = {0}", name);
        TypedQuery<PlatoEntity> q
                = em.createQuery("select u from PlatoEntity u where u.name = :name", PlatoEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }
      
     public List<PlatoEntity> findAll() {
        LOGGER.info("Consultando todos los platos");
        Query q = em.createQuery("select u from PlatoEntity u");
        return q.getResultList();
    }
    

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando plato con id={0}", id);
        PlatoEntity entity = em.find(PlatoEntity.class, id);
        em.remove(entity);
    }
}
