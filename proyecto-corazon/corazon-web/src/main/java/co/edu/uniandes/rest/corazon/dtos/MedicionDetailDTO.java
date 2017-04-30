/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import co.edu.uniandes.sisteam.corazon.entities.EmergenciaEntity;
import co.edu.uniandes.sisteam.corazon.entities.MedicionEntity;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author BarraganJeronimo
 */
@XmlRootElement
public class MedicionDetailDTO extends MedicionDTO{
    
    private EmergenciaDTO emergencia;
    
    private PacienteDTO paciente;

    public MedicionDetailDTO() {
     super();
    }

   

    public MedicionDetailDTO( MedicionEntity entity) {
        super(entity);
        
        if(entity.getEmergencia()!=null){
        this.emergencia = new EmergenciaDTO(entity.getEmergencia());
        }
        
        if (entity.getPaciente() != null) {
            this.paciente = new PacienteDTO(entity.getPaciente());
        }
    }
    
    @Override
    public MedicionEntity toEntity(){
        MedicionEntity entity= super.toEntity();
        if(emergencia!=null){
            entity.setEmergencia(emergencia.toEntity());
        }
        
        if (this.getPaciente() != null) {
            entity.setPaciente(this.getPaciente().toEntity());
        }
        
        return entity;
    }

    public EmergenciaDTO getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(EmergenciaDTO emergencia) {
        this.emergencia = emergencia;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }
    
    
    
}
