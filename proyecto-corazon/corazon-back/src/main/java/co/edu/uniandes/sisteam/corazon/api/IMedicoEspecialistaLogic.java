/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.api;

import co.edu.uniandes.sisteam.corazon.entities.MedicoEspecialistaEntity;
import co.edu.uniandes.sisteam.corazon.entities.PacienteEntity;
import co.edu.uniandes.sisteam.corazon.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public interface IMedicoEspecialistaLogic {

    public MedicoEspecialistaEntity getMedicoEspecialistaId(Long medicoId);

    public MedicoEspecialistaEntity getMedicoEspecialistaCedula(int cedula);

    public MedicoEspecialistaEntity getMedicoEspecialistaTarjetaProfesional(String nTarjetaProfesional);

    public List<MedicoEspecialistaEntity> getAllMedicoEspecialistas();

    public List<PacienteEntity> getPacientes();

    public MedicoEspecialistaEntity createMedicoEspecialista(MedicoEspecialistaEntity entity) throws BusinessLogicException;

    public MedicoEspecialistaEntity updateMedicoEspecialista(MedicoEspecialistaEntity entity) throws BusinessLogicException;

    public void deleteMedicoEspecialista(Long medicoid);

}
