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


import co.edu.uniandes.sisteam.restaurantes.api.IMDPLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.MDPLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.MDPPersistence;
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
public class MDPLogicTest {

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
    private IMDPLogic mdpLogic;

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
    private List<MDPEntity> data = new ArrayList<MDPEntity>();

    /**
     *
     */
    private List<ClienteEntity> clienteData = new ArrayList<>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MDPEntity.class.getPackage())
                .addPackage(MDPLogic.class.getPackage())
                .addPackage(IMDPLogic.class.getPackage())
                .addPackage(MDPPersistence.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
            
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
        em.createQuery("delete from MDPEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() 
    {
        fatherEntity = factory.manufacturePojo(ClienteEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        
        for (int i = 0; i < 3; i++) 
        {
            MDPEntity entity = factory.manufacturePojo(MDPEntity.class);
            entity.setCliente(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un medio de pago
     *
     *
     */
    @Test
    public void createMDPTest() {
        MDPEntity newEntity = factory.manufacturePojo(MDPEntity.class);
        MDPEntity result = mdpLogic.createMDP(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        MDPEntity entity = em.find(MDPEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Medios de Pago
     *
     *
     */
    @Test
    public void getMDPSTest() {
        List<MDPEntity> list = mdpLogic.getMDPCliente(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MDPEntity entity : list) {
            boolean found = false;
            for (MDPEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Medio de Pago
     *
     *
     */
    @Test
    public void getMDPTest() {
        MDPEntity entity = data.get(0);
        MDPEntity resultEntity = mdpLogic.getMDP(entity.getId());
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
    public void deleteMDPTest() {
        MDPEntity entity = data.get(1);
        mdpLogic.deleteMDP(entity.getId());
        MDPEntity deleted = em.find(MDPEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Medio de pago
     *
     *
     */
    @Test
    public void updateMDPTest() {
        MDPEntity entity = data.get(0);
        MDPEntity pojoEntity = factory.manufacturePojo(MDPEntity.class);

        pojoEntity.setId(entity.getId());

        mdpLogic.updateMDP(fatherEntity.getId(), pojoEntity);

        MDPEntity resp = em.find(MDPEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
