/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.dtos;

import java.io.Serializable;

/**
 *
 * @author sebastiansanchez
 */
public interface INivel {

    void cambiarEstado(PacienteDetailDTO paciente, MedicionDTO medicion);
    String getEstado();

    class NivelVerde implements INivel{
        
        public static final String estado = "VERDE";
        
        @Override
        public String getEstado()
        {
            return estado;
        }

        @Override
        public void cambiarEstado(PacienteDetailDTO paciente, MedicionDTO medicion) {

            paciente.set_nivel(new NivelAmarillo());

            paciente.set_nivel(new NivelRojo());

        }
    }

    class NivelAmarillo implements INivel {
        
        public static final String estado = "AMARILLO";

        @Override
        public String getEstado()
        {
            return estado;
        }
        
        @Override
        public void cambiarEstado(PacienteDetailDTO paciente, MedicionDTO medicion) {
            
                paciente.set_nivel(new NivelVerde());
            
                paciente.set_nivel(new NivelRojo());
            }
        }
    }

    class NivelRojo implements INivel {
        
        public static final String estado = "VERDE";

        
        @Override
        public String getEstado()
        {
            return estado;
        }
        
        @Override
        public void cambiarEstado(PacienteDetailDTO paciente, MedicionDTO medicion) {
        }
    }


