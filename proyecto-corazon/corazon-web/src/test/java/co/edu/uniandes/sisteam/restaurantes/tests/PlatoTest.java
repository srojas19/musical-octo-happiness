///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.sisteam.restaurantes.tests;
//
//import co.edu.uniandes.rest.restaurantes.dtos.PlatoDTO;
//import co.edu.uniandes.rest.restaurantes.resources.DomicilioResource;
//import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
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
///**
// *
// * @author n.sanabria10
// */
//@RunWith(Arquillian.class)
//public class PlatoTest {
//    
//    private final int Ok = Response.Status.OK.getStatusCode();
//    private final int Created = 200; // Status.CREATED.getStatusCode();
//    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();
//    private final String platoPath = "platos";
//    private final static List<PlatoEntity> platoList = new ArrayList<>();
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
//                .addPackage(DomicilioResource.class.getPackage())
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
//        em.createQuery("delete from PlatoEntity").executeUpdate();
//        em.createQuery("delete from DomicilioEntity").executeUpdate();
//        platoList.clear();
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
//            PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
//            plato.setId(i + 1L);
//            em.persist(plato);
//            platoList.add(plato);
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
//     * Prueba para crear un Plato
//     *
//     * 
//     */
//    @Test
//    public void createPlatoTest() throws IOException {
//        PodamFactory factory = new PodamFactoryImpl();
//        PlatoDTO plato = factory.manufacturePojo(PlatoDTO.class);
// 
//        Response response = target.path(platoPath)
//            .request()
//            .post(Entity.entity(plato, MediaType.APPLICATION_JSON));
//        
//        PlatoDTO  platoTest = (PlatoDTO) response.readEntity(PlatoDTO.class);
//        Assert.assertEquals(plato.getNombre(), platoTest.getNombre());
//        Assert.assertEquals(Created, response.getStatus());
//        PlatoEntity entity = em.find(PlatoEntity.class, platoTest.getId());
//        Assert.assertNotNull(entity);
//    }
//
//    /**
//     * Prueba para consultar un Plato
//     *
//     * 
//     */
//    @Test
//    public void getPlatoById() {
//        
//        PlatoDTO platoTest = target.path(platoPath)
//                .path(platoList.get(0).getId().toString())
//                .request().get(PlatoDTO.class);
//        
//        Assert.assertEquals(platoTest.getNombre(), platoList.get(0).getName());
//        Assert.assertEquals(platoTest.getId(), platoList.get(0).getId());
//    }
//
//    /**
//     * Prueba para consultar la lista de Platos
//     *
//     * 
//     */
//    @Test
//    public void listPlatoTest() throws IOException {
//       
//        Response response = target.path(platoPath)
//                .request().get();
//        
//        String listPlato = response.readEntity(String.class);
//        List<PlatoDTO> listPlatoTest = new ObjectMapper().readValue(listPlato, List.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(3, listPlatoTest.size());
//    }
//
//    /**
//     * Prueba para actualizar un Plato
//     *
//     * 
//     */
//    @Test
//    public void updatePlatoTest() throws IOException {
//       
//        PlatoDTO plato = new PlatoDTO(platoList.get(0));
//        PodamFactory factory = new PodamFactoryImpl();
//        PlatoDTO platoChanged = factory.manufacturePojo(PlatoDTO.class);
//        plato.setNombre(platoChanged.getNombre());
//        Response response = target.path(platoPath).path(plato.getId().toString())
//                .request().put(Entity.entity(plato, MediaType.APPLICATION_JSON));
//        
//        PlatoDTO platoTest = (PlatoDTO) response.readEntity(PlatoDTO.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(plato.getNombre(), platoTest.getNombre());
//    }
//    
//    /**
//     * Prueba para eliminar un Plato
//     *
//     * 
//     */
//    @Test
//    public void deletePlatoTest() {
//      
//        PlatoDTO plato = new PlatoDTO(platoList.get(0));
//        Response response = target.path(platoPath).path(plato.getId().toString())
//                .request().delete();
//        
//        Assert.assertEquals(OkWithoutContent, response.getStatus());
//    }
//
//    
//}
