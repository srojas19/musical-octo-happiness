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

    public MarcapasosRealEntity getMarcapasosId(long id);

    public List<MarcapasosRealEntity> getAllMarcapasos();

    public MarcapasosRealEntity getMarcapasosNumeroSerie(String numeroSerie);

    public MarcapasosRealEntity createMarcapasos(MarcapasosRealEntity entity, Long idPaciente) throws BusinessLogicException;

    public MarcapasosRealEntity updateMarcapasos(MarcapasosRealEntity entity)throws BusinessLogicException;
   
    public void deleteMarcapasos(long id);
}
