package co.edu.uniandes.sisteam.corazon.persistence;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.sisteam.restaurantes.persistence;
//
//import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
//import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//
///**
// *
// * @author BarraganJeronimo
// */
//@Stateless
//public class ReservaPersistence {
//
//    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());
//
//    @PersistenceContext(unitName = "SisteamPU")
//    protected EntityManager em;
//
//    public ReservaEntity update(ReservaEntity entity)throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "Actualizando reserva con id={0}", entity.getId());   
//        if (clienteTieneReservaEnFecha(entity.getCliente().getId(), entity.getFecha()) == false) {
//            em.persist(entity);
//            LOGGER.info("Reserva creada");
//            return em.merge(entity);
//        } 
//        else
//        {
//            throw new BusinessLogicException("Reserva no fue creada usuario ya tiene una reserva para ese dia");
//        }
//    }
//
//    public ReservaEntity create(ReservaEntity entity) throws BusinessLogicException {
//
//        LOGGER.info("Creando una reserva nueva");
//        if (clienteTieneReservaEnFecha(entity.getCliente().getId(), entity.getFecha()) == false) {
//            em.persist(entity);
//            LOGGER.info("Reserva creada");
//            return entity;
//        } else {
//               
//            LOGGER.info("Reserva no fue creada usuario ya tiene una reserva para ese dia");
//            throw new BusinessLogicException("Reserva no fue creada usuario ya tiene una reserva para ese dia");
//           
//        }
//    }
//
//    public ReservaEntity find(Long id) {
//        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", id);
//        ReservaEntity resp = em.find(ReservaEntity.class, id);
//        return resp;
//
//    }
//
//    public ReservaEntity findByName(String name) {
//        LOGGER.log(Level.INFO, "Consultando reserva con name = {0}", name);
//        TypedQuery<ReservaEntity> q
//                = em.createQuery("select u from ReservaEntity u where u.name = :name", ReservaEntity.class);
//        q = q.setParameter("name", name);
//
//        List<ReservaEntity> reservas = q.getResultList();
//        if (reservas.isEmpty()) {
//            return null;
//        } else {
//            return reservas.get(0);
//        }
//    }
//
//    public List<ReservaEntity> findByCliente(Long clienteid) {
//        LOGGER.log(Level.INFO, "Consultando reservas con clienteid = {0}", clienteid);
//        TypedQuery<ReservaEntity> q
//                = em.createQuery("select u from ReservaEntity u where u.cliente.id = :clienteid", ReservaEntity.class);
//        q = q.setParameter("clienteid", clienteid);
//        return q.getResultList();
//    }
//
//    public List<ReservaEntity> findBySucursal(Long sucursalid, Date fecha) {
//
//        LOGGER.log(Level.INFO, "Consultando reservas con sucursalid = {0}", sucursalid);
//        TypedQuery<ReservaEntity> q
//                = em.createQuery("select u from ReservaEntity u where u.mesa.sucursal.id = :sucursalid", ReservaEntity.class);
//        q = q.setParameter("sucursalid", sucursalid);
//
//        List<ReservaEntity> resp = new ArrayList();
//
//        for (ReservaEntity reserva : q.getResultList()) {
//            if (reserva.getFecha().equals(fecha)) {
//                resp.add(reserva);
//            }
//        }
//        return resp;
//    }
//
//    public List<ReservaEntity> findAll() {
//        LOGGER.info("Consultando todos las reservas");
//        Query q = em.createQuery("select u from ReservaEntity u");
//        return q.getResultList();
//    }
//
//    public void delete(Long id) {
//        LOGGER.log(Level.INFO, "Borrando reserva con id={0}", id);
//        ReservaEntity entity = em.find(ReservaEntity.class, id);
//        em.remove(entity);
//    }
//
//    /*
//    * Metodo auxiliar que determina si el Cliente cuenta con reserva en la fecha dada.
//     */
//    public boolean clienteTieneReservaEnFecha(Long clienteid, Date fecha) {
//
//        LOGGER.log(Level.INFO, "Consultando si el usurario tiene reserva con cliente de id = {0}", clienteid);
//        TypedQuery<ReservaEntity> q
//                = em.createQuery("select u from ReservaEntity u where u.cliente.id = :clienteid", ReservaEntity.class);
//        q = q.setParameter("clienteid", clienteid);
//
//        for (ReservaEntity reserva : q.getResultList()) {
//
//            if (reserva.getFecha().equals(fecha)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
