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

import co.edu.uniandes.rest.restaurantes.dtos.MesaDTO;
import co.edu.uniandes.rest.restaurantes.dtos.MesaDetailDTO;
import co.edu.uniandes.rest.restaurantes.dtos.SucursalDetailDTO;
import co.edu.uniandes.sisteam.restaurantes.api.IMesaLogic;
import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
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
@Path("/sucursales/{sucursalId: \\d+}/mesas")
public class MesaResource {

    @Inject
    private IMesaLogic mesaLogic;

    @Inject
    private ISucursalLogic sucursalLogic;

    @PathParam("sucursalId")
    private Long sucursalId;

    /**
     * Convierte una lista de MesaEntity a una lista de
     * MesaDetailDTO
     *
     * @param entityList Lista de MesaEntity a convertir
     * @return Lista de MesaDetailDTO convertida
     *
     */
    private List<MesaDetailDTO> listEntity2DTO(List<MesaEntity> entityList) {
        List<MesaDetailDTO> list = new ArrayList<>();
        for (MesaEntity entity : entityList) {
            list.add(new MesaDetailDTO(entity));
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
     * Obtiene los datos de los Mesas de una compañía a partir del ID de
     * la Sucursal
     *
     *
     * @return Lista de MesaDetailDTO con los datos del Mesa
     * consultado
     *
     */
    @GET
    public List<MesaDetailDTO> getMesas() {
        existsSucursal(sucursalId);
        
        List<MesaEntity> mesas = mesaLogic.getMesas(sucursalId);

        return listEntity2DTO(mesas);
    }

    /**
     * Obtiene los datos de una instancia de Mesa a partir de su ID
     * asociado a un Sucursal
     *
     * @param mesaId Identificador de la instancia a consultar
     * @return Instancia de MesaDetailDTO con los datos del Mesa
     * consultado
     *
     */
    @GET
    @Path("{mesaId: \\d+}")
    public MesaDetailDTO getMesa(@PathParam("mesaId") Long mesaId) {
        existsSucursal(sucursalId);
        MesaEntity entity = mesaLogic.getMesa(mesaId);
        if (entity.getSucursal() != null && !sucursalId.equals(entity.getSucursal().getId())) {
            throw new WebApplicationException(404);
        }
        return new MesaDetailDTO(entity);
    }

    /**
     * Asocia un Mesa existente a un Sucursal
     *
     * @param dto Objeto de MesaDetailDTO con los datos nuevos
     * @return Objeto de MesaDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
    public MesaDetailDTO createMesa(MesaDetailDTO dto) throws BusinessLogicException {
        existsSucursal(sucursalId);
        return new MesaDetailDTO(mesaLogic.createMesa(sucursalId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Mesa.
     *
     * @param mesaId Identificador de la instancia de Mesa a
     * modificar
     * @param dto Instancia de MesaDetailDTO con los nuevos datos.
     * @return Instancia de MesaDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{mesaId: \\d+}")
    public MesaDetailDTO updateMesa(@PathParam("mesaId") Long mesaId, MesaDetailDTO dto) {
        existsSucursal(sucursalId);
        MesaEntity entity = dto.toEntity();
        entity.setId(mesaId);
        MesaEntity oldEntity = mesaLogic.getMesa(mesaId);
        return new MesaDetailDTO(mesaLogic.updateMesa(sucursalId, entity));
    }

    /**
     * Elimina una instancia de Mesa de la base de datos.
     *
     * @param mesaId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{mesaId: \\d+}")
    public void deleteMesa(@PathParam("mesaId") Long mesaId) {
        existsSucursal(sucursalId);
        mesaLogic.deleteMesa(mesaId);
    }
    
    
    /**
     * Obtiene los datos de una instancia de Mesa a partir de su ID
     * asociado a un Sucursal
     *
     * @param mesaId Identificador de la instancia a consultar
     * @return Instancia de MesaDetailDTO con los datos del Mesa
     * consultado
     *
     */
    @GET
    @Path("{mesaId: \\d+}/get2")
    public MesaDetailDTO getMesa2(@PathParam("mesaId") Long mesaId) {
       
        MesaEntity entity = mesaLogic.getMesa(mesaId);
       
        return new MesaDetailDTO(entity);
    }

}
