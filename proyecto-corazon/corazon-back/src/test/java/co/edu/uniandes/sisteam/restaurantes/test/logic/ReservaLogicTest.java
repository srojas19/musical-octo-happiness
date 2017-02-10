/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.test.logic;

import co.edu.uniandes.sisteam.restaurantes.api.IClienteLogic;
import co.edu.uniandes.sisteam.restaurantes.api.IReservaLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.ReservaLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
import co.edu.uniandes.sisteam.restaurantes.persistence.MesaPersistence;
import co.edu.uniandes.sisteam.restaurantes.persistence.ReservaPersistence;
import co.edu.uniandes.sisteam.restaurantes.persistence.SucursalPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author BarraganJeronimo
 */
@RunWith(Arquillian.class)
public class ReservaLogicTest {
    
    
    /**
     *
     */
    ClienteEntity fatherEntity;
    
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private IReservaLogic reservaLogic;

    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 
     */
    @Inject
    private UserTransaction utx;

    /**
     * 
     */
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();

    private List<MesaEntity> mesas = new ArrayList<MesaEntity>();
    
    /**
     *
     */
    private SucursalEntity sucursalEntity ;

    
    private List<Date> fechas = new ArrayList<>();


    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(IReservaLogic.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaPersistence.class.getPackage())
                .addPackage(SucursalPersistence.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(IClienteLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba. 
     */
    private void clearData() {
        
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from MesaEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
        
      
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() throws ParseException {
        
      
        factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(ClienteEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        sucursalEntity = factory.manufacturePojo(SucursalEntity.class);
        sucursalEntity.setId(1L);
        em.persist(sucursalEntity);
        
        
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            MesaEntity mesaEntity = factory.manufacturePojo(MesaEntity.class);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse("2"+i+"/10/2016");
            mesaEntity.setSucursal(sucursalEntity);
            entity.setCliente(fatherEntity);
            entity.setMesa(mesaEntity);
            entity.setFecha(fecha);
            em.persist(entity);
            em.persist(mesaEntity);
            data.add(entity);
            fechas.add(fecha);
            mesas.add(mesaEntity);
        }
    }
    /**
     * Prueba para crear un una reserva , con fecha del sistema. 
     */
    @Test
    public void createReservaTest() throws BusinessLogicException {
       
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFecha(new Date(System.currentTimeMillis()));
        ReservaEntity result = reservaLogic.createReserva(fatherEntity.getId(),mesas.get(0).getId(),newEntity);
        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para evaluar que una ReservaEntity no es creada cuando un Cliente ya tiene una reserva para ese dia. 
     * @throws java.text.ParseException
     */
    @Test
    public void createReservaFailTest() throws ParseException, BusinessLogicException {
        
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFecha(fechas.get(0));
        ReservaEntity result = reservaLogic.createReserva(fatherEntity.getId(),mesas.get(0).getId(),newEntity);
        Assert.assertNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, newEntity.getId());
        Assert.assertNull(entity);
    }

    /**
     * Prueba para consultar la lista de Sucursales
     *
     * 
     */
    @Test
    public void getReservasTest() {
        List<ReservaEntity> list = reservaLogic.getReservasCliente(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity entity : list) {
            boolean found = false;
            for (ReservaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Sucursal
     *
     * 
     */
    @Test
    public void getReservaTest() {
        ReservaEntity entity = data.get(0);
        ReservaEntity resultEntity = reservaLogic.getReservaId(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Sucursal
     *
     * 
     */
    @Test
    public void deleteReservaTest() {
        ReservaEntity entity = data.get(1);
        reservaLogic.deleteReserva(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Sucursal
     *
     * 
     */
    @Test
    public void updateReservaTest() throws BusinessLogicException {
        
        ReservaEntity entity = data.get(0);
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setMesa(mesas.get(0));
        pojoEntity.setFecha(new Date(System.currentTimeMillis()));

        reservaLogic.updateReserva(fatherEntity.getId(),mesas.get(0).getId(), pojoEntity);

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
}