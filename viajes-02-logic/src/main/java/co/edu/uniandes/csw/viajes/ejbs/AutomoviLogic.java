/* 
 * The MIT License
 *
 * Copyright 2017 wr.ravelo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.viajes.ejbs;

import co.edu.uniandes.csw.viajes.entities.AutomovilEntity;
import co.edu.uniandes.csw.viajes.persistence.AutomovilPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Danny
 */
@Stateless
public class AutomoviLogic
{

    /**
     * Injeccion de la persistencia en la logica
     */
    @Inject
    private AutomovilPersistence persistence;

    /**
     * Obtiene la lista de los automoviles existentes.
     *
     * @return Colección de objetos de AutomovilEntity.
     */
    public List<AutomovilEntity> getAutomoviles()
    {
        return persistence.findAll();
    }

    /**
     * Obtiene un automoviles especifico.
     *
     * @param id (placa) Identificador del carro
     * @return AutomovilEntity.
     */
    public AutomovilEntity getAuto(String placa)
    {
        return persistence.find(placa);
    }

    /**
     * Se encarga de crear un nuevo carro AutomovilEntity en la base de datos.
     *
     * @param entity Objeto de AutomovilEntity con los datos nuevos
     * @return Objeto de AutomovilEntity con los datos nuevos y su ID.
     * @generated
     */
    public AutomovilEntity creatCar(AutomovilEntity entity) throws Exception
    {
        if (persistence.findByPlaca(entity.getPlaca()) == null)
        {
            AutomovilEntity car = persistence.create(entity);
            return entity;
        }
        else
        {
            throw new Exception("Ya existe un carro con esa placa");
        }
    }

    /**
     * *
     * Actualiza la informacion de un carro en particular
     *
     * @param carro Instancia de AutomovilEntity con los nuevo datos
     * @return Instancia de AutomovilEntity actualizada
     */
    public AutomovilEntity updateCar(AutomovilEntity carro)
    {
        return persistence.update(carro);
    }

    /**
     * Elimina una instancia de Automovil en la base de datos
     *
     * @param placa para identificar el carro a eliminar
     */
    public void deletCar(String placa)
    {
        persistence.delete(placa);
    }
}
