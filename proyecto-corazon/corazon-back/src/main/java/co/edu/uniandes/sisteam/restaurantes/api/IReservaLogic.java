///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.sisteam.restaurantes.api;
//
//
//
//import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
//import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * @author BarraganJeronimo
// */
//public interface IReservaLogic {
//    
//    public ReservaEntity getReservaId(Long reservaid);
//    public List<ReservaEntity> getAllReservas();
//    public List<ReservaEntity> getReservaSucursal(Long sucursalid, Date fecha);
//    public List<ReservaEntity> getReservasCliente(Long clienteid);
//    public ReservaEntity createReserva(Long clienteid,Long mesaid,ReservaEntity entity) throws BusinessLogicException; 
//    public ReservaEntity updateReserva(Long clienteid,Long mesaid,ReservaEntity entity)throws BusinessLogicException;
//    public void deleteReserva(Long reservaid);
//    
//      
//}
