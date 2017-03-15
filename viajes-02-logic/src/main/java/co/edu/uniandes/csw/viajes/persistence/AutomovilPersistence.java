/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Danny
 */
@Stateless
public class AutomovilPersistence
{

    /**
     * base de datos
     */
    @PersistenceContext(unitName = "viajesPU")
    protected EntityManager em;

    /**
     * encontrar todos los automoviles
     *
     * @return List de AutomovilEntity
     */
    public List<AutomovilEntity> findAll()
    {
        Query q = em.createQuery("SELECT A FROM AutomovilEntity A");
        return q.getResultList();
    }

    /**
     * encuentra un carro por una placa
     *
     * @param placa
     * @return AutomovilEntity
     */
    public AutomovilEntity find(String placa)
    {
        return em.find(AutomovilEntity.class, placa);
    }

    /**
     * crea un carro
     *
     * @param carro
     * @return AutomovilEntity carro nuevo
     */
    public AutomovilEntity create(AutomovilEntity carro)
    {
        em.persist(carro);
        return carro;
    }

    /**
     * actualiza un carro
     *
     * @param auto
     * @return AutomovilEntity
     */

    public AutomovilEntity update(AutomovilEntity auto)
    {
        return em.merge(auto);

    }

    /**
     * borra un auto con una placa especifica
     *
     * @param placa
     */
    public void delete(String placa)
    {
        AutomovilEntity borrar = em.find(AutomovilEntity.class, placa);
        em.remove(borrar);

    }

    /**
     * busca una placa en el sistema y devuelve un booleano para saber si ya
     * existe o no en el sistema.
     *
     * @param placa
     * @return AutomovilEntity
     */

    public AutomovilEntity findByPlaca(String placa)
    {
        TypedQuery<AutomovilEntity> q = em.createQuery("select u from AutomovilEntity u where u.placa = :placa", AutomovilEntity.class);
        q = q.setParameter("placa", placa);

        List<AutomovilEntity> samePlaca = q.getResultList();
        if (samePlaca.isEmpty())
        {
            return null;
        }
        else
        {
            return samePlaca.get(0);
        }
    }
}
