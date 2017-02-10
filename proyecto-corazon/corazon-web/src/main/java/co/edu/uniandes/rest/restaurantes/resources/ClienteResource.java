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

import co.edu.uniandes.rest.restaurantes.dtos.ClienteDTO;
import co.edu.uniandes.rest.restaurantes.dtos.ClienteDetailDTO;
import co.edu.uniandes.rest.restaurantes.exceptions.ClienteLogicException;
import co.edu.uniandes.sisteam.restaurantes.api.IClienteLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
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

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    private IClienteLogic clienteLogic;

    /**
     * Convierte una lista de ClienteEntity a una lista de ClienteDetailDTO.
     *
     * @param entityList Lista de ClienteEntity a convertir.
     * @return Lista de ClienteDetailDTO convertida.
     *
     */
    private List<ClienteDetailDTO> listEntity2DTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Cliente
     *
     * @return Colección de objetos de ClienteDetailDTO
     *
     */
    @GET
    public List<ClienteDetailDTO> getClientes() {

        return listEntity2DTO(clienteLogic.getClientes());
    }

    /**
     * Obtiene los datos de una instancia de Cliente a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ClienteDetailDTO con los datos del Cliente
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("id") Long id) {
        return new ClienteDetailDTO(clienteLogic.getCliente(id));
    }

    /**
     * Obtiene los datos de una instancia de Cliente a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ClienteDetailDTO con los datos del Cliente
     * consultado
     *
     */
    @GET
    @Path("/name")
    public ClienteDetailDTO getClienteByUsername(@QueryParam("name") String name) {
        return new ClienteDetailDTO(clienteLogic.getClienteByUsername(name));
    }

    /**
     * Se encarga de crear un Cliente en la base de datos
     *
     * @param dto Objeto de ClienteDetailDTO con los datos nuevos
     * @return Objeto de ClienteDetailDTOcon los datos nuevos y su ID
     *
     */
    @POST
    public ClienteDetailDTO createCliente(ClienteDetailDTO dto) throws BusinessLogicException {
        return new ClienteDetailDTO(clienteLogic.createCliente(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Cliente
     *
     * @param id Identificador de la instancia de Cliente a modificar
     * @param dto Instancia de ClienteDetailDTO con los nuevos datos
     * @return Instancia de ClienteDetailDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("id") Long id, ClienteDetailDTO dto) {
        ClienteEntity entity = dto.toEntity();
        entity.setId(id);
        return new ClienteDetailDTO(clienteLogic.updateCliente(entity));
    }

    /**
     * Elimina una instancia de Cliente de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCliente(@PathParam("id") Long id) {
        clienteLogic.deleteCliente(id);
    }

}
