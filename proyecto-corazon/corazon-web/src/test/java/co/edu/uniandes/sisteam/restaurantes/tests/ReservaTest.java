///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.sisteam.restaurantes.tests;
//
//import co.edu.uniandes.rest.restaurantes.resources.SucursalResource;
//import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
//import java.io.File;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Response;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.test.api.ArquillianResource;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
//import org.junit.runner.RunWith;
//
///**
// *
// * @author BarraganJeronimo
// */
//
//@RunWith(Arquillian.class)
//public class ReservaTest {
//    
//    
//    private final int Ok = Response.Status.OK.getStatusCode();
//    private final int Created = 200; // Status.CREATED.getStatusCode();
//    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();
//    private final String sucursalPath = "sucursales";//al api 
//    private final static List<ReservaEntity> reservaList = new ArrayList<>();
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
//    
//}
