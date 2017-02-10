/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.resources;

import co.edu.uniandes.rest.restaurantes.dtos.PlatoDTO;
import co.edu.uniandes.rest.restaurantes.dtos.PlatoDetailDTO;
import co.edu.uniandes.sisteam.restaurantes.api.IPlatoLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import co.edu.uniandes.rest.restaurantes.exceptions.PlatoLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.inject.Inject;

/**
 * Clase que implementa el recurso REST correspondiente a Platos
 * @author n.sanabria10
 */
@Path("platos")
@Produces("application/json")
public class PlatoResource {
    
    @Inject
    private IPlatoLogic platoLogic;
    
      private List<PlatoDTO> listEntityDTO(List<PlatoEntity> entityList) {

        List<PlatoDTO> list = new ArrayList<PlatoDTO>();
        for (PlatoEntity entity : entityList) {
            list.add(new PlatoDTO(entity));
        }
        return list;
    }
    
    /**
     * Obtiene listado de plato
     */
    @GET
    public List<PlatoDTO> getPlatos() throws PlatoLogicException {
        return listEntityDTO(platoLogic.getPlatos());
    }
    /**
     * Obtiene platos de un domicilio
     * @param idDomicilio
     * @return
     * @throws PlatoLogicException 
     */
    
//    @GET
//    @Path("{idDomicilio: \\d+}")
//    public List<PlatoDTO> getPlatosDomicilio(@PathParam("idDomicilio") Long idDomicilio) throws PlatoLogicException {
//        return listEntityDTO(platoLogic.getPlatosDomicilio(idDomicilio));
//    }
    
    /**
     * Obtiene un plato por id
     */
    @GET
    @Path("{id: \\d+}")
    public PlatoDetailDTO getPlato(@PathParam("id") Long idPlato) throws PlatoLogicException
    {
        return new PlatoDetailDTO(platoLogic.getPlato(idPlato));
    }
    
    /**
     * Agrega un plato  
     */
    @POST
    @Produces("application/json")
   public PlatoDTO createPlato(PlatoDetailDTO plato) throws PlatoLogicException {
       
        return new PlatoDTO(platoLogic.createPlato(plato.getDomicilio().getIdDomicilio(), plato.toEntity()));
    }
 
    
    /**
     * Actualiza un plato con el id dado
     */
    @PUT
    @Path("{idPlato: \\d+}")
    public PlatoDTO actualizarPlato(@PathParam("idPlato") Long idPlato, PlatoDetailDTO plato) throws PlatoLogicException
    {
        PlatoEntity entity = plato.toEntity();
        entity.setId(idPlato);
        return new PlatoDetailDTO(platoLogic.updatePlato(entity.getDomicilio().getId(), entity));
    }
    
    /**
     * Elimina un plato con el id dado
     */
    @DELETE
    @Path("{idPlato: \\d+}")
    public void eliminarPlato(@PathParam("idPlato") Long idPlato) throws PlatoLogicException
    {
        platoLogic.deletePlato(idPlato);
    }
}
