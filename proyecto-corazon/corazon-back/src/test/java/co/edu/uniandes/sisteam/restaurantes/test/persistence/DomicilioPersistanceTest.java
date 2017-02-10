/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.test.persistence;

import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.DomicilioPersistence;
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
public class DomicilioPersistanceTest {
    
     /**
     * @return el jar que se desplegará para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(PlatoEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Compañía que contiene los departamentos. La relación entre Sucursal y
     * Domicilio es "composite"
     */
    SucursalEntity fatherEntityS;
    
    /**
     * Compañía que contiene los departamentos. La relación entre Cliente y
     * Domicilio es "composite"
     */
    ClienteEntity fatherEntityC;
    
    /**
     * Compañía que contiene los departamentos. La relación entre Cliente y
     * Domicilio es "composite"
     */
    ArrayList<PlatoEntity> fatherEntityP;
    
    /**
     * Lista de los departamentos que serán utilizados en las pruebas. La
     * relación entre Sucursal y Mesa es "composite"
     */
    private List<DomicilioEntity> data = new ArrayList<>();
    
    @Inject
    private DomicilioPersistence domicilioPersistence;

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
        em.createQuery("delete from DomicilioEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from PlatoEntity").executeUpdate();
    }

    /**
     * Para el correcto funcionamiento de las pruebas, inserta los datos
     * iniciales en la base de datos utilizando un manejador de persistencia.
     *
     * Crea una compañía y luego le adiciona tres departamentos.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntityS = factory.manufacturePojo(SucursalEntity.class);
        fatherEntityS.setId(1L);
        fatherEntityC = factory.manufacturePojo(ClienteEntity.class);
        fatherEntityP = new ArrayList<>();
        fatherEntityC.setId(1L);
        
        for (int i = 0; i < 4; i++) 
        {
            PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class);
            fatherEntityP.add(entity);
            em.persist(entity);
        }
        
        em.persist(fatherEntityS);
        em.persist(fatherEntityC);
        
        for (int i = 0; i < 3; i++) {
            DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);
            entity.setSucursal(fatherEntityS);
            entity.setCliente(fatherEntityC);
            entity.setPlatos(fatherEntityP);
            data.add(entity);
            em.persist(entity);
            fatherEntityP.remove(3-i);

        }
    }
    
    /**
     * Prueba para crear un Domicilio.
     */
    @Test
    public void createDomicilioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        newEntity.setSucursal(fatherEntityS);
        newEntity.setCliente(fatherEntityC);
        newEntity.setPlatos(fatherEntityP);
        DomicilioEntity result = domicilioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        DomicilioEntity entity = em.find(DomicilioEntity.class, result.getId());

        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar los Domicilios en la lista.
     */
    @Test
    public void getDomiciliosTest() {
        
        List<DomicilioEntity> list = domicilioPersistence.findAll();
        int i=0;
        while (i<data.size())
        {
            System.out.println(data.get(i).getId());
            i++;
        }
        Assert.assertEquals(data.size(), list.size());
        for (DomicilioEntity entity : list) {
            boolean found = false;
            for (DomicilioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Domicilio.
     */
    @Test
    public void getDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity resultEntity = domicilioPersistence.find(entity.getId());
        
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para eliminar un Domicilio.
     */
    @Test
    public void deleteDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        domicilioPersistence.delete(entity.getId());
        DomicilioEntity deleted = em.find(DomicilioEntity.class, entity.getId());
        
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Domicilio.
     */
    @Test
    public void updateDomicilioTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        
        DomicilioEntity entity = data.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);

        pojoEntity.setId(entity.getId());

        domicilioPersistence.update(pojoEntity);

        DomicilioEntity resp = em.find(DomicilioEntity.class, entity.getId());
        
        Assert.assertNotNull(resp);
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para agregar un plato a un Domicilio.
     */
    @Test
    public void addPlatoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class);
        data.get(data.size()-1).addPlato(entity);
        
        Assert.assertEquals(data.get(data.size()-1).getPlato(entity.getId()), entity);
    }
    
    /**
     * Prueba para eliminar un plato a un Domicilio.
     */
    @Test
    public void deletePlatoTest()
    {
        PlatoEntity entity = fatherEntityP.get(0);
        Assert.assertNotNull(entity);
        
        data.get(0).deletePlato(entity.getId());
        
        Assert.assertNull(data.get(0).getPlato(entity.getId()));
    }
}
