/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Temporal;

@Entity
public class DomicilioEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @ManyToOne
    private SucursalEntity sucursal;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    @PodamExclude
    @OneToMany(mappedBy= "domicilio",cascade = CascadeType.ALL)
    private List<PlatoEntity> platos;
    
    private boolean distanciaAprovada;
    private String lugarEntrega;
    private boolean descuento;
    private boolean entregado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    /**
     * Obtiene el atributo sucursal.
     *
     * @return atributo sucursal.
     *
     */
    public SucursalEntity getSucursal() 
    {
        return sucursal;
    }

    /**
     * Establece el valor del atributo sucursal.
     *
     * @param sucursal nuevo valor del atributo
     *
     */
    public void setSucursal(SucursalEntity sucursal) {
        this.sucursal = sucursal;
    }
    
    
    /**
     * Obtiene el atributo cliente.
     *
     * @return atributo cliente.
     *
     */
     
    public ClienteEntity getCliente() 
    {
        return cliente;
    }

    
    /** Establece el valor del atributo cliente.
     *
     * @param cliente nuevo valor del atributo
     */
     
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    
    /**
     * Obtiene el atributo platos.
     *
     * @return atributo platos.
     *
     */
    public List<PlatoEntity> getPlatos()
    {
        return platos;
    }
    
    
    /** Establece el valor del atributo platos.
     *
     * @param platos nuevo valor del atributo
     */
    public void setPlatos (List<PlatoEntity> platos)
    {
        this.platos = platos;
    }
    
    /** Elimina un plato del domicilio.
     *
     * @param idPlato id del plato a borrar
     */
    public void deletePlato (Long idPlato)
    {
        PlatoEntity platoABorrar = null;
        for(PlatoEntity plato: platos)
        {
            if(plato.getId()==idPlato) {
                platoABorrar = plato;
            }
        }
        if (platoABorrar != null) {
            platos.remove(platoABorrar);
        }
    }
    
    /** Agregar un plato al domicilio.
     *
     * @param plato plato a agregar
     */
    public void addPlato (PlatoEntity plato)
    {
        platos.add(plato);
    }
    
    public PlatoEntity getPlato(Long idPlato)
            {
        for(PlatoEntity plato: platos)
        {
            if(plato.getId()==idPlato)
                return plato;
        }
        return null;
    }
    
    /**
     * @return si la distancia es aprovada para el domicilio
     */
    public boolean getDistanciaAprovada() {
        return distanciaAprovada;
    }

    /**
     * @param distancia la distancia a asignar
     */
    public void setDistanciaAprovada(boolean distancia) {
        this.distanciaAprovada = distancia;
    }
    
    /**
     * @return el lugar donde se entrega el domicilio
     */
    public String getLugarEntrega() {
        return lugarEntrega;
    }

    /**
     * @param entrega el lugar de la entrega a asignar
     */
    public void setLugarEntrega(String entrega) {
        this.lugarEntrega = entrega;
    }
    
    /**
     * @return si el domicilio tiene descuento
     */
    public boolean getDescuento()
    {
        return descuento;
    }
    
    /**
     * @param descuento el descuento a asignar
     */
    public void setDescuento(boolean descuento)
    {
        this.descuento = descuento;
    }
    
    
    /**
     * @return si el domicilio fue entregado
     */
    public boolean getEntregado()
    {
        return entregado;
    }
    
    /**
     * @param entregado el estado de entrega a asignar
     */
    public void setEntregado(boolean entregado)
    {
        this.entregado = entregado;
    }

    /**
     * @return la fecha de entrega
     */
    public Date getFecha(){
        return fecha;
    }
    
    /**
     * @param fecha la fecha de entrega a asignar
     */
    public void setFecha(Date fecha)
    {
        this.fecha=fecha;
    }
}
