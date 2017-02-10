/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.rest.restaurantes.dtos;

import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.PlatoEspEntity;
import co.edu.uniandes.sisteam.restaurantes.entities.SucursalEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SucursalDetailDTO extends SucursalDTO {

    // relación  cero o muchos con mesas 
    private List<MesaDTO> mesas = new ArrayList<>();
    
    // relación  cero o muchos con platosEsp 
    private List<PlatoEspDTO> platosEsp = new ArrayList<>();

    public SucursalDetailDTO() {
        super();
    }

    /**
     * Crea un objeto SucursalDetailDTO a partir de un objeto SucursalEntity
     * incluyendo los atributos de SucursalDTO.
     *
     * @param entity Entidad SucursalEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public SucursalDetailDTO(SucursalEntity entity) {
        super(entity);
        List<MesaEntity> mesasList = entity.getMesas();
        for (MesaEntity mesa : mesasList) 
        {
            this.mesas.add(new MesaDTO(mesa));
        }
        List<PlatoEspEntity> platosEspList = entity.getPlatosEsp();
        for (PlatoEspEntity platoEsp : platosEspList) 
        {
            this.platosEsp.add(new PlatoEspDTO(platoEsp));
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
    public SucursalEntity toEntity() {
        SucursalEntity entity = super.toEntity();
         List<MesaDTO> mesas = this.getMesas();
        for (MesaDTO mesa : this.mesas) 
        {         
            entity.getMesas().add(mesa.toEntity());
        }
        List<PlatoEspDTO> platosEsp = this.getPlatosEsp();
        for (PlatoEspDTO platoEsp : this.platosEsp) 
        {         
            entity.getPlatosEsp().add(platoEsp.toEntity());
        }
        return entity;
    }

    /**
     * @return the mesas
     */
    public List<MesaDTO> getMesas() {
        return mesas;
    }

    /**
     * @param mesas the mesas to set
     */
    public void setMesas(List<MesaDTO> mesas) {
        this.mesas = mesas;
    }
    
    /**
     * @return the platosEsp
     */
    public List<PlatoEspDTO> getPlatosEsp() {
        return platosEsp;
    }

    /**
     * @param platosEsp the platosEsp to set
     */
    public void setPlatosEsp(List<PlatoEspDTO> platosEsp) {
        this.platosEsp = platosEsp;
    }

}
