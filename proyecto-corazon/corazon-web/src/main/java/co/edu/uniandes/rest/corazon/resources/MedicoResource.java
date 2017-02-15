/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.resources;

import co.edu.uniandes.rest.corazon.dtos.MedicoDetailDTO;
import co.edu.uniandes.rest.corazon.dtos.MedicoDTO;
import co.edu.uniandes.rest.corazon.exceptions.MedicoLogicException;
import co.edu.uniandes.sisteam.corazon.api.IMedicoLogic;
import co.edu.uniandes.sisteam.corazon.entities.MedicoEntity;
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
public class MedicoResource {
    
     @Inject
    private IMedicoLogic medicoLogic;
    
    
    /**
     * Convierte una lista de MedicoEntity a una lista de MedicoDTO.
     *
     * @param entityList Lista de MedicoEntity a convertir.
     * @return Lista de MedicoDTO convertida.
     *
     */
    private List<MedicoDTO> listEntity2DTO(List<MedicoEntity> entityList) {
        List<MedicoDTO> list = new ArrayList<>();
        for (MedicoEntity entity : entityList) {
            list.add(new MedicoDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<MedicoDTO> getMedico() { 
        
        return listEntity2DTO(medicoLogic.getAllMedicos());
    }
    
    @GET
    @Path("{id: \\d+}")
    public MedicoDetailDTO getMedico(@PathParam("id") Long id) 
    {
        return new MedicoDetailDTO(medicoLogic.getMedicoId(id));
    }
    
    @POST
    public MedicoDetailDTO createMedico(MedicoDetailDTO dto) throws MedicoLogicException  {
        System.out.println("dto es "+ dto.toEntity());
        MedicoDetailDTO respuesta= dto;      
        try {
            respuesta = new MedicoDetailDTO(medicoLogic.createMedico(dto.toEntity()));
        } catch (BusinessLogicException ex) {
            throw new MedicoLogicException(ex.getMessage());
        }
        
        
        return respuesta;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public MedicoDetailDTO updateMedico(@PathParam("id") Long id, MedicoDetailDTO dto) throws MedicoLogicException {
        MedicoEntity entity = dto.toEntity();
        entity.setId(id);
        try {
            return new MedicoDetailDTO(medicoLogic.updateMedico(entity));
        } catch (BusinessLogicException ex) {
             throw new MedicoLogicException(ex.getMessage());
        }
    }
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMedico(@PathParam("id") Long id) {
        medicoLogic.deleteMedico(id);
    }
    
}
