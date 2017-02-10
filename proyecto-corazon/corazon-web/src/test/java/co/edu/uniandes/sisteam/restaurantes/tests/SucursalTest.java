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
//*/
//package co.edu.uniandes.sisteam.restaurantes.tests;
//import co.edu.uniandes.rest.restaurantes.dtos.SucursalDTO;
//import co.edu.uniandes.rest.restaurantes.resources.SucursalResource;
//import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import javax.inject.Inject;
//import javax.transaction.UserTransaction;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//import org.codehaus.jackson.map.ObjectMapper;
//
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//
//import org.jboss.arquillian.test.api.ArquillianResource;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
//import org.junit.Assert;
//import org.junit.Before;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
//
//@RunWith(Arquillian.class)
//public class SucursalTest {
//
//    private final int Ok = Status.OK.getStatusCode();
//    private final int Created = 200; // Status.CREATED.getStatusCode();
//    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
//    private final String sucursalPath = "sucursales";
//    private final static List<SucursalEntity> sucursalList = new ArrayList<>();
//    private WebTarget target;
//    private final String apiPath = "api";
//    
//    @ArquillianResource
//    private URL deploymentURL;
//
//    @Deployment
//    public static WebArchive createDeployment() {
//        return ShrinkWrap.create(WebArchive.class)
//                // Se agrega las dependencias
//                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
//                        .importRuntimeDependencies().resolve()
//                        .withTransitivity().asFile())
//                // Se agregan los compilados de los paquetes de servicios
//                .addPackage(SucursalResource.class.getPackage())
//                // El archivo que contiene la configuracion a la base de datos.
//                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//                // El archivo beans.xml es necesario para injeccion de dependencias.
//                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
//               
//                // El archivo web.xml es necesario para el despliegue de los servlets
//                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
//    }
//
//    private WebTarget createWebTarget() {
//        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
//    }
//
//    @PersistenceContext(unitName = "SisteamPU")
//    private EntityManager em;
//
//    @Inject
//    private UserTransaction utx;
//
//    private void clearData() {
//        
//        em.createQuery("delete from SucursalEntity").executeUpdate();    
//        sucursalList.clear();
//    }
//
//  
//
//   /**
//     * Datos iniciales para el correcto funcionamiento de las pruebas.
//     *
//     * 
//     */
//    public void insertData() {
//        PodamFactory factory = new PodamFactoryImpl();
//        for (int i = 0; i < 3; i++) {            
//            SucursalEntity sucursal = factory.manufacturePojo(SucursalEntity.class);
//            sucursal.setId(i + 1L);
//            em.persist(sucursal);
//            sucursalList.add(sucursal);
//        }
//    }
//
//    
//    /**
//     * Configuración inicial de la prueba.
//     *
//     * 
//     */
//    @Before
//    public void setUpTest() {
//        target = createWebTarget();
//        try {
//            utx.begin();
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
//     * Prueba para crear un Sucursal
//     *
//     * 
//     */
//    @Test
//    public void createSucursalTest() throws IOException {
//        PodamFactory factory = new PodamFactoryImpl();
//        SucursalDTO sucursal = factory.manufacturePojo(SucursalDTO.class);
// 
//        Response response = target.path(sucursalPath)
//            .request()
//            .post(Entity.entity(sucursal, MediaType.APPLICATION_JSON));
//        
//        SucursalDTO  sucursalTest = (SucursalDTO) response.readEntity(SucursalDTO.class);
//        Assert.assertEquals(sucursal.getName(), sucursalTest.getName());
//        Assert.assertEquals(Created, response.getStatus());
//        SucursalEntity entity = em.find(SucursalEntity.class, sucursalTest.getId());
//        Assert.assertNotNull(entity);
//    }
//
//    /**
//     * Prueba para consultar un Sucursal
//     *
//     * 
//     */
//    @Test
//    public void getSucursalById() {
//        
//        SucursalDTO sucursalTest = target.path(sucursalPath)
//                .path(sucursalList.get(0).getId().toString())
//                .request().get(SucursalDTO.class);
//        
//        Assert.assertEquals(sucursalTest.getName(), sucursalList.get(0).getName());
//        Assert.assertEquals(sucursalTest.getId(), sucursalList.get(0).getId());
//    }
//
//    /**
//     * Prueba para consultar la lista de Sucursales
//     *
//     * 
//     */
//    @Test
//    public void listSucursalTest() throws IOException {
//       
//        Response response = target.path(sucursalPath)
//                .request().get();
//        
//        String listSucursal = response.readEntity(String.class);
//        List<SucursalDTO> listSucursalTest = new ObjectMapper().readValue(listSucursal, List.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(3, listSucursalTest.size());
//    }
//
//    /**
//     * Prueba para actualizar un Sucursal
//     *
//     * 
//     */
//    @Test
//    public void updateSucursalTest() throws IOException {
//       
//        SucursalDTO sucursal = new SucursalDTO(sucursalList.get(0));
//        PodamFactory factory = new PodamFactoryImpl();
//        SucursalDTO sucursalChanged = factory.manufacturePojo(SucursalDTO.class);
//        sucursal.setName(sucursalChanged.getName());
//        Response response = target.path(sucursalPath).path(sucursal.getId().toString())
//                .request().put(Entity.entity(sucursal, MediaType.APPLICATION_JSON));
//        
//        SucursalDTO sucursalTest = (SucursalDTO) response.readEntity(SucursalDTO.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(sucursal.getName(), sucursalTest.getName());
//    }
//    
//    /**
//     * Prueba para eliminar un Sucursal
//     *
//     * 
//     */
//    @Test
//    public void deleteSucursalTest() {
//      
//        SucursalDTO sucursal = new SucursalDTO(sucursalList.get(0));
//        Response response = target.path(sucursalPath).path(sucursal.getId().toString())
//                .request().delete();
//        
//        Assert.assertEquals(OkWithoutContent, response.getStatus());
//    }
//}
