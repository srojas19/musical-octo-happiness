//package co.edu.uniandes.rest.corazon.mocks;
//
///**
// * Mock del recurso pacientes (Mock del servicio REST)
// *
// * @author Asistente
// */
//import co.edu.uniandes.rest.corazon.dtos.PacienteDTO;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import co.edu.uniandes.rest.corazon.dtos.PacienteDTO;
//import co.edu.uniandes.rest.corazon.exceptions.PacienteLogicException;
//import co.edu.uniandes.rest.corazon.exceptions.PacienteLogicException;
//import java.sql.Date;
//
///*
// * pacienteLogicMock
// * Mock del recurso pacientes (Mock del servicio REST)
// */
//public class PacienteLogicMock {
//
//    // objeto para presentar logs de las operaciones
//    private final static Logger logger = Logger.getLogger(PacienteLogicMock.class.getName());
//
//    // listado de pacientes
//    private static ArrayList<PacienteDTO> pacientes;
//
//    /**
//     * Constructor. Crea los datos de ejemplo.
//     */
//    public PacienteLogicMock() {
//
//        if (pacientes == null) {
//            pacientes = new ArrayList<>();
//            pacientes.add(new PacienteDTO(1L, 1, "Sebastian", "Sanchez Galiano", 60, 'M',
//                    "Cra 7C-bis","Colsanitas", new Date(01/03/1948), "A+"));
//            
//           pacientes.add(new PacienteDTO(2L, 2, "Juan", "Hernandez Chavez", 70, 'M',
//                    "Calle50","Colsanitas", new Date(04/03/1956), "O+"));
//                   
//           pacientes.add(new PacienteDTO(3L, 3, "Maria", "Villareal Atuesta", 60, 'F',
//                    "Cra9","Famisanar", new Date(04/05/1943), "B+"));
//                   
//        }
//        // indica que se muestren todos los mensajes
//        logger.setLevel(Level.INFO);
//
//        // muestra información 
//        logger.info("Inicializa la lista de pacientes");
//        logger.info("pacientes" + pacientes);
//    }
//
//    /**
//     * Obtiene el listado de pacientes.
//     *
//     * @return lista de pacientes
//     * @throws pacienteLogicException cuando no existe la lista en memoria
//     */
//    /**
//     * Obtiene el listado de pacientes.
//     *
//     * @return lista de pacientes
//     * @throws
//     * co.edu.uniandes.rest.corazon.exceptions.PacienteLogicException
//     */
//
//public List<PacienteDTO> getPacientes() throws PacienteLogicException
//    {
//        if (pacientes == null) {
//            logger.severe("Error interno: lista de pacientes no existe.");
//            throw new PacienteLogicException("Error interno: lista de pacientes no existe.");
//        }
//
//        logger.info("retornando todas las pacientes");
//        return pacientes;
//    }
//
//    /**
//     * Obtiene la paciente con el id dado.
//     *
//     * @param idpaciente id de la paciente buscada
//     * @return atributos de la instancia de paciente
//     * @throws pacienteLogicException cuando no existe la lista en memoria
//     */
//    public PacienteDTO getPaciente(Long idPaciente) throws PacienteLogicException {
//        logger.info("recibiendo solicitud de buscar paciente con id" + idPaciente);
//
//        // busca la paciente con el id suministrado
//        for (PacienteDTO paciente : pacientes) {
//            // si existe una paciente con ese id
//            if (Objects.equals(paciente.getId(), idPaciente)) {
//                logger.info("retornando paciente " + paciente);
//                return paciente;
//            }
//        }
//
//        // si no existe paciente con ese id
//        logger.info("No existe una paciente con ese id");
//        throw new PacienteLogicException("Error interno: No existe paciente con ese id.");
//    }
//
//    /**
//     * Agrega una paciente a la lista.
//     *
//     * @param newpaciente paciente a adicionar
//     * @throws
//     * co.edu.uniandes.rest.restaurantes.exceptions.SucursalLogicException
//     * @throws pacienteLogicException cuando ya existe una paciente con el id
//     * suministrado
//     * @return paciente agregada
//     */
//    public PacienteDTO createPaciente(PacienteDTO newPaciente) throws PacienteLogicException {
//        logger.info("recibiendo solicitud de agregar paciente " + newPaciente);
//
//        // la nueva getpaciente tiene id ?
//        if (newPaciente.getId() != null) {
//            // busca la getReview con el id suministrado
//            for (PacienteDTO getPaciente : pacientes) {
//                // si existe una getReview con ese id
//                if (Objects.equals(getPaciente.getId(), newPaciente.getId())) {
//                    logger.severe("Ya existe una paciente con ese id");
//                    throw new PacienteLogicException("Ya existe una paciente con ese id");
//                }
//            }
//
//            // la nueva getReview no tiene id ? 
//        } else {
//
//            // genera un id para la getReview
//            logger.info("Generando id para el nuevo paciente");
//            long newId = 1;
//            for (PacienteDTO getPaciente : pacientes) {
//                if (newId <= getPaciente.getId()) {
//                    newId = getPaciente.getId() + 1;
//                }
//            }
//            newPaciente.setId(newId);
//        }
//
//        // agrega la getReview
//        logger.info("agregando paciente " + newPaciente);
//        pacientes.add(newPaciente);
//        return newPaciente;
//    }
//
//     /**
//     *
//     * @param id
//     * @param paciente
//     * @return
//     */
//    public PacienteDTO updatePaciente(Long idPaciente, PacienteDTO PacienteActualizada) throws PacienteLogicException {
//        logger.info("recibiendo solicitud de actualizar paciente " + PacienteActualizada);
//
//        // la nueva paciente tiene id ?
//        if (PacienteActualizada.getId() != null) {
//            // busca la paciente con el id suministrado
//            for (PacienteDTO paciente : pacientes) {
//                // si existe una paciente con ese id
//                if (Objects.equals(paciente.getId(), PacienteActualizada.getId())) {
//
//                    // actualiza la paciente
//                    logger.info("actualizando paciente " + PacienteActualizada);
//                    paciente.setId(PacienteActualizada.getId());
//                    paciente.setCedula(PacienteActualizada.getCedula());
//                    paciente.setNombres(PacienteActualizada.getNombres());
//                    paciente.setApellidos(PacienteActualizada.getApellidos());
//                    paciente.setEdad(PacienteActualizada.getEdad());
//                    paciente.setSexo(PacienteActualizada.getSexo());
//
//                  
//
//
//                    logger.info("Modificando Paciente " + paciente);
//
//                    return paciente;
//
//                }
//            }
//        }
//
//        logger.severe("No se pudo actualizar la paciente");
//        throw new PacienteLogicException("No existe una paciente con ese id");
//
//    }
//
//    /**
//     *
//     *
//     * @param id
//     */
//    public void deletePaciente(Long idPaciente) throws PacienteLogicException {
//        logger.info("recibiendo solicitud de eliminar paciente con id" + idPaciente);
//
//        // busca la paciente con el id suministrado
//        for (PacienteDTO paciente : pacientes) {
//            // si existe una paciente con ese id
//            if (Objects.equals(paciente.getId(), idPaciente)) {
//                // elimina la paciente
//                logger.info("eliminando paciente ");
//                pacientes.remove(paciente);
//                return;
//            }
//        }
//
//        logger.severe("No se pudo eliminar la paciente");
//        throw new PacienteLogicException("No existe una paciente con ese id");
//
//    }
//}
