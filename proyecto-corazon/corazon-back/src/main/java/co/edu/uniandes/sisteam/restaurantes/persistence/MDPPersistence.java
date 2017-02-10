/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.persistence;

import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


@Stateless
public class MDPPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MDPPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamPU")
    protected EntityManager em;

       public MDPEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medio de pago con id={0}", id);
        return em.find(MDPEntity.class, id);
    }
    
    public MDPEntity update(MDPEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando medio de pago con id={0}", entity.getId());
        return em.merge(entity);
    }

    public MDPEntity create(MDPEntity entity) {
        
        LOGGER.info("Creando un medio de pago nuevo");
        em.persist(entity);
        LOGGER.info("Medio de pago creado");
        return entity;
        
    }

    public MDPEntity findInCliente(Long id) {
        LOGGER.log(Level.INFO, "Consultando medio de pago con id={0}", id);
        return em.find(MDPEntity.class, id);
    }
    
    public List<MDPEntity> findAllInCliente(Long clienteId) {
        LOGGER.log(Level.INFO, "Consultando consultando todos los medios de pago id={0}", clienteId);
        TypedQuery q = em.createQuery("select d from MDPEntity d  where d.cliente.id = :clienteId", MDPEntity.class);
        q = q.setParameter("clienteId", clienteId);
        return q.getResultList();
    }
    
    public List<MDPEntity> findAll() {
        LOGGER.info("Consultando todos los medios de pago");
        Query q = em.createQuery("select u from MDPEntity u");
        return q.getResultList();
    }
    

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando medio de pago con id={0}", id);
        MDPEntity entity = em.find(MDPEntity.class, id);
        em.remove(entity);
    }
}
