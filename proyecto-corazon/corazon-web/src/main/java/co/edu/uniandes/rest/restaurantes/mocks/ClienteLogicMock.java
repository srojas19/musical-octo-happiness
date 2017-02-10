//package co.edu.uniandes.rest.restaurantes.mocks;
//
///**
// * Mock del recurso Clientes (Mock del servicio REST)
// * @author Asistente
// */
//import co.edu.uniandes.rest.restaurantes.dtos.ClienteDTO;
//import co.edu.uniandes.rest.restaurantes.dtos.MDPDTO;
//import co.edu.uniandes.rest.restaurantes.exceptions.ClienteLogicException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///*
// * CityLogicMock
// * Mock del recurso Clientes (Mock del servicio REST)
// */
//
//public class ClienteLogicMock {
//	
//    // objeto para presentar logs de las operaciones
//    private final static Logger logger = Logger.getLogger(ClienteLogicMock.class.getName());
//	
//	// listado de clientes
//    private static ArrayList<ClienteDTO> clientes;
//    private static ArrayList<Integer> domicilios;
//
//    /**
//     * Constructor. Crea los datos de ejemplo.
//     */
//    public ClienteLogicMock() {
//
////    	if (clientes == null) {
////            clientes = new ArrayList<>();
////            clientes.add(new ClienteDTO(0, "Elbo Nito", "1234", "123, Avenida Siempre Viva", 15, domicilios));
////            clientes.add(new ClienteDTO(1, "Susana Horia", "0000", "Pesherman, Calle Wallaby, 4-2 Sidney", 23, domicilios));
////            clientes.add(new ClienteDTO(2, "Elza Capuntas", "123456", "124 Conch Street, Bikini Bottom, Pacific Ocean", 42, domicilios));
////        }
////        
//    	// indica que se muestren todos los mensajes
//    	logger.setLevel(Level.INFO);
//    	
//    	// muestra información 
//    	logger.info("Inicializa la lista de clientes");
//    	logger.info("clientes" + clientes );
//    }    
//    
//	/**
//	 * Obtiene el listado de clientes. 
//	 * @return lista de Clientes
//	 * @throws CclienteLogicException cuando no existe la lista en memoria  
//	 */    
//    public List<ClienteDTO> getClientes() throws ClienteLogicException {
//    	if (clientes == null) {
//    		logger.severe("Error interno: lista de clientes no existe.");
//    		throw new ClienteLogicException("Error interno: lista de clientes no existe.");    		
//    	}
//    	
//    	logger.info("retornando todas los clientes");
//    	return clientes;
//    }
//
// 
//
//    /**
//     * Agrega un cliente a la lista.
//     * @param newCliente cliente a adicionar
//     * @throws ClienteLogicException cuando ya existe un cliente con el usuario suministrado
//     * @return cliente agregado
//     */
//    public ClienteDTO createCliente(ClienteDTO newCliente) throws ClienteLogicException {
//    	logger.info("recibiendo solicitud de agregar cliente " + newCliente);
//    	        
//        //El nuevo cliente tiene id ?
//        if(newCliente.getId()!=null)
//        {
//            //Busca el cliente con el id suministrado 
//            for(ClienteDTO cliente:clientes)
//            {
//                //Si existe un cliente con ese id 
//                if(Objects.equals(cliente.getId(), newCliente.getId()))
//                {
//                    logger.severe("Ya existe un cliente con ese id");
//                    throw new ClienteLogicException("Ya existe un cliente con ese id");
//                }
//            }
//            
//        }
//        //No tiene un id ?
//        else
//        {
//            //Genera un id para el cliente
//            logger.info("Generando un nuevo id para el cliente");
//            long newID = 1;
//            for(ClienteDTO cliente : clientes)
//            {
//                if(newID <= cliente.getId())
//                {
//                    newID = cliente.getId()+1;
//                }
//            }
//            newCliente.setId(newID);
//        }
//        //Agrega agrega el cliente
//        logger.info("Agregando cliente: " + newCliente );
//        clientes.add(newCliente);
//        return newCliente;
//    }
//    
//    public ClienteDTO getCliente(Long id) throws ClienteLogicException
//    {
//        logger.info("recibiendo solicitud de busqueda de cliente con id: " + id);
//         ClienteDTO cl = null;
//         
//         //Busca el cliente con el id solicitado
//         for(ClienteDTO cliente : clientes)
//         {
//             if(Objects.equals(cliente.getId(), id))
//             {
//                 cl = cliente;
//             }
//         }
//         
//         //No existe cliente con tal id
//         if(cl==null)
//         {
//         logger.info("No existe un cliente con el id suministrado");
//         throw new ClienteLogicException("No existe un cliente con el id suministrado");
//         }
//         return cl;
//    }
//    
//        /**
//         * Modifica la informacion de un cliente
//         * @param id
//         * @param newClient
//         * @return
//         * @throws ClienteLogicException 
//         */
//    public ClienteDTO actualizarCliente(Long id, ClienteDTO newClient) throws ClienteLogicException
//    {
//        logger.info("recibiendo solicitud de actualizar cliente con id: " + id);
//         ClienteDTO pd = null;
//         
//         //El nuevo cliente tiene id ?
//         if(newClient.getId()!=null)
//         {
//             //Busca el cliente con el id solicitado
//         for(ClienteDTO cliente : clientes)
//         {
//             if(Objects.equals(cliente.getId(), newClient.getId()))
//             {
//                 //Actualiza el cliente
//                 logger.info("Actualizando plato: " +newClient);
//                 cliente.setId(newClient.getId());
//                 cliente.setUsername(newClient.getUsername());
//                 cliente.setPassword(newClient.getPassword());
//                 cliente.setPuntos(newClient.getPuntos());
//                 cliente.setDireccion(newClient.getDireccion());
//                 
//                 return cliente;
//             }
//         }
//         
//         }
//         
//         //No existe cliente con tal id
//         logger.info("No existe un plato con el id suministrado");
//         throw new ClienteLogicException("No existe un plato con el id suministrado");
//    }
//    
//    /**
//     * Elimina un cliente de la lista
//     * @param id
//     * @throws ClienteLogicException 
//     */
//    public void eliminarCliente(Long id)throws ClienteLogicException
//    {
//        logger.info("Recibiendo solicitud de eliminar cliente con id: " + id);
//        
//        ClienteDTO cl = null;
//        for(ClienteDTO cliente : clientes)
//         {
//             if(Objects.equals(cliente.getId(), id))
//             {
//                 cl = cliente;
//                 clientes.remove(cl);
//                 break;
//             }
//         }
//         
//         //No existe cliente con tal id
//         if(id==null)
//         {
//         logger.info("No existe un cliente con el id suministrado");
//         throw new ClienteLogicException("No existe un cliente con el id suministrado");
//         }
//    }
//    
//    /**
//     * Agrega un medio de pago a un cliente con id
//     * @param id
//     * @param medio
//     * @throws ClienteLogicException 
//     */
//    public void agregarMedioDePago(Long id, MDPDTO medio) throws ClienteLogicException{
//        if(id == null){
//            throw new ClienteLogicException("Por favor de un ID, pongase serio");
//        }
//        else{
//            boolean esta = false;
//            for(ClienteDTO cliente: clientes){
//                if(cliente.getId().equals(id)){
////                    cliente.agregarMedioDePago(medio);
//                    esta = true;
//                }
//            }
//        
//         if(esta == false){
//                throw new ClienteLogicException("El cliente con el id " + id + " no existe.");
//            }
//        }
//    }
//    
//    public void removerMedioDepago(Long id, MDPDTO medio) throws ClienteLogicException{
//        if(id==null){
//            throw new ClienteLogicException("Por favor de un ID, pongase serio");
//        }
//        else{
//            boolean a = false;
//            for(ClienteDTO cliente: clientes){
//                if(cliente.getId().equals(id)){
////                    cliente.removerMedioDePago(medio);
//                    a = true;
//                }
//            }
//            if(a==false){
//                throw new ClienteLogicException("El cliente con el id "+id+" no existe.");
//            }
//        }
//    }
//}