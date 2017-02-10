/*
 * DomicilioResource.java
 * Clase que representa el recurso "/domicilios"
 * Implementa varios métodos para manipular los domicilios
 */
package co.edu.uniandes.rest.restaurantes.resources;

import co.edu.uniandes.rest.restaurantes.dtos.DomicilioDTO;
import co.edu.uniandes.rest.restaurantes.dtos.DomicilioDetailDTO;
import co.edu.uniandes.rest.restaurantes.dtos.PlatoDTO;
import co.edu.uniandes.rest.restaurantes.exceptions.DomicilioLogicException;
import co.edu.uniandes.sisteam.restaurantes.api.IDomicilioLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Clase que implementa el recurso REST correspondiente a "domicilios".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "domicilios". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/domicilios"
 *
 * @author Asistente
 */
@Path("domicilios")
@Produces("application/json")
public class DomicilioResource {

    @Inject
    IDomicilioLogic domicilioLogic;
    
    private List<DomicilioDetailDTO> listEntityToDTO(List<DomicilioEntity> entityList) {

        List<DomicilioDetailDTO> list = new ArrayList<>();
        for (DomicilioEntity entity : entityList) {
            list.add(new DomicilioDetailDTO(entity));
        }
        return list;
    }
    
    private List<PlatoDTO> listPEntityToDTO(List<PlatoEntity> entityList) {

        List<PlatoDTO> list = new ArrayList<>();
        for (PlatoEntity entity : entityList) {
            list.add(new PlatoDTO(entity));
        }
        return list;
    }

    
    /**
     * Obtiene el listado de domicilios.
     *
     * @return lista de domicilios
     * @throws DomicilioLogicException excepción retornada por la lógica
     */
    @GET
    public List<DomicilioDetailDTO> getDomicilios() throws DomicilioLogicException 
    {
        return listEntityToDTO(domicilioLogic.getDomicilios());
    }
    
    /**
     * Agrega un domicilio
     *
     * @param domicilio domicilio a agregar
     * @return datos del domicilio a agregar
     * @throws DomicilioLogicException cuando ya existe un domicilio con el id
     * suministrado
     */
    @POST
    public DomicilioDTO createDomicilio(DomicilioDetailDTO domicilio) throws DomicilioLogicException 
    {
        return new DomicilioDTO (domicilioLogic.createDomicilio(domicilio.getCliente().getId(), domicilio.getSucursal().getId(), domicilio.toEntity()));
    }
    

    /**
     * Obtiene la domicilio con el id dado en el path.
     *
     * @param domicilio domicilio a retornar
     * @return domicilio con el id dado
     * @throws DomicilioLogicException excepción retornada por la lógica
     */
    @GET
    @Path("{idDomicilio: \\d+}")
    public DomicilioDTO getDomicilio(@PathParam("idDomicilio") Long idDomicilio) throws DomicilioLogicException 
    {
        return new DomicilioDetailDTO(domicilioLogic.getDomicilio(idDomicilio));
    }
    
     /**
     * Actualiza un domicilio con un Id
     *
     * @param id id del domicilio a actualizar 
     * @param domicilio 
     * @return datos del domicilio actualizada. 
     * @throws DomicilioLogicException cuando no existe un domicilio con el id
     * suministrado
     */
    @PUT
    @Path("{idDomicilio: \\d+}") 
    public DomicilioDTO updateDomicilio(@PathParam("idSucursal") Long idDomicilio, DomicilioDetailDTO domicilio) throws DomicilioLogicException {
        return new DomicilioDetailDTO(domicilioLogic.updateDomicilio(idDomicilio ,domicilio.toEntity()));
    }
    
//    @PUT
//    @Path("domicilio/{idDomicilio: \\d+}")
//    public DomicilioDTO entregarDomicilio(@PathParam("idDomicilio") Long idDomicilio) throws DomicilioLogicException
//    {
//        return domicilioLogic.entregarDomicilio(idDomicilio);
//    }
//    
    /**
     * 
     * @param idDomicilio
     * @throws DomicilioLogicException 
     */
    @DELETE
    @Path("{idDomicilio: \\d+}") 
    public void deleteDomicilio(@PathParam("idDomicilio") Long idDomicilio) throws DomicilioLogicException {
        domicilioLogic.deleteDomicilio(idDomicilio);
    }
    
    
   /**
     * 
     * @param idSucursal
     * @return 
     * @throws DomicilioLogicException 
     */
    @GET
    @Path("/{idSucursal: \\d+}/sucursal") 
    public List<DomicilioDetailDTO> getDomiciliosSucursal(@PathParam("idSucursal") Long idSucursal) throws DomicilioLogicException {
        return listEntityToDTO(domicilioLogic.getDomiciliosSucursal(idSucursal));
    }
    
   /**
     * 
     * @param idCliente
     * @return 
     * @throws DomicilioLogicException 
     */
    @GET
    @Path("/cliente/{idCliente: \\d+}") 
    public List<DomicilioDetailDTO> getDomiciliosCliente(@PathParam("idCliente") Long idCliente) throws DomicilioLogicException {
        return listEntityToDTO(domicilioLogic.getDomiciliosCliente(idCliente));
    }
    
    
    /**
     * 
     * @param idDomicilio
     * @param plato
     * @throws DomicilioLogicException 
     */
    @POST
    @Path("/{idDomicilio: \\d+}/agregarPlato") 
    public void addPlato(@PathParam("idDomicilio") Long idDomicilio, PlatoDTO plato) throws DomicilioLogicException {
        domicilioLogic.addPlato(idDomicilio, plato.toEntity());
    }
    
    /**
     * 
     * @param idDomicilio
     * @param idPlato
     * @throws DomicilioLogicException 
     */
    @DELETE
    @Path("/{idDomicilio: \\d+}/{idPlato: \\d+}/plato") 
    public void deletePlato(@PathParam("idDomicilio") Long idDomicilio, @PathParam("idPlato") Long idPlato) throws DomicilioLogicException {
        domicilioLogic.deletePlato(idDomicilio, idPlato);
    }
    
 
    /**
     * 
     * @param idDomicilio
     * @throws DomicilioLogicException 
     */
    @GET
    @Path("/{idDomicilio: \\d+}/platos") 
    public List<PlatoDTO> getPlatosDomicilio(@PathParam("idDomicilio") Long idDomicilio) throws DomicilioLogicException {
        return listPEntityToDTO(domicilioLogic.getDomicilio(idDomicilio).getPlatos());
    }
}