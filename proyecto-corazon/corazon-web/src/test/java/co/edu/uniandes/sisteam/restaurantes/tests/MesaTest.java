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
//import co.edu.uniandes.rest.restaurantes.dtos.MesaDTO;
//import co.edu.uniandes.rest.restaurantes.resources.MesaResource;
//import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
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
// * Testing URI: sucursales/{mesasId: \\d+}/mesas/
// */
//@RunWith(Arquillian.class)
//public class MesaTest {
//
//    private WebTarget target;
//
//    PodamFactory factory = new PodamFactoryImpl();
//
//    private final int Ok = Status.OK.getStatusCode();
//    private final int Created = 200; //Status.CREATED.getStatusCode();
//    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
//
//    private final static List<MesaEntity> mesaList = new ArrayList<>();
//
//    private final String sucursalPath = "sucursales";
//    private final String mesaPath = "mesas";
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
//                .addPackage(MesaResource.class.getPackage())
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
//        em.createQuery("delete from MesaEntity").executeUpdate();
//        em.createQuery("delete from SucursalEntity").executeUpdate();
//        mesaList.clear();
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
//            MesaEntity mesa = factory.manufacturePojo(MesaEntity.class);
//            mesa.setId(i + 1L);
//            mesa.setSucursal(fatherSucursalEntity);
//            em.persist(mesa);
//            mesaList.add(mesa);
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
//     * Prueba para crear un Mesa
//     *
//     * @generated
//     */
//    @Test
//    public void createMesaTest() throws IOException {
//        MesaDTO mesa = factory.manufacturePojo(MesaDTO.class);
//
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(mesaPath)
//                .request()
//                .post(Entity.entity(mesa, MediaType.APPLICATION_JSON));
//
//        MesaDTO mesaTest = (MesaDTO) response.readEntity(MesaDTO.class);
//
//        Assert.assertEquals(Created, response.getStatus());
//
//        Assert.assertEquals(mesa.getName(), mesaTest.getName());
//
//        MesaEntity entity = em.find(MesaEntity.class, mesaTest.getId());
//        Assert.assertNotNull(entity);
//    }
//
//    /**
//     * Prueba para consultar un Mesa
//     *
//     * @generated
//     */
//    @Test
//    public void getMesaByIdTest() {
//        MesaDTO mesa = new MesaDTO(mesaList.get(0));
//
//        MesaDTO mesaTest = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(mesaPath)
//                .path(mesa.getId().toString())
//                .request().get(MesaDTO.class);
//
//        Assert.assertEquals(mesaTest.getId(), mesaList.get(0).getId());
//        Assert.assertEquals(mesaTest.getName(), mesaList.get(0).getName());
//    }
//
//    /**
//     * Prueba para consultar la lista de Mesas
//     *
//     * @generated
//     */
//    @Test
//    public void listMesaTest() throws IOException {
//
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(mesaPath)
//                .request().get();
//
//        String listMesa = response.readEntity(String.class);
//        List<MesaDTO> listMesaTest = new ObjectMapper().readValue(listMesa, List.class);
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(mesaList.size(), listMesaTest.size());
//    }
//
//    /**
//     * Prueba para actualizar un Mesa
//     *
//     * @generated
//     */
//    @Test
//    public void updateMesaTest() throws IOException {
//
//        MesaDTO mesa = new MesaDTO(mesaList.get(0));
//
//        MesaDTO mesaChanged = factory.manufacturePojo(MesaDTO.class);
//
//        mesa.setName(mesaChanged.getName());
//
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(mesaPath)
//                .path(mesa.getId().toString())
//                .request()
//                .put(Entity.entity(mesa, MediaType.APPLICATION_JSON));
//
//        MesaDTO mesaTest = (MesaDTO) response.readEntity(MesaDTO.class);
//
//        Assert.assertEquals(Ok, response.getStatus());
//        Assert.assertEquals(mesa.getName(), mesaTest.getName());
//    }
//
//    /**
//     * Prueba para eliminar un Mesa
//     *
//     * @generated
//     */
//    @Test
//    public void deleteMesaTest() {
//
//        MesaDTO mesa = new MesaDTO(mesaList.get(0));
//        Response response = target
//                .path(fatherSucursalEntity.getId().toString())
//                .path(mesaPath)
//                .path(mesa.getId().toString())
//                .request().delete();
//
//        Assert.assertEquals(OkWithoutContent, response.getStatus());
//    }
//}
