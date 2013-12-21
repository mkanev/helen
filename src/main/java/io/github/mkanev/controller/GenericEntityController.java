package io.github.mkanev.controller;

import java.io.Serializable;
import java.util.List;

import io.github.mkanev.model.GenericEntity;

/**
 * Общий класс объектов доступа к данным (DAO)
 *
 * @param <T>  класс сущности
 * @param <PK> класс идентификатора сущности
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public interface GenericEntityController<T extends GenericEntity, PK extends Serializable> {

    /**
     * Общий метод для получения всех сущностей одного типа. Все равно, что выгрузить все записи из таблицы.
     *
     * @return Список полученных объектов
     */
    List<T> getAll();

    /**
     * Общий метод для получения сущностей одного типа, которые не были удалены.
     *
     * @return Список полученных объектов
     */
    List<T> getExistingEntityList();

    /**
     * Общий метод для получения сущности по идентификатору. Возвращает <code>null</code>, если ничего не найдено.
     *
     * @param id идентификатор (primary key) искомого объекта
     * @return полученный объект либо <code>null</code>, если ничего не найдено
     */
    T getEntity(PK id);

    /**
     * Общий метод для сохранения объекта в БД. Используется и для создания записи, и для ее обновления.
     *
     * @param object сохраняемый объект
     * @return сохраненный объект
     */
    T saveEntity(T object);

    /**
     * Общий метод для удаления объекта по идентификатору Устанавливает флаг deleted = 1
     *
     * @param id идентификатор (primary key) удаляемого объекта
     */
    void deleteEntity(PK id);

    /**
     * Общий метод для удаления объекта Устанавливает флаг deleted = 1
     *
     * @param object удаляемый объект
     */
    void deleteEntity(T object);

    /**
     * Удаляет из базы запись em.remove(object)
     *
     * @param object удаляемый объект
     */
    void removeEntity(T object);

    /**
     * fill all lazy objects.
     *
     * Warning: this method must be called in hibernate session & transaction
     *
     * @param obj object of type T
     */
    void fill(T obj);

    /**
     * get fully loaded object from database
     *
     * @param u non-fully loaded object instance
     * @return object of type T
     */
    T getFull(T u);

    /**
     * get fully loaded object from database
     *
     * @param id id of loading object
     * @return loaded object
     */
    T getFull(PK id);

    List<T> findByNamedQuery(String queryName, Object... parameters);

    List<T> findByNamedQuery(String queryName, int resultLimit, Object... parameters);

    List<T> findByNamedQuery(String queryName, int start, int end, Object... parameters);

}