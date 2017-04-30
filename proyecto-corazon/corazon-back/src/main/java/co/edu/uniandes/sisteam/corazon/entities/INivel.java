/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

import java.io.Serializable;

/**
 *
 * @author sebastiansanchez
 */
public interface INivel {

    void cambiar(PacienteEntity paciente, MedicionEntity medicion);

    class NivelVerde implements INivel, Serializable {

        @Override
        public void cambiar(PacienteEntity paciente, MedicionEntity medicion) {
            if (medicion.getDictamen().equalsIgnoreCase("amarillo")) {
                paciente.set_nivel(new NivelAmarillo());       
            }
            else if (medicion.getDictamen().equalsIgnoreCase("rojo")) {
                paciente.set_nivel(new NivelRojo());  
            }
        }
    }

    class NivelAmarillo implements INivel, Serializable {

        @Override
        public void cambiar(PacienteEntity paciente, MedicionEntity medicion) {
           if (medicion.getDictamen().equalsIgnoreCase("verde")) {
                paciente.set_nivel(new NivelVerde());       
            }
            else if (medicion.getDictamen().equalsIgnoreCase("rojo")) {
                paciente.set_nivel(new NivelRojo());  
            }
        }
    }

    class NivelRojo implements INivel, Serializable {

        @Override
        public void cambiar(PacienteEntity paciente, MedicionEntity medicion) 
        {
        }
    }

}
