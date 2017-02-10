//package co.edu.uniandes.rest.restaurantes.mocks;
//
///**
// * Mock del recurso Domicilio (Mock del servicio REST)
// *
// * @author E
// */
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import co.edu.uniandes.rest.restaurantes.dtos.DomicilioDTO;
//import co.edu.uniandes.rest.restaurantes.dtos.PlatoDTO;
//import co.edu.uniandes.rest.restaurantes.exceptions.DomicilioLogicException;
//import java.util.Date;
//
//
///*
// * DomicilioLogicMock
// * Mock del recurso Domicilio (Mock del servicio REST)
// */
//public class DomicilioLogicMock {
//
//    // objeto para presentar logs de las operaciones
//    private final static Logger logger = Logger.getLogger(DomicilioLogicMock.class.getName());
//
//    // listado de domicilios
//    private static ArrayList<DomicilioDTO> domicilios;
//
//    /**
//     * Constructor. Crea los datos de ejemplo.
//     */
//    public DomicilioLogicMock() {
//
//        if (domicilios == null) {
//           
//            ClienteLogicMock clientes = new ClienteLogicMock();
//            domicilios = new ArrayList<>();
//            Date fecha = new Date(01/01/01);
////            try
////            {
////            domicilios.add(new DomicilioDTO(1L, sucursales.getSucursales().get(0), clientes.getClientes().get(0), true, "Sitio1", new ArrayList(), false, true, fecha));
////            domicilios.add(new DomicilioDTO(2L, sucursales.getSucursales().get(0), clientes.getClientes().get(1), true, "Sitio1", new ArrayList(), false, false, fecha));
////            domicilios.add(new DomicilioDTO(3L, sucursales.getSucursales().get(1), clientes.getClientes().get(2), false, "Sitio2", new ArrayList(), true, true, fecha));
////            }
////            catch(Exception e)
////            {}
//            
//        }
//
//        // indica que se muestren todos los mensajes
//        logger.setLevel(Level.INFO);
//
//        // muestra información 
//        logger.info("Inicializa la lista de domicilios");
//        logger.info("domicilios" + domicilios);
//    }
//
//    /**
//     * Obtiene el listado de domicilios.
//     *
//     * @return lista de domicilios
//     * @throws DomicilioLogicException cuando no existe la lista en memoria
//     */
//    public List<DomicilioDTO> getDomicilios() throws DomicilioLogicException {
//        if (domicilios == null) {
//            logger.severe("Error interno: lista de domicilios no existe.");
//            throw new DomicilioLogicException("Error interno: lista de domicilios no existe.");
//        }
//
//        logger.info("retornando todos los domicilios");
//        return domicilios;
//    }
//
//    /**
//     * Agrega un domicilio a la lista.
//     *
//     * @param newDomicilio domicilio a adicionar
//     * @throws DomicilioLogicException cuando ya existe un domicilio con el id
//     * suministrado
//     * @return domicilio agregada
//     */
//    public DomicilioDTO createDomicilio(DomicilioDTO newDomicilio) throws DomicilioLogicException {
//        logger.info("recibiendo solicitud de agregar domicilio " + newDomicilio);
//            
//            if(newDomicilio.getIdDomicilio() != null)
//            {
//                // busca el domicilio con el id suministrado
//                for (DomicilioDTO domicilio : domicilios)
//                {
//                  // si existe un domicilio con ese id
//                  if (Objects.equals(domicilio.getIdDomicilio(), newDomicilio.getIdDomicilio()))
//                  {
//                      logger.severe("Ya existe un domicilio con ese id");
//                    throw new DomicilioLogicException("Ya existe un domicilio con ese id");
//                  }
//                }
//            }
//            else
//            {
//                logger.info("Generando un nuevo id para el plato");
//            Long newID = 1L;
//            for(DomicilioDTO domicilio : domicilios)
//            {
//                if(newID <= domicilio.getIdDomicilio())
//                {
//                    newID = domicilio.getIdDomicilio()+1;
//                }
//            }
//            newDomicilio.setIdDomicilio(newID);
//            }
//
//        // agrega el domicilio
//        logger.info("agregando domicilio " + newDomicilio);
//        domicilios.add(newDomicilio);
//        return newDomicilio;
//    }
//
//    /**
//     * Obtiene el domicilio con el id dado.
//     *
//     * @param idDomicilio id del domicilio buscado
//     * @return atributos de la instancia de domicilio
//     * @throws DomiclioLogicException cuando no existe la lista en memoria
//     */
//    public DomicilioDTO getDomicilio(Long idDomicilio) throws DomicilioLogicException {
//        logger.info("recibiendo solicitud de buscar domicilio con id" + idDomicilio);
//
//        DomicilioDTO s = null;
//
//        // busca la domicilio con el id suministrado
//        for (DomicilioDTO domicilio : domicilios) {
//            // si existe un domicilio con ese id
//            if (Objects.equals(domicilio.getIdDomicilio(), idDomicilio)) {
//                s = domicilio;
//            }
//
//        }
//
//        // si no existe domicilio con ese id
//        if (s == null) {
//            logger.info("No existe un domicilio con ese id");
//            throw new DomicilioLogicException("Error interno: No existe domicilio con ese id.");
//        }
//
//        return s;
//
//    }
//
//    /**
//     *
//     * @param id
//     * @param domicilio
//     * @return
//     */
//    public DomicilioDTO updateDomicilio(Long idDomicilio, DomicilioDTO domicilioActualizar) throws DomicilioLogicException {
//        logger.info("recibiendo solicitud de actualizar domicilio " + domicilioActualizar);
//
//        if(domicilioActualizar.getIdDomicilio() != null)
//        {
////            // busca el domicilio con el id suministrado
////            for (DomicilioDTO domicilio : domicilios) {
////                // si existe un domicilio con ese id
////                if (Objects.equals(domicilio.getIdDomicilio(), domicilioActualizar.getIdDomicilio())) {
////
////                    // actualiza el domicilio
////                    logger.info("actualizando domicilio " + domicilioActualizar);
////                    domicilio.setIdDomicilio(domicilioActualizar.getIdDomicilio());
////                    domicilio.setSucursal(domicilioActualizar.getSucursal());
////                    domicilio.setCliente(domicilioActualizar.getCliente());
////                    domicilio.setDistanciaAprovada(domicilioActualizar.getDistanciaAprovada());
////                    domicilio.setLugarEntrega(domicilioActualizar.getLugarEntrega());
////                    domicilio.setPlatosDomicilio(domicilioActualizar.getPlatosDomicilio());
////
////                    return domicilio;
////
//                }
////            }
//        
//
//        logger.severe("No se pudo actualizar el domicilio");
//        throw new DomicilioLogicException("No existe un domicilio con ese id");
//
//    }
//
//    /**
//     *
//     *
//     * @param id
//     */
//    public void deleteDomicilio(Long idDomicilio) throws DomicilioLogicException {
//        logger.info("recibiendo solicitud de eliminar domicilio con id" + idDomicilio);
//
//        DomicilioDTO s = null;
//        
//        // busca el domicilio con el id suministrado
//        for (DomicilioDTO domicilio : domicilios) {
//
//            // si existe un domicilio con ese id
//            if (Objects.equals(domicilio.getIdDomicilio(), idDomicilio)) {
//                // elimina el domicilio
//                logger.info("eliminando domicilio ");
//                s = domicilio;
//                domicilios.remove(domicilio);
//                break;
//            }
//        }
//
//        if (s == null) {
//            logger.severe("No se pudo eliminar el domicilio");
//            throw new DomicilioLogicException("No existe un domicilio con ese id");
//        }
//    }
//    
//    public DomicilioDTO entregarDomicilio(Long idDomicilio) throws DomicilioLogicException
//    {
//        DomicilioDTO d = getDomicilio(idDomicilio);
//        d.setEntregado(true);
//        return d;
//    }
//    
//    public void agregarPlato(Long idDomicilio, PlatoDTO plato) throws DomicilioLogicException
//    {
//        DomicilioDTO d = getDomicilio(idDomicilio);
////        d.addPlatosDomicilio(plato);
//    }
//    
//    public void eliminarPlato(Long idDomicilio, Long idPlato) throws DomicilioLogicException
//    {
//        DomicilioDTO d= getDomicilio(idDomicilio);
////        d.removePlatosDomicilio(idPlato);
//    }
//    
//    public ArrayList<DomicilioDTO> getDomiciliosPorSucursal(Long idSucursal) throws DomicilioLogicException
//    {
//        if(domicilios == null)
//            throw new DomicilioLogicException("Error interno: lista de domicilios no existe.");
//        ArrayList<DomicilioDTO> respuesta = new ArrayList<>();
//        for(DomicilioDTO domicilio: domicilios)
//        {
////            if(domicilio.getSucursal().getId() == idSucursal)
////                respuesta.add(domicilio);
//        }
//        return respuesta;
//    }
//    
//    public ArrayList<DomicilioDTO> getDomiciliosPorCliente(Long idCliente) throws DomicilioLogicException
//    {
//        if(domicilios == null)
//            throw new DomicilioLogicException("Error interno: lista de domicilios no existe.");
//        ArrayList<DomicilioDTO> respuesta = new ArrayList<>();
//        for(DomicilioDTO domicilio: domicilios)
//        {
////            if(domicilio.getCliente().getId() == idCliente)
////                respuesta.add(domicilio);
//        }
//        return respuesta;
//    }
//    
//    public ArrayList<PlatoDTO> getPlatosDomicilio(Long idDomicilio) throws DomicilioLogicException
//    {
//        if(domicilios == null)
//            throw new DomicilioLogicException("Error interno: lista de domicilios no existe.");
//        ArrayList<PlatoDTO> respuesta = new ArrayList<>();
//        for(DomicilioDTO domicilio: domicilios)
//        {
////            if(domicilio.getIdDomicilio()==idDomicilio)
////                respuesta = domicilio.getPlatosDomicilio();
//        }
//        return respuesta;
//    }
//}
