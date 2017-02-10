/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.exceptions;

/**
 *
 * @author n.sanabria10
 */
public class PlatoLogicException extends Exception {
    
    /**
     * Versión usada de la serialización de la clase
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor por defecto
     */
    public PlatoLogicException(){
        
    }
    
    /**
     * Constructor con un mensaje
     * @param message Mensaje de la excepción
     */
    public PlatoLogicException(String message){
        super(message);
    }
    
    /**
     * Constructor con causa
     * @param cause Causa de la excepción
     */
    public PlatoLogicException(Throwable cause)
    {
        super(cause);
    }
    
    /**
     * Constructor con mensaje y causa
     * @param message Mensaje de la excepción
     * @param cause Causa de la excepción
     */
    public PlatoLogicException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
