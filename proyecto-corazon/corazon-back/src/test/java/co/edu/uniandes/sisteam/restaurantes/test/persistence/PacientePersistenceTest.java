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

import co.edu.uniandes.sisteam.restaurantes.entities.PacienteEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.PacientePersistence;
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
public class PacientePersistenceTest {

    /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PacienteEntity.class.getPackage())
                .addPackage(PacientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private PacientePersistence pacientePersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PacienteEntity> data = new ArrayList<PacienteEntity>();

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
        em.createQuery("delete from PacienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PacienteEntity entity = factory.manufacturePojo(PacienteEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Paciente.
     */
    @Test
    public void createPacienteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);

        PacienteEntity result = pacientePersistence.create(newEntity);

        Assert.assertNotNull(result);
        PacienteEntity entity = em.find(PacienteEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Pacientes.
     *
     *
     */
    @Test
    public void getPacientesTest() 
    {
        List<PacienteEntity> list = pacientePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PacienteEntity ent : list) 
        {
            boolean found = false;
            for (PacienteEntity entity : data) 
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
     * Prueba para consultar una Paciente.
     */
    @Test
    public void getPacienteTest() 
    {
        PacienteEntity entity = data.get(0);
        PacienteEntity newEntity = pacientePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

   /**
     * Prueba para consultar una Paciente que existe.
     */
    @Test
    public void getPacienteByNameTest1() {
        PacienteEntity entity = data.get(0);
        PacienteEntity newEntity = pacientePersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
   /**
     * Prueba para consultar una Paciente que no existe.
     */
    @Test
    public void getPacienteByNameTest2() {
        
        PacienteEntity newEntity = pacientePersistence.findByName("");
        Assert.assertNull(newEntity);
     
    }

    /**
     * Prueba para eliminar un Paciente.
     */
    @Test
    public void deletePacienteTest() 
    {
        PacienteEntity entity = data.get(0);
        pacientePersistence.delete(entity.getId());
        PacienteEntity deleted = em.find(PacienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Paciente.
     */
    @Test
    public void updatePacienteTest() 
    {
        PacienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PacienteEntity newEntity = factory.manufacturePojo(PacienteEntity.class);

        newEntity.setId(entity.getId());

        pacientePersistence.update(newEntity);

        PacienteEntity resp = em.find(PacienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
