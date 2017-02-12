package co.edu.uniandes.sisteam.restaurantes.ejbs;

import co.edu.uniandes.sisteam.restaurantes.api.IPacienteLogic;
import co.edu.uniandes.sisteam.restaurantes.entities.PacienteEntity;
import co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException;
import co.edu.uniandes.sisteam.restaurantes.persistence.PacientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class PacienteLogic implements IPacienteLogic {

    @Inject
    private PacientePersistence persistence;

    /**
     * Obtiene la lista de los registros de Paciente.
     *
     * @return Colección de objetos de PacienteEntity.
     *
     */
    @Override
    public List<PacienteEntity> getPacientes() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Paciente a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de PacienteEntity con los datos del Paciente
     * consultado.
     *
     */
    public PacienteEntity getPaciente(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Paciente en la base de datos.
     *
     * @param entity Objeto de PacienteEntity con los datos nuevos
     * @return Objeto de PacienteEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.sisteam.restaurantes.exceptions.BusinessLogicException
     *
     */
    @Override
    public PacienteEntity createPaciente(PacienteEntity entity) throws BusinessLogicException 
    {
        PacienteEntity alreadyExist = getPacienteByCedula(entity.getCedula());
        if (alreadyExist != null) {
            throw new BusinessLogicException("Ya existe una paciente con ese nombre");
        } else {
            persistence.create(entity);
        }
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Paciente.
     *
     * @param entity Instancia de PacienteEntity con los nuevos datos.
     * @return Instancia de PacienteEntity con los datos actualizados.
     *
     */
    @Override
    public PacienteEntity updatePaciente(PacienteEntity entity) {
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Paciente de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    @Override
    public void deletePaciente(Long id) {
        persistence.delete(id);
    }

    @Override
    public PacienteEntity getPacienteByCedula(int cedula) {
        return persistence.findByCedula(cedula);
    }

}
