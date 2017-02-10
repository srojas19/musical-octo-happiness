/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.sisteam.restaurantes.test.logic;


import co.edu.uniandes.sisteam.restaurantes.api.IPlatoEspLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.PlatoEspLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.PlatoEspPersistence;
import java.util.ArrayList;
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
 */
@RunWith(Arquillian.class)
public class PlatoEspLogicTest {

    /**
     *
     */
    SucursalEntity fatherEntity;

    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private IPlatoEspLogic platoEspLogic;

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
    private List<PlatoEspEntity> data = new ArrayList<PlatoEspEntity>();

    /**
     *
     */
    private List<SucursalEntity> sucursalData = new ArrayList<>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEspEntity.class.getPackage())
                .addPackage(PlatoEspLogic.class.getPackage())
                .addPackage(IPlatoEspLogic.class.getPackage())
                .addPackage(PlatoEspPersistence.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
            
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
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
     *
     *
     */
    private void clearData() 
    {
        em.createQuery("delete from PlatoEspEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() 
    {

        fatherEntity = factory.manufacturePojo(SucursalEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        
        for (int i = 0; i < 3; i++) 
        {
            PlatoEspEntity entity = factory.manufacturePojo(PlatoEspEntity.class);
            entity.setSucursal(fatherEntity);
            em.persist(entity);
            data.add(entity);

        }
    }

    /**
     * Prueba para crear un PlatoEsp
     *
     *
     */
    @Test
    public void createPlatoEspTest() {
        PlatoEspEntity newEntity = factory.manufacturePojo(PlatoEspEntity.class);
        PlatoEspEntity result = platoEspLogic.createPlatoEsp(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        PlatoEspEntity entity = em.find(PlatoEspEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de PlatosEsp
     *
     *
     */
    @Test
    public void getPlatosEspTest() {
        List<PlatoEspEntity> list = platoEspLogic.getPlatosEsp(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PlatoEspEntity entity : list) {
            boolean found = false;
            for (PlatoEspEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un PlatoEsp
     *
     *
     */
    @Test
    public void getPlatoEspTest() {
        PlatoEspEntity entity = data.get(0);
        PlatoEspEntity resultEntity = platoEspLogic.getPlatoEsp(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un PlatoEsp
     *
     *
     */
    @Test
    public void deletePlatoEspTest() {
        PlatoEspEntity entity = data.get(1);
        platoEspLogic.deletePlatoEsp(entity.getId());
        PlatoEspEntity deleted = em.find(PlatoEspEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un PlatoEsp
     *
     *
     */
    @Test
    public void updatePlatoEspTest() {
        PlatoEspEntity entity = data.get(0);
        PlatoEspEntity pojoEntity = factory.manufacturePojo(PlatoEspEntity.class);

        pojoEntity.setId(entity.getId());

        platoEspLogic.updatePlatoEsp(fatherEntity.getId(), pojoEntity);

        PlatoEspEntity resp = em.find(PlatoEspEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
