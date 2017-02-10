//package co.edu.uniandes.rest.restaurantes.mocks;
//
///**
// * Mock del recurso Mesas (Mock del servicio REST)
// *
// * @author Asistente
// */
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import co.edu.uniandes.rest.restaurantes.dtos.MesaDTO;
//import co.edu.uniandes.rest.restaurantes.dtos.SucursalDTO;
//import co.edu.uniandes.rest.restaurantes.exceptions.MesaLogicException;
//import co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException;
//
///*
// * MesaLogicMock
// * Mock del recurso Mesas (Mock del servicio REST)
// */
//public class MesaLogicMock {
//
//    // objeto para presentar logs de las operaciones
//    private final static Logger logger = Logger.getLogger(MesaLogicMock.class.getName());
//
//    // listado de mesas
//    private static ArrayList<MesaDTO> mesas;
//
//    /**
//     * Constructor. Crea los datos de ejemplo.
//     */
//    public MesaLogicMock() {
//
//        if (mesas == null) {
//            
//            mesas = new ArrayList<>();
//            mesas.add(new MesaDTO(1L, 4L, 1L));
//            mesas.add(new MesaDTO(2L, 6L, 1L));
//            mesas.add(new MesaDTO(3L, 10L, 2L));
//            
//            mesas.add(new MesaDTO(1L, 4L, 1L));
//            mesas.add(new MesaDTO(2L, 6L, 1L));
//            mesas.add(new MesaDTO(3L, 10L, 2L));
//            
//            mesas.add(new MesaDTO(1L, 4L, 1L));
//            mesas.add(new MesaDTO(2L, 6L, 1L));
//            mesas.add(new MesaDTO(3L, 10L, 2L));
//        }
//
//        // indica que se muestren todos los mensajes
//        logger.setLevel(Level.INFO);
//
//        // muestra información 
//        logger.info("Inicializa la lista de mesas");
//        logger.info("mesas" + mesas);
//    }
//
//    /**
//     * Obtiene el listado de mesas.
//     *
//     * @return lista de mesas
//     * @throws MesaLogicException cuando no existe la lista en memoria
//     */
//    public List<MesaDTO> getMesas(Long idSucursal) throws SucursalLogicException {
//        if (mesas == null) {
//            logger.severe("Error interno: lista de mesas no existe.");
//            throw new SucursalLogicException("Error interno: lista de mesas no existe.");
//        }
//
//        logger.info("retornando todas las mesas");
//        return mesas;
//    }
//
//    /**
//     * Obtiene la mesa con el id dado.
//     *
//     * @param idMesa id de la mesa buscada
//     * @return atributos de la instancia de mesa
//     * @throws MesaLogicException cuando no existe la lista en memoria
//     */
//    public MesaDTO getMesa(Long idSucursal, Long idMesa) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de buscar mesa con id" + idMesa);
//
//        // busca la mesa con el id suministrado
//        for (MesaDTO mesa : mesas) {
//            // si existe una mesa con ese id
//            if (Objects.equals(mesa.getId(), idMesa)) {
//                logger.info("retornando mesa " + mesa);
//                return mesa;
//            }
//        }
//
//        // si no existe mesa con ese id
//        logger.info("No existe una mesa con ese id");
//        throw new SucursalLogicException("Error interno: No existe mesa con ese id.");
//    }
//
//    /**
//     * Agrega una mesa a la lista.
//     *
//     * @param idSucursal
//     * @param newMesa mesa a adicionar
//     * @throws
//     * co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException
//     * @throws MesaLogicException cuando ya existe una mesa con el id
//     * suministrado
//     * @return mesa agregada
//     */
//    public MesaDTO createMesa(Long idSucursal, MesaDTO newMesa) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de agregar mesa " + newMesa);
//
//        // la nueva getMesa tiene id ?
//        if (newMesa.getId() != null) {
//            // busca la getReview con el id suministrado
//            for (MesaDTO getMesa : mesas) {
//                // si existe una getReview con ese id
//                if (Objects.equals(getMesa.getId(), newMesa.getId())) {
//                    logger.severe("Ya existe una mesa con ese id");
//                    throw new SucursalLogicException("Ya existe una mesa con ese id");
//                }
//            }
//
//            // la nueva getReview no tiene id ? 
//        } else {
//
//            // genera un id para la getReview
//            logger.info("Generando id para la nueva mesa");
//            long newId = 1;
//            for (MesaDTO getMesa : mesas) {
//                if (newId <= getMesa.getId()) {
//                    newId = getMesa.getId() + 1;
//                }
//            }
//            newMesa.setId(newId);
//        }
//
//        // agrega la getReview
//        logger.info("agregando mesa " + newMesa);
//        mesas.add(newMesa);
//        return newMesa;
//    }
//
//    /**
//     *
//     * @param id
//     * @param mesa
//     * @return
//     */
//    public MesaDTO updateMesa(Long idSucursal, Long idMesa, MesaDTO MesaActualizada) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de actualizar mesa " + MesaActualizada);
//
//        // la nueva mesa tiene id ?
//        if (MesaActualizada.getId() != null) {
//            // busca la mesa con el id suministrado
//            for (MesaDTO mesa : mesas) {
//                // si existe una mesa con ese id
//                if (Objects.equals(mesa.getId(), MesaActualizada.getId())) {
//
//                    // actualiza la mesa
//                    logger.info("actualizando mesa " + MesaActualizada);
//                    mesa.setId(MesaActualizada.getId());
//                    mesa.setCapacidad(MesaActualizada.getCapacidad());
//                    mesa.setNumPiso(MesaActualizada.getNumPiso());
//
//                    logger.info("Modificando Mesa " + mesa);
//
//                    return mesa;
//
//                }
//            }
//        }
//
//        logger.severe("No se pudo actualizar la mesa");
//        throw new SucursalLogicException("No existe una mesa con ese id");
//
//    }
//
//    /**
//     *
//     *
//     * @param id
//     */
//    public void deleteMesa(Long idSucursal, Long idMesa) throws SucursalLogicException {
//        logger.info("recibiendo solicitud de eliminar mesa con id" + idMesa);
//
//        // busca la mesa con el id suministrado
//        for (MesaDTO mesa : mesas) {
//            // si existe una mesa con ese id
//            if (Objects.equals(mesa.getId(), idMesa)) {
//                // elimina la mesa
//                logger.info("eliminando mesa ");
//                mesas.remove(mesa);
//                return;
//            }
//        }
//
//        logger.severe("No se pudo eliminar la mesa");
//        throw new SucursalLogicException("No existe una mesa con ese id");
//
//    }
//}
