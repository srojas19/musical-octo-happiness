/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class DomicilioPersistence
{
    private static final Logger LOGGER = Logger.getLogger(DomicilioPersistence.class.getName());

    @PersistenceContext(unitName = "SisteamPU")
    protected EntityManager em;

    public DomicilioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando domicilio con id={0}", id);
        return em.find(DomicilioEntity.class, id);
    }

    public List<DomicilioEntity> findAll() {
        LOGGER.info("Consultando todos los domicilios");
        Query q = em.createQuery("select d from DomicilioEntity d");
        List<DomicilioEntity> l = q.getResultList();
        int i=0;
        while (i<l.size())
        {
            System.out.println(l.get(i).getId());
            i++;
        }
        return q.getResultList();
    }

    public List<DomicilioEntity> findAllInSucursal(Long idSucursal) {
        LOGGER.log(Level.INFO, "Consultando todos los domicilios de la sucursal id={0}", idSucursal);
        TypedQuery q = em.createQuery("select from DomicilioEntity d where d.sucursal.id = :idSucursal", DomicilioEntity.class);
        q = q.setParameter("sucursalId", idSucursal);
        return q.getResultList();
    }
    
    public List<DomicilioEntity> findAllInCliente(Long idCliente) {
        LOGGER.log(Level.INFO, "Consultando todos los domicilios del cliente id={0}", idCliente);
        TypedQuery q = em.createQuery("select from DomicilioEntity d where d.cliente.id = :idCliente", DomicilioEntity.class);
        q = q.setParameter("sucursalId", idCliente);
        return q.getResultList();
    }

    public DomicilioEntity create(DomicilioEntity entity) {
        LOGGER.info("Creando un domicilio nuevo");
        em.persist(entity);
        LOGGER.info("Domicilio creada");
        return entity;
    }

    public DomicilioEntity update(DomicilioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando domicilio con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe en mesas
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando domicilio con id={0}", id);
        DomicilioEntity entity = em.find(DomicilioEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
