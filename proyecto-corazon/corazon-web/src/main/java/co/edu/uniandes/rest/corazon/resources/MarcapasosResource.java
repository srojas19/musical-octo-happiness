/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.resources;

import co.edu.uniandes.rest.corazon.dtos.HistoriaClinicaDTO;
import co.edu.uniandes.rest.corazon.dtos.MarcapasosDetailDTO;
import co.edu.uniandes.rest.corazon.dtos.MarcapasosDTO;
import co.edu.uniandes.rest.corazon.exceptions.MarcapasosLogicException;
import co.edu.uniandes.sisteam.corazon.api.IMarcapasosLogic;
import co.edu.uniandes.sisteam.corazon.entities.HistoriaClinicaEntity;
import co.edu.uniandes.sisteam.corazon.entities.MarcapasosRealEntity;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author BarraganJeronimo
 */
@Path("resources/pacientes/{idPaciente: \\d+}/marcapasos")
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
    private List<MarcapasosDTO> listEntity2DTO(List<MarcapasosRealEntity> entityList) {
        List<MarcapasosDTO> list = new ArrayList<>();
        for (MarcapasosRealEntity entity : entityList) {
            list.add(new MarcapasosDTO(entity));
        }
        return list;
    }

    @GET
    public MarcapasosDetailDTO getMarcapasosPaciente(@PathParam("idPaciente") Long idPaciente) {
        MarcapasosRealEntity entity = marcapasosLogic.getMarcapasosPaciente(idPaciente);
        if (entity == null) {
            throw new WebApplicationException(404);
        }
        return new MarcapasosDetailDTO(entity);
    }

    /**
     *
     * @param dto
     * @param idPaciente
     * @param id
     * @return
     * @throws MarcapasosLogicException
     */
    @POST
    public MarcapasosDetailDTO createMarcapasos(MarcapasosDetailDTO dto, @PathParam("idPaciente") Long idPaciente) throws MarcapasosLogicException {
        // System.out.println("dto es "+ dto.toEntity());
        MarcapasosDetailDTO respuesta = dto;

        try {
            respuesta = new MarcapasosDetailDTO(marcapasosLogic.createMarcapasos(dto.toEntity(), idPaciente));
        } catch (BusinessLogicException ex) {
            throw new MarcapasosLogicException(ex.getMessage());
        }

        return respuesta;
    }

    /**
     *
     * @param id
     * @param dto
     * @return
     * @throws MarcapasosLogicException
     */
    @PUT
    public MarcapasosDetailDTO updateMarcapasos(MarcapasosDetailDTO dto, @PathParam("idPaciente") Long idPaciente) throws MarcapasosLogicException {
        MarcapasosRealEntity entity = dto.toEntity();
        try 
        {
            return new MarcapasosDetailDTO(marcapasosLogic.updateMarcapasos(entity, idPaciente));
        } 
        catch (BusinessLogicException ex) 
        {
            throw new MarcapasosLogicException(ex.getMessage());

        }
    }
}
