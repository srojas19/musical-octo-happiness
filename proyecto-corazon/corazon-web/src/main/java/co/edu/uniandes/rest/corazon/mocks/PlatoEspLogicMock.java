package co.edu.uniandes.rest.corazon.mocks;

//package co.edu.uniandes.rest.restaurantes.mocks;
//
///**
// * Mock del recurso PlatosEsp (Mock del servicio REST)
// *
// * @author Asistente
// */
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import co.edu.uniandes.rest.restaurantes.dtos.PlatoEspDTO;
//import co.edu.uniandes.rest.restaurantes.exceptions.PlatoEspLogicException;
//import co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException;
//
///*
// * PlatoEspLogicMock
// * Mock del recurso PlatosEsp (Mock del servicio REST)
// */
//public class PlatoEspLogicMock {
//
//    // objeto para presentar logs de las operaciones
//    private final static Logger logger = Logger.getLogger(PlatoEspLogicMock.class.getName());
//
//    // listado de platosEsp
//    private static ArrayList<PlatoEspDTO> platosEsp;
//
//    /**
//     * Constructor. Crea los datos de ejemplo.
//     */
//    public PlatoEspLogicMock() {
//
//        if (platosEsp == null) {
//            platosEsp = new ArrayList<>();
//            platosEsp.add(new PlatoEspDTO(1L, "x", 10, "xxx"));
//            platosEsp.add(new PlatoEspDTO(2L, "y", 15, "yyy"));
//            platosEsp.add(new PlatoEspDTO(3L, "z", 25, "zzz"));
//
//        }
//
//        // indica que se muestren todos los mensajes
//        logger.setLevel(Level.INFO);
//
//        // muestra información 
//        logger.info("Inicializa la lista de platosEsp");
//        logger.info("platosEsp" + platosEsp);
//    }
//
//    /**
//     * Obtiene el listado de platosEsp.
//     *
//     * @return lista de platosEsp
//     * @throws PlatoEspLogicException cuando no existe la lista en memoria
//     */
//    public List<PlatoEspDTO> getPlatosEsp(Long idSucursal) throws SucursalLogicException {
//        if (platosEsp == null) {
//            logger.severe("Error interno: lista de platosEsp no existe.");
//            throw new SucursalLogicException("Error interno: lista de platosEsp no existe.");
//        }
//
//        logger.info("retornando todas las platosEsp");
//        return platosEsp;
//    }
//
//    /**
//     * Obtiene la platoEsp con el id dado.
//     *
//     * @param idPlatoEsp id de la platoEsp buscada
//     * @return atributos de la instancia de platoEsp
//     * @throws PlatoEspLogicException cuando no existe la lista en memoria
//     */
//    public PlatoEspDTO getPlatoEsp(Long idSucursal, Long idPlatoEsp) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de buscar platoEsp con id" + idPlatoEsp);
//
//        // busca la platoEsp con el id suministrado
//        for (PlatoEspDTO platoEsp : platosEsp) {
//            // si existe una platoEsp con ese id
//            if (Objects.equals(platoEsp.getId(), idPlatoEsp)) {
//                logger.info("retornando platoEsp " + platoEsp);
//                return platoEsp;
//            }
//        }
//
//        // si no existe platoEsp con ese id
//        logger.info("No existe una platoEsp con ese id");
//        throw new SucursalLogicException("Error interno: No existe platoEsp con ese id.");
//    }
//
//    /**
//     * Agrega una platoEsp a la lista.
//     *
//     * @param idSucursal
//     * @param newPlatoEsp platoEsp a adicionar
//     * @throws
//     * co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException
//     * @throws PlatoEspLogicException cuando ya existe una platoEsp con el id
//     * suministrado
//     * @return platoEsp agregada
//     */
//    public PlatoEspDTO createPlatoEsp(Long idSucursal, PlatoEspDTO newPlatoEsp) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de agregar platoEsp " + newPlatoEsp);
//
//        // la nueva getPlatoEsp tiene id ?
//        if (newPlatoEsp.getId() != null) {
//            // busca la getReview con el id suministrado
//            for (PlatoEspDTO getPlatoEsp : platosEsp) {
//                // si existe una getReview con ese id
//                if (Objects.equals(getPlatoEsp.getId(), newPlatoEsp.getId())) {
//                    logger.severe("Ya existe una platoEsp con ese id");
//                    throw new SucursalLogicException("Ya existe una platoEsp con ese id");
//                }
//            }
//
//            // la nueva getReview no tiene id ? 
//        } else {
//
//            // genera un id para la getReview
//            logger.info("Generando id para la nueva platoEsp");
//            long newId = 1;
//            for (PlatoEspDTO getPlatoEsp : platosEsp) {
//                if (newId <= getPlatoEsp.getId()) {
//                    newId = getPlatoEsp.getId() + 1;
//                }
//            }
//            newPlatoEsp.setId(newId);
//        }
//
//        // agrega la getReview
//        logger.info("agregando platoEsp " + newPlatoEsp);
//        platosEsp.add(newPlatoEsp);
//        return newPlatoEsp;
//    }
//
//    /**
//     *
//     * @param id
//     * @param platoEsp
//     * @return
//     */
//    public PlatoEspDTO updatePlatoEsp(Long idSucursal, Long idPlatoEsp, PlatoEspDTO PlatoEspActualizada) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de actualizar platoEsp " + PlatoEspActualizada);
//
//        // la nueva platoEsp tiene id ?
//        if (PlatoEspActualizada.getId() != null) {
//            // busca la platoEsp con el id suministrado
//            for (PlatoEspDTO platoEsp : platosEsp) {
//                // si existe una platoEsp con ese id
//                if (Objects.equals(platoEsp.getId(), PlatoEspActualizada.getId())) {
//
//                    // actualiza la platoEsp
//                    logger.info("actualizando platoEsp " + PlatoEspActualizada);
//                    platoEsp.setId(PlatoEspActualizada.getId());
//                    platoEsp.setName(PlatoEspActualizada.getName());
//                    platoEsp.setPrecio(PlatoEspActualizada.getPrecio());
//                    platoEsp.setDescripcion(PlatoEspActualizada.getDescripcion());
//
//                    logger.info("Modificando PlatoEsp " + platoEsp);
//
//                    return platoEsp;
//
//                }
//            }
//        }
//
//        logger.severe("No se pudo actualizar la platoEsp");
//        throw new SucursalLogicException("No existe una platoEsp con ese id");
//
//    }
//
//    /**
//     *
//     *
//     * @param id
//     */
//    public void deletePlatoEsp(Long idSucursal, Long idPlatoEsp) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de eliminar platoEsp con id" + idPlatoEsp);
//
//        // busca la platoEsp con el id suministrado
//        for (PlatoEspDTO platoEsp : platosEsp) {
//            // si existe una platoEsp con ese id
//            if (Objects.equals(platoEsp.getId(), idPlatoEsp)) {
//                // elimina la platoEsp
//                logger.info("eliminando platoEsp ");
//                platosEsp.remove(platoEsp);
//                return;
//            }
//        }
//
//        logger.severe("No se pudo eliminar la platoEsp");
//        throw new SucursalLogicException("No existe una platoEsp con ese id");
//
//    }
//}
