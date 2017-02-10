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

@RunWith(Arquillian.class)
public class MDPPersistenceTest {

    /**
     * @return el jar que se desplegará para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MDPEntity.class.getPackage())
                .addPackage(MDPPersistence.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Compañía que contiene los departamentos. La relación entre Sucursal y
     * Mesa es "composite"
     */
    ClienteEntity fatherEntity;

    /**
     * Lista de los departamentos que serán utilizados en las pruebas. La
     * relación entre Sucursal y Mesa es "composite"
     */
    private List<MDPEntity> data = new ArrayList<>();

    @Inject
    private MDPPersistence mdpPersistence;

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
        em.createQuery("delete  from MDPEntity").executeUpdate();
        em.createQuery("delete  from ClienteEntity").executeUpdate();
    }

    /**
     * Para el correcto funcionamiento de las pruebas, inserta los datos
     * iniciales en la base de datos utilizando un manejador de persistencia.
     *
     * Crea una compañía y luego le adiciona tres departamentos.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(ClienteEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            MDPEntity entity = factory.manufacturePojo(MDPEntity.class);
            entity.setCliente(fatherEntity);
            data.add(entity);
            em.persist(entity);
        }

    }

    /**
     * Prueba para crear un Medio de pago.
     *
     *
     */
    @Test
    public void createMDPTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MDPEntity newEntity = factory.manufacturePojo(MDPEntity.class);
        newEntity.setCliente(fatherEntity);
        MDPEntity result = mdpPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MDPEntity entity = em.find(MDPEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Medios de pago.
     *
     *
     */
    @Test
    public void getMDPSInClienteTest() {
        List<MDPEntity> list = mdpPersistence.findAllInCliente(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MDPEntity ent : list) {
            boolean found = false;
            for (MDPEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Medio de pago.
     *
     *
     */
    @Test
    public void getMDPTest() {
        MDPEntity entity = data.get(0);
        MDPEntity newEntity = mdpPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Medio de Pago.
     *
     *
     */
    @Test
    public void deleteMDPTest() {
        MDPEntity entity = data.get(0);
        mdpPersistence.delete(entity.getId());
        MDPEntity deleted = em.find(MDPEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Medio de pago.
     *
     *
     */
    @Test
    public void updateMDPTest() {
        MDPEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MDPEntity newEntity = factory.manufacturePojo(MDPEntity.class);

        newEntity.setId(entity.getId());

        mdpPersistence.update(newEntity);

        MDPEntity resp = em.find(MDPEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
