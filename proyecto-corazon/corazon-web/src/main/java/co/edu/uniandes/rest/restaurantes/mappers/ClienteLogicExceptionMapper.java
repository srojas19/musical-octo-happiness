package co.edu.uniandes.rest.restaurantes.mappers;

import co.edu.uniandes.rest.restaurantes.exceptions.ClienteLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * Convertidor de Excepciones ClienteLogicException a mensajes REST.
 */
@Provider
public class ClienteLogicExceptionMapper implements ExceptionMapper<ClienteLogicException> {

	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */
	@Override
	public Response toResponse(ClienteLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}	
}
