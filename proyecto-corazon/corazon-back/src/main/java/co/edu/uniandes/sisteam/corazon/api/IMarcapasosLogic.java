/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.corazon.api;

import co.edu.uniandes.sisteam.corazon.entities.MarcapasosEntity;
import java.util.List;

/**
 *
 * @author BarraganJeronimo
 */
public interface IMarcapasosLogic {

    public MarcapasosEntity getMarcapasosId(long id);

    public List<MarcapasosEntity> getAllMarcapasos();

    public MarcapasosEntity getMarcapasosNumeroSerie(String numeroSerie);

    public MarcapasosEntity createMarcapasos(MarcapasosEntity entity);

    public MarcapasosEntity updateMarcapasos(MarcapasosEntity entity);
   
}