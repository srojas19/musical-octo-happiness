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

import co.edu.uniandes.rest.restaurantes.dtos.SucursalDTO;
import co.edu.uniandes.rest.restaurantes.dtos.SucursalDetailDTO;
import co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException;
import co.edu.uniandes.sisteam.restaurantes.api.ISucursalLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
import java.util.List;

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
import javax.inject.Inject;
import javax.ws.rs.QueryParam;

@Path("/sucursales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SucursalResource {

    @Inject
    private ISucursalLogic sucursalLogic;

    /**
     * Convierte una lista de SucursalEntity a una lista de SucursalDTO.
     *
     * @param entityList Lista de SucursalEntity a convertir.
     * @return Lista de SucursalDetailDTO convertida.
     *
     */
    private List<SucursalDTO> listEntity2DTO(List<SucursalEntity> entityList) {
        List<SucursalDTO> list = new ArrayList<>();
        for (SucursalEntity entity : entityList) {
            list.add(new SucursalDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Sucursal
     *
     * @return Colección de objetos de SucursalDetailDTO
     *
     */
    @GET
    public List<SucursalDTO> getSucursales() {

        return listEntity2DTO(sucursalLogic.getSucursales());
    }

    /**
     * Obtiene los datos de una instancia de Sucursal a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SucursalDetailDTO con los datos del Sucursal
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public SucursalDetailDTO getSucursal(@PathParam("id") Long id) {
        return new SucursalDetailDTO(sucursalLogic.getSucursal(id));
    }

    /**
     * Obtiene los datos de una instancia de Sucursal a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SucursalDetailDTO con los datos del Sucursal
     * consultado
     *
     */
    @GET
    @Path("/name")
    public SucursalDetailDTO getSucursalByName(@QueryParam("name") String name) {
        return new SucursalDetailDTO(sucursalLogic.getSucursalByName(name));
    }

    /**
     * Se encarga de crear un Sucursal en la base de datos
     *
     * @param dto Objeto de SucursalDetailDTO con los datos nuevos
     * @return Objeto de SucursalDetailDTOcon los datos nuevos y su ID
     *
     */
    @POST
    public SucursalDetailDTO createSucursal(SucursalDetailDTO dto) throws BusinessLogicException {
        return new SucursalDetailDTO(sucursalLogic.createSucursal(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Sucursal
     *
     * @param id Identificador de la instancia de Sucursal a modificar
     * @param dto Instancia de SucursalDetailDTO con los nuevos datos
     * @return Instancia de SucursalDetailDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public SucursalDetailDTO updateSucursal(@PathParam("id") Long id, SucursalDetailDTO dto) {
        SucursalEntity entity = dto.toEntity();
        entity.setId(id);
        return new SucursalDetailDTO(sucursalLogic.updateSucursal(entity));
    }

    /**
     * Elimina una instancia de Sucursal de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSucursal(@PathParam("id") Long id) {
        sucursalLogic.deleteSucursal(id);
    }

//    @GET
//    @Path("{id: \\d+}/numberofmesas")
//    public Integer getNumberOfMesasSucursal(@PathParam("id") Long id) {
//        return sucursalLogic.getNumberOfMesasSucursal(id);
//    }
    /**
     * 
     * @param sucursalId
     * @return
     * @throws SucursalLogicException
     */
    @Path("{sucursalId: \\d+}-{fecha}/reservas")
    public Class<SucursalReservasResourse> getReservasResource(@PathParam("sucursalId") Long sucursalId) throws SucursalLogicException {

        SucursalDTO sucursal = getSucursal(sucursalId);
        if (sucursal == null) {
            throw new SucursalLogicException("El sucursal no existe");
        }
        return SucursalReservasResourse.class;

    }


}
