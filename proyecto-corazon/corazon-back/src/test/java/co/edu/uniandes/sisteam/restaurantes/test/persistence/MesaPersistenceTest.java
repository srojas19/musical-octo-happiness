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

@RunWith(Arquillian.class)
public class MesaPersistenceTest {

    /**
     * @return el jar que se desplegará para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaPersistence.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Compañía que contiene los departamentos. La relación entre Sucursal y
     * Mesa es "composite"
     */
    SucursalEntity fatherEntity;

    /**
     * Lista de los departamentos que serán utilizados en las pruebas. La
     * relación entre Sucursal y Mesa es "composite"
     */
    private List<MesaEntity> mesaData = new ArrayList<>();

    @Inject
    private MesaPersistence mesaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de cada método de prueba.
     *
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
        em.createQuery("delete  from MesaEntity").executeUpdate();
        em.createQuery("delete  from SucursalEntity").executeUpdate();
    }

    /**
     * Para el correcto funcionamiento de las pruebas, inserta los datos
     * iniciales en la base de datos utilizando un manejador de persistencia.
     *
     * Crea una compañía y luego le adiciona tres departamentos.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(SucursalEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            MesaEntity entity = factory.manufacturePojo(MesaEntity.class);
            entity.setSucursal(fatherEntity);
            mesaData.add(entity);
            em.persist(entity);
        }

    }

    /**
     * Prueba para crear un Mesa.
     *
     *
     */
    @Test
    public void createMesaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);
        newEntity.setSucursal(fatherEntity);
        MesaEntity result = mesaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MesaEntity entity = em.find(MesaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Mesas.
     *
     *
     */
    @Test
    public void getMesasInSucursalTest() 
    {
        List<MesaEntity> list = mesaPersistence.findAllInSucursal(fatherEntity.getId());
        Assert.assertEquals(mesaData.size(), list.size());
        for (MesaEntity ent : list) 
        {
            boolean found = false;
            for (MesaEntity entity : mesaData) 
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
     * Prueba para consultar un Mesa.
     *
     *
     */
    @Test
    public void getMesaTest() 
    {
        MesaEntity entity = mesaData.get(0);
        MesaEntity newEntity = mesaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para consultar una Mesa que existe.
     */
    @Test
    public void getMesaByNameTest1() {
        Long sucursalId = mesaData.get(0).getSucursal().getId();
        MesaEntity dept = mesaPersistence.findByName(sucursalId, mesaData.get(0).getName());
        Assert.assertNotNull(dept);  
    }
   /**
     * Prueba para consultar una Mesa que no existe.
     */
    @Test
    public void getMesaByNameTest2() {
        
        Long companyId = mesaData.get(0).getSucursal().getId();
        MesaEntity dept = mesaPersistence.findByName(companyId, " ");
        Assert.assertNull(dept);
     
    }
    
    
    /**
     * Prueba para eliminar un Mesa.
     *
     *
     */
    @Test
    public void deleteMesaTest() {
        MesaEntity entity = mesaData.get(0);
        mesaPersistence.delete(entity.getId());
        MesaEntity deleted = em.find(MesaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Mesa.
     *
     *
     */
    @Test
    public void updateMesaTest() 
    {
        MesaEntity entity = mesaData.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);

        newEntity.setId(entity.getId());

        mesaPersistence.update(newEntity);

        MesaEntity resp = em.find(MesaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
