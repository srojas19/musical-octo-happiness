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
package co.edu.uniandes.sisteam.restaurantes.test.persistence;

import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.SucursalPersistence;
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

@RunWith(Arquillian.class)
public class SucursalPersistenceTest {

    /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private SucursalPersistence sucursalPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<SucursalEntity> data = new ArrayList<SucursalEntity>();

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() 
    {
        try 
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PlatoEspEntity").executeUpdate();
        em.createQuery("delete from MesaEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SucursalEntity entity = factory.manufacturePojo(SucursalEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Sucursal.
     */
    @Test
    public void createSucursalTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SucursalEntity newEntity = factory.manufacturePojo(SucursalEntity.class);

        SucursalEntity result = sucursalPersistence.create(newEntity);

        Assert.assertNotNull(result);
        SucursalEntity entity = em.find(SucursalEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Sucursales.
     *
     *
     */
    @Test
    public void getSucursalesTest() 
    {
        List<SucursalEntity> list = sucursalPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SucursalEntity ent : list) 
        {
            boolean found = false;
            for (SucursalEntity entity : data) 
            {
                if (ent.getId().equals(entity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Sucursal.
     */
    @Test
    public void getSucursalTest() 
    {
        SucursalEntity entity = data.get(0);
        SucursalEntity newEntity = sucursalPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

   /**
     * Prueba para consultar una Sucursal que existe.
     */
    @Test
    public void getSucursalByNameTest1() {
        SucursalEntity entity = data.get(0);
        SucursalEntity newEntity = sucursalPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
   /**
     * Prueba para consultar una Sucursal que no existe.
     */
    @Test
    public void getSucursalByNameTest2() {
        
        SucursalEntity newEntity = sucursalPersistence.findByName("");
        Assert.assertNull(newEntity);
     
    }

    /**
     * Prueba para eliminar un Sucursal.
     */
    @Test
    public void deleteSucursalTest() 
    {
        SucursalEntity entity = data.get(0);
        sucursalPersistence.delete(entity.getId());
        SucursalEntity deleted = em.find(SucursalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Sucursal.
     */
    @Test
    public void updateSucursalTest() 
    {
        SucursalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SucursalEntity newEntity = factory.manufacturePojo(SucursalEntity.class);

        newEntity.setId(entity.getId());

        sucursalPersistence.update(newEntity);

        SucursalEntity resp = em.find(SucursalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
