///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.sisteam.restaurantes.test.persistence;
//
//
//import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
//import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
//import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
//import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
//import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
//import co.edu.uniandes.sisteam.restaurantes.persistence.ReservaPersistence;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//
//import org.junit.Assert;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
///**
// *
// * @author BarraganJeronimo
// */
//@RunWith(Arquillian.class)
//public class ReservaPersistenceTest {
//    
//    /**
//     * @return el jar que se desplegará para la prueba
//     */
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(ReservaEntity.class.getPackage())
//                .addPackage(ReservaPersistence.class.getPackage())
//                .addPackage(MesaEntity.class.getPackage())
//                .addPackage(ClienteEntity.class.getPackage())
//                .addPackage(SucursalEntity.class.getPackage())
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * Entidad Father de la ReservaEntity.
//     * 
//     */
//    ClienteEntity fatherEntity;
//    
//    
//    /**
//     * Entidad SucursalEntity para asociar una ReservaEntity.
//     */
//    SucursalEntity sucursalEntity;
//
//    /**
//     * Lista de ReservasEntity.
//     */
//    private List<ReservaEntity> data = new ArrayList<>();
//    
//    
//    /**
//     * Lista de fechas para manejar distintas pruebas para las reservas.  
//     */
//    private List<Date> fechas = new ArrayList<>();
//  
//    
//    @Inject
//    private ReservaPersistence reservaPersistence;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    /**
//     * Configuración inicial de cada método de prueba.
//     *
//     */
//    @Before
//    public void setUp() {
//        try {
//            utx.begin();
//            em.joinTransaction();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     */
//    private void clearData() {
//        em.createQuery("delete  from ReservaEntity").executeUpdate();
//        em.createQuery("delete  from MesaEntity").executeUpdate();
//        em.createQuery("delete  from ClienteEntity").executeUpdate();
//        em.createQuery("delete  from SucursalEntity").executeUpdate();
//    }
//
//   
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas. 
//     */
//    private void insertData() throws ParseException {
//        
//        PodamFactory factory = new PodamFactoryImpl();
//        fatherEntity = factory.manufacturePojo(ClienteEntity.class);
//        fatherEntity.setId(1L);
//        em.persist(fatherEntity);
//        sucursalEntity = factory.manufacturePojo(SucursalEntity.class);
//        sucursalEntity.setId(1L);
//        em.persist(sucursalEntity);
//        
//        ArrayList<MesaEntity> mesas= new ArrayList();
//                
//        for (int i = 0; i < 3; i++) {
//            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
//            MesaEntity mesaEntity = factory.manufacturePojo(MesaEntity.class);
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date fecha = sdf.parse("2"+i+"/10/2016");
//            mesaEntity.setSucursal(sucursalEntity);
//            mesaEntity.addReservas(entity);
//            mesas.add(mesaEntity);
//            entity.setCliente(fatherEntity);
//            entity.setMesa(mesaEntity);
//            entity.setFecha(fecha);
//            em.persist(entity);
//            em.persist(mesaEntity);
//            data.add(entity);
//            fechas.add(fecha);
//        }       
//        sucursalEntity.setMesas(mesas);
//        em.persist(sucursalEntity);
//    }
//    /**
//     * Prueba para crear una ReservaEntity 
//     * @throws java.text.ParseExceptin 
//     */
//    @Test
//    public void createReservaTest() throws ParseException, BusinessLogicException {
//        
//        PodamFactory factory = new PodamFactoryImpl();
//        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);    
//        MesaEntity mesaEntity;
//        mesaEntity = sucursalEntity.getMesas().get(0);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Date fecha = sdf.parse("01/09/2016");
//        mesaEntity.addReservas(newEntity);
//        newEntity.setCliente(fatherEntity);
//        newEntity.setMesa(mesaEntity);
//        newEntity.setFecha(fecha);
//        ReservaEntity result = reservaPersistence.create(newEntity);
//        Assert.assertNotNull(result);
//        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
//        Assert.assertEquals(newEntity.getName(), entity.getName());
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//    }
//
//    /**
//     * Prueba para evaluar que una ReservaEntity no es creada cuando un Cliente ya tiene una reserva para ese dia. 
//     * @throws java.text.ParseException
//     */
//    @Test
//    public void createReservaFailTest() throws ParseException, BusinessLogicException {
//        
//        PodamFactory factory = new PodamFactoryImpl();
//        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);    
//        MesaEntity mesaEntity = sucursalEntity.getMesas().get(0);
//        Date fecha = fechas.get(0); // fecha existente
//        mesaEntity.addReservas(newEntity);
//        newEntity.setCliente(fatherEntity);
//        newEntity.setMesa(mesaEntity);
//        newEntity.setFecha(fecha);
//        ReservaEntity result = reservaPersistence.create(newEntity);
//        Assert.assertNull(result);    
//        ReservaEntity entity = em.find(ReservaEntity.class, newEntity.getId());
//        Assert.assertNull(entity);
//    }
//    
//    /**
//     * Prueba para consultar la lista de reservas de un cliente.
//     */
//    @Test
//    public void getReservasInClienteTest() {
//        
//        List<ReservaEntity> list = reservaPersistence.findByCliente(fatherEntity.getId());
//        Assert.assertEquals(data.size(), list.size());
//        for (ReservaEntity entity : list) {
//            boolean found = false;
//            for (ReservaEntity storedEntity : data) {
//                if (entity.getId().equals(storedEntity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//    
//    /**
//     * Prueba para consultar la lista de reservas de una sucursal.
//     */
//    @Test
//    public void getReservasInSucursalTest() {
//        
//        for (int i = 0; i < 3; i++) {
//        List<ReservaEntity> list = reservaPersistence.findBySucursal(sucursalEntity.getId(),fechas.get(i));
//        Assert.assertEquals(1, list.size());
//        for (ReservaEntity entity : list) {
//            boolean found = false;
//            for (ReservaEntity storedEntity : data) {
//                if (entity.getId().equals(storedEntity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//        }
//    }
//
//    
//    /**
//     * Prueba para consultar una reserva por identificacion. 
//     */
//    @Test
//    public void getReservaTest() {
//        ReservaEntity entity = data.get(0);
//        ReservaEntity resultEntity = reservaPersistence.find(entity.getId());
//        Assert.assertNotNull(resultEntity);
//        Assert.assertEquals(entity.getName(), resultEntity.getName());
//        Assert.assertEquals(entity.getId(), resultEntity.getId());
//    }
//
//    /**
//     * Prueba para eliminar una reserva. 
//     */
//    @Test
//    public void deleteReservaTest() {
//        ReservaEntity entity = data.get(1);
//        reservaPersistence.delete(entity.getId());
//        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar una reserva. 
//     */
//    @Test
//    public void updateReservaTest() throws BusinessLogicException {
//        
//        PodamFactory factory = new PodamFactoryImpl();
//        
//        ReservaEntity entity = data.get(0);
//        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);
//        MesaEntity mesaEntity = factory.manufacturePojo(MesaEntity.class);
//        mesaEntity.setSucursal(sucursalEntity);
//        pojoEntity.setId(entity.getId());
//        pojoEntity.setCliente(fatherEntity);
//        pojoEntity.setFecha(new Date(System.currentTimeMillis()));
//        pojoEntity.setMesa(mesaEntity);
//        reservaPersistence.update(pojoEntity);
//        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());
//
//        Assert.assertEquals(pojoEntity.getName(), resp.getName());
//        Assert.assertEquals(pojoEntity.getId(), resp.getId());
//    }
//    
//}
