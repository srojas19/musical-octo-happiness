package co.edu.uniandes.rest.corazon.dtos;
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

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

@XmlRootElement
public class EmergenciaDetailDTO extends EmergenciaDTO {

    @PodamExclude
    private MedicionDTO medicion;

    /**
     *
     */
    public EmergenciaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto EmergenciaDetailDTO a partir de un objeto EmergenciaEntity incluyendo
     * los atributos de EmergenciaDTO.
     *
     * @param entity Entidad EmergenciaEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public EmergenciaDetailDTO(EmergenciaEntity entity) 
    {
        super(entity);
        if (entity.getMedicion() != null) {
            this.medicion = new MedicionDTO(entity.getMedicion());
        }
    }

    /**
     * Convierte un objeto EmergenciaDetailDTO a EmergenciaEntity incluyendo los atributos
     * de EmergenciaDTO.
     *
     * @return objeto EmergenciaEntity.
     *
     */
    @Override
    public EmergenciaEntity toEntity() {
        EmergenciaEntity entity = super.toEntity();
        if (this.getMedicion() != null) {
            entity.setMedicion(this.getMedicion().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo medicion.
     *
     * @return atributo medicion.
     *
     */
    public MedicionDTO getMedicion() {
        return medicion;
    }

    /**
     * Establece el valor del atributo medicion.
     *
     * @param medicion nuevo valor del atributo
     *
     */
    public void setMedicion(MedicionDTO medicion) {
        this.medicion = medicion;
    }

}
