/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sisteam.restaurantes.api;



import co.edu.uniandes.sisteam.restaurantes.entities.MDPEntity;
import java.util.List;

/**
 *
 * @author ChavarroIvan
 */
public interface IMDPLogic {
    
    public MDPEntity getMDP(Long mdpid);
    public List<MDPEntity> getMDPCliente(Long clienteid);
    public MDPEntity createMDP(Long clienteid, MDPEntity entity); 
    public MDPEntity updateMDP(Long clienteid, MDPEntity entity);
    public void deleteMDP(Long mdpid);
    
      
}
