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
import co.edu.uniandes.sisteam.restaurantes.api.IPlatoEspLogic;
import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.MesaLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.PlatoEspLogic;
import co.edu.uniandes.sisteam.restaurantes.ejbs.SucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.persistence.MesaPersistence;
import co.edu.uniandes.sisteam.restaurantes.persistence.PlatoEspPersistence;
import co.edu.uniandes.sisteam.restaurantes.persistence.SucursalPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.NotSupportedException;
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
public class SucursalLogicTest {

    /**
     *
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     *
     */
    @Inject
    private ISucursalLogic sucursalLogic;

    /**
     *
     */
    @Inject
    private MesaPersistence mesaPersistence;

    /**
     *
     */
    @Inject
    private PlatoEspPersistence platoEspPersistence;

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
    private List<SucursalEntity> data = new ArrayList<SucursalEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalLogic.class.getPackage())
                .addPackage(ISucursalLogic.class.getPackage())
                .addPackage(SucursalPersistence.class.getPackage())
                .addPackage(MesaPersistence.class.getPackage())
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaLogic.class.getPackage())
                .addPackage(IMesaLogic.class.getPackage())
                .addPackage(PlatoEspPersistence.class.getPackage())
                .addPackage(PlatoEspEntity.class.getPackage())
                .addPackage(PlatoEspLogic.class.getPackage())
                .addPackage(IPlatoEspLogic.class.getPackage())
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
    private void clearData() {
        em.createQuery("delete from PlatoEspEntity").executeUpdate();
        em.createQuery("delete from MesaEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) 
        {
            SucursalEntity entity = factory.manufacturePojo(SucursalEntity.class);
            

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Sucursal
     *
     *
     */
    @Test
    public void createSucursalTest() throws NotSupportedException, Exception {
        SucursalEntity newEntity = factory.manufacturePojo(SucursalEntity.class);
        System.out.println("1 " + newEntity.getMesas().size());
        System.out.println("1 " + newEntity.getPlatosEsp().size());
        SucursalEntity result = sucursalLogic.createSucursal(newEntity);
        Assert.assertNotNull(result);
        System.out.println("2 " + result.getMesas().size());
        System.out.println("2 " + result.getPlatosEsp().size());
        SucursalEntity entity = em.find(SucursalEntity.class, result.getId());
        System.out.println("3 " + result.getMesas().size());
        System.out.println("3 " + result.getPlatosEsp().size());

        
        //mesas
        TypedQuery q = em.createQuery("select d from MesaEntity d  where d.sucursal.id = :sucursalId", MesaEntity.class);
        q = q.setParameter("sucursalId", result.getId());
        
        List<MesaEntity> listMesas = (List<MesaEntity>) q.getResultList();

        System.out.println("4 " + listMesas.size());
        listMesas = mesaPersistence.findAll();

        for (MesaEntity d : listMesas) 
        {
            System.out.println(d.getName() + " " + d.getSucursal().getId());
        }
        
        
        //platosEsp
        TypedQuery q2 = em.createQuery("select d from PlatoEspEntity d  where d.sucursal.id = :sucursalId", PlatoEspEntity.class);
        q2 = q2.setParameter("sucursalId", result.getId());
        List<PlatoEspEntity> listPlatosEsp = (List<PlatoEspEntity>) q2.getResultList();

        System.out.println("4 " + listPlatosEsp.size());
        listPlatosEsp = platoEspPersistence.findAll();
        
        for (PlatoEspEntity d: listPlatosEsp)
        {
            System.out.println(d.getName()+" "+d.getSucursal().getId());
            
        }

        
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        
        //mesas
        entity.setMesas(listMesas);

        Assert.assertNotNull(entity.getMesas());
        Assert.assertNotNull(result.getMesas());
        Assert.assertEquals(result.getMesas().size(), entity.getMesas().size());

        //platosEsp
        entity.setPlatosEsp(listPlatosEsp);

        Assert.assertNotNull(entity.getPlatosEsp());
        Assert.assertNotNull(result.getPlatosEsp());
        Assert.assertEquals(result.getPlatosEsp().size(), entity.getPlatosEsp().size());

        
    }

    /**
     * Prueba para consultar la lista de Sucursales
     *
     *
     */
    @Test
    public void getSucursalesTest() {
        List<SucursalEntity> list = sucursalLogic.getSucursales();
        Assert.assertEquals(data.size(), list.size());
        for (SucursalEntity entity : list) {
            boolean found = false;
            for (SucursalEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Sucursal
     *
     *
     */
    @Test
    public void getSucursalTest() {
        SucursalEntity entity = data.get(0);
        SucursalEntity resultEntity = sucursalLogic.getSucursal(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Sucursal
     *
     *
     */
    @Test
    public void deleteSucursalTest() {
        SucursalEntity entity = data.get(1);
        sucursalLogic.deleteSucursal(entity.getId());
        SucursalEntity deleted = em.find(SucursalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Sucursal
     *
     *
     */
    @Test
    public void updateSucursalTest() {
        SucursalEntity entity = data.get(0);
        SucursalEntity pojoEntity = factory.manufacturePojo(SucursalEntity.class);

        pojoEntity.setId(entity.getId());

        sucursalLogic.updateSucursal(pojoEntity);

        SucursalEntity resp = em.find(SucursalEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
