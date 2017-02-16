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
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

@XmlRootElement
public class MedicionDetailDTO extends MedicionDTO {

    @PodamExclude
    private PacienteDTO paciente;

    // relacion una a uno con emergencia
    private EmergenciaDTO emergencia;

    /**
     *
     */
    public MedicionDetailDTO() {
        super();
    }

    /**
     * Crea un objeto MedicionDetailDTO a partir de un objeto MedicionEntity
     * incluyendo los atributos de MedicionDTO.
     *
     * @param entity Entidad MedicionEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public MedicionDetailDTO(MedicionEntity entity) {
        super(entity);
        if (entity.getPaciente() != null) {
            this.paciente = new PacienteDTO(entity.getPaciente());
        }

        if (entity.getEmergencia() != null) {
            EmergenciaEntity emer = entity.getEmergencia();
            this.emergencia = new EmergenciaDTO(emer);
        }
    }

    /**
     * Convierte un objeto MedicionDetailDTO a MedicionEntity incluyendo los
     * atributos de MedicionDTO.
     *
     * @return objeto MedicionEntity.
     *
     */
    @Override
    public MedicionEntity toEntity() {
        MedicionEntity entity = super.toEntity();

        if (this.getPaciente() != null) {
            entity.setPaciente(this.getPaciente().toEntity());
        }
        if(this.getEmergencia()!=null){
        EmergenciaDTO emer = this.getEmergencia();
        entity.setEmergencia(emer.toEntity());
        }

        return entity;
    }

    /**
     * Obtiene el atributo paciente.
     *
     * @return atributo paciente.
     *
     */
    public PacienteDTO getPaciente() {
        return paciente;
    }

    /**
     * Establece el valor del atributo paciente.
     *
     * @param paciente nuevo valor del atributo
     *
     */
    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public EmergenciaDTO getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(EmergenciaDTO emergencia) {
        this.emergencia = emergencia;
    }

}
