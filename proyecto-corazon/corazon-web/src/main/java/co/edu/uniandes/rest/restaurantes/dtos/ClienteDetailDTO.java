/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lnv
 */
@XmlRootElement
public class ClienteDetailDTO extends ClienteDTO{
   
    // relación  cero o muchos con medios de pago
    private List<MDPDTO> mdps = new ArrayList<>();
    
    // relación  cero o muchas con reserva
    private List<ReservaDTO> reservas = new ArrayList<>();

    public ClienteDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ClienteDetailDTO a partir de un objeto ClienteEntity
     * incluyendo los atributos de ClienteDTO.
     *
     * @param entity Entidad ClienteEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
        List<MDPEntity> mdpsList = entity.getMDPS();
        for (MDPEntity mdp : mdpsList) 
        {
            this.mdps.add(new MDPDTO(mdp));
        }
        List<ReservaEntity> reservaList = entity.getReservas();
        for (ReservaEntity reserva : reservaList) 
        {
            this.reservas.add(new ReservaClienteDetailDTO(reserva));
        }
    }

    /**
     * Convierte un objeto SucursalDetailDTO a SucursalEntity incluyendo los
     * atributos de SucursalDTO.
     *
     * @return objeto SucursalEntity.
     *
     */
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
         List<MDPDTO> mdps = this.getMDPS();
        for (MDPDTO mdp : this.mdps) 
        {         
            entity.getMDPS().add(mdp.toEntity());
        }
        return entity;
    }

    /**
     * @return the mesas
     */
    public List<MDPDTO> getMDPS() {
        return mdps;
    }

   
    public void setMDPS(List<MDPDTO> mdps) {
        this.mdps = mdps;
    }

    /**
     * @return lista de reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

   /**
    * 
    * @param reservas 
    */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
}
