package io.github.mkanev.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Entity
@Table(name = "hel_meta_class_entity")
@DiscriminatorValue(MetaClassEntity.DISCRIMINATOR)
public class MetaClassEntity extends MetaClass {

    public static final String DISCRIMINATOR = "entity";
    // Имя таблицы в базе - получается на основе имени сущности
    @Column(name = "table_name")
    protected String tableName;
    // Наименование сгенерированного класса
    // префикса пакета (ru.it.dob.sunis.jpa.dynamic)+ любой валидный
    // идентификатор java
    // или полное имя стандартного класса
    @Column(name = "entity_name")
    private String entityName;
}
