/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lnv
 */
@XmlRootElement
public class MDPDetailDTO extends MDPDTO{
    
    @PodamExclude
    private ClienteDTO cliente;

    /**
     *
     */
    public MDPDetailDTO() {
        super();
    }

    /**
     * Crea un objeto MDPDetailDTO a partir de un objeto MDPEntity incluyendo
     * los atributos de MDPDTO.
     *
     * @param entity Entidad MDPEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public MDPDetailDTO(MDPEntity entity) {
        super(entity);
        if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        }
    }

    /**
     * Convierte un objeto MDPDetailDTO a MDPEntity incluyendo los atributos
     * de MDPDTO.
     *
     * @return objeto MDPEntity.
     *
     */
    @Override
    public MDPEntity toEntity() {
        MDPEntity entity = super.toEntity();
        if (this.getCliente() != null) {
            entity.setCliente(this.getCliente().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo cliente.
     *
     * @return atributo cliente.
     *
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Establece el valor del atributo cliente.
     *
     * @param cliente nuevo valor del atributo
     *
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
