/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.test.logic;

import co.edu.uniandes.sisteam.restaurantes.api.IPlatoLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.PlatoLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.PlatoPersistence;
import co.edu.uniandes.sisteam.restaurantes.persistence.DomicilioPersistence;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.sanabria10
 */
@RunWith(Arquillian.class)
public class PlatoLogicTest {
    
    DomicilioEntity fatherEntity;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IPlatoLogic platoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<PlatoEntity> data = new ArrayList<PlatoEntity>();
    
    private List<DomicilioEntity> domicilios = new ArrayList<DomicilioEntity>();
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEntity.class.getPackage())
                .addPackage(PlatoLogic.class.getPackage())
                .addPackage(IPlatoLogic.class.getPackage())
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
                .addPackage(PlatoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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
    
    private void clearData() {
        
        em.createQuery("delete from PlatoEntity").executeUpdate();
        em.createQuery("delete from DomicilioEntity").executeUpdate();
    }
    
   private void insertData() throws ParseException {
        
      
        factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(DomicilioEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        
        List<PlatoEntity> platos = fatherEntity.getPlatos();
        
        for (int i = 0; i < 3; i++) {
            PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class);
            entity.setDomicilio(fatherEntity);
            platos.add(entity);
            em.persist(entity);
            data.add(entity);
        }
        
        fatherEntity.setPlatos(platos);
    }
    
    @Test
    public void createPlatoTest() {
        PlatoEntity newEntity = factory.manufacturePojo(PlatoEntity.class);
        PlatoEntity result = platoLogic.createPlato(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        PlatoEntity entity = em.find(PlatoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test
    public void getPlatosTest() {
        List<PlatoEntity> list = platoLogic.getPlatosDomicilio(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PlatoEntity entity : list) {
            boolean found = false;
            for (PlatoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getPlatoTest() {
        PlatoEntity entity = data.get(0);
        PlatoEntity resultEntity = platoLogic.getPlato(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    @Test
    public void deletePlatoTest() {
        PlatoEntity entity = data.get(1);
        platoLogic.deletePlato(entity.getId());
        PlatoEntity deleted = em.find(PlatoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updatePlatoTest() {
        PlatoEntity entity = data.get(0);
        PlatoEntity platoEntity = factory.manufacturePojo(PlatoEntity.class);

        platoEntity.setId(entity.getId());

        platoLogic.updatePlato(fatherEntity.getId(), platoEntity);

        PlatoEntity resp = em.find(PlatoEntity.class, entity.getId());

        Assert.assertEquals(platoEntity.getName(), resp.getName());
        Assert.assertEquals(platoEntity.getId(), resp.getId());
    }
}
