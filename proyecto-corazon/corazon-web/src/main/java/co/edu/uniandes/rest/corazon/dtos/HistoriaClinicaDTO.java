/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santiago
 */
public class HistoriaClinicaDTO {
    
    private Long id;
    
    private PacienteDTO paciente;
    
    private List<ExamenDTO> examenes= new ArrayList<>();
    
    private List<TratamientoDTO> tratamientos= new ArrayList<>();
    
    private List<DiagnosticoDTO> diagnosticos= new ArrayList<>();

    public HistoriaClinicaDTO() {
    }
    
    
    
    
    
    
    
}
