/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.resources;

import co.edu.uniandes.rest.restaurantes.dtos.ReservaClienteDetailDTO;

import co.edu.uniandes.rest.restaurantes.dtos.ReservaDetailDTO;
import co.edu.uniandes.sisteam.restaurantes.api.IReservaLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
import co.edu.uniandes.rest.restaurantes.exceptions.ReservaLogicException;
import co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException;
import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("clientes/{clienteId: \\d+}/reservas")
@Produces("application/json")
public class ReservaResource {

    @Inject
    private IReservaLogic reservaLogic;

    private List<ReservaClienteDetailDTO> listEntityDTO(List<ReservaEntity> entityList) {

        List<ReservaClienteDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaClienteDetailDTO(entity));
        }
        return list;
    }
    
     private List<ReservaDetailDTO> listEntityDTO2(List<ReservaEntity> entityList) {

        List<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene el listado de reservaciones.
     *
     * @param clienteId
     * @return lista de reservas para un cliente con id dado por parametro
     * @throws ReservaLogicException excepción retornada por la lógica ( cuando
     * id del cliente no tiene reservas)
     */
    @GET
    public List<ReservaClienteDetailDTO> getReservasCliente(@PathParam("clienteId") Long clienteId) throws ReservaLogicException {
        return listEntityDTO(reservaLogic.getReservasCliente(clienteId));
    }
    
   
    /**
     * Obtiene una reserva.
     *
     * @param reservaId
     * @return lista de reservas para un cliente con id dado por parametro
     * @throws ReservaLogicException excepción retornada por la lógica ( cuando
     * id del cliente no tiene reservas)
     */
    @GET
    @Path("{id: \\d+}")
    public ReservaClienteDetailDTO getReservaId(@PathParam("id") Long reservaId) throws ReservaLogicException {
        return new ReservaClienteDetailDTO(reservaLogic.getReservaId(reservaId));
    }

    /**
     * Agrega una reserva
     *
     * @param clienteId
     * @param reserva reserva a agregar
     * @return datos de la reservaa agregar
     * @throws ReservaLogicException cuando ya existe una reservacon el id
     * suministrado
     * @throws java.text.ParseException
     */
    @POST
    @Produces("application/json")
    public ReservaClienteDetailDTO createReserva(@PathParam("clienteId") Long clienteId, ReservaDetailDTO reserva) throws ReservaLogicException  {
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(reserva.getFechaS()!=null){
                Date nFecha;
                try {
                    nFecha = sdf.parse(reserva.getFechaS());
                    reserva.setFecha(nFecha);
                } catch (ParseException ex) {
                    throw new ReservaLogicException (""+ex.getMessage());     
                }
            }
            
            return new ReservaClienteDetailDTO(reservaLogic.createReserva(clienteId, reserva.getMesa().getId(), reserva.toEntity()));
        } catch (BusinessLogicException ex) {
            throw new ReservaLogicException (ex.getMessage());
        }
    }

    /**
     * Actualiza una reservacon un Id
     *
     * @param clienteId
     * @param reservaId
     * @param reserva reservaa actualizar
     * @return datos de la reservaactualizada.
     * @throws ReservaLogicException cuando no existe una reservacon el id
     * suministrado
     * @throws java.text.ParseException
     */
    @PUT
    @Path("{reservaId: \\d+}")
    public ReservaDetailDTO updateReserva(@PathParam("clienteId") Long clienteId,@PathParam("reservaId") Long reservaId, ReservaDetailDTO reserva) throws ReservaLogicException  {
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(reserva.getFechaS()!=null){
        Date nFecha;
            try {
                nFecha = sdf.parse(reserva.getFechaS());
                 reserva.setFecha(nFecha);
            } catch (ParseException ex) {
                throw new ReservaLogicException (""+ex.getMessage());
            }     
        }
        
        ReservaEntity entity= reserva.toEntity();
        entity.setId(reservaId);
        try {
            return new ReservaDetailDTO(reservaLogic.updateReserva(clienteId, entity.getMesa().getId(), entity));
        } catch (BusinessLogicException ex) {
           throw new ReservaLogicException (ex.getMessage());
        }
    }

    /**
     * elimina una reserva
     *
     * @param idReserva
     * @throws ReservaLogicException cuando no existe una reservacon el id
     * suministrado
     * @throws
     * co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException
     */
    @DELETE
    @Path("{idReserva: \\d+}")
    public void deleteReserva(@PathParam("idReserva") Long idReserva) throws ReservaLogicException, SucursalLogicException {
        reservaLogic.deleteReserva(idReserva);
    }
    
    
     /**
     * Obtiene el listado de reservaciones.
     *
     * @param sudursalId
     * @param fecha
     * @return lista de reservas para un cliente con id dado por parametro
     * @throws ReservaLogicException excepción retornada por la lógica ( cuando id del cliente no tiene reservas)
     * @throws co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException
     * @throws java.text.ParseException
     */
    @GET 
    @Path("{sucursalId: \\d+}-{fecha}/sucursal")
    public List<ReservaDetailDTO> getReservas(@PathParam("sucursalId") Long sudursalId,@PathParam("fecha") Long fecha)  throws ReservaLogicException, SucursalLogicException, ParseException {
       
  //      SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        Date nFecha = new Date(fecha);
        
       return listEntityDTO2(reservaLogic.getReservaSucursal(sudursalId, nFecha));
    }
    
    

}
