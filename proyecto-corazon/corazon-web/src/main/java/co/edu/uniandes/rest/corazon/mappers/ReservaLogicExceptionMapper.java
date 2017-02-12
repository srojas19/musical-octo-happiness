/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.mappers;

import co.edu.uniandes.rest.corazon.exceptions.ReservaLogicException;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author BarraganJeronimo
 */
@Provider
public class ReservaLogicExceptionMapper implements ExceptionMapper<ReservaLogicException>{
    
    /**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
     * @return 
	 */
	@Override
	public Response toResponse(ReservaLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
            
	}
    
    
}
