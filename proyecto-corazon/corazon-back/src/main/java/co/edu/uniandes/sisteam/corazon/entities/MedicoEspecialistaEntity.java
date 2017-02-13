/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author BarraganJeronimo
 */
public class MedicoEspecialistaEntity extends MedicoEntity implements Serializable {
    
    private String tiluloEspecialista;
    
    @PodamExclude
    @OneToMany(mappedBy = "medicoResponsable")
    private List<AjusteMarcapasosEntity> ajustesAMarcapasos=new ArrayList<>();

    
   
    public String getTiluloEspecialista() {
        return tiluloEspecialista;
    }

    public void setTiluloEspecialista(String tiluloEspecialista) {
        this.tiluloEspecialista = tiluloEspecialista;
    }
    
    
    public List<AjusteMarcapasosEntity> getAjustesAMarcapasos() {
        return ajustesAMarcapasos;
    }

    public void setAjustesAMarcapasos(List<AjusteMarcapasosEntity> ajustesAMarcapasos) {
        this.ajustesAMarcapasos = ajustesAMarcapasos;
    }
    
    public void addAjustesAMarcapasos(AjusteMarcapasosEntity ajusteAMarcapasos) {
        this.ajustesAMarcapasos.add(ajusteAMarcapasos);
    }
    
}
