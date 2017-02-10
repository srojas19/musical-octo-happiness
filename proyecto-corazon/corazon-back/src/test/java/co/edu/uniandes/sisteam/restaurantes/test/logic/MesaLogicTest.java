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


import co.edu.uniandes.sisteam.restaurantes.api.IMesaLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.MesaLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.MesaPersistence;
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
public class MesaLogicTest {

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
    private IMesaLogic mesaLogic;

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
    private List<MesaEntity> data = new ArrayList<MesaEntity>();

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
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaLogic.class.getPackage())
                .addPackage(IMesaLogic.class.getPackage())
                .addPackage(MesaPersistence.class.getPackage())
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
        em.createQuery("delete from MesaEntity").executeUpdate();
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
            MesaEntity entity = factory.manufacturePojo(MesaEntity.class);
            entity.setSucursal(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Mesa
     *
     *
     */
    @Test
    public void createMesaTest() {
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);
        MesaEntity result = mesaLogic.createMesa(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        MesaEntity entity = em.find(MesaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Mesas
     *
     *
     */
    @Test
    public void getMesasTest() {
        List<MesaEntity> list = mesaLogic.getMesas(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MesaEntity entity : list) {
            boolean found = false;
            for (MesaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Mesa
     *
     *
     */
    @Test
    public void getMesaTest() {
        MesaEntity entity = data.get(0);
        MesaEntity resultEntity = mesaLogic.getMesa(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Mesa
     *
     *
     */
    @Test
    public void deleteMesaTest() {
        MesaEntity entity = data.get(1);
        mesaLogic.deleteMesa(entity.getId());
        MesaEntity deleted = em.find(MesaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Mesa
     *
     *
     */
    @Test
    public void updateMesaTest() {
        MesaEntity entity = data.get(0);
        MesaEntity pojoEntity = factory.manufacturePojo(MesaEntity.class);

        pojoEntity.setId(entity.getId());

        mesaLogic.updateMesa(fatherEntity.getId(), pojoEntity);

        MesaEntity resp = em.find(MesaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
