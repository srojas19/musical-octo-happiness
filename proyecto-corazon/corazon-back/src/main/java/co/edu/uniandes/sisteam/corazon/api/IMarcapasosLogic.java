/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.api;

import co.edu.uniandes.sisteam.corazon.entities.MarcapasosRealEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public interface IMarcapasosLogic {

    public MarcapasosRealEntity getMarcapasosPaciente(Long idPaciente);
}
