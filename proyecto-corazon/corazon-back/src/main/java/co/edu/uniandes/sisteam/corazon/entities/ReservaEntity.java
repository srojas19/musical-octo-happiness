package co.edu.uniandes.sisteam.corazon.entities;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.sisteam.restaurantes.entities;
//
//
//import javax.persistence.Entity;
//import uk.co.jemos.podam.common.PodamExclude;
//import javax.persistence.ManyToOne;
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Temporal;
//
///**
// *
// * @author BarraganJeronimo
// */
//@Entity
//public class ReservaEntity extends BaseEntity implements Serializable  {    
//        
//        private int numPersonas;	
//	private int numPiso;       
//        @PodamExclude        
//        @Temporal(javax.persistence.TemporalType.DATE)
//        private Date fecha;              
//        
//        @PodamExclude
//        @ManyToOne
//        private ClienteEntity cliente;
//        @PodamExclude
//        @ManyToOne
//        private MesaEntity mesa;
//  
//    public int getNumPersonas() {
//        return numPersonas;
//    }
//
//    public void setNumPersonas(int numPersonas) {
//        this.numPersonas = numPersonas;
//    }
//
//    public int getNumPiso() {
//        return numPiso;
//    }
//
//    public void setNumPiso(int numPiso) {
//        this.numPiso = numPiso;
//    }
//
//    public Date getFecha() {
//        return fecha;
//    }
//
//    public void setFecha(Date fecha) {
//        this.fecha = fecha;
//    }
//
//
//    public MesaEntity getMesa() {
//        return mesa;
//    }
//
//    public void setMesa(MesaEntity mesa) {
//        this.mesa = mesa;
//    }
//    
//    public ClienteEntity getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(ClienteEntity cliente) {
//        this.cliente = cliente;
//    }
// 
//    
//    
//}
