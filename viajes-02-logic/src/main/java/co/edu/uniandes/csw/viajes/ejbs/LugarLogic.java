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

import co.edu.uniandes.csw.viajes.entities.LugarEntity;
import co.edu.uniandes.csw.viajes.persistence.LugarPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author wr.ravelo
 */
@Stateless
public class LugarLogic
{

    /**
     * Atributo que conecta con la persistencia de lugar.
     */
    @Inject
    private LugarPersistence persistence;

    /**
     * Crea un nuevo lugar con los datos de la entidad que entra por parametro.
     *
     * @param entity Entidad que contiene los datos que se van a guardar en la
     * base de datos.
     * @return Entidad creada a partir de los datos ingresados en la base de
     * datos.
     */
    public LugarEntity createLugar(LugarEntity entity)
    {
        return persistence.create(entity);
    }

    /**
     * Encuentra un lugar con el id que entra por parametro.
     *
     * @param id Id del lugar que se quiere buscar.
     * @return El lugar que tiene id igual al parametro, null si no hay ninguno.
     */
    public LugarEntity findLugar(Long id)
    {
        return persistence.find(id);
    }

    /**
     * Encuentra todos los lugares registrados.
     *
     * @return Array con todos los lugares que hay en la base de datos.
     */
    public List<LugarEntity> findLugares()
    {
        return persistence.findAll();
    }

    /**
     * Actualiza un lugar con los nuevos datos de la entidad que entra por
     * parametro.
     *
     * @param entity Entidad que contiene los nuevos datos a guardar.
     * @return La entidad actualizada.
     */
    public LugarEntity updateLugar(LugarEntity entity)
    {
        return persistence.update(entity);
    }

    /**
     * Elimina un lugar con el id que entra por parametro.
     *
     * @param id Id del lugar a eliminar.
     */
    public void deleteLugar(Long id)
    {
        persistence.delete(id);
    }
}
