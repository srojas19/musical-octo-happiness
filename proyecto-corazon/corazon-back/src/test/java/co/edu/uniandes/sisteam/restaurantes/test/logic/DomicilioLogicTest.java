/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.test.logic;

import co.edu.uniandes.sisteam.restaurantes.api.IDomicilioLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.DomicilioLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
import co.edu.uniandes.sisteam.restaurantes.persistence.DomicilioPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.transaction.NotSupportedException;
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

@RunWith(Arquillian.class)
public class DomicilioLogicTest
{
    ClienteEntity fatherEntityC;
    SucursalEntity fatherEntityS;

    private final PodamFactory factory = new PodamFactoryImpl();
 
    @Inject
    private IDomicilioLogic domicilioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private final List<DomicilioEntity> data = new ArrayList<>();
    private List<PlatoEntity> fatherEntityP = new ArrayList<>();

    private List<SucursalEntity> sucursalData = new ArrayList<>();
    private List<ClienteEntity> clienteData = new ArrayList<>();

    /**
     *
     * @return 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioLogic.class.getPackage())
                .addPackage(IDomicilioLogic.class.getPackage())
                .addPackage(BusinessLogicException.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
            
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
    private void clearData() 
    {
        em.createQuery("delete from DomicilioEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from PlatoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() 
    {
        fatherEntityS = factory.manufacturePojo(SucursalEntity.class);
        fatherEntityS.setId(1L);
        fatherEntityC = factory.manufacturePojo(ClienteEntity.class);
        fatherEntityP = new ArrayList<>();
        fatherEntityC.setId(1L);
        
        for (int i = 0; i < 4; i++) 
        {
            PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class);
            fatherEntityP.add(entity);
            em.persist(entity);
        }
        
        em.persist(fatherEntityS);
        em.persist(fatherEntityC);
        
        for (int i = 0; i < 3; i++) {
            DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);
            entity.setSucursal(fatherEntityS);
            entity.setCliente(fatherEntityC);
            entity.setPlatos(fatherEntityP);
            data.add(entity);
            em.persist(entity);
            fatherEntityP.remove(3-i);
        }
    }
    
    /**
     * Prueba para crear un Domicilio
     */
    @Test
    public void createDomicilioTest()
    {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        DomicilioEntity result = domicilioLogic.createDomicilio(fatherEntityC.getId(), fatherEntityS.getId(), newEntity);
        Assert.assertNotNull(result);
        DomicilioEntity entity = em.find(DomicilioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Domicilios de una Sucursal
     */
    @Test
    public void getDomiciliosSucursalTest() {
        List<DomicilioEntity> list = domicilioLogic.getDomiciliosSucursal(fatherEntityS.getId());
        Assert.assertEquals(data.size(), list.size());
        for (DomicilioEntity entity : list) {
            boolean found = false;
            for (DomicilioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        
    }
    
    /**
     * Prueba para consultar la lista de Domicilios de un Cliente
     * 
     */
    @Test
    public void getDomiciliosClienteTest() {
        List<DomicilioEntity> list = domicilioLogic.getDomiciliosCliente(fatherEntityC.getId());
        Assert.assertEquals(data.size(), list.size());
        for (DomicilioEntity entity : list) {
            boolean found = false;
            for (DomicilioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Domicilio
     */
    @Test
    public void getDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity resultEntity = domicilioLogic.getDomicilio(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Domicilio
     */
    @Test
    public void deleteDomicilioTest() {
        DomicilioEntity entity = data.get(1);
        domicilioLogic.deleteDomicilio(entity.getId());
        DomicilioEntity deleted = em.find(DomicilioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Domicilio
     */
    @Test
    public void updateDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);

        pojoEntity.setId(entity.getId());

        domicilioLogic.updateDomicilio(fatherEntityS.getId(), pojoEntity);

        DomicilioEntity resp = em.find(DomicilioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
