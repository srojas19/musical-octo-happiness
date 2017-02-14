/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author santiago
 */
@Entity
public class HistoriaClinicaEntity extends BaseEntity implements Serializable {

    @OneToOne(mappedBy = "historiaClinica")
    private PacienteEntity paciente;

    @OneToMany(mappedBy = "historiaClinica")
    private List<DiagnosticoEntity> diagnosticos = new ArrayList<>();

    @OneToMany(mappedBy = "historiaClinica")
    private List<ExamenEntity> examenes = new ArrayList<>();

    @OneToMany(mappedBy = "historiaClinica")
    private List<TratamientoEntity> tratamientos = new ArrayList<>();

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public List<DiagnosticoEntity> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<DiagnosticoEntity> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public List<ExamenEntity> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<ExamenEntity> examenes) {
        this.examenes = examenes;
    }

    public List<TratamientoEntity> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<TratamientoEntity> tratamientos) {
        this.tratamientos = tratamientos;
    }
    
    
}
