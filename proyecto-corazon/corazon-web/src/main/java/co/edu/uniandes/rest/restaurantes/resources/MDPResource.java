/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.rest.restaurantes.resources;

import co.edu.uniandes.rest.restaurantes.dtos.MDPDTO;
import co.edu.uniandes.rest.restaurantes.dtos.MDPDetailDTO;
import co.edu.uniandes.rest.restaurantes.dtos.ClienteDetailDTO;
import co.edu.uniandes.sisteam.restaurantes.api.IMDPLogic;
import co.edu.uniandes.sisteam.restaurantes.api.IClienteLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;
import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
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

import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/clientes/{clienteId: \\d+}/mdps")
public class MDPResource {

    @Inject
    private IMDPLogic mdpLogic;

    @Inject
    private IClienteLogic clienteLogic;

    @PathParam("clienteId")
    private Long clienteId;

    /**
     * Convierte una lista de MDPEntity a una lista de
     * MDPDetailDTO
     *
     * @param entityList Lista de MDPEntity a convertir
     * @return Lista de MDPDetailDTO convertida
     *
     */
    private List<MDPDetailDTO> listEntity2DTO(List<MDPEntity> entityList) {
        List<MDPDetailDTO> list = new ArrayList<>();
        for (MDPEntity entity : entityList) {
            list.add(new MDPDetailDTO(entity));
        }
        return list;
    }

    public void existsCliente(Long clienteId) {
        ClienteDetailDTO cliente = new ClienteDetailDTO(clienteLogic.getCliente(clienteId));
        if (cliente == null) {
            throw new WebApplicationException(404);
        }
    }

    /**
     * Obtiene los datos de los MDPs de una compañía a partir del ID de
     * la Cliente
     *
     *
     * @return Lista de MDPDetailDTO con los datos del MDP
     * consultado
     *
     */
    @GET
    public List<MDPDetailDTO> getMDPs() {
        existsCliente(clienteId);
        
        List<MDPEntity> mdps = mdpLogic.getMDPCliente(clienteId);

        return listEntity2DTO(mdps);
    }

    /**
     * Obtiene los datos de una instancia de MDP a partir de su ID
     * asociado a un Cliente
     *
     * @param mdpId Identificador de la instancia a consultar
     * @return Instancia de MDPDetailDTO con los datos del MDP
     * consultado
     *
     */
    @GET
    @Path("{mdpId: \\d+}")
    public MDPDetailDTO getMDP(@PathParam("mdpId") Long mdpId) {
        existsCliente(clienteId);
        MDPEntity entity = mdpLogic.getMDP(mdpId);
        if (entity.getCliente() != null && !clienteId.equals(entity.getCliente().getId())) {
            throw new WebApplicationException(404);
        }
        return new MDPDetailDTO(entity);
    }

    /**
     * Asocia un MDP existente a un Cliente
     *
     * @param dto Objeto de MDPDetailDTO con los datos nuevos
     * @return Objeto de MDPDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
    public MDPDetailDTO createMDP(MDPDetailDTO dto) throws BusinessLogicException {
        existsCliente(clienteId);
        return new MDPDetailDTO(mdpLogic.createMDP(clienteId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de MDP.
     *
     * @param mdpId Identificador de la instancia de MDP a
     * modificar
     * @param dto Instancia de MDPDetailDTO con los nuevos datos.
     * @return Instancia de MDPDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{mdpId: \\d+}")
    public MDPDetailDTO updateMDP(@PathParam("mdpId") Long mdpId, MDPDetailDTO dto) {
        existsCliente(clienteId);
        MDPEntity entity = dto.toEntity();
        entity.setId(mdpId);
        MDPEntity oldEntity = mdpLogic.getMDP(mdpId);
        return new MDPDetailDTO(mdpLogic.updateMDP(clienteId, entity));
    }

    /**
     * Elimina una instancia de MDP de la base de datos.
     *
     * @param mdpId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{mdpId: \\d+}")
    public void deleteMDP(@PathParam("mdpId") Long mdpId) {
        existsCliente(clienteId);
        mdpLogic.deleteMDP(mdpId);
    }

}
