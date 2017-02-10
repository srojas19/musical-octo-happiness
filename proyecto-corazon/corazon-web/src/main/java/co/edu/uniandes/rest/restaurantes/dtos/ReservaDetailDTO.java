/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;


/**
 *
 * @author BarraganJeronimo
 */
public class ReservaDetailDTO extends ReservaDTO {

    private ClienteDTO cliente;
    private MesaDTO mesa;

    public ReservaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ReservaDTO a partir de un objeto ReservaEntity.
     * @param entity
     */
    public ReservaDetailDTO(ReservaEntity entity) {
        super(entity);
        if (entity != null) {
            if(entity.getCliente()!=null){
                this.cliente = new ClienteDTO(entity.getCliente());
            }
            if(entity.getMesa()!=null){
                this.mesa= new MesaDTO(entity.getMesa());
            }
                
        }

    }

    /**
     * Convierte un objeto ReservaDTO a ReservaEntity.
     *
     * @return Nueva objeto ReservaEntity.
     *
     */
    @Override
    public ReservaEntity toEntity() {

        ReservaEntity entity = super.toEntity();
        if (cliente != null) {
         entity.setCliente(cliente.toEntity());
        }
        if (mesa != null) {
            entity.setMesa(mesa.toEntity());
        }
        return entity;

    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public MesaDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaDTO mesa) {
        this.mesa = mesa;
    }

}
