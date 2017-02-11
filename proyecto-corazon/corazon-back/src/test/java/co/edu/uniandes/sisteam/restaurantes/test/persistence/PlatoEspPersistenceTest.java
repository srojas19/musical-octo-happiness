///*
//The MIT License (MIT)
//
//Copyright (c) 2015 Los Andes University
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.
// */
//package co.edu.uniandes.sisteam.restaurantes.test.persistence;
//
//import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
//import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
//import co.edu.uniandes.sisteam.restaurantes.persistence.PlatoEspPersistence;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import org.junit.Assert;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
//@RunWith(Arquillian.class)
//public class PlatoEspPersistenceTest {
//
//    /**
//     * @return el jar que se desplegará para la prueba
//     */
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(PlatoEspEntity.class.getPackage())
//                .addPackage(PlatoEspPersistence.class.getPackage())
//                .addPackage(SucursalEntity.class.getPackage())
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * Compañía que contiene los departamentos. La relación entre Sucursal y
//     * PlatoEsp es "composite"
//     */
//    SucursalEntity fatherEntity;
//
//    /**
//     * Lista de los departamentos que serán utilizados en las pruebas. La
//     * relación entre Sucursal y PlatoEsp es "composite"
//     */
//    private List<PlatoEspEntity> platoEspData = new ArrayList<>();
//
//    @Inject
//    private PlatoEspPersistence platoEspPersistence;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    /**
//     * Configuración inicial de cada método de prueba.
//     *
//     */
//    @Before
//    public void setUp() {
//        try {
//            utx.begin();
//            em.joinTransaction();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     */
//    private void clearData() {
//        em.createQuery("delete  from PlatoEspEntity").executeUpdate();
//        em.createQuery("delete  from SucursalEntity").executeUpdate();
//    }
//
//    /**
//     * Para el correcto funcionamiento de las pruebas, inserta los datos
//     * iniciales en la base de datos utilizando un manejador de persistencia.
//     *
//     * Crea una compañía y luego le adiciona tres departamentos.
//     */
//    private void insertData() {
//        PodamFactory factory = new PodamFactoryImpl();
//        fatherEntity = factory.manufacturePojo(SucursalEntity.class);
//        fatherEntity.setId(1L);
//        em.persist(fatherEntity);
//        for (int i = 0; i < 3; i++) {
//            PlatoEspEntity entity = factory.manufacturePojo(PlatoEspEntity.class);
//            entity.setSucursal(fatherEntity);
//            platoEspData.add(entity);
//            em.persist(entity);
//        }
//
//    }
//
//    /**
//     * Prueba para crear un PlatoEsp.
//     *
//     *
//     */
//    @Test
//    public void createPlatoEspTest() {
//        PodamFactory factory = new PodamFactoryImpl();
//        PlatoEspEntity newEntity = factory.manufacturePojo(PlatoEspEntity.class);
//        newEntity.setSucursal(fatherEntity);
//        PlatoEspEntity result = platoEspPersistence.create(newEntity);
//
//        Assert.assertNotNull(result);
//
//        PlatoEspEntity entity = em.find(PlatoEspEntity.class, result.getId());
//
//        Assert.assertEquals(newEntity.getName(), entity.getName());
//    }
//
//    /**
//     * Prueba para consultar la lista de PlatosEsp.
//     *
//     *
//     */
//    @Test
//    public void getPlatosEspInSucursalTest() {
//        List<PlatoEspEntity> list = platoEspPersistence.findAllInSucursal(fatherEntity.getId());
//        Assert.assertEquals(platoEspData.size(), list.size());
//        for (PlatoEspEntity ent : list) {
//            boolean found = false;
//            for (PlatoEspEntity entity : platoEspData) {
//                if (ent.getId().equals(entity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//
//    /**
//     * Prueba para consultar un PlatoEsp.
//     *
//     *
//     */
//    @Test
//    public void getPlatoEspTest() {
//        PlatoEspEntity entity = platoEspData.get(0);
//        PlatoEspEntity newEntity = platoEspPersistence.find(entity.getId());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(entity.getName(), newEntity.getName());
//    }
//
//    /**
//     * Prueba para consultar una PlatoEsp que existe.
//     */
//    @Test
//    public void getPlatoEspByNameTest1() {
//        Long sucursalId = platoEspData.get(0).getSucursal().getId();
//        PlatoEspEntity dept = platoEspPersistence.findByName(sucursalId, platoEspData.get(0).getName());
//        Assert.assertNotNull(dept);  
//    }
//   /**
//     * Prueba para consultar una PlatoEsp que no existe.
//     */
//    @Test
//    public void getPlatoEspByNameTest2() {
//        
//        Long companyId = platoEspData.get(0).getSucursal().getId();
//        PlatoEspEntity dept = platoEspPersistence.findByName(companyId, " ");
//        Assert.assertNull(dept);
//     
//    }
//    
//    /**
//     * Prueba para eliminar un PlatoEsp.
//     *
//     *
//     */
//    @Test
//    public void deletePlatoEspTest() {
//        PlatoEspEntity entity = platoEspData.get(0);
//        platoEspPersistence.delete(entity.getId());
//        PlatoEspEntity deleted = em.find(PlatoEspEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un PlatoEsp.
//     *
//     *
//     */
//    @Test
//    public void updatePlatoEspTest() {
//        PlatoEspEntity entity = platoEspData.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        PlatoEspEntity newEntity = factory.manufacturePojo(PlatoEspEntity.class);
//
//        newEntity.setId(entity.getId());
//
//        platoEspPersistence.update(newEntity);
//
//        PlatoEspEntity resp = em.find(PlatoEspEntity.class, entity.getId());
//
//        Assert.assertEquals(newEntity.getName(), resp.getName());
//    }
//}
