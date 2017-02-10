///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.rest.restaurantes.mocks;
//
//import co.edu.uniandes.rest.restaurantes.dtos.PlatoDTO;
//import co.edu.uniandes.rest.restaurantes.exceptions.PlatoLogicException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * MesaLogicMock
// * Mock del recurso Platos(Mock del servicio REST)
// * @author n.sanabria10
// */
//public class PlatoLogicMock {
//    
//    //Objeto para presentar logs de las operaciones 
//    private final static Logger logger = Logger.getLogger(PlatoLogicMock.class.getName());
//    
//    //Listado de platos
//    private static ArrayList<PlatoDTO> platos;
//    
//    /**
//     * Constructor. Crea los datos para plato
//     */
//    public PlatoLogicMock()
//    {
//        //Indica que se muestren todos los mensajes
//        logger.setLevel(Level.INFO);
//        
//        if(platos == null)
//        {
//            platos = new ArrayList<>();
//            platos.add(new PlatoDTO(1L,"a",10,"aa"));
//            platos.add(new PlatoDTO(2L,"b",15,"bbb"));
//            platos.add(new PlatoDTO(3L,"c",25,"abc"));
//        }
//        
//        //Muestra información
//        logger.info("Inicializa muestra de platos");
//        logger.info("Platos" + platos);
//    }
//    
//    /**
//     * Obtiene el listado de platos
//     */
//    public List<PlatoDTO> getPlatos() throws  PlatoLogicException
//    {
//        if(platos == null)
//        {
//            logger.severe("Error Interno: Listado de platos no existe");
//            throw new PlatoLogicException("Error Interno: Listado de platos no existe"); 
//        }
//        else
//        {
//            logger.info("retornando listado de platos");
//            return platos;
//        }
//    }
//    
//    /**
//     * Agrega un plato a la lista
//     */
//    public PlatoDTO agregarPlato(PlatoDTO nPlato) throws PlatoLogicException
//    {
//        logger.info("recibiendo solicitud de agregar plato: " + nPlato);
//        
//        //El nuevo plato tiene id ?
//        if(nPlato.getId()!=null)
//        {
//            //Busca el plato con el id suministrado 
//            for(PlatoDTO plato:platos)
//            {
//                //Si existe un plato con esa id 
//                if(Objects.equals(plato.getId(), nPlato.getId()))
//                {
//                    logger.severe("Ya existe un plato con ese id");
//                    throw new PlatoLogicException("Ya existe un plato con ese id");
//                }
//            }
//            
//        }
//        //No tiene un id ?
//        else
//        {
//            //Genera un id para la mesa
//            logger.info("Generando un nuevo id para el plato");
//            long newID = 1;
//            for(PlatoDTO plato : platos)
//            {
//                if(newID <= plato.getId())
//                {
//                    newID = plato.getId()+1;
//                }
//            }
//            nPlato.setId(newID);
//        }
//        //Agrega la mesa
//        logger.info("Agregando plato: " + nPlato );
//        platos.add(nPlato);
//        return nPlato;
//    }
//    
//    /**
//     * Obtiene un plato con el id dado
//     */
//    public PlatoDTO getPlato(Long idb) throws PlatoLogicException
//    {
//        logger.info("recibiendo solicitud de busqueda de plato con id: " + idb);
//         PlatoDTO pd = null;
//         
//         //Busca el plato con el id solicitado
//         for(PlatoDTO plato : platos)
//         {
//             if(Objects.equals(plato.getId(), idb))
//             {
//                 pd = plato;
//             }
//         }
//         
//         //No existe plato con tal id
//         if(pd==null)
//         {
//         logger.info("No existe un plato con el id suministrado");
//         throw new PlatoLogicException("No existe un plato con el id suministrado");
//         }
//         return pd;
//    }
//    
//    /**
//     * Actualiza un plato
//     */
//    public PlatoDTO actualizarPlato(Long idb, PlatoDTO pa) throws PlatoLogicException
//    {
//        logger.info("recibiendo solicitud de actualizar plato con id: " + idb);
//         PlatoDTO pd = null;
//         
//         //El nuevo plato tiene id ?
//         if(pa.getId()!=null)
//         {
//             //Busca el plato con el id solicitado
//         for(PlatoDTO plato : platos)
//         {
//             if(Objects.equals(plato.getId(), pa.getId()))
//             {
//                 //Actualiza el plato
//                 logger.info("Actualizando plato: " +pa);
//                 plato.setId(pa.getId());
//                 plato.setNombre(pa.getNombre());
//                 plato.setPrecio(pa.getPrecio());
//                 plato.setDescripcion(pa.getDescripcion());
//                 //plato.setExclusivo(pa.isExclusivo());
//                 
//                 return plato;
//             }
//         }
//         
//         }
//         
//         //No existe plato con tal id
//         logger.info("No existe un plato con el id suministrado");
//         throw new PlatoLogicException("No existe un plato con el id suministrado");
//    }
//    
//    /**
//     * Eliimina un plato con el id suministrado
//     */
//    public void eliminarPlato(Long ipd)throws PlatoLogicException
//    {
//        logger.info("Recibiendo solicitud de eliminar plato con id: " + ipd);
//        
//        PlatoDTO pd = null;
//        for(PlatoDTO plato : platos)
//         {
//             if(Objects.equals(plato.getId(), ipd))
//             {
//                 pd = plato;
//                 platos.remove(pd);
//                 break;
//             }
//         }
//         
//         //No existe plato con tal id
//         if(pd==null)
//         {
//         logger.info("No existe un plato con el id suministrado");
//         throw new PlatoLogicException("No existe un plato con el id suministrado");
//         }
//    }
//}
