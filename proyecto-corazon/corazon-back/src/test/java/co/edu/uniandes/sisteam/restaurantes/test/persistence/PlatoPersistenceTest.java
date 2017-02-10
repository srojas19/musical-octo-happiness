/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.test.persistence;

import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.PlatoPersistence;
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
public class PlatoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEntity.class.getPackage())
                .addPackage(PlatoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Inject
    private PlatoPersistence platoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PlatoEntity> data = new ArrayList<PlatoEntity>();
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete from PlatoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

   
    @Test
    public void createPlatoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlatoEntity newEntity = factory.manufacturePojo(PlatoEntity.class);

        PlatoEntity result = platoPersistence.create(newEntity);

        Assert.assertNotNull(result);
        PlatoEntity entity = em.find(PlatoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    
    @Test
    public void getPlatosTest() {
        List<PlatoEntity> list = platoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PlatoEntity ent : list) {
            boolean found = false;
            for (PlatoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

   
    @Test
    public void getPlatoTest() {
        PlatoEntity entity = data.get(0);
        PlatoEntity newEntity = platoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

   
    @Test
    public void getPlatoByNameTest() {
        PlatoEntity entity = data.get(0);
        PlatoEntity newEntity = platoPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    
    @Test
    public void deletePlatoTest() {
        PlatoEntity entity = data.get(0);
        platoPersistence.delete(entity.getId());
        PlatoEntity deleted = em.find(PlatoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
    @Test
    public void updatePlatoTest() {
        PlatoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PlatoEntity newEntity = factory.manufacturePojo(PlatoEntity.class);

        newEntity.setId(entity.getId());

        platoPersistence.update(newEntity);

        PlatoEntity resp = em.find(PlatoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
