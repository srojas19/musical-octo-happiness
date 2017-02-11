///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.rest.restaurantes.dtos;
//
//import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
//
//
///**
// *
// * @author BarraganJeronimo
// */
//public class ReservaClienteDetailDTO extends ReservaDTO {
//
//    private MesaReservaClienteDetailDTO mesa;
//
//    public ReservaClienteDetailDTO() {
//        super();
//    }
//
//    /**
//     * Crea un objeto ReservaDTO a partir de un objeto ReservaEntity.
//     * @param entity
//     */
//    public ReservaClienteDetailDTO(ReservaEntity entity) {
//        super(entity);
//        if (entity != null) {
//            if(entity.getMesa()!=null){
//                this.mesa= new MesaReservaClienteDetailDTO(entity.getMesa());
//            }
//                
//        }
//
//    }
//
//    /**
//     * Convierte un objeto ReservaDTO a ReservaEntity.
//     *
//     * @return Nueva objeto ReservaEntity.
//     *
//     */
//    @Override
//    public ReservaEntity toEntity() {
//
//        ReservaEntity entity = super.toEntity();
//
//        if (mesa != null) {
//            entity.setMesa(mesa.toEntity());
//        }
//        return entity;
//
//    }
//
//
//
//    public MesaReservaClienteDetailDTO getMesa() {
//        return mesa;
//    }
//
//    public void setMesa(MesaReservaClienteDetailDTO mesa) {
//        this.mesa = mesa;
//    }
//
//}
