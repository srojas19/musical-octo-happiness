/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.sisteam.restaurantes.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.sisteam.restaurantes.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ClientePersistence {

    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "SisteamPU")
    protected EntityManager em;

    public ClienteEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando cliente con id={0}", id);
        return em.find(ClienteEntity.class, id);
    }

    public ClienteEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando cliente con nombre = {0}", name);
        TypedQuery<ClienteEntity> q
                = em.createQuery("select u from ClienteEntity u where u.name = :name", ClienteEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }

    public List<ClienteEntity> findAll() {
        LOGGER.info("Consultando todos los clientes");
        Query q = em.createQuery("select u from ClienteEntity u");
        return q.getResultList();
    }

    public ClienteEntity create(ClienteEntity entity) {
        LOGGER.info("Creando un cliente nuevo");
        em.persist(entity);
        LOGGER.info("Cliente creado");
        return entity;
    }

    public ClienteEntity update(ClienteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando cliente con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando cliente con id={0}", id);
        ClienteEntity entity = em.find(ClienteEntity.class, id);
        em.remove(entity);
    }
}
