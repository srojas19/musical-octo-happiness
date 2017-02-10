/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.DomicilioEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

@XmlRootElement
public class DomicilioDetailDTO extends DomicilioDTO
{
    @PodamExclude
    private SucursalDTO sucursal;
    
    @PodamExclude
    private ClienteDTO cliente;
    
    @PodamExclude
    private ArrayList<PlatoDTO> platosDomicilio = new ArrayList();
    
    /**
     * Constructor por defecto
     */
    public DomicilioDetailDTO() {super();}
    
    /**
     * Constructor con parámetros.
     * @param sucursal sucursal del domicilio
     * @param cliente cliente del domicilio
     * @param platos platos del domicilio
     */
    public DomicilioDetailDTO(SucursalDTO sucursal, ClienteDTO cliente, ArrayList<PlatoDTO> platosDomicilio)
    {
		super();
		this.sucursal = sucursal;
		this.cliente = cliente;
                this.platosDomicilio = platosDomicilio;
                
    }
    
    public DomicilioDetailDTO(DomicilioEntity entity)
    {
        super(entity);
        this.sucursal = new SucursalDTO(entity.getSucursal());
        this.cliente = new ClienteDTO(entity.getCliente());
        
        int i = 0;
        while(i<entity.getPlatos().size())
        {
            this.platosDomicilio.add(new PlatoDTO(entity.getPlatos().get(i)));
            i++;
        }
        
    }
    
    public DomicilioEntity toEntity()
    {
        DomicilioEntity entidad = super.toEntity();
        entidad.setSucursal(this.getSucursal().toEntity());
        entidad.setCliente(this.getCliente().toEntity());
        ArrayList<PlatoEntity> pe = new ArrayList<>();
        
        int i = 0;
        if(platosDomicilio != null)
        {
            while(i<platosDomicilio.size())
            {
                pe.add(platosDomicilio.get(i).toEntity());
                i++;
            }
        }
        entidad.setPlatos(pe);
        return entidad;
    }
    
    /**
     * @return la sucursal del domcilio
     */
    public SucursalDTO getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal la sucursal a actualizar
     */
    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }
    
    /**
     * @return el cliente del domicilio
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente el cliente a actualizar
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    /**
     * @return los platos del domicilio
     */
    public ArrayList getPlatosDomicilio() {
        return platosDomicilio;
    }

    /**
     * @param plato el plato a agregar
     */
    public void addPlatosDomicilio(PlatoDTO plato) {
        this.platosDomicilio.add(plato);
    }
    
    /**
     * @param id del plato a eliminar
     */ 
    public void removePlatosDomicilio(Long id) {
        int i = 0;
        while(i<platosDomicilio.size())
        {
            if(platosDomicilio.get(i).getId()==id)
            {
                platosDomicilio.remove(i);
                return;
            }
            i++;
        }
    }
    
    /**
     * @param platos platos a asignar
     */
    public void setPlatosDomicilio(ArrayList<PlatoDTO> platos)
    {
        platosDomicilio = platosDomicilio;
    }
}
