/*
 * DomicilioDTO
 * Objeto de transferencia de datos de Domicilios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author E
 */
import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DomicilioDTO {
    private Long idDomicilio;
    private boolean distanciaAprovada;
    private String lugarEntrega;
    private boolean descuento;
    private boolean entregado;
    private Date fecha;

    /**
     * Constructor por defecto
     */
    public DomicilioDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del domicilio
     * @param distacia define si la distancia esta aprovada
     * @param lugar el lugar donde se entrega el domicilio
     * @param descuento define si el domicilio tiene descuentoo
     * @param entregado define si el domicilio ya se entrego
     * @param fecha la fecha de creacion del domicilio
     */
    public DomicilioDTO(Long id, boolean distancia, String lugar, boolean descuento, boolean entregado, Date fecha)
    {
		super();
		this.idDomicilio = id;
                this.distanciaAprovada = distancia;
                this.lugarEntrega = lugar;
                this.descuento = descuento;
                this.entregado = entregado;
                this.fecha = fecha;
    }
    
    /**
     * Crea un objeto DomicilioDTO a partir de un objeto DomicilioEntity.
     *
     * @param entity Entidad DomcilioEntity desde la cual se va a crear el nuevo
     * objeto.
     * 
     */
    public DomicilioDTO(DomicilioEntity entity)
    {
        if(entity != null)
        {
            this.idDomicilio = entity.getId();
            this.distanciaAprovada = entity.getDistanciaAprovada();
            this.lugarEntrega = entity.getLugarEntrega();
            this.descuento = entity.getDescuento();
            this.entregado = entity.getEntregado();
            this.fecha = entity.getFecha();
        }
    }
    
    /**
     * Convierte un objeto DomcilioDTO a DomicilioEntity.
     *
     * @return Nueva objeto SucursalEntity.
     * 
     */
    public DomicilioEntity toEntity() {
        DomicilioEntity entity = new DomicilioEntity();
        entity.setId(this.getIdDomicilio());
        entity.setDistanciaAprovada(this.getDistanciaAprovada());
        entity.setLugarEntrega(this.getLugarEntrega());
        entity.setDescuento(this.getDescuento());
        entity.setEntregado(this.getEntregado());
        entity.setFecha(this.getFecha());
        return entity;
    }

	/**
     * @return el id del domicilio
     */
    public Long getIdDomicilio() {
        return idDomicilio;
    }

    /**
     * @param id el id a asignar al domicilio
     */
    public void setIdDomicilio(Long id) {
        this.idDomicilio = id;
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
  /** 
    public List<PlatoDTO> platoMasPedido()
    {
        List<PlatoDTO> respuesta = new ArrayList<>();
        int contador1 = 0;
        int contador2 = 0;
        for(int i = 0; i < platosDomicilio.size(); i++)
        {
            PlatoDTO pd = platosDomicilio.get(i);
            
        }
    }
  */ 
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ idDomicilio : " + getIdDomicilio()+ ", idSucursal : " + ", lugarEntrega : " + getLugarEntrega()+ 
                ", distanciaAprovada: " + getDistanciaAprovada() + ", descuento: " + descuento + ", entregado: " + entregado + " }" ;  
    }
}