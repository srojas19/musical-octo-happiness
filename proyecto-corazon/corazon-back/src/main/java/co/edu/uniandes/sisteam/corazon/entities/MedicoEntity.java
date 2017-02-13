/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author BarraganJeronimo
 */
public class MedicoEntity extends BaseEntity implements Serializable {

    private int cedula;
    private String tarjetaProfesional;
    private String nombres;   
    private boolean especialista;
    private String apellidos;
    private char sexo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @PodamExclude
    @OneToMany(mappedBy = "medico")
    private List<MedicoTratanteEntity> pacientesTratando =new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "realizadoPor")
    private List<ConsejoEntity> consejosRealizados=new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "medicoResponsable")
    private List<CitaMedicaEntity> citasRealizadas=new ArrayList<>();

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<MedicoTratanteEntity> getPacientesTratando() {
        return pacientesTratando;
    }

    public void setPacientesTratando(List<MedicoTratanteEntity> pacientesTratando) {
        this.pacientesTratando = pacientesTratando;
    }

    public void addpacientesTratando(MedicoTratanteEntity pacienteTratando) {
        this.pacientesTratando.add(pacienteTratando);
    }

    public List<ConsejoEntity> getConsejosRealizados() {
        return consejosRealizados;
    }

    public void setConsejosRealizados(List<ConsejoEntity> consejosRealizados) {
        this.consejosRealizados = consejosRealizados;
    }

    public void addConsejosRealizados(ConsejoEntity consejosRealizado) {
        this.consejosRealizados.add(consejosRealizado);
    }

    public List<CitaMedicaEntity> getCitasRealizadas() {
        return citasRealizadas;
    }

    public void setCitasRealizadas(List<CitaMedicaEntity> citasRealizadas) {
        this.citasRealizadas = citasRealizadas;
    }

    public void addCitasRealizadas(CitaMedicaEntity citaRealizada) {
        this.citasRealizadas.add(citaRealizada);
    }
}
