///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.rest.restaurantes.mocks;
//
//import co.edu.uniandes.rest.restaurantes.dtos.MDPDTO;
//import co.edu.uniandes.rest.restaurantes.exceptions.MDPLogicException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *Medio De Pago Logic Mock
// * Mock del recurso Medio De Pago
// * @author lnv
// */
//public class MDPLogicMock {
//    
//    // objeto para presentar logs de las operaciones
//    private final static Logger logger = Logger.getLogger(MesaLogicMock.class.getName());
//
//    // listado de mdp's
//    private static ArrayList<MDPDTO> mdps;
//
//    /**
//     * Constructor. Crea los datos de ejemplo.
//     */
//    public MDPLogicMock() {
//
//        if (mdps == null) {
//            mdps = new ArrayList<>();
//            mdps.add(new MDPDTO(1L, "a", 1L));
//            mdps.add(new MDPDTO(2L, "b", 1L));
//            mdps.add(new MDPDTO(3L, "c", 2L));
//        }
//
//        // indica que se muestren todos los mensajes
//        logger.setLevel(Level.INFO);
//
//        // muestra información 
//        logger.info("Inicializa la lista de medios de pago");
//        logger.info("medios de pago" + mdps);
//    }
//
//    /**
//     * Obtiene el listado de medios de pago.
//     *
//     * @return lista de medios de pago
//     * @throws MDPLogicException cuando no existe la lista en memoria
//     */
//    public List<MDPDTO> getMDPS(Long idCliente) throws MDPLogicException {
//        if (mdps == null) {
//            logger.severe("Error interno: lista de mesas no existe.");
//            throw new MDPLogicException("Error interno: lista de medios de pago no existe.");
//        }
//
//        logger.info("retornando todas los medios de pago");
//        return mdps;
//    }
//
//    /**
//     * Obtiene el mdp con el id dado.
//     *
//     * @param idmdp id del mdp buscado
//     * @return atributos de la instancia de mesa
//     * @throws MDPLogicException cuando no existe la lista en memoria
//     */
//    public MDPDTO getMDP(Long idCliente, Long idMDP) throws MDPLogicException {
//        logger.info("recibiendo solicitud de buscar MDP con id" + idMDP);
//
//        // busca el mdp con el id suministrado
//        for (MDPDTO mdp : mdps) {
//            // si existe un mdp con ese id
//            if (Objects.equals(mdp.getId(), idMDP)) {
//                logger.info("retornando medio de pago" + mdp);
//                return mdp;
//            }
//        }
//
//        // si no existe mdp con ese id
//        logger.info("No existe un medio de pago con ese id");
//        throw new MDPLogicException("Error interno: No existe medio de pago con ese id.");
//    }
//
//    /**
//     * Agrega un mdp a la lista.
//     *
//     * @param idCliente
//     * @param newMDP mdp a adicionar
//     * @throws
//     * co.edu.uniandes.rest.restaurantes.exceptions.MDPLogicException
//     * @throws MDPLogicException cuando ya existe un mdp con el id
//     * suministrado
//     * @return mesa agregada
//     */
//    public MDPDTO createMDP(Long idCliente, MDPDTO newMDP) throws MDPLogicException {
//        logger.info("recibiendo solicitud de agregar medio de pago " + newMDP);
//
//        // el nuevo mdp tiene id ?
//        if (newMDP.getId() != null) {
//            // busca la getReview con el id suministrado
//            for (MDPDTO mdp : mdps) {
//                // si existe una getReview con ese id
//                if (Objects.equals(mdp.getId(), newMDP.getId())) {
//                    logger.severe("Ya existe un medio de pago con ese id");
//                    throw new MDPLogicException("Ya existe un medio de pago con ese id");
//                }
//            }
//
//            // la nueva getReview no tiene id ? 
//        } else {
//
//            // genera un id para la getReview
//            logger.info("Generando id para el nuevo medio de pago");
//            long newId = 1;
//            for (MDPDTO mdp : mdps) {
//                if (newId <= mdp.getId()) {
//                    newId = mdp.getId() + 1;
//                }
//            }
//            newMDP.setId(newId);
//        }
//
//        // agrega la getReview
//        logger.info("agregando medio de pago" + newMDP);
//        mdps.add(newMDP);
//        return newMDP;
//    }
//
//    /**
//     *
//     * @param id
//     * @param medio de pago
//     * @return
//     */
//    public MDPDTO updateMDP(Long idCliente, Long idMDP, MDPDTO MDPActualizada) throws MDPLogicException {
//        logger.info("recibiendo solicitud de actualizar medio de pago " + MDPActualizada);
//
//        // el nuevo medio de pago tiene id ?
//        if (MDPActualizada.getId() != null) {
//            // busca el medio de pago con el id suministrado
//            for (MDPDTO mdp : mdps) {
//                // si existe un medio de pago con ese id
//                if (Objects.equals(mdp.getId(), MDPActualizada.getId())) {
//
//                    // actualiza el medio de pago
//                    logger.info("actualizando medio de pago " + MDPActualizada);
//                    mdp.setId(MDPActualizada.getId());
//                    mdp.setNum(MDPActualizada.getNum());
//                    mdp.setTipo(MDPActualizada.getTipo());
//
//                    logger.info("Modificando medio de pago " + mdp);
//
//                    return mdp;
//
//                }
//            }
//        }
//
//        logger.severe("No se pudo actualizar el medio de pago");
//        throw new MDPLogicException("No existe un medio de pago con ese id");
//
//    }
//
//    /**
//     *
//     *
//     * @param id
//     */
//    public void deleteMDP(Long idCLiente, Long idMDP) throws MDPLogicException {
//        logger.info("recibiendo solicitud de eliminar medio de pago con id" + idMDP);
//
//        // busca el medio de pago con el id suministrado
//        for (MDPDTO mdp : mdps) {
//            // si existe un medio de pago con ese id
//            if (Objects.equals(mdp.getId(), idMDP)) {
//                // elimina el medio de pago
//                logger.info("eliminando medio de pago ");
//                mdps.remove(mdp);
//                return;
//            }
//        }
//
//        logger.severe("No se pudo eliminar el medio de pago");
//        throw new MDPLogicException("No existe un medio de pago con ese id");
//
//    }
//}
