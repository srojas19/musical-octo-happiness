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

import co.edu.uniandes.rest.restaurantes.dtos.PlatoEspDTO;
import co.edu.uniandes.rest.restaurantes.dtos.PlatoEspDetailDTO;
import co.edu.uniandes.rest.restaurantes.dtos.SucursalDetailDTO;
import co.edu.uniandes.sisteam.restaurantes.api.IPlatoEspLogic;
import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
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
@Path("/sucursales/{sucursalId: \\d+}/platosEsp")
public class PlatoEspResource {

    @Inject
    private IPlatoEspLogic platoEspLogic;

    @Inject
    private ISucursalLogic sucursalLogic;

    @PathParam("sucursalId")
    private Long sucursalId;

    /**
     * Convierte una lista de PlatoEspEntity a una lista de
     * PlatoEspDetailDTO
     *
     * @param entityList Lista de PlatoEspEntity a convertir
     * @return Lista de PlatoEspDetailDTO convertida
     *
     */
    private List<PlatoEspDetailDTO> listEntity2DTO(List<PlatoEspEntity> entityList) {
        List<PlatoEspDetailDTO> list = new ArrayList<>();
        for (PlatoEspEntity entity : entityList) {
            list.add(new PlatoEspDetailDTO(entity));
        }
        return list;
    }

    public void existsSucursal(Long sucursalId) {
        SucursalDetailDTO sucursal = new SucursalDetailDTO(sucursalLogic.getSucursal(sucursalId));
        if (sucursal == null) {
            throw new WebApplicationException(404);
        }
    }

    /**
     * Obtiene los datos de los PlatosEsp de una compañía a partir del ID de
     * la Sucursal
     *
     *
     * @return Lista de PlatoEspDetailDTO con los datos del PlatoEsp
     * consultado
     *
     */
    @GET
    public List<PlatoEspDetailDTO> getPlatosEsp() {
        existsSucursal(sucursalId);
        
        List<PlatoEspEntity> platosEsp = platoEspLogic.getPlatosEsp(sucursalId);

        return listEntity2DTO(platosEsp);
    }

    /**
     * Obtiene los datos de una instancia de PlatoEsp a partir de su ID
     * asociado a un Sucursal
     *
     * @param platoEspId Identificador de la instancia a consultar
     * @return Instancia de PlatoEspDetailDTO con los datos del PlatoEsp
     * consultado
     *
     */
    @GET
    @Path("{platoEspId: \\d+}")
    public PlatoEspDetailDTO getPlatoEsp(@PathParam("platoEspId") Long platoEspId) {
        existsSucursal(sucursalId);
        PlatoEspEntity entity = platoEspLogic.getPlatoEsp(platoEspId);
        if (entity.getSucursal() != null && !sucursalId.equals(entity.getSucursal().getId())) {
            throw new WebApplicationException(404);
        }
        return new PlatoEspDetailDTO(entity);
    }

    /**
     * Asocia un PlatoEsp existente a un Sucursal
     *
     * @param dto Objeto de PlatoEspDetailDTO con los datos nuevos
     * @return Objeto de PlatoEspDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
    public PlatoEspDetailDTO createPlatoEsp(PlatoEspDetailDTO dto) throws BusinessLogicException {
        existsSucursal(sucursalId);
        return new PlatoEspDetailDTO(platoEspLogic.createPlatoEsp(sucursalId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de PlatoEsp.
     *
     * @param platoEspId Identificador de la instancia de PlatoEsp a
     * modificar
     * @param dto Instancia de PlatoEspDetailDTO con los nuevos datos.
     * @return Instancia de PlatoEspDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{platoEspId: \\d+}")
    public PlatoEspDetailDTO updatePlatoEsp(@PathParam("platoEspId") Long platoEspId, PlatoEspDetailDTO dto) {
        existsSucursal(sucursalId);
        PlatoEspEntity entity = dto.toEntity();
        entity.setId(platoEspId);
        PlatoEspEntity oldEntity = platoEspLogic.getPlatoEsp(platoEspId);
        return new PlatoEspDetailDTO(platoEspLogic.updatePlatoEsp(sucursalId, entity));
    }

    /**
     * Elimina una instancia de PlatoEsp de la base de datos.
     *
     * @param platoEspId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{platoEspId: \\d+}")
    public void deletePlatoEsp(@PathParam("platoEspId") Long platoEspId) {
        existsSucursal(sucursalId);
        platoEspLogic.deletePlatoEsp(platoEspId);
    }

}
