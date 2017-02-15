/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.resources;

import co.edu.uniandes.rest.corazon.dtos.MarcapasosDetailDTO;
import co.edu.uniandes.rest.corazon.dtos.MarcapasosDTO;
import co.edu.uniandes.rest.corazon.exceptions.MarcapasosLogicException;
import co.edu.uniandes.sisteam.corazon.api.IMarcapasosLogic;
import co.edu.uniandes.sisteam.corazon.entities.MarcapasosEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author BarraganJeronimo
 */
@Path("/pacientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarcapasosResource {
    
    @Inject
    private IMarcapasosLogic marcapasosLogic;
    
    
    /**
     * Convierte una lista de MarcapasosEntity a una lista de MarcapasosDTO.
     *
     * @param entityList Lista de MarcapasosEntity a convertir.
     * @return Lista de MarcapasosDTO convertida.
     *
     */
    private List<MarcapasosDTO> listEntity2DTO(List<MarcapasosEntity> entityList) {
        List<MarcapasosDTO> list = new ArrayList<>();
        for (MarcapasosEntity entity : entityList) {
            list.add(new MarcapasosDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<MarcapasosDTO> getMarcapasos() { 
        
        return listEntity2DTO(marcapasosLogic.getAllMarcapasos());
    }
    
    @GET
    @Path("{id: \\d+}")
    public MarcapasosDetailDTO getMedico(@PathParam("id") Long id) 
    {
        return new MarcapasosDetailDTO(marcapasosLogic.getMarcapasosId(id));
    }
    
    @POST
    @Path("{id: \\d+}")
    public MarcapasosDetailDTO createMarcapasos(MarcapasosDetailDTO dto, Long idMarcapasos) throws MarcapasosLogicException  {
        System.out.println("dto es "+ dto.toEntity());
        MarcapasosDetailDTO respuesta= dto;
        
        try {
            respuesta = new MarcapasosDetailDTO(marcapasosLogic.createMarcapasos(dto.toEntity(),idMarcapasos));
        } catch (BusinessLogicException ex) {
            throw new MarcapasosLogicException(ex.getMessage());
        }
        
        
        return respuesta;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public MarcapasosDetailDTO updateMarcapasos(@PathParam("id") Long id, MarcapasosDetailDTO dto) throws MarcapasosLogicException {
        MarcapasosEntity entity = dto.toEntity();
        entity.setId(id);
        try {
            return new MarcapasosDetailDTO(marcapasosLogic.updateMarcapasos(entity));
        } catch (BusinessLogicException ex) {
             throw new MarcapasosLogicException(ex.getMessage());
        }
    }
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMarcapasos(@PathParam("id") Long id) {
        marcapasosLogic.deleteMarcapasos(id);
    }
   
}
