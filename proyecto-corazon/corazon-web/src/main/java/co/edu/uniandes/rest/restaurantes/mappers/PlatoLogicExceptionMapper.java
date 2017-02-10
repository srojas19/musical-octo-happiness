/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.mappers;

import co.edu.uniandes.rest.restaurantes.exceptions.PlatoLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de excepciones PlatoLogicException a mensajes REST
 * @author n.sanabria10
 */
@Provider
public class PlatoLogicExceptionMapper implements ExceptionMapper<PlatoLogicException>
{
    /**
     * Generador de una respuesta a partir de una excepción
     * @param ex Excepcion a convertir en mensaje REST
     */
    @Override
    public Response toResponse(PlatoLogicException ex)
    {
        //Retorna una respuesta
        return Response
                .status(Response.Status.NOT_FOUND) //Estado HTTP 404
                .entity(ex.getMessage())
                .type("text/plain")
                .build();
    }
}
