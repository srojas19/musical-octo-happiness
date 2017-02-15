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
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class PacienteEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicionEntity> mediciones = new ArrayList<>();
//    
//    @PodamExclude
//    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<PlatoEspEntity> platosEsp = new ArrayList<>();
//     
//    @PodamExclude
//    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<DomicilioEntity> domicilios = new ArrayList<>();
    
//    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ReservaEntity> reservas = new ArrayList<>();

    
    private HistoriaClinicaEntity historiaClinica;
    
    private int cedula;
    private String nombres; 
    private String apellidos;
    private int edad;
    private char sexo;
    private String direccionResidencia;
    private String entidadPrestadoraSalud;
    private Date fechaNacimiento;
    private String tipoSanguineo;
    

    /**
     * Obtiene la colección de medicion.
     * @return colección medicion. 
     */
    public List<MedicionEntity> getMediciones() {
        return mediciones;
    }

    /**
     * Establece el valor de la colección de medicion.
     * @param mediciones nuevo valor de la colección. 
     */
    public void setMediciones(List<MedicionEntity> mediciones) {
        this.mediciones = mediciones;
    }
    
    /**
     * Establece el valor de la colección de medicion.
     * @param mediciones nuevo valor de la colección. 
     */
    public void add(MedicionEntity medicion) {
        this.mediciones.add(medicion);
    }
    
    //Relaciones con Medico
    
    @ManyToOne
    private MedicoEntity medicoTratante;
    
    @ManyToMany(mappedBy = "pacientes")
    private List<MedicoEntity> medicos = new ArrayList<>();
    
    
    //Relaciones con MarcaPasos
    @OneToOne(mappedBy = "paciente")
    private MarcapasosEntity marcapasos;

    
    
    

     public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getEntidadPrestadoraSalud() {
        return entidadPrestadoraSalud;
    }

    public void setEntidadPrestadoraSalud(String entidadPrestadoraSalud) {
        this.entidadPrestadoraSalud = entidadPrestadoraSalud;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }


    public MedicoEntity getMedicoTratante() {
        return medicoTratante;
    }

    public void setMedicoTratante(MedicoEntity medicoTratante) {
        this.medicoTratante = medicoTratante;
    }

    public List<MedicoEntity> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<MedicoEntity> medicos) {
        this.medicos = medicos;
    }

    public MarcapasosEntity getMarcapasos() {
        return marcapasos;
    }

    public void setMarcapasos(MarcapasosEntity marcapasos) {
        this.marcapasos = marcapasos;
    }

    public HistoriaClinicaEntity getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinicaEntity historiaClinica) {
        this.historiaClinica = historiaClinica;
    }


    

}
