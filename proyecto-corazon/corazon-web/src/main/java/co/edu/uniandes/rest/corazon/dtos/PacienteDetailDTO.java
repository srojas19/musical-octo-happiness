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
package co.edu.uniandes.rest.corazon.dtos;


import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PacienteDetailDTO extends PacienteDTO {

    // relación  cero o muchos con mediciones 
    private List<MedicionDTO> mediciones = new ArrayList<>();
    

    public PacienteDetailDTO() {
        super();
    }

    /**
     * Crea un objeto PacienteDetailDTO a partir de un objeto PacienteEntity
     * incluyendo los atributos de PacienteDTO.
     *
     * @param entity Entidad PacienteEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public PacienteDetailDTO(PacienteEntity entity) {
        super(entity);
        List<MedicionEntity> medicionesList = entity.getMediciones();
        for (MedicionEntity medicion : medicionesList) 
        {
            this.mediciones.add(new MedicionDTO(medicion));
        }
        
    }

    /**
     * Convierte un objeto PacienteDetailDTO a PacienteEntity incluyendo los
     * atributos de PacienteDTO.
     *
     * @return objeto PacienteEntity.
     *
     */
    @Override
    public PacienteEntity toEntity() {
        PacienteEntity entity = super.toEntity();
         List<MedicionDTO> mediciones = this.getMediciones();
        for (MedicionDTO medicion : this.mediciones) 
        {         
            entity.getMediciones().add(medicion.toEntity());
        }
        
        return entity;
    }

    /**
     * @return the mediciones
     */
    public List<MedicionDTO> getMediciones() {
        return mediciones;
    }

    /**
     * @param mediciones the mediciones to set
     */
    public void setMediciones(List<MedicionDTO> mediciones) {
        this.mediciones = mediciones;
    }
}
