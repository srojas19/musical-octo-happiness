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
//package co.edu.uniandes.sisteam.restaurantes.tests;
//
//
//
//import co.edu.uniandes.rest.restaurantes.dtos.PlatoEspDTO;
//import co.edu.uniandes.rest.restaurantes.resources.PlatoEspResource;
//import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
//import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
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
///*
// * Testing URI: sucursales/{platosEspId: \\d+}/platosEsp/
// */
//@RunWith(Arquillian.class)
//public class PlatoEspTest {
//
//    private WebTarget target;
//
//    PodamFactory factory = new PodamFactoryImpl();
//
//    private final int Ok = Status.OK.getStatusCode();
//    private final int Created = 200; //Status.CREATED.getStatusCode();
//    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
//
//    private final static List<PlatoEspEntity> platoEspList = new ArrayList<>();
//
//    private final String sucursalPath = "sucursales";
//    private final String platoEspPath = "platosEsp";
//    private final String apiPath = "api";
//    SucursalEntity fatherSucursalEntity;
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
//                .addPackage(PlatoEspResource.class.getPackage())
//                // El archivo que contiene la configuracion a la base de datos.
//                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//                // El archivo beans.xml es necesario para injeccion de dependencias.
//                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
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
//        em.createQuery("delete from PlatoEspEntity").executeUpdate();
//        em.createQuery("delete from SucursalEntity").executeUpdate();
//        platoEspList.clear();
//    }
//
//    /**
//     * Datos iniciales para el correcto funcionamiento de las pruebas.
//     *
//     * @generated
//     */
//    public void insertData() {
//        fatherSucursalEntity = factory.manufacturePojo(SucursalEntity.class);
//        fatherSucursalEntity.setId(1L);
//        em.persist(fatherSucursalEntity);
//
//        for (int i = 0; i < 3; i++) {
//            PlatoEspEntity platoEsp = factory.manufacturePojo(PlatoEspEntity.class);
//            platoEsp.setId(i + 1L);
//            platoEsp.setSucursal(fatherSucursalEntity);
//            em.persist(platoEsp);
//            platoEspList.add(platoEsp);
//        }
//    }
//
//    /**
//     * Configuración inicial de la prueba.
//     *
//     * @generated
//     */
//    @Before
//    public void setUpTest() {
//        target = createWebTarget()
//                .path(sucursalPath);
//
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
//
//    }
//
//    /**
//     * Prueba para crear un PlatoEsp
//     *
//     * @generated
//     */
//    @Test
//    public void createPlatoEspTest() throws IOException {
//        PlatoEspDTO platoEsp = factory.manufacturePojo(PlatoEspDTO.class);
//
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(platoEspPath)
//                .request()
//                .post(Entity.entity(platoEsp, MediaType.APPLICATION_JSON));
//
//        PlatoEspDTO platoEspTest = (PlatoEspDTO) response.readEntity(PlatoEspDTO.class);
//
//        Assert.assertEquals(Created, response.getStatus());
//
//        Assert.assertEquals(platoEsp.getName(), platoEspTest.getName());
//
//        PlatoEspEntity entity = em.find(PlatoEspEntity.class, platoEspTest.getId());
//        Assert.assertNotNull(entity);
//    }
//
//    /**
//     * Prueba para consultar un PlatoEsp
//     *
//     * @generated
//     */
//    @Test
//    public void getPlatoEspByIdTest() {
//        PlatoEspDTO platoEsp = new PlatoEspDTO(platoEspList.get(0));
//
//        PlatoEspDTO platoEspTest = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(platoEspPath)
//                .path(platoEsp.getId().toString())
//                .request().get(PlatoEspDTO.class);
//
//        Assert.assertEquals(platoEspTest.getId(), platoEspList.get(0).getId());
//        Assert.assertEquals(platoEspTest.getName(), platoEspList.get(0).getName());
//    }
//
//    /**
//     * Prueba para consultar la lista de PlatosEsp
//     *
//     * @generated
//     */
//    @Test
//    public void listPlatoEspTest() throws IOException {
//
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(platoEspPath)
//                .request().get();
//
//        String listPlatoEsp = response.readEntity(String.class);
//        List<PlatoEspDTO> listPlatoEspTest = new ObjectMapper().readValue(listPlatoEsp, List.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(platoEspList.size(), listPlatoEspTest.size());
//    }
//
//    /**
//     * Prueba para actualizar un PlatoEsp
//     *
//     * @generated
//     */
//    @Test
//    public void updatePlatoEspTest() throws IOException {
//
//        PlatoEspDTO platoEsp = new PlatoEspDTO(platoEspList.get(0));
//
//        PlatoEspDTO platoEspChanged = factory.manufacturePojo(PlatoEspDTO.class);
//
//        platoEsp.setName(platoEspChanged.getName());
//
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(platoEspPath)
//                .path(platoEsp.getId().toString())
//                .request()
//                .put(Entity.entity(platoEsp, MediaType.APPLICATION_JSON));
//
//        PlatoEspDTO platoEspTest = (PlatoEspDTO) response.readEntity(PlatoEspDTO.class);
//
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(platoEsp.getName(), platoEspTest.getName());
//    }
//
//    /**
//     * Prueba para eliminar un PlatoEsp
//     *
//     * @generated
//     */
//    @Test
//    public void deletePlatoEspTest() {
//
//        PlatoEspDTO platoEsp = new PlatoEspDTO(platoEspList.get(0));
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(platoEspPath)
//                .path(platoEsp.getId().toString())
//                .request().delete();
//
//        Assert.assertEquals(OkWithoutContent, response.getStatus());
//    }
//}
