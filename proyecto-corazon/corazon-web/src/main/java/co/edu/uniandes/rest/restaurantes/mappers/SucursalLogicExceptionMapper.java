package co.edu.uniandes.rest.restaurantes.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException;

/**
 * Convertidor de Excepciones SucursalLogicException a mensajes REST.
 */
@Provider
public class SucursalLogicExceptionMapper implements ExceptionMapper<SucursalLogicException> {

	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */
	@Override
	public Response toResponse(SucursalLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
            
	}
	
}
