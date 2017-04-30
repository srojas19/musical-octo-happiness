/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.entities;

/**
 *
 * @author sebastiansanchez
 */
public interface INivel {

    void cambiar(PacienteEntity paciente, MedicionEntity medicion);

    class NivelVerde implements INivel {

        @Override
        public void cambiar(PacienteEntity paciente, MedicionEntity medicion) {
            paciente.set_nivel(new NivelAmarillo());
            System.out.println("cambio a Nivel Amarillo");
        
        }
    }

    class NivelAmarillo implements INivel {

        @Override
        public void cambiar(PacienteEntity paciente, MedicionEntity medicion) {
            paciente.set_nivel(new NivelRojo());
            System.out.println("cambio a Nivel Rojo");
        }
    }
    
    class NivelRojo implements INivel {

        @Override
        public void cambiar(PacienteEntity paciente, MedicionEntity medicion) {
            paciente.set_nivel(new NivelAmarillo());
            System.out.println("cambio a Nivel Amarillo");

        }
    }

}
