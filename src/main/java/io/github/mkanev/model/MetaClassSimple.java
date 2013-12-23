package io.github.mkanev.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Entity
@Table(name = "hel_meta_class_simple")
@DiscriminatorValue(MetaClassSimple.DISCRIMINATOR)
public class MetaClassSimple extends MetaClass {

    public static final String DISCRIMINATOR = "simple";

}
